package it.unina.sad.GestioneVoli.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Passeggero {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private String nome;
	private String cognome;
	private String eMail;
	private String documentoIdentità;
	private String codiceFiscale;
	@OneToMany(mappedBy = "passeggero") private List<Biglietto> biglietti;
	private String nomeUtente;	// TODO modellare come relazione con l'Utente autenticato

	public Passeggero() {
		super();
	}

	public Passeggero(String nome, String cognome, String eMail, String documentoIdentità, String codiceFiscale, String nomeUtente) {
		this.nome = nome;
		this.cognome = cognome;
		this.eMail = eMail;
		this.documentoIdentità = documentoIdentità;
		this.codiceFiscale = codiceFiscale;
		this.nomeUtente = nomeUtente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getDocumentoIdentità() {
		return documentoIdentità;
	}

	public void setDocumentoIdentità(String documentoIdentità) {
		this.documentoIdentità = documentoIdentità;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getCardId() {
		return this.documentoIdentità;
	}
}
