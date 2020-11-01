package ma.ensa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.ensa.entities.ComiteMembre;


@Repository
public interface ComiteMembreRepository extends JpaRepository<ComiteMembre, Long> {
	ComiteMembre findByUsername(String username);
}
