package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;

public class ExerciseChooserDao extends BaseDao {
	
	public Exercise chooseExercise(int user) {
		
		try {
		Exercise exercise = new Exercise();
		
		PreparedStatement stmt = this.connectionFactory.getConnection().prepareStatement("SELECT * from Exercise e where e.id not in"+
		        "(SELECT exerciseId from Movement m where m.userId = "+user+")");
		
		//stmt = this.connectionFactory.getConnection().prepareStatement("SELECT * from Exercise e where e.id = 1");
		        
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			exercise.setId(rs.getInt("id"));
			exercise.setName(rs.getString("name"));
			exercise.setStatement(rs.getString("statement"));
			exercise.setModelResponse(rs.getString("modelResponse"));
			exercise.setTests(rs.getString("tests"));
			exercise.setCode(rs.getString("code"));
		}
		rs.close();
		stmt.close();

		return exercise;
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	}

}
