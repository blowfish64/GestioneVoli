package it.unina.sad.GestioneVoli.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tratta {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private Long durataPrevistaMinuti;
	@ManyToOne private Aeroporto aeroportoPartenza;
	@ManyToOne private Aeroporto aeroportoDestinazione;
	@OneToMany private List<Volo> voli;

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
}
