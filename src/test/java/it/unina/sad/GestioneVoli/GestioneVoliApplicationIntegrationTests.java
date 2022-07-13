package it.unina.sad.GestioneVoli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

import it.unina.sad.GestioneVoli.dto.FlightBookRequestDTO;
import it.unina.sad.GestioneVoli.service.AereoService;
import it.unina.sad.GestioneVoli.service.BigliettoService;
import it.unina.sad.GestioneVoli.service.TrattaService;
import it.unina.sad.GestioneVoli.service.VoloService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class GestioneVoliApplicationIntegrationTests {

	/**
	 * 
	 * AereoService
	 * 
	 */

	@Autowired
	private AereoService aereoService;

	@Test
	void test_aereoService_getAll() {
		assertThat(aereoService.getAll()).hasSize(2);
	}

	@Test
	void test_aereoService_getById() {
		assertThat(aereoService.getById(11L)).isNotNull();
	}

	@Test
	void test_aereoService_getById_doesNotExist() {
		assertThat(aereoService.getById(1L)).isNull();
	}

	@Test
	void test_aereoService_add_airlineDoesNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				aereoService.add(0L, null, null, null, null);
			}
		}, "Questa Compagnia Aerea non esiste!");
	}

	@Test
	void test_aereoService_add_airplaneAlreadyExists() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				aereoService.add(1L, "M63001391", null, null, null);
			}
		}, "Questo Aereo Esiste Già!");
	}

	@Test
	void test_aereoService_add() {
		assertDoesNotThrow(new Executable() {
			@Override public void execute() throws Throwable {
				aereoService.add(1L, "XXXYYYYYY", 1L, 1L, 1L);
			}
		});
		assertThat(aereoService.getAll()).hasSize(3);
	}

	/**
	 * 
	 * TrattaService
	 * 
	 */

	@Autowired
	private TrattaService trattaService;

	@Test
	void test_trattaService_getByDepartureArrival_airportsDoNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.getByDepartureArrival(0L, 0L);
			}
		}, "Questi Aeroporti non esistono!");
	}

	@Test
	void test_trattaService_getByDepartureArrival_airportsMatch() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.getByDepartureArrival(3L, 3L);
			}
		}, "Partenza e Destinazione coincidono!");
	}

	@Test
	void test_trattaService_getByDepartureArrival_routeDoesNotExist() {
		assertThat(trattaService.getByDepartureArrival(3L, 7L)).isNull();
	}

	@Test
	void test_trattaService_getByDepartureArrival_routeExists() {
		assertThat(trattaService.getByDepartureArrival(3L, 6L)).isNotNull();
	}

	@Test
	void test_trattaService_add_airportsDoNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.add(0L, 0L, null, null);
			}
		}, "Questi Aeroporti non esistono!");
	}

	@Test
	void test_trattaService_add_airportsMatch() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.add(3L, 3L, null, null);
			}
		}, "Partenza e Destinazione coincidono!");
	}

	@Test
	void test_trattaService_add_durationNonNull() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.add(3L, 4L, 0L, null);
			}
		}, "La Durata Prevista non è valida!");
	}

	@Test
	void test_trattaService_add_airlineDoesNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.add(3L, 4L, 0L, Collections.singletonList(0L));
			}
		}, "Almeno una Compagnia Aerea non esiste!");
	}

	@Test
	void test_trattaService_add_routeExists() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.add(3L, 6L, 1L, Collections.singletonList(1L));
			}
		}, "Tratta Già Esistente!");
	}

	@Test
	void test_trattaService_add() {
		assertDoesNotThrow(new Executable() {
			@Override public void execute() throws Throwable {
				trattaService.add(3L, 4L, 1L, Collections.singletonList(1L));
			}
		});
		assertThat(trattaService.getAll()).hasSize(4);
	}

	/**
	 * 
	 * VoloService
	 * 
	 */

	@Autowired
	private VoloService voloService;

	@Test
	void test_voloService_add_airplaneDoesNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.add(null, null, null, 0L, null);
			}
		}, "Questo Aereo non esiste!");
	}

	@Test
	void test_voloService_add_routeDoesNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.add(null, null, null, 11L, 0L);
			}
		}, "Questa Tratta non esiste!");
	}

	@Test
	void test_voloService_add_flightCodeIsEmpty() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.add("", null, null, 11L, 8L);
			}
		}, "Codice Volo non valido!");
	}

	@Test
	void test_voloService_add_ticketPriceLessThanUnity() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.add("TESTFLIGHT", null, 0.5D, 11L, 8L);
			}
		}, "Prezzo Base non valido!");
	}

	@Test
	void test_voloService_add_invalidDateTime() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.add("TESTFLIGHT", "", 1.5D, 11L, 8L);
			}
		}, "Data/Ora Partenza non valida!");
	}

	@Test
	void test_voloService_add() {
		assertDoesNotThrow(new Executable() {
			@Override public void execute() throws Throwable {
				voloService.add("TESTFLIGHT", "2029-02-28 16:30:00", 1.5D, 11L, 8L);
			}
		});
		assertThat(voloService.getAll()).hasSize(6);
	}

	@Test
	void test_voloService_add_flightExists() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.add("XXX0001", "2022-01-03 00:00:00", 25.0D, 11L, 8L);
			}
		}, "Questo Volo esiste già!");
	}

	@Test
	void test_voloService_search_noParams() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.search(null, null, null);
			}
		});
	}

	@Test
	void test_voloService_search_oneAirport() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.search(null, 3L, null);
			}
		});
	}

	@Test
	void test_voloService_search_noRoute() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.search(null, 3L, 4L);
			}
		});
	}

	@Test
	void test_voloService_search_byRoute() {
		assertThat(voloService.search(null, 7L, 5L)).hasSize(1);
	}

	@Test
	void test_voloService_search_invalidDate() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.search("", null, null);
			}
		});
	}

	@Test
	void test_voloService_search_pastDate() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				voloService.search("1999-12-31 00:00:00", null, null);
			}
		});
	}

	@Test
	void test_voloService_search_byDate() {
		assertThat(voloService.search("2099-12-31 00:00:00", null, null)).isEmpty();
	}

	@Test
	void test_voloService_search_byDateAndRoute() {
		assertThat(voloService.search("2099-12-31 00:00:00", 7L, 5L)).isEmpty();
	}

	/**
	 * 
	 * BigliettoService
	 * 
	 */

	@Autowired
	private BigliettoService bigliettoService;

	@Test
	void test_bigliettoService_getByPassenger_doesNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				bigliettoService.getByPassenger(0L);
			}
		});
	}

	@Test
	void test_bigliettoService_getByPassenger() {
		assertThat(bigliettoService.getByPassenger(97L)).isNotEmpty();
	}

	@Test
	void test_bigliettoService_book_flightDoesNotExist() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				bigliettoService.book(null, "", null);
			}
		});
	}

	@Test
	void test_bigliettoService_book_noSpaceLeftInBulk() {
		FlightBookRequestDTO request = new FlightBookRequestDTO();
		request.setNumLuggageCabin(1L);
		request.setNumLuggageBulk(999L);
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				bigliettoService.book(Collections.singletonList(request), "XXX0001", null);
			}
		});
	}

	@Test
	void test_bigliettoService_book() {
		FlightBookRequestDTO request = new FlightBookRequestDTO();
		request.setNumLuggageCabin(1L);
		request.setNumLuggageBulk(0L);
		assertDoesNotThrow(new Executable() {
			@Override public void execute() throws Throwable {
				bigliettoService.book(Collections.singletonList(request), "XXX0001", "user");
			}
		});
	}
}
