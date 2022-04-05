package io.github.matskira.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.matskira.domain.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
