package br.com.assessmentsystem.assessmentsystem.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException {
		System.out.println("connecting ...");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		
		String url = "jdbc:mysql://halyendb.c3kdvr8hr8hf.us-west-2.rds.amazonaws.com:3306/";
		String userName = "halyendb";
		String password = "46jpw4195791";
		String dbName = "halyendb";
		String driver = "com.mysql.jdbc.Driver";
		Connection connection = DriverManager.getConnection(url + dbName, userName, password);
		
		return connection;
	}

}
