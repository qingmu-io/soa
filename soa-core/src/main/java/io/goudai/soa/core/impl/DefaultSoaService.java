package io.goudai.soa.core.impl;

import io.goudai.soa.common.context.SoaContext;
import io.goudai.soa.common.exception.AppException;
import io.goudai.soa.core.SoaService;
import io.goudai.soa.core.Status;
import io.goudai.soa.core.exception.SysException;
import io.goudai.soa.core.holder.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.Method;

@Service("soaService")
public final class DefaultSoaService implements SoaService {
    Logger logger = LoggerFactory.getLogger(DefaultSoaService.class);

    @Override
    public SoaContext invoke(SoaContext context) {
        Status status = beginTX(context);
        SoaContext result = null;
        try {
            Object service = SpringContextHolder.getBean(context.getService());
            Method method = service.getClass().getMethod(context.getMethod(),
                    SoaContext.class);
            result = (SoaContext) method.invoke(service, context);
            if (result != null && !result.isSuccess())
                throw new InvokerFailException();
        } catch (InvokerFailException ex) {
            if (status != null)
                status.needRollback = true;
        } catch (Throwable e) {
            e.printStackTrace();
            if (status != null) {
                status.needRollback = true;
            }
            if ((e.getCause() instanceof AppException)) {
                throw (AppException) e.getCause();
            }
            throw new SysException("service invoke error", e);
        } finally {
            endTX(status);
        }
        return result;
    }



    private Status beginTX(SoaContext context) {
        Status s = new Status();
        Integer txType = (Integer) context.getAttr("transactionType");
        context.removeAttr("transactionType");
        if (txType == null) {
            s.isCanTX = false;
            return s;
        }
        logger.info("**SoaManage Call by TxType[{}]", txType);
        PlatformTransactionManager txMgr = SpringContextHolder.getBean(PlatformTransactionManager.class);
        s.txManager = txMgr;
        //设置传播行为
        s.defaultTransactionDefinition = new DefaultTransactionDefinition(txType.intValue());
        //设置隔离级别
        s.defaultTransactionDefinition.setIsolationLevel(TransactionDefinition.PROPAGATION_MANDATORY);
        s.isCanTX = true;
        if (txMgr != null) {
            try {
                s.tx = txMgr.getTransaction(s.defaultTransactionDefinition);
                logger.info(
                        "**SoaManage Get New Transaction Success!");
            } catch (Exception e) {
                s.isCanTX = false;
                logger.error(
                        "**SoaManage Get New Transaction Fail!", e);
            }
        } else {
            logger.error("**SoaManage Get New Transaction Fail!");
            s.isCanTX = false;
        }
        return s;
    }

    private void endTX(Status s) {
        if (s.isCanTX) {
            if (s.needRollback) {
                logger.info("**SoaManage Rollback Transaction!");
                s.txManager.rollback(s.tx);
            } else {
                logger.info("**SoaManage Commit Transaction!");
                s.txManager.commit(s.tx);
            }
        }
    }

}

class InvokerFailException extends RuntimeException {
    public Throwable fillInStackTrace() {
        return this;
    }
}
