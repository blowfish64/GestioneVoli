package it.unina.sad.GestioneVoli.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Volo {
	@Id private String codice;
	private Timestamp dataOraPartenza;
	private Double prezzoBase;
	private int postiPasseggeroRestanti;
	private int postiBagaglioCabinaRestanti;
	private int postiBagaglioStivaRestanti;
	@ManyToOne private Aereo aereo;
	@ManyToOne private Tratta tratta;
	@OneToMany(mappedBy = "volo") private List<Biglietto> biglietti;

	public Volo() {
		super();
	}

	public Volo(String codice, Timestamp dataOraPartenza, Double prezzoBase, Integer postiPasseggeroRestanti,
			Integer postiBagaglioCabinaRestanti, Integer postiBagaglioStivaRestanti, Aereo aereo, Tratta tratta) {
		this.codice = codice;
		this.dataOraPartenza = dataOraPartenza;
		this.prezzoBase = prezzoBase;
		this.postiPasseggeroRestanti = postiPasseggeroRestanti;
		this.postiBagaglioCabinaRestanti = postiBagaglioCabinaRestanti;
		this.postiBagaglioStivaRestanti = postiBagaglioStivaRestanti;
		this.aereo = aereo;
		this.tratta = tratta;
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
