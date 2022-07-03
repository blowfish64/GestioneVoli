package it.unina.sad.GestioneVoli.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Tratta {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private Long durataPrevistaMinuti;
	@ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) private Aeroporto aeroportoPartenza;
	@ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) private Aeroporto aeroportoDestinazione;
	@OneToMany(mappedBy = "tratta") private List<Volo> voli;
	@ManyToMany private List<CompagniaAerea> compagnieAeree;

	public Tratta() {
		super();
	}

	public Tratta(Long durataPrevistaMinuti, Aeroporto aeroportoPartenza, Aeroporto aeroportoDestinazione, List<CompagniaAerea> compagnieAeree) {
		this.durataPrevistaMinuti = durataPrevistaMinuti;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoDestinazione = aeroportoDestinazione;
		this.compagnieAeree = compagnieAeree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDurataPrevistaMinuti() {
		return durataPrevistaMinuti;
	}

	public void setDurataPrevistaMinuti(Long durataPrevistaMinuti) {
		this.durataPrevistaMinuti = durataPrevistaMinuti;
	}

	public Aeroporto getAeroportoPartenza() {
		return aeroportoPartenza;
	}

	public void setAeroportoPartenza(Aeroporto aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}

	public Aeroporto getAeroportoDestinazione() {
		return aeroportoDestinazione;
	}

	public void setAeroportoDestinazione(Aeroporto aeroportoDestinazione) {
		this.aeroportoDestinazione = aeroportoDestinazione;
	}

	public List<Volo> getVoli() {
		return voli;
	}

	public void setVoli(List<Volo> voli) {
		this.voli = voli;
	}

	public List<CompagniaAerea> getCompagnieAeree() {
		return compagnieAeree;
	}

	public void setCompagnieAeree(List<CompagniaAerea> compagnieAeree) {
		this.compagnieAeree = compagnieAeree;
	}
}
