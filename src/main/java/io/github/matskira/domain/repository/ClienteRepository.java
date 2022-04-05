package io.github.matskira.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.matskira.domain.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	List<Cliente> findByNomeContains(String nome);

	boolean existsByNome(String nome);
}
