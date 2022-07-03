package it.unina.sad.GestioneVoli.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class CompagniaAerea {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private String nome;
	@OneToMany(mappedBy = "compagniaAerea") private List<Aereo> aerei;
	@ManyToMany(mappedBy = "compagnieAeree") private List<Tratta> tratte;

	public CompagniaAerea() {
		super();
	}

	public CompagniaAerea(String nome) {
		this.nome = nome;
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

	public List<Aereo> getAerei() {
		return aerei;
	}

	public void setAerei(List<Aereo> aerei) {
		this.aerei = aerei;
	}

	public List<Tratta> getTratte() {
		return tratte;
	}

	public void setTratte(List<Tratta> tratte) {
		this.tratte = tratte;
	}
}
