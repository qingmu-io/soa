package org.soa.shiro.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm2 extends AuthorizingRealm {
	@Override
	public void setName(String name) {
		super.setName("my");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//用户名
		final Object primaryPrincipal = principals.getPrimaryPrincipal();
		
		
		return new SimpleAuthorizationInfo();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// 用户名
		final String username = (String) token.getPrincipal();
		final String password = new String((char[]) token.getCredentials());
		System.out.println(username + ":" + password);
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
