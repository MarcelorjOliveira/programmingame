package br.com.assessmentsystem.assessmentsystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.assessmentsystem.assessmentsystem.model.User;

public class JdbcUserDao extends BaseDao {

	public boolean existUser(User usuario) {
		
		if(usuario == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from Users where login = ? and password = ? limit 1");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, DigestUtils.md5Hex(usuario.getPassword()) );
			ResultSet rs = stmt.executeQuery();
			
			boolean encontrado = rs.next();
			// TODO todos os dados do usuário com hibernate
			if(encontrado == true)
				usuario.setId(rs.getInt("id"));
			

			rs.close();
			stmt.close();

			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

    public void createUser(User user) {
   		
		if(user == null) {
			throw new IllegalArgumentException("Usuário não deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("insert into Users " +
                                "(login, password) " +
                                " values (?, ?)" );
			stmt.setString(1, user.getLogin());
			stmt.setString(2, DigestUtils.md5Hex(user.getPassword()));
                        
                        stmt.execute();
                        stmt.close();
                        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
 }
}

