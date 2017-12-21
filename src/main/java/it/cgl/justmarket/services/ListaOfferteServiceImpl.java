package it.cgl.justmarket.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.listaOfferte;
import it.cgl.justmarket.models.enums.Categoria;
import it.cgl.justmarket.repository.ListaOfferteRepository;
import it.cgl.justmarket.repository.ProdottoRepository;
@Service
public class ListaOfferteServiceImpl implements ListaOfferteService{
	
	@Autowired
	ListaOfferteRepository repo;

	@Override
	public listaOfferte saveOrUpdateProdotto(listaOfferte listaProd) {
		
		return repo.save(listaProd);
	}

	@Override
	public listaOfferte findLast() {
		List<listaOfferte> lista = (List<listaOfferte>) repo.findAll();
		return  lista.get(lista.size()-1);
	}


}