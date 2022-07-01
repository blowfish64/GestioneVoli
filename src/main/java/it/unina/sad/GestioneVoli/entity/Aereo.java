package it.unina.sad.GestioneVoli.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aereo {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private String matricola;
	private Integer capienzaPasseggeri;
	private Integer capienzaBagagliCabina;
	private Integer capienzaBagagliStiva;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Integer getCapienzaPasseggeri() {
		return capienzaPasseggeri;
	}

	public void setCapienzaPasseggeri(Integer capienzaPasseggeri) {
		this.capienzaPasseggeri = capienzaPasseggeri;
	}

	public Integer getCapienzaBagagliCabina() {
		return capienzaBagagliCabina;
	}

	public void setCapienzaBagagliCabina(Integer capienzaBagagliCabina) {
		this.capienzaBagagliCabina = capienzaBagagliCabina;
	}

	public Integer getCapienzaBagagliStiva() {
		return capienzaBagagliStiva;
	}

	public void setCapienzaBagagliStiva(Integer capienzaBagagliStiva) {
		this.capienzaBagagliStiva = capienzaBagagliStiva;
	}
}
