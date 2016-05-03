package io.goudai.soa.core;

import io.goudai.soa.common.context.SoaContext;

/**
 * 
 * @author liuyi
 *
 */
public interface SoaService {
	public abstract SoaContext invoke(SoaContext context);

}
