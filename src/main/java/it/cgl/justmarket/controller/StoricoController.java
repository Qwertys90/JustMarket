package it.cgl.justmarket.controller;

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
import it.cgl.justmarket.models.Storico;
import it.cgl.justmarket.models.User;
import it.cgl.justmarket.services.CreditCardService;
import it.cgl.justmarket.services.CreditCardServiceImpl;
import it.cgl.justmarket.services.StoricoService;
import it.cgl.justmarket.services.UserService;



@RestController
@RequestMapping("/storico")
public class StoricoController {

	private static final Logger logger = Logger.getLogger(CreditCardServiceImpl.class.getName());
	
	@Autowired
	private StoricoService storicoService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Storico>> getAll() {
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			logger.info("aaaa"+auth.getName());
			User user=userService.findByUsername(auth.getName());

			List<Storico> listaStorico = storicoService.findAll(user);
			return new ResponseEntity<List<Storico>>(listaStorico, HttpStatus.OK);
		} catch (Exception e) {
			logger.info("a"+e);
			return new ResponseEntity<List<Storico>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
}
