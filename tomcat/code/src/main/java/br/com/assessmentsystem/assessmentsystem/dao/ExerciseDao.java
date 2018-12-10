package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.OOJavaExercise;
import br.com.assessmentsystem.assessmentsystem.model.Solution;

public class ExerciseDao extends BaseDao {
	public Exercise findById(int id) {
		Exercise exercise = new Exercise();

		try {

			String query = "SELECT * from Exercise e where e.id = " + id;

			PreparedStatement stmt = this.connectionFactory.getConnection().prepareStatement(query);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int course = rs.getInt("course");
				//System.out.println("course:"+course);
				
				exercise = createByType(course);
				
				exercise.setId(rs.getInt("id"));
				exercise.setName(rs.getString("name"));
				exercise.setCode(rs.getString("code"));
				exercise.setStatement(rs.getString("statement"));
				exercise.setModelResponse(rs.getString("modelResponse"));
				exercise.setTests(rs.getString("tests"));
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return exercise;

	}
	
	public Exercise createByType(int tipo) {
		Exercise exercise = new Exercise();
		if(tipo == 1) {
			exercise = new Exercise();
		}
		if(tipo == 2) {
			exercise = new OOJavaExercise();
		}
		return exercise;
	}

}
