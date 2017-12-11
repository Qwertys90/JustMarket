package it.cgl.justmarket.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import it.cgl.justmarket.models.enums.Categoria;
import it.cgl.justmarket.models.enums.Unita;

@Entity
public class Prodotto {
	@Id
	@GeneratedValue
	private int id;

	private String img;
		
	private String marca;

	private String nome;
	
	private String descrizione;
	
	private String dataScadenza;
	
	private Categoria categoria;
	
	private double quantitaDisponibile;
	
	private double quantitaDaAcquistare;
	
	private Unita unita;
	
	private double prezzoUnitario;
	
	private double prezzoNoIva;
	
	private double prezzoIvato;
	
	private boolean offerta;
	
	private int sconto;

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

	public double getQuantitaDisponibile() {
		return quantitaDisponibile;
	}

	public void setQuantitaDisponibile(double quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
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

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", img=" + img + ", marca=" + marca + ", nome=" + nome + ", descrizione="
				+ descrizione + ", dataScadenza=" + dataScadenza + ", quantitaDisponibile=" + quantitaDisponibile
				+ ", quantitaDaAcquistare=" + quantitaDaAcquistare + ", prezzoUnitario=" + prezzoUnitario
				+ ", prezzoNoIva=" + prezzoNoIva + ", prezzoIvato=" + prezzoIvato + ", offerta=" + offerta + ", sconto="
				+ sconto + "]";
	}

	public Prodotto() {
	}
	
	
}