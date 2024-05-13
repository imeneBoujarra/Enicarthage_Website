package tn.enicarthage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.enicarthage.exception.RessourceNotFoundException;
import tn.enicarthage.model.Experience;
import tn.enicarthage.repository.ExperienceRepository;


public class ExperienceController {
	@Autowired
	private ExperienceRepository repository;
	
	@GetMapping("/experiences")
	public List<Experience> getAllExperience(){		
		List<Experience> experiences= new ArrayList<>();
		return repository.findAll();
		
	}
	
   @GetMapping("/experiences/{id}")
	   public ResponseEntity<Experience> getExperienceById(@PathVariable(value = "id") Long experienceId)
	   throws RessourceNotFoundException {
	   Experience experience= repository.findById(experienceId).orElseThrow(()-> new RessourceNotFoundException("Experience not found for this id :: "+ experienceId));
		   return ResponseEntity.ok().body(experience);
   }

   @PostMapping("/experiences")
   public Experience createExperience(@Valid @RequestBody Experience experience) {
	   return repository.save(experience);
   }
   
   @DeleteMapping("/experiences/{id}")
   public Map<String, Boolean> deleteExperience(@PathVariable(value = "id") Long experienceId)
   throws RessourceNotFoundException {
	   Experience experience= repository.findById(experienceId)
			   .orElseThrow(()-> new RessourceNotFoundException("Experience not found for this id ::" + experienceId));
	  repository.delete(experience);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/experiences/delete")
   public ResponseEntity<String> deleteAllExperiences(){
	   System.out.println("Delete All experiences...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All Experiences have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/experiences/{id}")
   public ResponseEntity<Experience> updateExperience(@PathVariable(value = "id") Long experienceId,
     @Valid @RequestBody Experience experienceDetails) throws RessourceNotFoundException {
	   Experience experience = repository.findById(experienceId)
        .orElseThrow(() -> new RessourceNotFoundException("experience not found for this id :: " + experienceId));

	   experience.setTitre(experienceDetails.getTitre());
	   experience.setEcole(experienceDetails.getEcole());
	   experience.setPeriode(experienceDetails.getPeriode());
	   experience.setId(experienceDetails.getId());
       

        
        final Experience updatedExperience = repository.save(experience);
        return ResponseEntity.ok(updatedExperience);
   }

}
