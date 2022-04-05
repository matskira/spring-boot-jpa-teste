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
			//No caso ao colocar o Contains, ele busca na base qualquer nome que tenha os caracteres e não o específico
			clienteRep.findByNomeContains("Amanda").forEach(System.out::println);
			
			boolean existe = clienteRep.existsByNome("Amanda Costa ATUALIZADO");
			System.out.println("Existe alguém com o nome Amanda? "+existe);
			
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
