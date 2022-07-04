package it.unina.sad.GestioneVoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unina.sad.GestioneVoli.entity.Aeroporto;
import it.unina.sad.GestioneVoli.entity.Tratta;

public interface TrattaRepository extends JpaRepository<Tratta, Long> {
	public Tratta findByAeroportoPartenzaAndAeroportoDestinazione(Aeroporto aeroportoPartenza, Aeroporto aeroportoDestinazione);
}
