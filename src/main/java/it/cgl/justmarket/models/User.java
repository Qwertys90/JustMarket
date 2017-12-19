package it.cgl.justmarket.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.cgl.justmarket.models.enums.TipoUtente;
import it.cgl.justmarket.models.enums.UserProfileType;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String via;
	
	private String cap;
	
	private String citta;
	
	private String provincia;
	
	private String cellulare;

	private TipoUtente tipo;
	
	private String nome;
	
	private String cognome;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private UserProfileType profileType;
	
	
	@OneToMany
	@JoinColumn(name="User_id")
	private List<CreditCard> listaCreditCard;
		
	
	@OneToMany
	@JoinColumn(name="User_id")
	private List<Transazione> transazioni;
	
	public User() {
		this.listaCreditCard = new ArrayList<CreditCard>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public TipoUtente getTipo() {
		return tipo;
	}
	public void setTipo(TipoUtente tipo) {
		this.tipo = tipo;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserProfileType getProfileType() {
		return profileType;
	}
	public void setProfileType(UserProfileType profileType) {
		this.profileType = profileType;
	}
	public List<CreditCard> getListaCreditCard() {
		return listaCreditCard;
	}
	public void setListaCreditCard(List<CreditCard> listaCreditCard) {
		this.listaCreditCard = listaCreditCard;
	}
	
		public List<Transazione> getTransazioni() {
		return transazioni;
	}

	public void setTransazioni(List<Transazione> transazioni) {
		this.transazioni = transazioni;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", via=" + via + ", cap=" + cap + ", citta=" + citta + ", provincia=" + provincia
				+ ", cellulare=" + cellulare + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", profileType=" + profileType + "]";
	}
	
	

}
