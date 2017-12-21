package it.cgl.justmarket.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.cgl.justmarket.models.listaOfferte;
import it.cgl.justmarket.services.ListaOfferteService;

@RestController
@RequestMapping("/offers")
public class ListaOffersController {

	@Autowired
	private ListaOfferteService offersService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/getall")
	public ResponseEntity<listaOfferte> getAll() {
		try {
			listaOfferte listaProdotti = offersService.findLast();
			logger.info(listaProdotti.toString());
			return new ResponseEntity<listaOfferte>(listaProdotti, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<listaOfferte>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
