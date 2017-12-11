package it.cgl.justmarket.services;

import it.cgl.justmarket.models.User;

public interface UserService {

	User saveUser(User user);

	User findByUsername(String username);
	
	User findById(int id);
	
	void deleteUser(int id);
	
	
}
