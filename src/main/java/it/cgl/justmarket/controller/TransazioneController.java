package it.cgl.justmarket.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.cgl.justmarket.models.Prodotto;
import it.cgl.justmarket.models.ProdottoAcquistato;
import it.cgl.justmarket.models.Transazione;
import it.cgl.justmarket.models.User;
import it.cgl.justmarket.services.CreditCardServiceImpl;
import it.cgl.justmarket.services.ProdottoAcquistatoService;
import it.cgl.justmarket.services.ProdottoService;
import it.cgl.justmarket.services.TransazioneService;
import it.cgl.justmarket.services.UserService;

@RestController
@RequestMapping("/transazione")
public class TransazioneController {

	private static final Logger logger = Logger.getLogger(CreditCardServiceImpl.class.getName());
	@Autowired
	private ProdottoAcquistatoService prodAcquiService;
	@Autowired
	private TransazioneService transazioneService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProdottoService prodottoService;

	@GetMapping("/getmodel")
	public ResponseEntity<Transazione> getModel() {
		Transazione trans = new Transazione();
		ProdottoAcquistato prod = new ProdottoAcquistato();
		List<ProdottoAcquistato> lista = new ArrayList<ProdottoAcquistato>();
		lista.add(prod);
		trans.setListaProdotti(lista);
		return new ResponseEntity<Transazione>(trans, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Transazione>> getAll() {
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			logger.info("aaaa" + auth.getName());
			User user = userService.findByUsername(auth.getName());

			List<Transazione> listaTransazione = transazioneService.findAll(user);
			return new ResponseEntity<List<Transazione>>(listaTransazione, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("a" + e);
			return new ResponseEntity<List<Transazione>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/buy")
	public ResponseEntity<String> buy(@RequestBody Transazione transazione){
		logger.info(transazione+"");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("aaaa" + auth.getName());
		User user = userService.findByUsername(auth.getName());
		boolean controlloQuantita=false;
		List<ProdottoAcquistato> nuovaLista= new ArrayList<ProdottoAcquistato>();
//		boolean controlloScadenza=false;
		double prezzoTotale=0.00;
		LocalDate dNow = LocalDate.now();
//					// trasforma data carta
//					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
//					String date = transazione.getCartaDiCredito().getScadenza();
//					YearMonth scadenzaMese = YearMonth.parse(date, formatter);
//					LocalDate scadenza = scadenzaMese.atEndOfMonth();
//					// -----
//		if(dNow.isAfter(scadenza)) {
//			controlloScadenza=true;
//		}
		logger.info("aaaa" + auth.getName());

		for(ProdottoAcquistato p : transazione.getListaProdotti())
		{
			if(p.getQuantita()>prodottoService.findById(p.getId()).getQuantita()) {
				controlloQuantita=true;
			}
			prezzoTotale+=p.getPrezzoUnitario()*p.getQuantita();
		}
	
		
		if(!controlloQuantita) {
			transazione.setData(dNow.toString());
			transazione.setPrezzoIva(prezzoTotale);
			transazione.setPrezzoNoIva(prezzoTotale/122*100);
			transazione.setUser(user);
			transazione.setCodiceTransazione(dNow.toString()+"prova");
			logger.info(transazione.toString());
			List<ProdottoAcquistato> listaTrans2 = transazione.getListaProdotti(); 
			transazione.setListaProdotti(new ArrayList<ProdottoAcquistato>());
			transazioneService.save(transazione);
			for(ProdottoAcquistato p:listaTrans2) {
				ProdottoAcquistato prodNew = new ProdottoAcquistato();
				prodNew.setMarca(p.getMarca());
				prodNew.setNome(p.getNome());
				prodNew.setQuantita(p.getQuantita());
				prodNew.setTransazione(transazione);
				nuovaLista.add(prodNew);
				logger.info(prodNew+"");
				prodAcquiService.saveOrUpdateProdottoAcquistato(prodNew);
				Prodotto prodAgg = prodottoService.findById(p.getId());
				prodAgg.setQuantita(prodAgg.getQuantita()-prodNew.getQuantita());
				prodottoService.saveOrUpdateProdotto(prodAgg);
				}
			transazione.setListaProdotti(nuovaLista);
			transazioneService.save(transazione);

		}
		if(controlloQuantita) {
			return new ResponseEntity<String>("Uno o più prodotti non rispecchiano le quantità massime",HttpStatus.OK);

		}else {
//			if(controlloScadenza) {
//			return new ResponseEntity<String>("Carta di credito scaduta",HttpStatus.OK);
//		}else {
			return new ResponseEntity<String>("Transazione eseguita"+transazione,HttpStatus.OK);

		}
		
		
	}

}
