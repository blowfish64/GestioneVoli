package it.unina.sad.GestioneVoli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import it.unina.sad.GestioneVoli.repository.VoloRepository;

@Controller @RequestMapping("/")
public class HomeController implements HandlerInterceptor {

	@Autowired
	private VoloRepository voloRepository;

	@Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		request.setAttribute("principal", request.getUserPrincipal());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@GetMapping
	public String homeUser(Model model) {
		model.addAttribute("voli", voloRepository.findAll());
		return "home";
	}
}
