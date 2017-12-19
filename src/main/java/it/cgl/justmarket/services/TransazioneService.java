package it.cgl.justmarket.services;

import java.util.List;

import it.cgl.justmarket.models.Transazione;
import it.cgl.justmarket.models.User;

public interface TransazioneService {
	
	Transazione findById(int id);
	
	List<Transazione> findByUser(User user);

	Transazione save(Transazione transazione);
	
}
