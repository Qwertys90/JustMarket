package it.cgl.justmarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cgl.justmarket.models.CreditCard;
import it.cgl.justmarket.models.Storico;
import it.cgl.justmarket.models.User;
import it.cgl.justmarket.repository.CreditCardRepository;
import it.cgl.justmarket.repository.StoricoRepository;
import it.cgl.justmarket.repository.UserRepository;

@Service
public class StoricoServiceImpl implements StoricoService {

	@Autowired
	StoricoRepository repo;

	@Override
	public Storico findById(int id) {
		return repo.findOne(id);
	}

	@Override
	public List<Storico> findAll(User user) {
		return (List<Storico>) repo.findAll();
	}

	@Override
	public Storico save(Storico storico) {
		return repo.save(storico);
	}
	
}
