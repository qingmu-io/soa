package org.sys.api.impl;

import java.sql.Timestamp;

import org.soa.common.context.SoaContext;
import org.soa.common.util.IdUtil;
import org.soa.core.service.BaseService;
import org.sys.api.UserService;

@org.springframework.stereotype.Service("userService")
//@Service(interfaceClass=UserService.class)
public class UserServiceImpl extends BaseService implements UserService {
	private static final String LOGIN = "login";
	private static final String NAMESPACE="USER";
	@Override
	public SoaContext login(SoaContext context) {
		return super.queryStatement(context, LOGIN);
	}
	public  SoaContext insert(SoaContext context){
		context.addAttr("salt",IdUtil.salt());
		context.addAttr("createDate", new Timestamp(System.currentTimeMillis()));
		super.insert(context);
		return context;
	}

	@Override
	public String getNameSpace() {
		return NAMESPACE;
	}

}
