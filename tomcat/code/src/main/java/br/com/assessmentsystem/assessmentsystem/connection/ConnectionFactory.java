package br.com.assessmentsystem.assessmentsystem.connection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.ini4j.Wini;

public class ConnectionFactory {

	public Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		
		Wini chooserIni = new Wini(new File("/usr/local/tomcat/ini/database.ini"));
		
		String environment = chooserIni.get("database", "environment");
		
		Wini instanceIni = new Wini(new File("/usr/local/tomcat/ini/"+environment+".ini"));

		String urlIni = instanceIni.get("instance", "url"); 
		String portIni = instanceIni.get("instance", "port"); 
		
		String url = "jdbc:mysql://"+urlIni+":"+portIni+"/";
		String userName = instanceIni.get("instance", "userName");
		String password = instanceIni.get("instance", "password");
		String dbName = instanceIni.get("instance", "dbName");
		
		
		System.out.println(url);
		System.out.println(userName);
		System.out.println(password);
		System.out.println(dbName);
		
		Connection connection;
		
		Properties properties = new Properties();

		properties.setProperty("user", userName);
		properties.setProperty("password", password);
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
