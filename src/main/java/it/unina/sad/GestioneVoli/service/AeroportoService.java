package it.unina.sad.GestioneVoli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.unina.sad.GestioneVoli.entity.Aeroporto;
import it.unina.sad.GestioneVoli.repository.AeroportoRepository;

@Service
public class AeroportoService {

	@Autowired
	private AeroportoRepository aeroportoRepository;

	public List<Aeroporto> getAll() {
		return aeroportoRepository.findAll();
	}

	public Aeroporto get(Long id) {
		return aeroportoRepository.findById(id).orElse(null);
	}

	public Boolean exists(Long id) {
		return aeroportoRepository.existsById(id);
	}

	public Boolean delete(Long id) {
		if(aeroportoRepository.existsById(id)) {
			aeroportoRepository.deleteById(id);
			return true;
		} else
			return false;
	}

	public void add(String name, String city) throws IllegalStateException {
		Aeroporto aeroporto = new Aeroporto(name, city);
		if(aeroportoRepository.exists(Example.of(aeroporto))) {
			throw new IllegalStateException("Aeroporto Già Esistente!");
		} else {
			aeroportoRepository.save(aeroporto);
		}
	}

}
