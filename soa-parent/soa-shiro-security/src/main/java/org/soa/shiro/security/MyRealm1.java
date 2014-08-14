package org.soa.shiro.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

	@Override
	public String getName() {
		return "myrealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		//用户名
		final String username = (String)token.getPrincipal();
		final String password = new String((char[])token.getCredentials());
		System.out.println(username +":" + password);
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
