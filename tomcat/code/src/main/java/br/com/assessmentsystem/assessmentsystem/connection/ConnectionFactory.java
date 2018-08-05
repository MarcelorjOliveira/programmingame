package br.com.assessmentsystem.assessmentsystem.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public Connection getConnection() throws Exception {
		System.out.println("connecting ...");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
/*

		String url = "jdbc:mysql://172.18.0.3:3306/";
		String userName = "root";
		String password = "46jpw4";
		String dbName = "halyendb";
		String driver = "com.mysql.jdbc.Driver";
*/
		
		String url = "jdbc:mysql://halyeninstance.c3kdvr8hr8hf.us-west-2.rds.amazonaws.com:3306/";
		String userName = "halyeninstance";
		String password = "46jpw4195791";
		String dbName = "halyeninstance";
		String driver = "com.mysql.jdbc.Driver";
/*
		String url = "jdbc:mysql://halyencombdb.c3kdvr8hr8hf.us-west-2.rds.amazonaws.com:3306/";
		String userName = "halyen";
		String password = "46jpw4195791";
		String dbName = "halyenCombDB";
		String driver = "com.mysql.jdbc.Driver";


/*		
		Connection connection = DriverManager.getConnection("jdbc:mysql://172.31.0.2:3306/"
				+ "halyendb?user=root&password=46");
		*/
		
		Connection connection;
		
		Properties properties = new Properties();

		properties.setProperty("user", "halyeninstance");
		properties.setProperty("password","46jpw4195791");
		properties.setProperty("useUnicode","true");
		properties.setProperty("characterEncoding","utf8");
		properties.setProperty("autoReconnect", "true");
		
		int tries = 3;
		
		while(true) {
			try {
				//connection = DriverManager.getConnection(url + dbName+"&autoReconnect=true", userName, password);
				connection = DriverManager.getConnection(url + dbName, properties);
				
				//connection.prepareStatement("SET GLOBAL wait_timeout=2000").executeQuery();
				
				break;
			} catch(Exception e) {
				if(--tries == 0) throw e;
				else Thread.sleep(1000);
			}
		}
		
		return connection;
	}

}
