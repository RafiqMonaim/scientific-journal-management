package ma.ensa.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.ensa.security.entities.AppRole;
import ma.ensa.security.entities.Roles;


public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	AppRole findByRole(Roles roleName);
}
