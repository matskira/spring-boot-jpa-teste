package io.github.matskira.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.matskira.domain.entity.Cliente;

@Repository
public class ClienteRepository {

	private static String INSERT = "INSERT INTO cliente(nome) VALUES (?)";
	private static String SELECT_ALL = "SELECT * FROM cliente";
	private static String UPDATE = "UPDATE cliente SET nome = ? WHERE id = ?";
	private static String DELETE = "DELETE FROM cliente WHERE id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Cliente salvar(Cliente cliente) {

		jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });

		return cliente;
	};
	
	public Cliente atualizar(Cliente cliente) {
		jdbcTemplate.update(UPDATE, new Object[] {
			cliente.getNome(), cliente.getId()});
		return cliente;
	}
	
	public void deletar(Cliente cliente) {
		deletar(cliente.getId());
	}
	
	public void deletar(Integer id) {
		jdbcTemplate.update(DELETE, new Object[] {
				id
		});
	}
	
	public List<Cliente> listarPorNome(String nome) {
		return jdbcTemplate.query(SELECT_ALL.concat(" WHERE nome LIKE ? "),  new Object[]{
				"%"+nome+"%"
		}, obterClienteMapper());
	};
	
	public List<Cliente> listarTodos(){
		return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
	};
	
	private RowMapper<Cliente> obterClienteMapper(){
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException{
				return new Cliente(resultSet.getInt("id"),resultSet.getString("nome"));
			}};
	};
}
