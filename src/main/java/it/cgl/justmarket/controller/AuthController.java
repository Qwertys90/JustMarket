package it.cgl.justmarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthService authService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/login")
	public UserDetails authenticate(@RequestBody User principal) throws Exception {
		logger.info("login"+principal);
		return authService.authenticate(principal);
	}

	@PostMapping("/register")
	public User addUser(@RequestBody User user) {
		logger.info("reg");
		user.setPassword(encoder.encode(user.getPassword()));
		user.setProfileType(UserProfileType.ROLE_USER);
		return userService.saveUser(user);
	}
	
	@PostMapping("/modifica")
	public User modUser(@RequestBody User user) {
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
	public ResponseEntity<Boolean> isLogged() {
		logger.info("testLogged");
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			logger.info("false");
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		} else {
			logger.info("true");
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	}

	@GetMapping("/userdetails")
	public ResponseEntity<User> userdetails() {
		logger.info("detailsuser");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			User user = new User();
			return new ResponseEntity<User>(user, HttpStatus.OK); 
		}else {
			User user = userService.findByUsername(auth.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@GetMapping("/logoutApp")
	public void logoutApp(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			logger.info("loggedOut");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}

}
