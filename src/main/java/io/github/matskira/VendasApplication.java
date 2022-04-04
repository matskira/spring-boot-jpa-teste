package io.github.matskira;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.matskira.domain.entity.Cliente;
import io.github.matskira.domain.repository.ClienteRepository;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRep) {
		return args -> {
			clienteRep.salvar(new Cliente("Matheus Poda"));
			clienteRep.salvar(new Cliente("Amanda Costa"));
			clienteRep.salvar(new Cliente("Alessandra Matos"));
			
			List<Cliente> todosClientes = clienteRep.listarTodos();
			todosClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
