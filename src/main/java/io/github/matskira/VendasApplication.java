package io.github.matskira;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.matskira.domain.entity.Cliente;
import io.github.matskira.domain.entity.Pedido;
import io.github.matskira.domain.repository.ClienteRepository;
import io.github.matskira.domain.repository.PedidoRepository;

@SpringBootApplication
public class VendasApplication {
	
	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository clienteRep,
			@Autowired PedidoRepository pedidoRep) {
		return args -> {
			System.out.println("SALVANDO CLIENTES");
			Cliente fulano = new Cliente("Matheus Poda");
			clienteRep.save(fulano);

			Pedido p = new Pedido();
			p.setCliente(fulano);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			
			pedidoRep.save(p);
			
			pedidoRep.findByCliente(fulano).forEach(System.out::println);
			
			
			List<Cliente> todosClientes = clienteRep.findAll();
			todosClientes.forEach(System.out::println);
		
			
			
			
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
