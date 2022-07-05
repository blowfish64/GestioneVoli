package it.unina.sad.GestioneVoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unina.sad.GestioneVoli.entity.Passeggero;

public interface PasseggeroRepository extends JpaRepository<Passeggero, Long> {

}
