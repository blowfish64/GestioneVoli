package it.unina.sad.GestioneVoli.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Aereo {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private String matricola;
	private Integer capienzaPasseggeri;
	private Integer capienzaBagagliCabina;
	private Integer capienzaBagagliStiva;
	@ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) private CompagniaAerea compagniaAerea;

	public Aereo() {
		super();
	}

	public Aereo(String matricola) {
		this.matricola = matricola;
	}

	public Aereo(String matricola, Integer capienzaPasseggeri, Integer capienzaBagagliCabina, Integer capienzaBagagliStiva, CompagniaAerea compagniaAerea) {
		this.matricola = matricola;
		this.capienzaPasseggeri = capienzaPasseggeri;
		this.capienzaBagagliCabina = capienzaBagagliCabina;
		this.capienzaBagagliStiva = capienzaBagagliStiva;
		this.compagniaAerea = compagniaAerea;
	}

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

	public CompagniaAerea getCompagniaAerea() {
		return compagniaAerea;
	}

	public void setCompagniaAerea(CompagniaAerea compagniaAerea) {
		this.compagniaAerea = compagniaAerea;
	}

//	@Formula("(SELECT )")
//	public Long numeroTratte;
}
