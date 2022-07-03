package it.unina.sad.GestioneVoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unina.sad.GestioneVoli.entity.Aereo;

public interface AereoRepository extends JpaRepository<Aereo, Long> {

}
