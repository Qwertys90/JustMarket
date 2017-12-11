package it.cgl.justmarket.services;

import java.util.List;
import it.cgl.justmarket.models.CreditCard;

public interface CreditCardService {

	CreditCard saveCreditCard(CreditCard creditCard);
	
	void deleteCreditCard(int id);
	
	List<CreditCard> findByUser_id(int id);
	
	public CreditCard findById(int id);
	
	
}
