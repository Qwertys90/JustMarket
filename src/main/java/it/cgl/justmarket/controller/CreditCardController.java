package it.cgl.justmarket.controller;

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
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

import it.cgl.justmarket.models.CreditCard;
import it.cgl.justmarket.models.User;
import it.cgl.justmarket.services.CreditCardService;
import it.cgl.justmarket.services.CreditCardServiceImpl;
import it.cgl.justmarket.services.UserService;



@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

	private static final Logger logger = Logger.getLogger(CreditCardServiceImpl.class.getName());
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getmodel")
	public ResponseEntity<CreditCard> getmodel() {
		CreditCard creditCard = new CreditCard();
		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<CreditCard>> getAll() {
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			logger.info("aaaa"+auth.getName());
//			Principal principal = request.getUserPrincipal();
//			logger.info("aaaa"+principal.getName());
			User user = userService.findByUsername(auth.getName());	
			logger.info(user.toString());

			List<CreditCard> listaCard = creditCardService.findByUser_id(user.getId());
			return new ResponseEntity<List<CreditCard>>(listaCard, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("a"+e);
			return new ResponseEntity<List<CreditCard>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/saveupdate")
	public ResponseEntity<CreditCard> saveOrUpdateProdotto(@RequestBody CreditCard creditCard) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());	
			creditCard.setUser(user);
			String encodedString = Base64.getEncoder().encodeToString(creditCard.getNumeroCarta().getBytes());
			creditCard.setNumeroCarta(encodedString);
			CreditCard saved = creditCardService.saveCreditCard(creditCard);
			return new ResponseEntity<CreditCard>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<CreditCard>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/deletecard/{cardid}")
	public ResponseEntity<User> resistuisciProdotto(@PathVariable("cardid") int idCard) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());	
			CreditCard creditCard = creditCardService.findById(idCard);
			List<CreditCard> listaCard = creditCardService.findByUser_id(user.getId());
			boolean check = false;
			for(CreditCard a : listaCard) {
				if(a.getId()==idCard) 
					check=true;
			}
//			listaCard.remove(creditCard);
//			user.setListaCreditCard(listaCard);
//			userService.saveUser(user);
			if(check==true) {
			creditCardService.deleteCreditCard(idCard);
			return new ResponseEntity<User>(HttpStatus.OK);
			}else {
				return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
