package it.unina.sad.GestioneVoli.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aeroporto {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private String nome;
	private String città;
	@OneToMany(mappedBy = "aeroportoPartenza") private List<Tratta> trattePartenza;
	@OneToMany(mappedBy = "aeroportoDestinazione") private List<Tratta> tratteDestinazione;

	public Aeroporto() {
		super();
	}

	public Aeroporto(String nome, String città) {
		this.nome = nome;
		this.città = città;
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

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getCity() {
		return this.città;
	}

	public List<Tratta> getTrattePartenza() {
		return trattePartenza;
	}

	public void setTrattePartenza(List<Tratta> trattePartenza) {
		this.trattePartenza = trattePartenza;
	}

	public List<Tratta> getTratteDestinazione() {
		return tratteDestinazione;
	}

	public void setTratteDestinazione(List<Tratta> tratteDestinazione) {
		this.tratteDestinazione = tratteDestinazione;
	}
}
