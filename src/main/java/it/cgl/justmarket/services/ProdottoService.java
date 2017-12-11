package it.cgl.justmarket.services;

import java.util.List;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.enums.Categoria;

public interface ProdottoService {
	
	Prodotto saveOrUpdateProdotto (Prodotto prodotto);
	
	List<Prodotto> findAll();
	
	void deleteProdotto (int id);
	
	public Prodotto findByid (int id);
	
	public List<Prodotto> findByCategoria (Categoria categoria);
	
	List<Prodotto> findByQuantitaGreaterThanEqual ( double quantita );
	
	List<Prodotto> findByUser_id(int id);


}