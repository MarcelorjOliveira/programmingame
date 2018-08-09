package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.assessmentsystem.assessmentsystem.connection.ConnectionFactory;

public class BaseDao {

	protected ConnectionFactory connectionFactory;
	
	public BaseDao() {
		try {
			connectionFactory = new ConnectionFactory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
