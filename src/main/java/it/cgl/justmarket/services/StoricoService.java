package it.cgl.justmarket.services;

import java.util.List;

import it.cgl.justmarket.models.Storico;
import it.cgl.justmarket.models.User;

public interface StoricoService {
	
	Storico findById(int id);
	
	List<Storico> findAll(User user);

	Storico save(Storico storico);
	
}
