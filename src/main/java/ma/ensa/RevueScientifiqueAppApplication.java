package ma.ensa;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.ensa.dao.ArticleAuteurRepository;
import ma.ensa.dao.ArticleRepository;
import ma.ensa.dao.AuteurRepository;
import ma.ensa.dao.ComiteMembreRepository;
import ma.ensa.dao.CommentaireRepository;
import ma.ensa.dao.RefereeRepository;
import ma.ensa.dao.RevueRepository;
import ma.ensa.metier.ArticleMetier;
import ma.ensa.security.dao.AppRoleRepository;
import ma.ensa.security.dao.AppUserRepository;
import ma.ensa.security.service.AccountService;
@SpringBootApplication
@ComponentScan(basePackages = { "ma.ensa" })
public class RevueScientifiqueAppApplication extends SpringBootServletInitializer  {
	/* implements CommandLineRunner*/
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private AuteurRepository auteurRepo;
	@Autowired
	private RefereeRepository refereeRepo;
	@Autowired 
	private RevueRepository revueRepo;
	@Autowired 
	private CommentaireRepository commentRepo;
	@Autowired 
	private ComiteMembreRepository comitemembreRepo;
	@Autowired 
	private ArticleAuteurRepository articleAuteurRepo;
	@Autowired 
	private AppUserRepository appUserRepo;
	@Autowired 
	private AppRoleRepository appRoleRepo;
	@Autowired 
	private ArticleMetier service;
	@Autowired 
	private AccountService accountService;
	
	public static void main(String[] args) {
		SpringApplication.run(RevueScientifiqueAppApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
	}
	public void run(String... args) throws Exception {
		/*
		Auteur auteur = new Auteur("Jhon", "Doe", "Stanford University", null);
		Auteur auteuR = auteurRepo.save(auteur);
		Article article = articleRepo.getPostedArticles().get(0);
		ArticleAuteur auteurAssocie = new ArticleAuteur(true, article, auteuR);
		articleAuteurRepo.save(auteurAssocie);
		*/
	}

}
