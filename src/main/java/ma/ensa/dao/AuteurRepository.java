package ma.ensa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.ensa.entities.Auteur;


@Repository
public interface AuteurRepository  extends JpaRepository<Auteur, Long> {
	Auteur findByUsername(String username);
}
