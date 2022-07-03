package it.unina.sad.GestioneVoli.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resource/**", "/img/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.permitAll()
				.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
					@Override public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						if(authentication.getAuthorities().stream().anyMatch(q -> q.getAuthority().equals("ROLE_ADMIN")) && !request.getContextPath().startsWith("/admin"))
							response.sendRedirect("/admin");
						else
							super.onAuthenticationSuccess(request, response, authentication);
					}
				})
		.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.deleteCookies("SESSION")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/login?logout")
				.permitAll()
		.and()
			.rememberMe()
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(60 * 60 * 24 * 7)
		.and()
			.csrf()
				.ignoringAntMatchers("/h2/**")
		.and()
			.headers()
				.frameOptions()
				.sameOrigin()
		.and()
			.sessionManagement()
				.maximumSessions(100)
				.sessionRegistry(sessionRegistry());
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/h2/**");
	}

	@Bean
	public SessionRegistry sessionRegistry() { 
	    return new SessionRegistryImpl(); 
	}

	@Bean
	public JdbcUserDetailsManager users() {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}