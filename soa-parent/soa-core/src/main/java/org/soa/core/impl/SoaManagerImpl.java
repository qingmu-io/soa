package org.soa.core.impl;

import javax.annotation.Resource;

import org.soa.common.context.SoaContext;
import org.soa.common.core.SoaManger;
import org.soa.common.exception.AppException;
import org.soa.core.SoaService;
import org.soa.core.exception.SysException;
import org.soa.logger.SoaLogger;

public abstract class SoaManagerImpl implements SoaManger {
	@Resource
	private SoaService soaService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soa.core.SoaManger#invoke(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext invoke(SoaContext context) {
		return callTx(context, 0);
	}
	public SoaManagerImpl() {
		SoaLogger.debug(this.getClass(), "******************************************soa service init");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soa.core.SoaManger#call(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext call(SoaContext context) {
		return soaService.invoke(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soa.core.SoaManger#invokeNoTx(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext invokeNoTx(SoaContext context) {
		return soaService.invoke(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soa.core.SoaManger#callNoTx(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext callNoTx(SoaContext context) {
		return callTx(context, 4);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.soa.core.SoaManger#callNewTx(org.soa.common.context.SoaContext)
	 */
	@Override
	public SoaContext callNewTx(SoaContext context) {
		return callTx(context, 3);
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
		SoaContext info = new SoaContext();
		try {
			context.addAttr("transactionType", Integer.valueOf(txType));
			info = this.soaService.invoke(context);
		} catch (Exception e) {
			if ((e instanceof AppException)) {
				throw ((AppException) e);
			} else {
				throw new SysException("SoaSoa: Service Service is error!"
						+ e.getMessage());
			}
		}
		return info;
	}

}