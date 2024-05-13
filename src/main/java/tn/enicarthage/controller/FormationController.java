package tn.enicarthage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.exception.RessourceNotFoundException;
import tn.enicarthage.model.Formation;
import tn.enicarthage.repository.FormationRepository;
import tn.enicarthage.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FormationController {

	@Autowired
	private FormationRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*@GetMapping("/users/{userId}/formations")
    public List < Formation > getFormationByUser(@PathVariable(value = "userId") Long userId) {
        return repository.findByUserId(userId);
    }
	
	@PostMapping("/users/{userId}/formations")
    public Formation createFormation(@PathVariable(value = "userId") Long userId,
        @Valid @RequestBody Formation formation) throws RessourceNotFoundException {
        return userRepository.findById(userId).map(user -> {
        	formation.setUser(user);
            return repository.save(formation);
        }).orElseThrow(() -> new RessourceNotFoundException("user not found"));
    }
	
	@PutMapping("/users/{userId}/formations/{formationId}")
    public Formation updateFormation(@PathVariable(value = "userId") Long userId,
        @PathVariable(value = "formationId") Long formationId, @Valid @RequestBody Formation formationRequest)
    throws RessourceNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new RessourceNotFoundException("userId not found");
        }

        Formation formation = repository.findById(formationId)
        .orElseThrow(() -> new RessourceNotFoundException("formation id not found"));
        formation.setId(formationRequest.getId());
        formation.setNom(formationRequest.getNom());
        formation.setEcole(formationRequest.getEcole());
        formation.setPeriode(formationRequest.getPeriode());
        formation.setContenu(formationRequest.getContenu());

        return repository.save(formation);
    }

	   
	@DeleteMapping("\"/users/{userId}/formations/{formationId}")
    public ResponseEntity < ? > deleteFormation(@PathVariable(value = "userId") Long userId,
        @PathVariable(value = "formationId") Long formationId) throws RessourceNotFoundException {
        return repository.findByIdAndUserId(formationId, userId).map(formation -> {
            repository.delete(formation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RessourceNotFoundException(
            "Course not found with id " + formationId + " and userId " + userId));
    }*/
	
	
	@GetMapping("/formations")
	public List<Formation> getAllFormation(){		
		List<Formation> formations= new ArrayList<>();
		return repository.findAll();
		
	}
	
   @GetMapping("/formations/{id}")
	   public ResponseEntity<Formation> getFormationById(@PathVariable(value = "id") Long formationId)
	   throws RessourceNotFoundException {
	   Formation formation= repository.findById(formationId).orElseThrow(()-> new RessourceNotFoundException("Formation not found for this id :: "+ formationId));
		   return ResponseEntity.ok().body(formation);
   }

   @PostMapping("/formations")
   public Formation createFormation(@Valid @RequestBody Formation formation) {
	   return repository.save(formation);
   }
   
   @DeleteMapping("/formations/{id}")
   public Map<String, Boolean> deleteFormation(@PathVariable(value = "id") Long formationId)
   throws RessourceNotFoundException {
	   Formation formation= repository.findById(formationId)
			   .orElseThrow(()-> new RessourceNotFoundException("Formation not found for this id ::" + formationId));
	  repository.delete(formation);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/formations/delete")
   public ResponseEntity<String> deleteAllFormations(){
	   System.out.println("Delete All Formations...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All Formations have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/formations/{id}")
   public ResponseEntity<Formation> updateFormation(@PathVariable(value = "id") Long formationId,
     @Valid @RequestBody Formation formationDetails) throws RessourceNotFoundException {
	   Formation formation = repository.findById(formationId)
        .orElseThrow(() -> new RessourceNotFoundException("Formation not found for this id :: " + formationId));

	   
        formation.setId(formationDetails.getId());
        formation.setNom(formationDetails.getNom());
        formation.setEcole(formationDetails.getEcole());
        formation.setPeriode(formationDetails.getPeriode());
        formation.setContenu(formationDetails.getContenu());

        final Formation updatedFormation = repository.save(formation);
        return ResponseEntity.ok(updatedFormation);
   }
}
