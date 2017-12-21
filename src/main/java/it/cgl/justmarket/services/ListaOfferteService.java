package it.cgl.justmarket.services;

import java.util.List;

import it.cgl.justmarket.models.listaOfferte;

public interface ListaOfferteService {
	
	listaOfferte saveOrUpdateProdotto (listaOfferte prodList);
	
	listaOfferte findLast();
	
}