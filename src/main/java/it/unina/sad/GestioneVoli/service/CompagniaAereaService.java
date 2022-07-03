package it.unina.sad.GestioneVoli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.unina.sad.GestioneVoli.entity.CompagniaAerea;
import it.unina.sad.GestioneVoli.repository.CompagniaAereaRepository;

@Service
public class CompagniaAereaService {

	@Autowired
	private CompagniaAereaRepository compagniaAereaRepository;

	public List<CompagniaAerea> getAll() {
		return compagniaAereaRepository.findAll();
	}

	public CompagniaAerea getById(Long id) {
		return compagniaAereaRepository.findById(id).orElse(null);
	}

	public Boolean exists(Long id) {
		return compagniaAereaRepository.existsById(id);
	}

	public Boolean delete(Long id) {
		if(compagniaAereaRepository.existsById(id)) {
			compagniaAereaRepository.deleteById(id);
			return true;
		} else
			return false;
	}

	public void add(String name) throws IllegalStateException {
		CompagniaAerea compagniaAerea = new CompagniaAerea(name);
		if(compagniaAereaRepository.exists(Example.of(compagniaAerea))) {
			throw new IllegalStateException("Compagnia Aerea Già Esistente!");
		} else {
			compagniaAereaRepository.save(compagniaAerea);
		}
	}
}
