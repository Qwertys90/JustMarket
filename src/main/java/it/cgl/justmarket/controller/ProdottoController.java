package it.cgl.justmarket.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.enums.Categoria;
import it.cgl.justmarket.services.CreditCardService;
import it.cgl.justmarket.services.ProdottoService;
import it.cgl.justmarket.services.UserService;

@RestController
@RequestMapping("/prodotti")
public class ProdottoController {

	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private ProdottoService prodottoService;

	@Autowired
	private UserService userService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/getmodel")
	public ResponseEntity<Prodotto> getmodel() {
		Prodotto prodott = new Prodotto();
		return new ResponseEntity<Prodotto>(prodott, HttpStatus.CREATED);
	}

	@PostMapping("/saveupdate")
	public ResponseEntity<Prodotto> saveOrUpdateProdotto(@RequestBody Prodotto prodotto) {
		try {
			prodotto.setPrezzoIvato(prodotto.getPrezzoUnitario());
			prodotto.setPrezzoNoIva((Math.floor(prodotto.getPrezzoIvato()/122*10000))/100);
			Prodotto saved = prodottoService.saveOrUpdateProdotto(prodotto);
			logger.info(saved + " saved");
			return new ResponseEntity<Prodotto>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<Prodotto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Prodotto> deleteProdotto(@PathVariable("id") int id) {
		try {
			prodottoService.deleteProdotto(id);
			logger.info(id + " deleted");
			return new ResponseEntity<Prodotto>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<Prodotto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Prodotto>> getAll() {
		try {
			List<Prodotto> listaProdotti = prodottoService.findAll();
			logger.info(listaProdotti.toString());
			return new ResponseEntity<List<Prodotto>>(listaProdotti, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<List<Prodotto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getcategoria/{categoria}")
	public ResponseEntity<List<Prodotto>> findByCategoria(@PathVariable Categoria categoria) {
		try {
			List<Prodotto> listaProdotti = prodottoService.findByCategoria(categoria);
			logger.info(listaProdotti.toString());
			return new ResponseEntity<List<Prodotto>>(listaProdotti, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<List<Prodotto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getdisponibilita")
	public ResponseEntity<List<Prodotto>> findByQuantitaGreaterThanEqual() {
		try {
			List<Prodotto> listaProdotti = prodottoService.findByQuantitaGreaterThan(0);
			logger.info(listaProdotti.toString());
			return new ResponseEntity<List<Prodotto>>(listaProdotti, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<List<Prodotto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Prodotto> findByIdProdotto(@PathVariable int id) {
		try {
			Prodotto findById = prodottoService.findById(id);
			return new ResponseEntity<Prodotto>(findById, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Prodotto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
