package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.assessmentsystem.assessmentsystem.model.Movement;
import br.com.assessmentsystem.assessmentsystem.model.Solution;

public class SolutionDao extends BaseDao {

	public List<Solution> getSolutions(List<String> conditions) {
		ArrayList<Solution> solutions = new ArrayList<Solution>();

		if (conditions.size() > 0) {

			String conditionsString = "'" + conditions.get(0).trim() + "'";

			for (int i = 1; i < conditions.size(); i++) {
				conditionsString += ",'" + conditions.get(i).trim() + "'";
			}

			try {
				Solution solution;

				String query = "SELECT * from Solution s where " + " s.test in (" + conditionsString + ")";

				getConnection();
				
				PreparedStatement stmt = connection.prepareStatement(query);

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					solution = new Solution();
					solution.setDescription(rs.getString("description"));
					solutions.add(solution);
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
		}
		return solutions;

	}

	public boolean insert(Movement movement) {
		try {
			getConnection();
			
			PreparedStatement stmt = connection.prepareStatement(
					"insert into Movement (exerciseId, userId," + "codeUsed, mark) values (?,?,?,?)  ");

			stmt.setInt(1, movement.getExerciseId());
			stmt.setInt(2, movement.getUserId());
			stmt.setString(3, movement.getCodeUsed());
			stmt.setDouble(4, movement.getMark());

			stmt.execute();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return true;
	}

	public List<Solution> getByIdExercise(int idExercise) {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		
		try {
			Solution solution;

			getConnection();
			
			String query = "SELECT * from Solution s where s.idExercise = "+Integer.toString(idExercise);
			
			PreparedStatement stmt = connection.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				solution = new Solution();
				solution.setIdSolution(rs.getInt("idSolution"));
				solution.setTest(rs.getString("test"));
				solution.setIdExercise(rs.getInt("idExercise"));
				solution.setDescription(rs.getString("description"));
				
				solutions.add(solution);
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
		return solutions;

	}

}
