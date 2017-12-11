package it.cgl.justmarket.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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

import it.cgl.justmarket.models.CreditCard;
import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.Storico;
import it.cgl.justmarket.models.User;
import it.cgl.justmarket.models.enums.Categoria;
import it.cgl.justmarket.services.CreditCardService;
import it.cgl.justmarket.services.ProdottoService;
import it.cgl.justmarket.services.StoricoService;
import it.cgl.justmarket.services.UserService;



@RestController
@RequestMapping("/prodotti")
public class ProdottoController {
	
	@Autowired
	private StoricoService storicoService;
	
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
	public ResponseEntity<Prodotto> findByIdProdotto (@PathVariable int id){
		try {
			Prodotto findById = prodottoService.findById(id);
			return new ResponseEntity<Prodotto>(findById, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Prodotto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/acquista/{prodottoid}/{carta}/{quantAcquista}")
	public ResponseEntity<User> addProdotto(@PathVariable("prodottoid") int idProd,@PathVariable("carta") int idCarta,@PathVariable("quantAcquista") int quant) {
		try {
			boolean check=false;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());		

			CreditCard card = creditCardService.findById(idCarta);
			Prodotto prodotto = prodottoService.findById(idProd);
			LocalDate dNow = LocalDate.now();
		    logger.info("anno" + dNow);
		    //-----
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		    String date = card.getScadenza();
		    YearMonth scadenzaMese = YearMonth.parse(date, formatter);
		    LocalDate scadenza = scadenzaMese.atEndOfMonth();
		    //-----
		    logger.info("anno" + scadenza);
		    logger.info("prova" + dNow.isBefore(scadenza));
		    for(CreditCard a : user.getListaCreditCard()) {
				if(a.getId()==idCarta)
					check=true;
			}
			if(prodotto.getQuantita()>=quant && quant>0 && dNow.isBefore(scadenza) && check==true) {
			    logger.info("provaasd");

			Storico storico=new Storico();
			storico.setDate(dNow.toString());
			storico.setMarca(prodotto.getMarca());
			storico.setNome(prodotto.getNome());
			storico.setPrezzoIvato(prodotto.getPrezzoIvato());
			storico.setPrezzoUnitario(prodotto.getPrezzoUnitario());
			storico.setQuantita(quant);
			storico.setPrezzoTotale(quant*prodotto.getPrezzoIvato());
			storico.setUser(user);
			storicoService.save(storico);
			
			user.getListaProdotti().add(prodottoService.findById(idProd));
			
			userService.saveUser(user);
			prodotto.setQuantita(prodotto.getQuantita()-1);
			prodottoService.saveOrUpdateProdotto(prodotto);
			//------
			//int credito = card.getCredito();
			//card.setCredito(credito-prodotto.getPrezzo());
			//creditCardService.saveCreditCard(card);
			//---------
			return new ResponseEntity<User>(HttpStatus.OK);
			}else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/prodottiacquistati")
	public ResponseEntity<List<Prodotto>> getAllAcquistati() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			int userid = userService.findByUsername(auth.getName()).getId();	
			List<Prodotto> listaProdotti = prodottoService.findByUser_id(userid);
			return new ResponseEntity<List<Prodotto>>(listaProdotti, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Errore " + e);
			return new ResponseEntity<List<Prodotto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}


