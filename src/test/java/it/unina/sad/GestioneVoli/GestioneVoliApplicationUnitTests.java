package it.unina.sad.GestioneVoli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

import it.unina.sad.GestioneVoli.service.AeroportoService;
import it.unina.sad.GestioneVoli.service.CompagniaAereaService;
import it.unina.sad.GestioneVoli.service.PasseggeroService;
import it.unina.sad.GestioneVoli.service.UsersService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class GestioneVoliApplicationUnitTests {

	/**
	 * 
	 * UsersService
	 * 
	 */

	@Autowired
	private UsersService usersService;

	@Autowired
	private UserDetailsManager userDetails;

	@Test
	void test_usersService_getAllUsers() {
		assertThat(usersService.getAllUsers())
			.containsAll(Arrays.asList(
					userDetails.loadUserByUsername("admin"),
					userDetails.loadUserByUsername("guest"),
					userDetails.loadUserByUsername("user"))
			);
	}

	@Test
	void test_usersService_addUser_userExists() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				usersService.addUser("user", null, null, null);
			}
		}, "Esiste già un utente con questo nome.");
	}

	@Test
	void test_usersService_addUser_blankPassword() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				usersService.addUser("test", null, null, null);
			}
		}, "Nome Utente o Password non validi");
	}

	@Test
	void test_usersService_addUser_invalidRole() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				usersService.addUser("test", "test", "mock", null);
			}
		}, "Ruolo non valido!");
	}

	@Test
	void test_usersService_addUser_nonAdminCreatesAdmin() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				usersService.addUser("test", "test", "admin", false);
			}
		}, "Non autorizzato!");
	}

	@Test
	void test_usersService_addUser_add() {
		assertDoesNotThrow(new Executable() {
			@Override public void execute() throws Throwable {
				usersService.addUser("test", "test", "admin", true);
			}
		});
	}

	/**
	 * 
	 * AeroportoService
	 * 
	 */

	@Autowired
	private AeroportoService aeroportoService;

	@Test
	void test_aeroportoService_getAll() {
		assertThat(aeroportoService.getAll()).hasSize(5);
	}

	@Test
	void test_aeroportoService_add() {
		assertDoesNotThrow(new Executable() {
			@Override public void execute() throws Throwable {
				aeroportoService.add("Gino Lisa", "Foggia");
			}
		});
		assertThat(aeroportoService.getAll()).hasSize(6);
	}

	@Test
	void test_aeroportoService_add_throws() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				aeroportoService.add("Linate", "Milano");
			}
		});
	}

	/**
	 * 
	 * CompagniaAereaService
	 * 
	 */

	@Autowired
	private CompagniaAereaService compagniaAereaService;

	@Test
	void test_compagniaAereaService_getAll() {
		assertThat(compagniaAereaService.getAll()).extracting("nome").containsAll(Arrays.asList("RyanAir", "EasyJet"));
	}

	@Test
	void test_compagniaAereaService_add_doesNotThrow() {
		assertDoesNotThrow(new Executable() {
			@Override public void execute() throws Throwable {
				compagniaAereaService.add("Wizz Air");
			}
		});
		assertThat(compagniaAereaService.getAll()).hasSize(3);
	}

	@Test
	void test_compagniaAereaService_add_throws_alreadyExists() {
		assertThrows(IllegalStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				compagniaAereaService.add("RyanAir");
			}
		});
	}

	/**
	 * 
	 * PasseggeroService
	 * 
	 */

	@Autowired
	private PasseggeroService passeggeroService;

	@Test
	void test_passeggeroService_getFromUser() {
		assertThat(passeggeroService.getFromUser("user")).isNotEmpty();
	}

	@Test
	void test_passeggeroService_getOrAdd_throws_noTransaction() {
		assertThrows(IllegalTransactionStateException.class, new Executable() {
			@Override public void execute() throws Throwable {
				passeggeroService.getOrAdd(null, null, null, null, null, null);
			}
		});
	}

	@Test @Transactional
	void test_passeggeroService_getOrAdd_get() {
		assertThat(passeggeroService.getOrAdd("Xxx", "Xxx", null, "XX", "XX", "user")).isNotNull().matches(t -> "xxx@studenti.unina.it".equals(t.geteMail()));
	}

	@Test @Transactional
	void test_passeggeroService_getOrAdd_add() {
		assertThat(passeggeroService.getOrAdd("User", "User", "user@mail.com", "XX", "XX", "user")).isNotNull().matches(t -> t.getId() > 193);
	}

	@Test
	void test_passeggeroService_getById_exists() {
		assertThat(passeggeroService.getById(193L)).isNotNull();
	}

	@Test
	void test_passeggeroService_getById_doesNotExist() {
		assertThat(passeggeroService.getById(9L)).isNull();
	}
}
