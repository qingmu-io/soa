package org.soa.core;

import org.soa.common.context.SoaContext;

/**
 * 
 * @author liuyi
 *
 */
public interface SoaService {
	public abstract SoaContext invoke(SoaContext context);

}
