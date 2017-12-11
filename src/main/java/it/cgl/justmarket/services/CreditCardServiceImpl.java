package it.cgl.justmarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cgl.justmarket.models.CreditCard;
import it.cgl.justmarket.models.User;
import it.cgl.justmarket.repository.CreditCardRepository;
import it.cgl.justmarket.repository.UserRepository;



@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	CreditCardRepository creditCardRepository;
	
	@Override
	public CreditCard saveCreditCard(CreditCard creditCard) {
		return creditCardRepository.save(creditCard);
	}

	@Override
	public void deleteCreditCard(int id) {
		creditCardRepository.delete(creditCardRepository.findOne(id));;
	}

	@Override
	public List<CreditCard> findByUser_id(int id) {
		return creditCardRepository.findByUser_id(id);
	}
	
	@Override
	public CreditCard findById(int id) {
		return creditCardRepository.findById(id);
	}


}
