package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.assessmentsystem.assessmentsystem.connection.ConnectionFactory;

public class BaseDao {

	protected Connection connection;
	
	public BaseDao() {
		try {
			connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
