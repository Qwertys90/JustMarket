package it.cgl.justmarket.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.enums.Categoria;

public interface ProdottoRepository extends CrudRepository<Prodotto, Integer> {
	
	List<Prodotto> findByUser_id(int id);
	
	List<Prodotto> findByCategoria (Categoria categoria);
	
	List<Prodotto> findByQuantitaGreaterThanEqual ( double quantita );

}
