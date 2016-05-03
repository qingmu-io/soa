package io.goudai.soa.sys.api.impl;

import io.goudai.soa.common.context.SoaContext;
import io.goudai.soa.core.service.BaseService;
import io.goudai.soa.sys.api.UserService;
import io.goudai.soa.util.IdUtil;

import java.sql.Timestamp;


@org.springframework.stereotype.Service("userService")
//@Service(interfaceClass=UserService.class)
public class SoaUserService extends BaseService implements UserService {
	private static final String LOGIN = "login";
	private static final String NAMESPACE="USER";
	
	@Override
	public SoaContext login(SoaContext context) {
		return super.queryStatement(context, LOGIN);
	}
	
	public  SoaContext insert(SoaContext context){
		context.addAttr("salt", IdUtil.salt());
		context.addAttr("createDate", new Timestamp(System.currentTimeMillis()));
		super.insert(context);
		return context;
	}

	@Override
	public String getNameSpace() {
		return NAMESPACE;
	}
	
	@Override
	public SoaContext page(SoaContext context) {
		super.queryByPage(context);
		return context;
	}

}
