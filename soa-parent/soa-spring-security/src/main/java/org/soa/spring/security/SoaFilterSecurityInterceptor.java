package org.soa.spring.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liuyi
 * 
 */
@Component("myFilter")
public class SoaFilterSecurityInterceptor extends AbstractSecurityInterceptor
		implements Filter {

	@Autowired
	private SoaInvocationSecurityMetadataSource securityMetadataSource;
	@Autowired
	private SoaAccessDecisionManager accessDecisionManager;
	@Autowired
	private AuthenticationManager myAuthenticationManager;

	@PostConstruct
	public void init() {
		super.setAccessDecisionManager(accessDecisionManager);
		super.setAuthenticationManager(myAuthenticationManager);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		FilterInvocation invocation = new FilterInvocation(request, response,
				chain);

		InterceptorStatusToken token = super.beforeInvocation(invocation);
		try {
			invocation.getChain().doFilter(invocation.getRequest(),
					invocation.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

}
