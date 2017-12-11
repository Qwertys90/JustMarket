package it.cgl.justmarket.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CreditCard {
	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	
	private String cognome;

	private int numeroCarta;

	private int cvv;
		
	private String scadenza;
	
	@ManyToOne
	private User user;

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", numeroCarta=" + numeroCarta
				+ ", cvv=" + cvv + ", scadenza=" + scadenza + ", user=" + user + "]";
	}

	public CreditCard() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(int numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	

	
}
