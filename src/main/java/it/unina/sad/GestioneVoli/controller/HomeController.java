package it.unina.sad.GestioneVoli.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import it.unina.sad.GestioneVoli.dto.FlightBookRequestDTO;
import it.unina.sad.GestioneVoli.service.AeroportoService;
import it.unina.sad.GestioneVoli.service.BigliettoService;
import it.unina.sad.GestioneVoli.service.VoloService;

@Controller @RequestMapping("/")
public class HomeController implements HandlerInterceptor {

	@Autowired
	private AeroportoService aeroportoService;

	@Autowired
	private VoloService voloService;

	@Autowired
	private BigliettoService bigliettoService;

	@Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		request.setAttribute("principal", request.getUserPrincipal());
		request.setAttribute("page", request.getServletPath());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@GetMapping("/flights")
	public String flights(Model model, @RequestParam(required = false) String flightDateTime, @RequestParam(required = false) Long departureAirport, @RequestParam(required = false) Long arrivalAirport) {
		if(flightDateTime != null || departureAirport != null || arrivalAirport != null)
			try {
				model.addAttribute("flights", voloService.search(flightDateTime, departureAirport, arrivalAirport));
				model.addAttribute("success", true);
			} catch(IllegalStateException e) {
				model.addAttribute("error", e.getMessage());
				model.addAttribute("success", false);
			}
		model.addAttribute("airports", aeroportoService.getAll());
		return "home";
	}

	@GetMapping("/flights/book")
	public String bookFlight(Model model, @RequestParam String flight) {
		model.addAttribute("flight", voloService.getById(flight));
		return "home";
	}

	@PostMapping("/flights/book")
	public String confirmBooking(HttpServletRequest request, Model model, @RequestBody List<FlightBookRequestDTO> passengerForm, @RequestParam String flight) {
		bigliettoService.book(passengerForm, flight, request.getUserPrincipal().getName());
		return "home";
	}

	@GetMapping
	public String homeUser(Model model) {
		return "home";
	}
}
