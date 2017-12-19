package it.cgl.justmarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cgl.justmarket.models.CreditCard;
import it.cgl.justmarket.models.Transazione;
import it.cgl.justmarket.models.User;
import it.cgl.justmarket.repository.CreditCardRepository;
import it.cgl.justmarket.repository.TransazioniRepository;
import it.cgl.justmarket.repository.UserRepository;

@Service
public class TransazioneServiceImpl implements TransazioneService {

	@Autowired
	TransazioniRepository repo;

	@Override
	public Transazione findById(int id) {
		return repo.findOne(id);
	}

	@Override
	public List<Transazione> findByUser(User user) {
		return repo.findByUser(user);
	}

	@Override
	public Transazione save(Transazione transazione) {
		return repo.save(transazione);
	}
	
}
