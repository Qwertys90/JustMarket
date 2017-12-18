package it.cgl.justmarket.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transazione {

	@Id
	@GeneratedValue
	private int id;
	
	private String data;
	
	private String codiceTransazione;
	
	@OneToMany
	@JoinColumn(name="User_id")
	private List<ProdottoAcquistato> listaProdotti;
	
	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	@ManyToOne
	private User user;
	
	private String numeroCarta;
		
	private double prezzoNoIva;
	
	private double prezzoIva;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCodiceTransazione() {
		return codiceTransazione;
	}

	public void setCodiceTransazione(String codiceTransazione) {
		this.codiceTransazione = codiceTransazione;
	}

	public List<ProdottoAcquistato> getListaProdotti() {
		return listaProdotti;
	}

	public void setListaProdotti(List<ProdottoAcquistato> listaProdotti) {
		this.listaProdotti = listaProdotti;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPrezzoNoIva() {
		return prezzoNoIva;
	}

	public void setPrezzoNoIva(double prezzoNoIva) {
		this.prezzoNoIva = prezzoNoIva;
	}

	public double getPrezzoIva() {
		return prezzoIva;
	}

	public void setPrezzoIva(double prezzoIva) {
		this.prezzoIva = prezzoIva;
	}

	@Override
	public String toString() {
		return "Transazione [id=" + id + ", data=" + data + ", codiceTransazione=" + codiceTransazione
				+ ", listaProdotti=" + listaProdotti + ", user=" + user + ", numeroCarta=" + numeroCarta
				+ ", prezzoNoIva=" + prezzoNoIva + ", prezzoIva=" + prezzoIva + "]";
	}

	
}
