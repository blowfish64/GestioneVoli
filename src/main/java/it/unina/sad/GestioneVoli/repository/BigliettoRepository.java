package it.unina.sad.GestioneVoli.repository;

import java.sql.Date;

import org.springframework.data.repository.CrudRepository;

import it.unina.sad.GestioneVoli.entity.Biglietto;

public interface BigliettoRepository extends CrudRepository<Biglietto, Long> {
	public Boolean existsByCodiceIdentificativoAndGiornoValidità(String codiceIdentificativo, Date giornoValidità);
}
