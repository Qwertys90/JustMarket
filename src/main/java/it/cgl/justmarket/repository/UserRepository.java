package it.cgl.justmarket.repository;

import org.springframework.data.repository.CrudRepository;

import it.cgl.justmarket.models.User;



public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);
	
	User findById(int id);

}
