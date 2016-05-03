package io.goudai.soa.core.impl;

import io.goudai.soa.common.context.SoaContext;
import io.goudai.soa.common.core.SoaManger;
import io.goudai.soa.common.exception.AppException;
import io.goudai.soa.core.SoaService;
import io.goudai.soa.core.exception.SysException;
import org.springframework.transaction.TransactionDefinition;

import javax.annotation.Resource;

public abstract class DefaultSoaManager implements SoaManger {
	@Resource
	private SoaService soaService;
	
	/*
	 * (non-Javadoc)
	 * @see org.soa.common.core.SoaManger#invokeNoTx(org.soa.common.context.SoaContext)
	 * 直接进行调用 不开启事物
	 * @param context
	 * @return
	 */
	 
	@Override
	public SoaContext invokeNoTx(SoaContext context) {
		return soaService.invoke(context);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.soa.core.SoaManger#invoke(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext invoke(SoaContext context) {
		return callTx(context, TransactionDefinition.PROPAGATION_REQUIRED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soa.core.SoaManger#callNoTx(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext callNoTx(SoaContext context) {
		//4
		return callTx(context, TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soa.core.SoaManger#callNewTx(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext callNewTx(SoaContext context) {
		//3
		return callTx(context, TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	}

	private SoaContext callTx(SoaContext context, int txType) {
		if (context == null) {
			throw new SysException("SoaSoa: Service Service is error");
		}
		if (context.getService() == null) {
			throw new SysException("SoaSoa: Service is null !!");
		}
		if (context.getMethod() == null) {
			throw new SysException("SoaSoa: Service's method is null !!");
		}
		try {
			context.addAttr("transactionType", Integer.valueOf(txType));
			context = this.soaService.invoke(context);
		} catch (Exception e) {
			if ((e instanceof AppException)) {
				throw ((AppException) e);
			} else {
				throw new SysException("SoaSoa: Service Service is error!"
						+ e.getMessage());
			}
		}
		return context;
	}

}