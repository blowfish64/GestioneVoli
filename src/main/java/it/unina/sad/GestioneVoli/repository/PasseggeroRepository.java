package it.unina.sad.GestioneVoli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unina.sad.GestioneVoli.entity.Passeggero;

public interface PasseggeroRepository extends JpaRepository<Passeggero, Long> {
	public List<Passeggero> findByNomeUtente(String nomeUtente);
}
