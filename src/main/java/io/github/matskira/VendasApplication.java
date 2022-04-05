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
			System.out.println("SALVANDO CLIENTES");
			clienteRep.save(new Cliente("Matheus Poda"));
			clienteRep.save(new Cliente("Amanda Costa"));
			clienteRep.save(new Cliente("Alessandra Matos"));
			
			List<Cliente> todosClientes = clienteRep.findAll();
			todosClientes.forEach(System.out::println);
			
			System.out.println("ATUALIZANDO CLIENTES");
			todosClientes.forEach(c->{
				c.setNome(c.getNome()+" ATUALIZADO");
				clienteRep.save(c);
			});
			
			System.out.println("PROCURANDO CLIENTES");
			clienteRep.findByNomeLike("Cost").forEach(System.out::println);
			
			System.out.println("DELETANDO CLIENTES");
			clienteRep.findAll().forEach(c -> {
				clienteRep.delete(c);
			});
			
			todosClientes = clienteRep.findAll();
			if (todosClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado");
			}else {
				todosClientes.forEach(System.out::println );
			}
			
			
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
