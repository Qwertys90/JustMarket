package it.cgl.justmarket.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Storico {

	@Id
	@GeneratedValue
	private int id;
	
	private String marca;
	
	private String nome;
	
	private double quantita;
	
	private double prezzoUnitario;
	
	private double prezzoIvato;
	
	private String date;
	
	private double prezzoTotale;
	
	@ManyToOne
	private User user;
		
	public double getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public double getPrezzoIvato() {
		return prezzoIvato;
	}

	public void setPrezzoIvato(double prezzoIvato) {
		this.prezzoIvato = prezzoIvato;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Storico [id=" + id + ", marca=" + marca + ", nome=" + nome + ", quantita=" + quantita
				+ ", prezzoUnitario=" + prezzoUnitario + ", prezzoIvato=" + prezzoIvato + ", date=" + date + "]";
	}

	public Storico() {
		
	}
	
	
	
}
