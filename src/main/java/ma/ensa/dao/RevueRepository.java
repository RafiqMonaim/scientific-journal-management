package ma.ensa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensa.entities.Revue;

@Repository
public interface RevueRepository extends JpaRepository<Revue, Long>{

}
