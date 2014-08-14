package org.soa.spring.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *在这个类中，最重要的是decide方法， 如果不存在对该资源的定义，直接放行 ；
 *否则，如果找到正确的角色，即认为拥有权限，并放行， 否则throw new
 *AccessDeniedException("no right")。 所有的异常建议平台统一进行封装并管理。
* @ClassName: MyAccessDecisionManager 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuY
* @date 2014-4-9 下午1:37:40 
*
 */
@Component("accessDecisionManager")
public class SoaAccessDecisionManager implements AccessDecisionManager {
	
	
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> class1) {
		return true;
	}

}
