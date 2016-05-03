package io.goudai.soa.common.core;

import io.goudai.soa.common.context.SoaContext;

public interface SoaManger {

	/**
	 * 支持当前事物，如果不存在，创建一个新事物
	 * @param context
	 * @return
	 */
	public abstract SoaContext invoke(SoaContext context);
	/**
	 * 直接进行调用 不开启事物
	 * @param context
	 * @return
	 */
	public abstract SoaContext invokeNoTx(SoaContext context);
	
	
	/**
	 * 使用不传播的事物
	 * @param context
	 * @return
	 */
	public abstract SoaContext callNoTx(SoaContext context);
	/**
	 * 总是开启一个新事物
	 * @param context
	 * @return
	 */
	public abstract SoaContext callNewTx(SoaContext context);

}