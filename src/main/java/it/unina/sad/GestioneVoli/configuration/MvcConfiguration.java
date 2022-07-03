package it.unina.sad.GestioneVoli.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.unina.sad.GestioneVoli.controller.HomeController;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
	/*
	 * @Override public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addViewController("/login").setViewName("login"); }
	 */

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HomeController());
		WebMvcConfigurer.super.addInterceptors(registry);
	}
}
