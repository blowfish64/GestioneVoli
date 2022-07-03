package it.unina.sad.GestioneVoli.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import it.unina.sad.GestioneVoli.service.AereoService;
import it.unina.sad.GestioneVoli.service.AeroportoService;
import it.unina.sad.GestioneVoli.service.CompagniaAereaService;
import it.unina.sad.GestioneVoli.service.TrattaService;
import it.unina.sad.GestioneVoli.service.UsersService;
import it.unina.sad.GestioneVoli.service.VoloService;

@Controller @RequestMapping("/admin")
public class AdminController {

	/* Compagnie Aeree */

	@Autowired
	private CompagniaAereaService compagniaAereaService;

	@GetMapping("/airlines")
	public String homeAirlines(HttpServletRequest request, Model model) {
		model.addAttribute("airlines", compagniaAereaService.getAll());

		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@PostMapping("/airlines")
	public String addAirline(HttpServletRequest request, Model model, @RequestParam String airlineName) {
		model.addAttribute("post", true);
		try {
			compagniaAereaService.add(airlineName);
			model.addAttribute("success", true);
		} catch (IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("success", false);
		}

		model.addAttribute("airlines", compagniaAereaService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@DeleteMapping("/airlines")
	public ResponseEntity<Void> deleteAirline(HttpServletRequest request, @RequestParam Long id) {
		if(compagniaAereaService.delete(id))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.badRequest().build();
	}

	/* Aeroporti */

	@Autowired
	private AeroportoService aeroportoService;

	@GetMapping("/airports")
	public String homeAirports(HttpServletRequest request, Model model) {
		model.addAttribute("airports", aeroportoService.getAll());

		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@PostMapping("/airports")
	public String addAirport(HttpServletRequest request, Model model, @RequestParam String airportName, @RequestParam String airportCity) {
		model.addAttribute("post", true);
		try {
			aeroportoService.add(airportName, airportCity);
			model.addAttribute("success", true);
		} catch (IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("success", false);
		}

		model.addAttribute("airports", aeroportoService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@DeleteMapping("/airports")
	public ResponseEntity<Void> deleteAirport(HttpServletRequest request, @RequestParam Long id) {
		if(aeroportoService.delete(id))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.badRequest().build();
	}

	/* Tratte */

	@Autowired
	private TrattaService trattaService;

	@GetMapping("/routes")
	public String routes(HttpServletRequest request, Model model) {
		model.addAttribute("routes", trattaService.getAll());
		model.addAttribute("airports", aeroportoService.getAll());
		model.addAttribute("airlines", compagniaAereaService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@PostMapping("/routes")
	public String addRoute(HttpServletRequest request, Model model, @RequestParam Long departureAirport, @RequestParam Long arrivalAirport, @RequestParam Long flightMinutes, @RequestParam List<Long> airlines) {
		model.addAttribute("post", true);

		try {
			trattaService.add(departureAirport, arrivalAirport, flightMinutes, airlines);
			model.addAttribute("success", true);
		} catch(IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("success", false);
		}

		model.addAttribute("routes", trattaService.getAll());
		model.addAttribute("airports", aeroportoService.getAll());
		model.addAttribute("airlines", compagniaAereaService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	// TODO Eliminazione Tratte

	// Aerei

	@Autowired
	private AereoService aereoService;

	@GetMapping("/airplanes")
	public String airplanes(HttpServletRequest request, Model model) {
		model.addAttribute("airlines", compagniaAereaService.getAll());
		model.addAttribute("airplanes", aereoService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@PostMapping("/airplanes")
	public String addRoute(HttpServletRequest request, Model model, @RequestParam Long airline, @RequestParam String serialNumber, @RequestParam Long numPassengers, @RequestParam Long numCabin, @RequestParam Long numBulk) {
		model.addAttribute("post", true);

		try {
			aereoService.add(airline, serialNumber, numPassengers, numCabin, numBulk);
			model.addAttribute("success", true);
		} catch(IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("success", false);
		}

		model.addAttribute("airlines", compagniaAereaService.getAll());
		model.addAttribute("airplanes", aereoService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	// TODO Eliminazione Aerei

	/* Voli */

	@Autowired
	private VoloService voloService;

	@GetMapping("/flights")
	public String flights(HttpServletRequest request, Model model) {
		model.addAttribute("flights", voloService.getAll());
		model.addAttribute("airplanes", aereoService.getAll());
		model.addAttribute("routes", trattaService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@PostMapping("/flights")
	public String addFlight(HttpServletRequest request, Model model, @RequestParam String code, @RequestParam String flightDateTime, @RequestParam Double ticketPrice, @RequestParam Long airplane, @RequestParam Long route) {
		model.addAttribute("post", true);

		try {
			voloService.add(code, flightDateTime, ticketPrice, airplane, route);
			model.addAttribute("success", true);
		} catch(IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("success", false);
		}

		model.addAttribute("flights", voloService.getAll());
		model.addAttribute("airplanes", aereoService.getAll());
		model.addAttribute("routes", trattaService.getAll());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	// TODO Eliminazione Voli

	/* Utenti */

	@Autowired
	private UsersService usersService;

	@GetMapping("/users")
	public String users(HttpServletRequest request, Model model) {
		model.addAttribute("users", usersService.getAllUsers());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	//authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch("ROLE_ADMIN"::equals)
	@PostMapping("/users")
	public String usersAdd(HttpServletRequest request, Model model, @RequestParam String email, @RequestParam String password, @RequestParam String role) {
		model.addAttribute("post", true);
		try {
			usersService.addUser(email, password, role, true);
			model.addAttribute("success", true);
		} catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("success", false);
		}

		model.addAttribute("users", usersService.getAllUsers());
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

	@PutMapping("/users")
	public ResponseEntity<Void> resetPassword(HttpServletRequest request, Model model, @RequestPart String username, @RequestPart String password) {
		if(usersService.resetUserPassword(username, password))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.internalServerError().build();
	}

	@GetMapping("/**")
	public String homeAdmin(HttpServletRequest request, Model model) {
		model.addAttribute("page", request.getServletPath());
		return "admin";
	}

}
