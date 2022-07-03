package it.unina.sad.GestioneVoli.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.unina.sad.GestioneVoli.entity.Aereo;
import it.unina.sad.GestioneVoli.repository.AereoRepository;

@Service
public class AereoService {

	@Autowired
	private AereoRepository aereoRepository;

	@Autowired
	private CompagniaAereaService compagniaAereaService;

	public List<Aereo> getAll() {
		return aereoRepository.findAll();
	}

	public Aereo getById(Long id) {
		return aereoRepository.findById(id).orElse(null);
	}

	public Boolean exists(Long id) {
		return aereoRepository.existsById(id);
	}

	public void add(Long airline, String serialNumber, Long numPassengers, Long numCabin, Long numBulk) throws IllegalStateException {
		if(!compagniaAereaService.exists(airline))
			throw new IllegalStateException("Questa Compagnia Aerea non esiste!");

		if(aereoRepository.exists(Example.of(new Aereo(serialNumber))))
			throw new IllegalStateException("Questo Aereo Esiste Già!");

		Aereo aereo = new Aereo(serialNumber, numPassengers.intValue(), numCabin.intValue(), numBulk.intValue(), compagniaAereaService.getById(airline));
		aereoRepository.save(aereo);
	}
}
