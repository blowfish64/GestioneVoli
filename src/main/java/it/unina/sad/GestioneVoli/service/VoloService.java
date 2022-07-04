package it.unina.sad.GestioneVoli.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unina.sad.GestioneVoli.entity.Aereo;
import it.unina.sad.GestioneVoli.entity.Tratta;
import it.unina.sad.GestioneVoli.entity.Volo;
import it.unina.sad.GestioneVoli.repository.VoloRepository;

@Service
public class VoloService {

	@Autowired
	private VoloRepository voloRepository;

	@Autowired
	private AereoService aereoService;

	@Autowired
	private TrattaService trattaService;

	@Autowired AeroportoService aeroportoService;

	public List<Volo> getAll() {
		return voloRepository.findAll();
	}

	public void add(String code, String flightDateTime, Double ticketPrice, Long airplane, Long route) throws IllegalStateException {
		if(!aereoService.exists(airplane))
			throw new IllegalStateException("Questo Aereo non esiste!");

		if(!trattaService.exists(route))
			throw new IllegalStateException("Questa Tratta non esiste!");

		if(code.isEmpty())
			throw new IllegalStateException("Codice Volo non valido!");

		if(ticketPrice < 1.0D)
			throw new IllegalStateException("Prezzo Base non valido!");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp dataOraPartenza = null;

		try {
			dataOraPartenza = new Timestamp(df.parse(flightDateTime).getTime());
		} catch(ParseException e) {
			throw new IllegalStateException("Data/Ora Partenza non valida!");
		}

		if(voloRepository.existsById(code))
			throw new IllegalStateException("Questo Volo esiste già!");

		Aereo aereo = aereoService.getById(airplane);
		Tratta tratta = trattaService.getById(route);
		Volo volo = new Volo(code, dataOraPartenza, ticketPrice, aereo.getCapienzaPasseggeri(), aereo.getCapienzaBagagliCabina(), aereo.getCapienzaBagagliStiva(), aereo, tratta);
		voloRepository.save(volo);
	}

	public List<Volo> search(String flightDateTime, Long departureAirport, Long arrivalAirport) throws IllegalStateException {
		Tratta tratta = null;
		if(departureAirport != null && arrivalAirport != null) {
			if(!aeroportoService.exists(departureAirport) || !aeroportoService.exists(arrivalAirport))
				throw new IllegalStateException("Partenza o Destinazione non valida!");

			tratta = trattaService.getByDepartureArrival(departureAirport, arrivalAirport);
			if(tratta == null)
				throw new IllegalStateException("Gli Aeroporti non sono collegati!");
		} else if((departureAirport == null) != (arrivalAirport == null))
			throw new IllegalStateException("Tratta Incompleta!");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp dataOraPartenza = null;

		if(flightDateTime != null && !flightDateTime.isEmpty())
			try {
				Date d = df.parse(flightDateTime);
				if(d.before(new Date()))
					throw new IllegalStateException("Data/Ora Partenza già passata!");
				else
					dataOraPartenza = new Timestamp(d.getTime());
			} catch(ParseException e) {
				throw new IllegalStateException("Data/Ora Partenza non valida!");
			}

		if(tratta != null && dataOraPartenza == null)
			return voloRepository.findByTratta(tratta);
		else if(tratta == null && dataOraPartenza != null)
			return voloRepository.findByDataOraPartenzaGreaterThanEqual(dataOraPartenza);
		else if(tratta != null && dataOraPartenza != null)
			return voloRepository.findByTrattaAndDataOraPartenzaGreaterThanEqual(tratta, dataOraPartenza);
		else
			throw new IllegalStateException("Non è stato indicato nessun parametro di ricerca!");
	}
}
