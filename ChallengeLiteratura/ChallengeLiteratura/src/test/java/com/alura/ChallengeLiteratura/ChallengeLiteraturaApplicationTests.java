package com.alura.ChallengeLiteratura;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.marcelobaranowski.LiterAluraMB.principal.Principal;
import com.marcelobaranowski.LiterAluraMB.repository.AutorRepository;
import com.marcelobaranowski.LiterAluraMB.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootTest
class ChallengeLiteraturaApplicationTests {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraMbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal(libroRepository, autorRepository);
		principal.mostrarMenuOpciones();

}
