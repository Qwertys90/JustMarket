package it.cgl.justmarket.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import it.cgl.justmarket.services.ProdottoService;

@Entity
public class listaOfferte {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToMany
	@JoinTable(
		      name="OFFERS_PRODJ",
		      joinColumns= @JoinColumn (name="OFFERSJ_ID", referencedColumnName="ID"),
		      inverseJoinColumns=@JoinColumn(name="PRODJ_ID", referencedColumnName="ID")
		      )
	List<Prodotto> listaProdotti;

	String dataGenerazione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Prodotto> getListaProdotti() {
		return listaProdotti;
	}

	public void setListaProdotti(List<Prodotto> listaProdotti) {
		this.listaProdotti = listaProdotti;
	}

	public String getDataGenerazione() {
		return dataGenerazione;
	}

	public void setDataGenerazione(String dataGenerazione) {
		this.dataGenerazione = dataGenerazione;
	}
	
}
