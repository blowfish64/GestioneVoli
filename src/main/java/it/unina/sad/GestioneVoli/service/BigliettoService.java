package it.unina.sad.GestioneVoli.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unina.sad.GestioneVoli.dto.FlightBookRequestDTO;
import it.unina.sad.GestioneVoli.entity.Biglietto;
import it.unina.sad.GestioneVoli.entity.Passeggero;
import it.unina.sad.GestioneVoli.entity.Volo;
import it.unina.sad.GestioneVoli.repository.BigliettoRepository;
import it.unina.sad.GestioneVoli.utility.BigliettoUtility;

@Service
public class BigliettoService {

	@Autowired
	private BigliettoRepository bigliettoRepository;

	@Autowired
	private BigliettoUtility bigliettoUtility;

	@Autowired
	private VoloService voloService;

	@Autowired
	private PasseggeroService passeggeroService;

	@Transactional
	public void book(List<FlightBookRequestDTO> requests, String flight, String loggedInUser) throws IllegalStateException {
		Volo volo = voloService.getById(flight);
		if(volo == null)
			throw new IllegalStateException("Questo Volo non esiste!");

		List<Biglietto> biglietti = requests.stream().map(r -> {
			Biglietto biglietto = new Biglietto();
			Date giornoValidita = new Date(volo.getDataOraPartenza().getTime());
			String codiceBiglietto;
			do {
				codiceBiglietto = bigliettoUtility.generaCodiceBiglietto();
			} while(bigliettoRepository.existsByCodiceIdentificativoAndGiornoValidità(codiceBiglietto, giornoValidita));
			biglietto.setCodiceIdentificativo(codiceBiglietto);
			biglietto.setGiornoValidità(giornoValidita);
			biglietto.setDataOraEmissione(new Timestamp(Instant.now().toEpochMilli()));
			biglietto.seteMailPasseggero(r.getConfirmEMail());
			biglietto.setNumeroCartaCredito(r.getCreditCard());
			biglietto.setPostoPasseggero(voloService.allocateSeat(volo));
			biglietto.setNumeroBagagliCabina(voloService.allocateLuggageCabin(volo, r.getNumLuggageCabin().intValue()));
			biglietto.setNumeroBagagliStiva(voloService.allocateLuggageBulk(volo, r.getNumLuggageBulk().intValue()));
			biglietto.setCostoTotale(volo.getPrezzoBase() + Math.max(biglietto.getNumeroBagagliCabina() - 1, 0) * 10 + biglietto.getNumeroBagagliStiva() * 20);
			biglietto.setVolo(volo);
			biglietto.setPasseggero(passeggeroService.getOrAdd(r.getFirstName(), r.getFamilyName(), r.getConfirmEMail(), r.getCardId(), r.getUniqueId(), loggedInUser));
			return biglietto;
		}).collect(Collectors.toList());

		bigliettoRepository.saveAll(biglietti);
	}

	public List<Biglietto> getByPassenger(Long id) {
		Passeggero passeggero = passeggeroService.getById(id);
		if(passeggero == null)
			throw new IllegalStateException("Questo Passeggero non esiste!");
		else
			return bigliettoRepository.findByPasseggero(passeggero);
	}
}
