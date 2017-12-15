package it.cgl.justmarket.services;

import java.util.List;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.ProdottoAcquistato;
import it.cgl.justmarket.models.Transazione;
import it.cgl.justmarket.models.enums.Categoria;

public interface ProdottoAcquistatoService {
	
	ProdottoAcquistato saveOrUpdateProdottoAcquistato (ProdottoAcquistato prodotto);

}