package org.sys.api;

import org.soa.common.context.SoaContext;

public interface UserService {
	
	public abstract SoaContext login(SoaContext context);
	public abstract SoaContext insert(SoaContext context);
	

}
