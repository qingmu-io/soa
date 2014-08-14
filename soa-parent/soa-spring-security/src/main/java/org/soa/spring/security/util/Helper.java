package org.soa.spring.security.util;

import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public final class Helper {

	public String getSecurityUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String name = ((UserDetails) principal).getUsername();
			@SuppressWarnings("unchecked")
			Iterator<GrantedAuthority> it = (Iterator<GrantedAuthority>) ((UserDetails) principal).getAuthorities().iterator();
			String authority = "";
			while (it.hasNext()) {
				authority = it.next().getAuthority();
				System.out.println("Authority:" + authority);
			}
			return name;
		}
		return null;

	}
}
