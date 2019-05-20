package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.assessmentsystem.assessmentsystem.model.Course;
import br.com.assessmentsystem.assessmentsystem.model.Exercise;

public class CourseDao extends BaseDao {
	public List<Course> courseWithExercises() {
		List<Course> courses = new ArrayList<Course>(); 
		ExerciseDao exerciseDao = new ExerciseDao();
		try {

			String query = "SELECT Course.id as Course_id, Exercise.id as Exercise_id, Course.name as Course_name"
				+" from Course inner join Exercise on Course.id = Exercise.course order by Course.id";

			getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();

			Course course = new Course();
			
			while (rs.next()) {
				//System.out.println("Courseid"+rs.getInt("Course_id"));
				//System.out.println("Courseid"+rs.getInt("Exercise_id"));
				
				if(course.getId() != rs.getInt("Course_id")) {
					course = fillCourse(rs);
					
					courses.add(course);
					course.setExercises(new ArrayList<Exercise>());
				}
				
				Exercise exercise = exerciseDao.findById(rs.getInt("Exercise_id"));
				
				course.getExercises().add(exercise);
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
		return courses;
	}
	
	public Course fillCourse(ResultSet rs) throws SQLException {
		Course course = new Course();
		course.setId(rs.getInt("Course_id"));
		course.setName(rs.getString("Course_name"));
		return course;
	}
	
	}
