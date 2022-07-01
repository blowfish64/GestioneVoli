package it.unina.sad.GestioneVoli.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerConfiguration {

	@Autowired
	public void configure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder, DataSource dataSource)
	  throws Exception {
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = auth.jdbcAuthentication()
	      .dataSource(dataSource);
	    if (!dataSource.getConnection().getMetaData().getTables(null, "", "USERS", null).first()) {
	    	configurer
	    		.withDefaultSchema()
	    		.withUser(User.withUsername("user")
	    	        .password(passwordEncoder.encode("pass"))
	    	        .roles("USER"));
	    }
	}

}
