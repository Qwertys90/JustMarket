package it.cgl.justmarket.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.cgl.justmarket.models.CreditCard;


public interface CreditCardRepository extends CrudRepository<CreditCard, Integer> {

	List<CreditCard> findByUser_id(int id);
	
	CreditCard findById(int id);

	
}
