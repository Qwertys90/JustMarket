package it.cgl.justmarket.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.listaOfferte;
import it.cgl.justmarket.services.ListaOfferteService;
import it.cgl.justmarket.services.ProdottoService;
@Component
public class Scheduled {

	private static final Logger log= LoggerFactory.getLogger(Scheduled.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	private ProdottoService prodottoService;
	
	@Autowired
	private ListaOfferteService offerteService;
	
	@org.springframework.scheduling.annotation.Scheduled(fixedRate = 20000)
	public void reportCurrentTime() {
		Random random = new Random();
		log.info("Offerte generate alle: " + dateFormat.format(new Date()) , dateFormat.format(new Date()) );
		List<Prodotto> listaProdottiTotale= prodottoService.findAll();
		List<Prodotto> listaProdottiOfferte= new ArrayList<Prodotto>();
		listaOfferte listaOffers=new listaOfferte();
		for(int i=0;i<5;i++)
		listaProdottiOfferte.add(prodottoService.findById(random.nextInt(listaProdottiTotale.size())));
		listaOffers.setListaProdotti(listaProdottiOfferte);
		offerteService.saveOrUpdateProdotto(listaOffers);
		
	}
	
}
