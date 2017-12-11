package it.cgl.justmarket.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.enums.Categoria;
import it.cgl.justmarket.repository.ProdottoRepository;
@Service
public class ProdottoServiceImpl implements ProdottoService{
	
	@Autowired
	ProdottoRepository repo;

	@Override
	public Prodotto saveOrUpdateProdotto(Prodotto prodotto) {
		
		return repo.save(prodotto);
	}

	@Override
	public List<Prodotto> findAll() {
		return (List<Prodotto>) repo.findAll();
	}

	@Override
	public void deleteProdotto(int id) {
		repo.delete(id);
	}

	@Override
	public Prodotto findByid(int id) {
		return repo.findOne(id);
	}

	@Override
	public List<Prodotto> findByCategoria(Categoria categoria) {
		return repo.findByCategoria(categoria);
	}

	@Override
	public List<Prodotto> findByQuantitaGreaterThanEqual(double quantita) {
		return repo.findByQuantitaGreaterThanEqual(quantita);
	}

	@Override
	public List<Prodotto> findByUser_id(int id) {
		return repo.findByUser_id(id);
	}

}