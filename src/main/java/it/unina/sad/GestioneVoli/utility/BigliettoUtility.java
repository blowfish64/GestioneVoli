package it.unina.sad.GestioneVoli.utility;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class BigliettoUtility {
	public String generaCodiceBiglietto() {
		return (new Random())
			.ints(48, 122 + 1)
			.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			.limit(8)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();
	}
}
