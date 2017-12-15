package it.cgl.justmarket.repository;

import org.springframework.data.repository.CrudRepository;
import it.cgl.justmarket.models.Transazione;
import it.cgl.justmarket.models.User;


public interface TransazioniRepository extends CrudRepository<Transazione, Integer> {
	
	Transazione findByUser(User user);
	
}
