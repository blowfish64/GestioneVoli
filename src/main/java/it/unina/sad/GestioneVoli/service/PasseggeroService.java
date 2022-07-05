package it.unina.sad.GestioneVoli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.unina.sad.GestioneVoli.entity.Passeggero;
import it.unina.sad.GestioneVoli.repository.PasseggeroRepository;

@Service
public class PasseggeroService {

	@Autowired
	private PasseggeroRepository passeggeroRepository;

	@Transactional(propagation = Propagation.MANDATORY)
	public Passeggero getOrAdd(String nome, String cognome, String eMail, String documentoIdentità, String codiceFiscale, String nomeUtente) {
		Passeggero passeggeroTest = new Passeggero(nome, cognome, null, documentoIdentità, codiceFiscale, nomeUtente);
		return passeggeroRepository.findOne(Example.of(passeggeroTest)).orElseGet(() -> {
			passeggeroTest.seteMail(eMail);
			return passeggeroRepository.save(passeggeroTest);
		});
	}
}
