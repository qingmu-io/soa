package org.soa.shiro.security;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest   {
		
	@Test
	public void testHello(){
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//验证主体 通过subject 实现委托验证
		Subject subject = SecurityUtils.getSubject();
		//构架一个令牌
		final UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		//进行验证
		subject.login(token);
		
		subject.logout();
	}
	@Test
	public void testrealm(){
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//验证主体 通过subject 实现委托验证
		Subject subject = SecurityUtils.getSubject();
		//构架一个令牌
		final UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		//进行验证
		subject.login(token);
		
		subject.isPermitted("role1");
		
		subject.logout();
	}
	
	@Test
	public void testRole(){
		final IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
		SecurityUtils.setSecurityManager(factory.getInstance());
		final Subject subject = SecurityUtils.getSubject();
		final UsernamePasswordToken token = new UsernamePasswordToken("liuyi", "123");
		subject.login(token);
		final boolean hasRole = subject.hasRole("role1");
		final boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1","role2"));
		System.out.println();
	}

	@Test
	public void testPermission(){
		
		SecurityUtils.setSecurityManager(new IniSecurityManagerFactory("classpath:shiro-permission.ini").getInstance());
		final Subject subject = SecurityUtils.getSubject();
		final UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		subject.login(token);
		
		Assert.assertTrue(subject.isPermitted("user:create"));  
	    //判断拥有权限：user:update and user:delete  
	    Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));  
	    //判断没有权限：user:view  
	    Assert.assertFalse(subject.isPermitted("user:view"));  
		
	    //断言拥有权限：user:create  
	    subject.checkPermission("user:create");  
	    //断言拥有权限：user:delete and user:update  
	    subject.checkPermissions("user:delete", "user:update");  
	    //断言拥有权限：user:view 失败抛出异常  
	    subject.checkPermissions("user:view");  
		
		
		
	}
	
}
