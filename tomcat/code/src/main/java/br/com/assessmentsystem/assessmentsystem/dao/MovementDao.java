package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.assessmentsystem.assessmentsystem.model.Movement;

public class MovementDao extends BaseDao {
	
	public boolean insert(Movement movement) {
		try {
		getConnection();
		
		PreparedStatement stmt = connection.prepareStatement("insert into Movement (exerciseId, userId,"
		+ "codeUsed, mark) values (?,?,?,?)  ");
		
		stmt.setInt(1, movement.getExerciseId());
		stmt.setInt(2,  movement.getUserId());
		stmt.setString(3, movement.getCodeUsed());
		stmt.setDouble(4, movement.getMark());
			
		stmt.execute();
		stmt.close();
		connection.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return true;
	}
	
}
