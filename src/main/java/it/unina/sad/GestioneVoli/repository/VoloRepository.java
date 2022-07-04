package it.unina.sad.GestioneVoli.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unina.sad.GestioneVoli.entity.Tratta;
import it.unina.sad.GestioneVoli.entity.Volo;

@Repository
public interface VoloRepository extends JpaRepository<Volo, String> {
	public List<Volo> findByTratta(Tratta tratta);
	public List<Volo> findByDataOraPartenzaGreaterThanEqual(Timestamp dataOraPartenza);
	public List<Volo> findByTrattaAndDataOraPartenzaGreaterThanEqual(Tratta tratta, Timestamp dataOraPartenza);
}
