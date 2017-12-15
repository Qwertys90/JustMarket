package it.cgl.justmarket.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.ProdottoAcquistato;
import it.cgl.justmarket.models.Transazione;
import it.cgl.justmarket.models.enums.Categoria;
import it.cgl.justmarket.repository.ProdottoAcquistatoRepository;
import it.cgl.justmarket.repository.ProdottoRepository;
@Service
public class ProdottoAcquistatoServiceImpl implements ProdottoAcquistatoService{
	
	@Autowired
	ProdottoAcquistatoRepository repo;

	@Override
	public ProdottoAcquistato saveOrUpdateProdottoAcquistato(ProdottoAcquistato prodotto) {
		return repo.save(prodotto);
	}

}