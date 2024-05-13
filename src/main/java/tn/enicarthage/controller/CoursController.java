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
import tn.enicarthage.model.Cours;
import tn.enicarthage.repository.CoursRepository;


public class CoursController {
	@Autowired
	private CoursRepository repository;
	
	@GetMapping("/cours")
	public List<Cours> getAllCours(){		
		List<Cours> enseignants= new ArrayList<>();
		return repository.findAll();
		
	}
	
   @GetMapping("/cours/{id}")
	   public ResponseEntity<Cours> getCourById(@PathVariable(value = "id") Long courId)
	   throws RessourceNotFoundException {
	   Cours cour= repository.findById(courId).orElseThrow(()-> new RessourceNotFoundException("Cour not found for this id :: "+ courId));
		   return ResponseEntity.ok().body(cour);
   }

   @PostMapping("/cours")
   public Cours createCour(@Valid @RequestBody Cours cour) {
	   return repository.save(cour);
   }
   
   @DeleteMapping("/cours/{id}")
   public Map<String, Boolean> deleteCour(@PathVariable(value = "id") Long courId)
   throws RessourceNotFoundException {
	   Cours cour= repository.findById(courId)
			   .orElseThrow(()-> new RessourceNotFoundException("Cour not found for this id ::" + courId));
	  repository.delete(cour);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/cours/delete")
   public ResponseEntity<String> deleteAllCours(){
	   System.out.println("Delete All Cours...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All Cours have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/cours/{id}")
   public ResponseEntity<Cours> updateCours(@PathVariable(value = "id") Long courId,
     @Valid @RequestBody Cours courDetails) throws RessourceNotFoundException {
	   Cours cour = repository.findById(courId)
        .orElseThrow(() -> new RessourceNotFoundException("Cour not found for this id :: " + courId));

	   cour.setId(courDetails.getId());
	   cour.setTitre(courDetails.getTitre());
	   cour.setNiveau(courDetails.getNiveau());
	   cour.setFichier(courDetails.getFichier());

        
        final Cours updatedCours = repository.save(cour);
        return ResponseEntity.ok(updatedCours);
   }

}
