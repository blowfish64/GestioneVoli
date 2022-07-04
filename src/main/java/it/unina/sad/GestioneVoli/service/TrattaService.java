package it.unina.sad.GestioneVoli.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import it.unina.sad.GestioneVoli.entity.Aeroporto;
import it.unina.sad.GestioneVoli.entity.Tratta;
import it.unina.sad.GestioneVoli.repository.TrattaRepository;

@Service
public class TrattaService {

	@Autowired
	private TrattaRepository trattaRepository;

	@Autowired
	private AeroportoService aeroportoService;

	@Autowired
	private CompagniaAereaService compagniaAereaService;

	public List<Tratta> getAll() {
		return trattaRepository.findAll();
	}

	public Tratta getById(Long id) {
		return trattaRepository.findById(id).orElse(null);
	}

	public Tratta getByDepartureArrival(Long departure, Long arrival) throws IllegalStateException {
		if(!aeroportoService.exists(departure) || !aeroportoService.exists(arrival))
			throw new IllegalStateException("Questi Aeroporti non esistono!");

		if(departure.equals(arrival))
			throw new IllegalStateException("Partenza e Destinazione coincidono!");

		Aeroporto partenza = aeroportoService.get(departure), destinazione = aeroportoService.get(arrival);
		return trattaRepository.findByAeroportoPartenzaAndAeroportoDestinazione(partenza, destinazione);
	}

	public void add(Long departure, Long arrival, Long flightMinutes, List<Long> airlines) throws IllegalStateException {
		if(!aeroportoService.exists(departure) || !aeroportoService.exists(arrival))
			throw new IllegalStateException("Questi Aeroporti non esistono!");

		if(departure.equals(arrival))
			throw new IllegalStateException("Partenza e Destinazione coincidono!");

		if(new Long(0).equals(flightMinutes))
			throw new IllegalStateException("La Durata Prevista non è valida!");

		if(!airlines.stream().allMatch(compagniaAereaService::exists))
			throw new IllegalStateException("Almeno una Compagnia Aerea non esiste!");

		Aeroporto partenza = aeroportoService.get(departure), destinazione = aeroportoService.get(arrival);
		Tratta tratta = new Tratta(null, partenza, destinazione, null);

		if(trattaRepository.exists(Example.of(tratta))) {
			throw new IllegalStateException("Tratta Già Esistente!");
		} else {
			tratta.setDurataPrevistaMinuti(flightMinutes);
			tratta.setCompagnieAeree(airlines.stream().map(compagniaAereaService::getById).collect(Collectors.toList()));
			trattaRepository.save(tratta);
		}
	}

	public Boolean exists(Long route) {
		return trattaRepository.existsById(route);
	}
}
