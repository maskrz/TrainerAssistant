package com.herokuapp.tassistant.util.security.spring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.herokuapp.tassistant.database.entity.User;
import com.herokuapp.tassistant.service.user.UserService;

@Service("myUserDataService")
public class MyUserDataService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void test() {
		System.out.println("CLASS HAS BEEN CREATED");
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		 
	    Optional<User> optUser = getUserService().getUserByColumnValue("login", username);
	 
	    // TODO
	    if(!optUser.isPresent()) {	 
	        throw new UsernameNotFoundException("User for username " + username + "was not found."); 
	    }
	    
	    User user = optUser.get();
	 
	    List<String> permissions = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
	 
	    if(permissions.isEmpty()) {
	 
	        throw new UsernameNotFoundException(username + "has no permissions.");
	 
	    }
	 
	    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	 
	    for(String permission : permissions) {
	 
	        authorities.add(new SimpleGrantedAuthority(permission));
	 
	    }
	 
	    return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, authorities);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
