package it.unina.sad.GestioneVoli.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Biglietto {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private String codiceIdentificativo;
	private Date giornoValidità;
	private int postoPasseggero;
	private int numeroBagagliCabina;
	private int numeroBagagliStiva;
	private Timestamp dataOraEmissione;
	private double costoTotale;
	private String eMailPasseggero;
	private String numeroCartaCredito;
	@ManyToOne private Volo volo;
	@ManyToOne private Passeggero passeggero;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceIdentificativo() {
		return codiceIdentificativo;
	}

	public void setCodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}

	public Date getGiornoValidità() {
		return giornoValidità;
	}

	public void setGiornoValidità(Date giornoValidità) {
		this.giornoValidità = giornoValidità;
	}

	public int getPostoPasseggero() {
		return postoPasseggero;
	}

	public void setPostoPasseggero(int postoPasseggero) {
		this.postoPasseggero = postoPasseggero;
	}

	public int getNumeroBagagliCabina() {
		return numeroBagagliCabina;
	}

	public void setNumeroBagagliCabina(int numeroBagagliCabina) {
		this.numeroBagagliCabina = numeroBagagliCabina;
	}

	public int getNumeroBagagliStiva() {
		return numeroBagagliStiva;
	}

	public void setNumeroBagagliStiva(int numeroBagagliStiva) {
		this.numeroBagagliStiva = numeroBagagliStiva;
	}

	public Timestamp getDataOraEmissione() {
		return dataOraEmissione;
	}

	public void setDataOraEmissione(Timestamp dataOraEmissione) {
		this.dataOraEmissione = dataOraEmissione;
	}

	public double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}

	public String geteMailPasseggero() {
		return eMailPasseggero;
	}

	public void seteMailPasseggero(String eMailPasseggero) {
		this.eMailPasseggero = eMailPasseggero;
	}

	public String getNumeroCartaCredito() {
		return numeroCartaCredito;
	}

	public void setNumeroCartaCredito(String numeroCartaCredito) {
		this.numeroCartaCredito = numeroCartaCredito;
	}

	public Volo getVolo() {
		return volo;
	}

	public void setVolo(Volo volo) {
		this.volo = volo;
	}

	public Passeggero getPasseggero() {
		return passeggero;
	}

	public void setPasseggero(Passeggero passeggero) {
		this.passeggero = passeggero;
	}

	public Date getDay() {
		return this.giornoValidità;
	}
}
