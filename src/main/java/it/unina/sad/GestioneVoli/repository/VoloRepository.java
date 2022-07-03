package it.unina.sad.GestioneVoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unina.sad.GestioneVoli.entity.Volo;

@Repository
public interface VoloRepository extends JpaRepository<Volo, String> {

}
