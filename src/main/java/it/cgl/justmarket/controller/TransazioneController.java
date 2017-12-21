package it.cgl.justmarket.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Random;

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
import it.cgl.justmarket.services.CreditCardService;
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
	@Autowired
	private CreditCardService creditService;

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
			logger.info("getall1");
			User user = userService.findByUsername(auth.getName());
			List<Transazione> listaTransazione = transazioneService.findByUser(user);
			logger.info("getall2");

			return new ResponseEntity<List<Transazione>>(listaTransazione, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("a" + e);
			return new ResponseEntity<List<Transazione>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/buy/{id}")
	public ResponseEntity<String> buy(@RequestBody Transazione transazione, @PathVariable int id){
		logger.info(transazione+"");
		transazione.setNumeroCarta(this.creditService.findById(id).getNumeroCarta());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("aaaa" + auth.getName());
		User user = userService.findByUsername(auth.getName());
		boolean controlloQuantita=false;
		List<ProdottoAcquistato> nuovaLista= new ArrayList<ProdottoAcquistato>();
		double prezzoTotale=0.00;
		LocalDate dNow = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		logger.info("aaaa" + auth.getName());

		for(ProdottoAcquistato p : transazione.getListaProdotti())
		{
			logger.info(p+"");
			if(p.getQuantita()>prodottoService.findById(p.getId()).getQuantita()) {
				controlloQuantita=true;
			}
			String date;
			if(p.getDataScadenza().contains("-")) {
				date = p.getDataScadenza().replaceAll("-", "/");
			}else {
				date = p.getDataScadenza().replaceAll(",", "/");
			}
			date= date.substring(0,10);
			logger.info(date);
			YearMonth scadenzaMese = YearMonth.parse(date,formatter);
			LocalDate scadenza = scadenzaMese.atDay(Integer.parseInt(date.split("/")[2]));
			logger.info(scadenza.toString());
			if(scadenza.isBefore(dNow.plusDays(3)))
				p.setPrezzoUnitario(p.getPrezzoUnitario()*0.7);
			logger.info(scadenza + " "+ dNow + " "+scadenza.compareTo(dNow));
			if(scadenza.compareTo(dNow)==0)
				p.setPrezzoUnitario(p.getPrezzoUnitario()*0.3);
			prezzoTotale+=p.getPrezzoUnitario()*p.getQuantitaDaAcquistare();
		}
	
		logger.info("Username-> " + auth.getName());
		if(!controlloQuantita) {
			Random random = new Random();
			String resultNumber = "";
			String result = "";
			String number = "123456789";
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for(int i=0;i<3;i++){
			      result += alphabet.charAt(random.nextInt(alphabet.length()));
			      resultNumber += random.nextInt(number.length());
			}
			transazione.setData(dNow.toString());
			transazione.setPrezzoIva(prezzoTotale);
			transazione.setPrezzoNoIva(prezzoTotale/122*100);
			transazione.setUser(user);
			transazione.setCodiceTransazione(dNow.toString() + "-" + result + resultNumber + "-" + transazione.getId());
			logger.info(transazione.toString());
			List<ProdottoAcquistato> listaTrans2 = transazione.getListaProdotti(); 
			transazione.setListaProdotti(new ArrayList<ProdottoAcquistato>());
			transazioneService.save(transazione);
			for(ProdottoAcquistato p:listaTrans2) {
				ProdottoAcquistato prodNew = new ProdottoAcquistato();
				prodNew.setMarca(p.getMarca());
				prodNew.setNome(p.getNome());
				prodNew.setQuantitaDaAcquistare(p.getQuantitaDaAcquistare());
				prodNew.setTransazione(transazione);
				prodNew.setPrezzoUnitario(p.getPrezzoUnitario());
				nuovaLista.add(prodNew);
				logger.info(prodNew+"");
				prodAcquiService.saveOrUpdateProdottoAcquistato(prodNew);
				Prodotto prodAgg = prodottoService.findById(p.getId());
				prodAgg.setQuantita(prodAgg.getQuantita()-prodNew.getQuantitaDaAcquistare());
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
