package ma.ensa.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ensa.security.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	AppUser findByUsername(String usrName);
}
