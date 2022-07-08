package it.unina.sad.GestioneVoli.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unina.sad.GestioneVoli.entity.Biglietto;
import it.unina.sad.GestioneVoli.entity.Passeggero;

public interface BigliettoRepository extends JpaRepository<Biglietto, Long> {
	public Boolean existsByCodiceIdentificativoAndGiornoValidità(String codiceIdentificativo, Date giornoValidità);
	public List<Biglietto> findByPasseggero(Passeggero passeggero);
}
