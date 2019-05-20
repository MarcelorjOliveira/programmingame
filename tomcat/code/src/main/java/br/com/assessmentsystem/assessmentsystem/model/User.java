package br.com.assessmentsystem.assessmentsystem.model;

import java.lang.reflect.Field;

public class User {
	public int id;
	private String login;
	private String password;
	
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		String result = "";
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i=0; i<fields.length; i++)
		{
		    try {
				result += fields[i].getName() + " - " + fields[i].get(this);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
