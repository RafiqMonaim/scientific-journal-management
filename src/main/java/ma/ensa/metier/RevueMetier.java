package ma.ensa.metier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.ensa.dao.ComiteMembreRepository;
import ma.ensa.dao.RevueRepository;
import ma.ensa.entities.ComiteMembre;
import ma.ensa.entities.Revue;
import ma.ensa.exceptions.BadRequestException;
import ma.ensa.security.dao.AppUserRepository;
import ma.ensa.security.entities.Roles;
import ma.ensa.security.service.AccountService;


@Service
@Transactional
public class RevueMetier {
	@Autowired 
	AppUserRepository appUserRepository;
	@Autowired
	AccountService accountService;
	@Autowired
	ComiteMembreRepository membreComiteRepository;
	@Autowired
	RevueRepository revueRepository;
	
	public ComiteMembre addComiteMembre(String usrName,String nom, String prenom, Long RevueID) {
		ComiteMembre  membre = new ComiteMembre(usrName, nom, prenom);
		if (appUserRepository.findByUsername(membre.getUsername()) == null) {
			throw new BadRequestException("Cannot not find the username");
		}
		membre.setRevue(getRevue(RevueID));
		if (membreComiteRepository.findByUsername(membre.getUsername()) == null) {
			accountService.addRoleToUser(usrName, Roles.MEMBRE_COMITE);
		}else {
			throw new BadRequestException("There's already a membre");
		}
		return membreComiteRepository.save(membre);
	}
	
	public Revue getRevue(Long id) {
		if (revueRepository.findById(id).isPresent()) {
			return revueRepository.findById(id).get();
		}else {
			throw new BadRequestException("we could not find the revue");
		}
	}
	
	public Revue addRevue(String politique) {
		Revue revue = new Revue(politique);
		return revueRepository.save(revue);
	}
	
	
	
}
