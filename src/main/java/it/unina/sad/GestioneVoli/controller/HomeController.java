package it.unina.sad.GestioneVoli.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.unina.sad.GestioneVoli.repository.VoloRepository;

@Controller
public class HomeController {

	@Autowired
	private VoloRepository voloRepository;

	@GetMapping("/")
	public String home(Principal principal, Model model) {
		model.addAttribute("principal", principal);
		model.addAttribute("voli", voloRepository.findAll());
		return "home";
	}

}
