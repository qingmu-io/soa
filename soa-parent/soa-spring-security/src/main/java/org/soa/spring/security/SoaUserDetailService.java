package org.soa.spring.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 在这个类中，你就可以从数据库中读入用户的密码，角色信息，是否锁定 ，账号是否过期等。建议通过我们封装的平台级持久层管理类获取和管理
 * 
 * @author LiuY
 * 
 */
@Component("myUserDetailService")
public class SoaUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// if(user == null)throw new UsernameNotFoundException("用户没有找到");
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		GrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_ADMIN");
		// auths.add(auth2);
		if (username.equals("ly")) {
			auths = new ArrayList<GrantedAuthority>();
			GrantedAuthority auth1 = new SimpleGrantedAuthority("ROLE_USER");
			auths.add(auth1);
			auths.add(auth2);
		}

		// User(String username, String password, boolean enabled, boolean
		// accountNonExpired,
		// boolean credentialsNonExpired, boolean accountNonLocked,
		// Collection<GrantedAuthority> authorities) {
		// 用户密码
		return new User(username, "ly", true, true, true, true, auths);
	}
}
