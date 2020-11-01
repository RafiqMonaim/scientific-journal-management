package ma.ensa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ma.ensa.security.entities.AppUser;
import ma.ensa.security.entities.Roles;
import ma.ensa.security.service.AccountService;

public class UserController {
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/users")
	public AppUser signUp(@RequestBody RegistrationForm data) {
		return accountService.signUp(data);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/roles")
	public void associateUserWithRole(@RequestParam String usrName,@RequestParam Roles role) {
	accountService.addRoleToUser(usrName, role);
}
}
