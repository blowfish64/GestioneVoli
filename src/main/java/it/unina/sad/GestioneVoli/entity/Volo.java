package it.unina.sad.GestioneVoli.entity;

import java.security.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Volo {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
	private String codice;
	private Timestamp dataOraPartenza;
	private Double prezzoBase;
	private int postiPasseggeroRestanti;
	private int postiBagaglioCabinaRestanti;
	private int postiBagaglioStivaRestanti;
	@ManyToOne private Aereo aereo;
	@ManyToOne private Tratta tratta;
	@OneToMany private List<Biglietto> biglietti;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Timestamp getDataOraPartenza() {
		return dataOraPartenza;
	}

	public void setDataOraPartenza(Timestamp dataOraPartenza) {
		this.dataOraPartenza = dataOraPartenza;
	}

	public Double getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(Double prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public int getPostiPasseggeroRestanti() {
		return postiPasseggeroRestanti;
	}

	public void setPostiPasseggeroRestanti(int postiPasseggeroRestanti) {
		this.postiPasseggeroRestanti = postiPasseggeroRestanti;
	}

	public int getPostiBagaglioCabinaRestanti() {
		return postiBagaglioCabinaRestanti;
	}

	public void setPostiBagaglioCabinaRestanti(int postiBagaglioCabinaRestanti) {
		this.postiBagaglioCabinaRestanti = postiBagaglioCabinaRestanti;
	}

	public int getPostiBagaglioStivaRestanti() {
		return postiBagaglioStivaRestanti;
	}

	public void setPostiBagaglioStivaRestanti(int postiBagaglioStivaRestanti) {
		this.postiBagaglioStivaRestanti = postiBagaglioStivaRestanti;
	}

	public Aereo getAereo() {
		return aereo;
	}

	public void setAereo(Aereo aereo) {
		this.aereo = aereo;
	}

	public Tratta getTratta() {
		return tratta;
	}

	public void setTratta(Tratta tratta) {
		this.tratta = tratta;
	}

	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}
}
