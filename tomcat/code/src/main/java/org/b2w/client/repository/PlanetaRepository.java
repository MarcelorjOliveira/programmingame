package org.b2w.client.repository;

import org.b2w.client.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/*
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface PlanetaRepository extends CassandraRepository<Planeta, Integer> {

}
*/

public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
	
	@Query("SELECT p FROM Planeta p WHERE p.nome = ?1")
	public Planeta planetaPeloNome(String nome);
	
}
