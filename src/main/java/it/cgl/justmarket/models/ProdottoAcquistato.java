package it.cgl.justmarket.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import it.cgl.justmarket.models.enums.Categoria;
import it.cgl.justmarket.models.enums.Unita;

@Entity
public class ProdottoAcquistato {

	@Id
	@GeneratedValue
	private int id;

	private String img;
		
	private String marca;

	private String nome;
	
	private String descrizione;
	
	private String dataScadenza;
	
	private Categoria categoria;
	
	private double quantita;
	
	private double quantitaDaAcquistare;
	
	private Unita unita;
	
	private double prezzoUnitario;
	
	private double prezzoNoIva;
	
	private double prezzoIvato;
	
	private boolean offerta;
	
	private int sconto;
	
	@ManyToOne
	private Transazione transazione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getQuantita() {
		return quantita;
	}

	public void setQuantita(double quantita) {
		this.quantita = quantita;
	}

	public double getQuantitaDaAcquistare() {
		return quantitaDaAcquistare;
	}

	public void setQuantitaDaAcquistare(double quantitaDaAcquistare) {
		this.quantitaDaAcquistare = quantitaDaAcquistare;
	}

	public Unita getUnita() {
		return unita;
	}

	public void setUnita(Unita unita) {
		this.unita = unita;
	}

	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}

	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}

	public double getPrezzoNoIva() {
		return prezzoNoIva;
	}

	public void setPrezzoNoIva(double prezzoNoIva) {
		this.prezzoNoIva = prezzoNoIva;
	}

	public double getPrezzoIvato() {
		return prezzoIvato;
	}

	public void setPrezzoIvato(double prezzoIvato) {
		this.prezzoIvato = prezzoIvato;
	}

	public boolean isOfferta() {
		return offerta;
	}

	public void setOfferta(boolean offerta) {
		this.offerta = offerta;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}

	public Transazione getTransazione() {
		return transazione;
	}

	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}

	@Override
	public String toString() {
		return "ProdottoAcquistato [id=" + id + ", img=" + img + ", marca=" + marca + ", nome=" + nome
				+ ", descrizione=" + descrizione + ", dataScadenza=" + dataScadenza + ", categoria=" + categoria
				+ ", quantita=" + quantita + ", quantitaDaAcquistare=" + quantitaDaAcquistare + ", unita=" + unita
				+ ", prezzoUnitario=" + prezzoUnitario + ", prezzoNoIva=" + prezzoNoIva + ", prezzoIvato=" + prezzoIvato
				+ ", offerta=" + offerta + ", sconto=" + sconto + "]";
	} 
	
	
}
