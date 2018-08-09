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
		String conditionsString = "'"+conditions.get(0).trim()+"'";
		
		for(int i = 1; i < conditions.size(); i++) {
			conditionsString += ",'"+conditions.get(i).trim()+"'";
		}
		
		try {
			ArrayList<Solution> solutions = new ArrayList<Solution>(); 
			Solution solution = new Solution();

			PreparedStatement stmt = this.connectionFactory.getConnection().prepareStatement("SELECT * from Solution s where "
					+ " s.condition in ("+conditionsString+")");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				solution.setDescription(rs.getString("statement"));
				solutions.add(solution);
			}
			rs.close();
			stmt.close();

			return solutions;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	public boolean insert(Movement movement) {
		try {
			PreparedStatement stmt = this.connectionFactory.getConnection().prepareStatement(
					"insert into Movement (exerciseId, userId," + "codeUsed, mark) values (?,?,?,?)  ");

			stmt.setInt(1, movement.getExerciseId());
			stmt.setInt(2, movement.getUserId());
			stmt.setString(3, movement.getCodeUsed());
			stmt.setDouble(4, movement.getMark());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return true;
	}

}
