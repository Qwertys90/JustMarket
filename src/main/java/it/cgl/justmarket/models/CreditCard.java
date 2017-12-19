package it.cgl.justmarket.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CreditCard {
	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	
	private String cognome;

	private String numeroCarta;

	private int cvv;
		
	private String scadenza;
	
	@JsonIgnore
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

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
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
