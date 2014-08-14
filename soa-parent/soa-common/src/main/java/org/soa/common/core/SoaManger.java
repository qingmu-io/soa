package org.soa.common.core;

import org.soa.common.context.SoaContext;

public interface SoaManger {

	public abstract SoaContext invoke(SoaContext context);

	public abstract SoaContext call(SoaContext context);

	public abstract SoaContext invokeNoTx(SoaContext context);

	public abstract SoaContext callNoTx(SoaContext context);

	public abstract SoaContext callNewTx(SoaContext context);

}