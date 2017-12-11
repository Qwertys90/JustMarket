package it.cgl.justmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.cgl.justmarket.models.User;
import it.cgl.justmarket.models.enums.UserProfileType;
import it.cgl.justmarket.services.UserService;
import it.cgl.justmarket.services.auth.AuthService;


@RestController
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/login")
	public UserDetails authenticate(@RequestBody User principal) throws Exception {
		return authService.authenticate(principal);
	}

	@PostMapping("/register")
	public User addUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setProfileType(UserProfileType.ROLE_USER);
		return userService.saveUser(user);
	}

	@GetMapping("/getusermodel")
	public User getModel() {
		return new User();
	}
	
	@RequestMapping("/delete")
	public void deleteUser(int id) {
		userService.deleteUser(id);
	}
	
	@GetMapping("/islogged")
	public boolean isLogged() {
		if(SecurityContextHolder.getContext().getAuthentication() 
		          instanceof AnonymousAuthenticationToken)
		return false;
		else {
			return true;
		}
	}

}
