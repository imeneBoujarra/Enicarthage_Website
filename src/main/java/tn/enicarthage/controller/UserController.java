package tn.enicarthage.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.domaine.JwtResponse;
import tn.enicarthage.exception.RessourceNotFoundException;
import tn.enicarthage.model.Cours;
import tn.enicarthage.model.Enseignant;
import tn.enicarthage.model.Experience;
import tn.enicarthage.model.Formation;
import tn.enicarthage.model.Publication;
import tn.enicarthage.model.User;
import tn.enicarthage.repository.UserRepository;
import tn.enicarthage.request.SigninRequest;
import tn.enicarthage.security.JwtTokenUtil;
import tn.enicarthage.service.MyUserDetails;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired 	UserRepository repository;
	@Autowired 	AuthenticationManager authenticationManager;

	@Autowired	UserRepository userRepository;

	@Autowired	PasswordEncoder encoder;

	@Autowired	JwtTokenUtil jwtUtils;
	
	@Autowired  ServletContext context;


	 @GetMapping("/users")
	  public List<User> getAllUtilisateur() {
	    System.out.println("Get all Utilisateur...");
	 
	    List<User> Utilisateur = new ArrayList<>();
	    repository.findAll().forEach(Utilisateur::add);
	 
	    return Utilisateur;
	  }
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUtilisateurById(@PathVariable(value = "id") Long UtilisateurId)
			throws RessourceNotFoundException {
		User Utilisateur = repository.findById(UtilisateurId)
				.orElseThrow(() -> new RessourceNotFoundException("Utilisateur not found for this id :: " + UtilisateurId));
		return ResponseEntity.ok().body(Utilisateur);
	}

	 
	
	
	
		@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUtilisateur(@PathVariable(value = "id") Long UtilisateurId)
			throws RessourceNotFoundException {
		User Utilisateur = repository.findById(UtilisateurId)
				.orElseThrow(() -> new RessourceNotFoundException("Utilisateur not found  id :: " + UtilisateurId));

		repository.delete(Utilisateur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/users/delete")
	  public ResponseEntity<String> deleteAllUtilisateur() {
	    System.out.println("Delete All Utilisateur...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Utilisateurs have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/users/{id}")
	  public ResponseEntity<User> updateUtilisateur(@PathVariable("id") long id, @RequestBody User Utilisateur) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	 
	    Optional<User> UtilisateurInfo = repository.findById(id);
	 
	    if (UtilisateurInfo.isPresent()) {
	    	User utilisateur = UtilisateurInfo.get();
	    	utilisateur.setRole(Utilisateur.getRole());
	    	utilisateur.setEmail(Utilisateur.getEmail());
	    	utilisateur.setUsername(Utilisateur.getUsername());
	    	utilisateur.setPassword(Utilisateur.getPassword());
	    	
	    	utilisateur.setNom(Utilisateur.getNom());
	    	utilisateur.setForm(Utilisateur.getForm());
	    	utilisateur.setExp(Utilisateur.getExp());
	          utilisateur.setPub(Utilisateur.getPub());
	          utilisateur.setCour(Utilisateur.getCour());
	          utilisateur.setPrenom(Utilisateur.getPrenom());
	          utilisateur.setTel(Utilisateur.getTel());
	          utilisateur.setFilename(Utilisateur.getFilename());
	          utilisateur.setSpecialite(Utilisateur.getSpecialite());
	          utilisateur.setResume(Utilisateur.getResume());
	          
	      return new ResponseEntity<>(repository.save(Utilisateur), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	  public ResponseEntity<?> loginUser(@Valid @RequestBody SigninRequest data){
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							data.getUsername(),
							data.getPassword())	
					);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
					
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();		
			System.out.println(jwt);
			System.out.println(userDetails.getUsername());
			System.out.println(userDetails.getRole());
			return ResponseEntity.ok(new JwtResponse(jwt, 
															userDetails.getId(), 
															userDetails.getUsername(), 
															userDetails.getEmail(), 
															userDetails.getRole(),
															userDetails.getNom(),
															userDetails.getPrenom(),
															userDetails.getTel(),
															userDetails.getFilename(),
															userDetails.getSpecialite(),
															userDetails.getResume(),
															userDetails.getForm(),
															userDetails.getExp(),
															userDetails.getPub(),
															userDetails.getCour())); 
		  }
	  
	  
	  @GetMapping(path="/Imguser/{id}")
		 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		   User User = userRepository.findById(id).get();
			 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+User.getFilename()));
		 }
	/*
		@PostMapping("/users/login")
		public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
			System.out.println("aaaa");
			System.out.println(data.getPassword());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							data.getUsername(),
							data.getPassword())
				
					);
			  System.out.println("bbbbb");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			

			return ResponseEntity.ok(new JwtResponse(jwt, 
													 userDetails.getId(), 
													 userDetails.getUsername(), 
													 userDetails.getEmail(), 
													 userDetails.getRole()));
		}

		@PostMapping("/users")
		public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Username is already taken!"));
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity
						.badRequest()
						.body(new Message("Error: Email is already in use!"));
			}

			// Create new user's account
			User user = new User(signUpRequest.getUsername(), 
								 signUpRequest.getEmail(),
								 encoder.encode(signUpRequest.getPassword()),
										 signUpRequest.getRole()		 );
			userRepository.save(user);

			return ResponseEntity.ok(new Message("User registered successfully!"));
 	*/
}
