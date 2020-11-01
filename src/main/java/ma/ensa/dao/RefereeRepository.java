package ma.ensa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.entities.Referee;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Long> {
	Referee findByUsername(String username);
}
