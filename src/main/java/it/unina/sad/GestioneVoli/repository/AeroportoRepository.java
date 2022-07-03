package it.unina.sad.GestioneVoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unina.sad.GestioneVoli.entity.Aeroporto;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {

}
