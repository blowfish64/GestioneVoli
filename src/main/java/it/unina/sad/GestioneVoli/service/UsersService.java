package it.unina.sad.GestioneVoli.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JdbcUserDetailsManager userDetailsManager;

//	@Autowired
//	private SessionRegistry sessionRegistry;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addUser(String email, String password, String role, Boolean isAdmin) throws Exception {
		if(userDetailsManager.userExists(email))
			throw new IllegalStateException("Esiste già un utente con questo nome.");

		if(password == null || password.isEmpty() || email == null || email.isEmpty())
			throw new IllegalStateException("Nome Utente o Password non validi");

		if(!"admin".equals(role) && !"user".equals(role))
			throw new IllegalStateException("Ruolo non valido!");

		if("admin".equals(role) && !isAdmin)
			throw new IllegalStateException("Non autorizzato!");

		userDetailsManager.createUser(User.withUsername(email).password(passwordEncoder.encode(password)).roles(role.toUpperCase()).build());
	}

	public Boolean resetUserPassword(String username, String newPassword) {
		if(!userDetailsManager.userExists(username))
			return false;

		userDetailsManager.changePassword(null, passwordEncoder.encode(newPassword));
		return true;
	}

	public List<UserDetails> getAllUsers() {
		return jdbcTemplate.queryForList("SELECT USERNAME FROM USERS", String.class).stream().map(userDetailsManager::loadUserByUsername).collect(Collectors.toList());
	}
}
