package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.assessmentsystem.assessmentsystem.model.BasicExercise;
import br.com.assessmentsystem.assessmentsystem.model.Exercise;
import br.com.assessmentsystem.assessmentsystem.model.OOJavaDirectory;
import br.com.assessmentsystem.assessmentsystem.model.OOJavaExercise;

public class ExerciseDao extends BaseDao {
	public Exercise findById(int id) {
		Exercise exercise = new Exercise();

		try {
			String query = "SELECT * from Exercise e where e.id = " + id;

			getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int course = rs.getInt("course");
				//System.out.println("course:"+course);
				int useDirectoryTree = rs.getInt("useDirectoryTree");
				
				exercise = createByType(course, useDirectoryTree);
				
				exercise.setId(rs.getInt("id"));
				exercise.setName(rs.getString("name"));
				exercise.setCode(rs.getString("code"));
				exercise.setStatement(rs.getString("statement"));
				exercise.setModelResponse(rs.getString("modelResponse"));
				exercise.setTests(rs.getString("tests"));
				exercise.setUseDirectoryTree(rs.getInt("useDirectoryTree"));
			}

			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return exercise;

	}
	
	public Exercise createByType(int tipo, int useDirectoryTree) {
		Exercise exercise = new Exercise();
		if(tipo == 1) {
			exercise = new BasicExercise();
		}
		if(tipo == 2) {
			exercise = new OOJavaExercise();
		}
		
		if (useDirectoryTree == 1) { 
			exercise = new OOJavaDirectory();
		}
		return exercise;
	}

}
