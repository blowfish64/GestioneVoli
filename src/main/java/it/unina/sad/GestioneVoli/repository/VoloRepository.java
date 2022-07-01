package it.unina.sad.GestioneVoli.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.unina.sad.GestioneVoli.entity.Volo;

@Repository
public interface VoloRepository extends CrudRepository<Volo, Long> {

}
