package it.unina.sad.GestioneVoli.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerConfiguration {

	@Value("${gestionevoli.admin.defaultpassword}") private String adminDefaultPassword;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder, DataSource dataSource)
			throws Exception {
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = auth.jdbcAuthentication()
				.dataSource(dataSource);
		if (!dataSource.getConnection().getMetaData().getTables(null, "", "USERS", null).first()) {
			configurer.withDefaultSchema()
					.withUser(User.withUsername("guest").password(passwordEncoder.encode("")).roles("GUEST"))
					.withUser(User.withUsername("admin").password(passwordEncoder.encode(adminDefaultPassword)).roles("ADMIN"));
		}
	}

}
