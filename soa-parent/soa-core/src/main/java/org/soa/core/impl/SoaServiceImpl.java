package org.soa.core.impl;

import java.lang.reflect.Method;

import org.soa.common.context.SoaContext;
import org.soa.common.util.SpringContextHolder;
import org.soa.core.SoaService;
import org.soa.core.Status;
import org.soa.logger.SoaLogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service("soaService")
public final class SoaServiceImpl implements SoaService {
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
				throw new invokerFailException();
		} catch (Throwable e) {
			SoaLogger.error(getClass(), "调用服务{}中的{}方法出现错误 :{}",
					context.getService(), context.getMethod(), e);
		} finally {
			endTX(status);
		}
		return result;
	}

	/**
	 * Propagation.
	 * 
	 * TransactionDefinition
	 * @param context
	 * @return
	 */
	
	private Status beginTX(SoaContext context) {
		Status s = new Status();
		Integer txType = (Integer) context.getAttr("transactionType");
		context.removeAttr("transactionType");
		if (txType == null) {
			s.isCanTX = false;
			return s;
		}
		SoaLogger.debug(getClass(), "**SoaManage Call by TxType[{}]", txType);
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
				SoaLogger.debug(getClass(),
						"**SoaManage Get New Transaction Success!");
			} catch (Exception e) {
				s.isCanTX = false;
				SoaLogger.error(getClass(),
						"**SoaManage Get New Transaction Fail!",e);
			}
		} else {
			SoaLogger
					.error(getClass(), "**SoaManage Get New Transaction Fail!");
			s.isCanTX = false;
		}
		return s;
	}

	private void endTX(Status s) {
		if (s.isCanTX) {
			if (s.needRollback) {
				SoaLogger.debug(getClass(), "**SoaManage Rollback Transaction!");
				s.txManager.rollback(s.tx);
			} else {
				SoaLogger.debug(getClass(), "**SoaManage Commit Transaction!");
				s.txManager.commit(s.tx);
			}
		}
	}

}

class invokerFailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public Throwable fillInStackTrace() {
		return this;
	}
}
