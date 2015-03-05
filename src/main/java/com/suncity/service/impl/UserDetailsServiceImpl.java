package com.suncity.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suncity.dao.UserDao;
import com.suncity.model.User;
import com.suncity.model.UserStatus;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.findUserByName(username); // our own
																		// User
																		// model
																		// class

		if (user != null) {
			String password = user.getPassword();
			// additional information on the security object
			/*
			 * boolean enabled = user.getStatus().equals(UserStatus.ACTIVE);
			 * boolean accountNonExpired =
			 * user.getStatus().equals(UserStatus.ACTIVE); boolean
			 * credentialsNonExpired =
			 * user.getStatus().equals(UserStatus.ACTIVE); boolean
			 * accountNonLocked = user.getStatus().equals(UserStatus.ACTIVE);
			 */
			// Let's populate user roles
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			authorities.add(new GrantedAuthorityImpl(user.getRole()));

			// Now let's create Spring Security User object
			/*org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(
					username, password, enabled, accountNonExpired,
					credentialsNonExpired, accountNonLocked, authorities);
			*/org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(
					username, password, authorities);
			

			return securityUser;
		} else {
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}

}
