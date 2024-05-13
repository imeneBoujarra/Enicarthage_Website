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
import tn.enicarthage.model.Publication;
import tn.enicarthage.repository.PublicationRepository;


public class PublicationController {
	@Autowired
	private PublicationRepository repository;
	
	@GetMapping("/publications")
	public List<Publication> getAllPublication(){		
		List<Publication> publications= new ArrayList<>();
		return repository.findAll();
		
	}
	
   @GetMapping("/publications/{id}")
	   public ResponseEntity<Publication> getPublicationById(@PathVariable(value = "id") Long publicationId)
	   throws RessourceNotFoundException {
	   Publication publication= repository.findById(publicationId).orElseThrow(()-> new RessourceNotFoundException("Publication not found for this id :: "+ publicationId));
		   return ResponseEntity.ok().body(publication);
   }

   @PostMapping("/publications")
   public Publication createPublication(@Valid @RequestBody Publication publication) {
	   return repository.save(publication);
   }
   
   @DeleteMapping("/publications/{id}")
   public Map<String, Boolean> deletePublication(@PathVariable(value = "id") Long publicationId)
   throws RessourceNotFoundException {
	   Publication publication= repository.findById(publicationId)
			   .orElseThrow(()-> new RessourceNotFoundException("Publication not found for this id ::" + publicationId));
	  repository.delete(publication);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/publications/delete")
   public ResponseEntity<String> deleteAllPublications(){
	   System.out.println("Delete All Publications...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All Publications have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/enseignants/{id}")
   public ResponseEntity<Publication> updatePublication(@PathVariable(value = "id") Long publicationId,
     @Valid @RequestBody Publication publicationDetails) throws RessourceNotFoundException {
	   Publication publication = repository.findById(publicationId)
        .orElseThrow(() -> new RessourceNotFoundException("publication not found for this id :: " + publicationId));

	   publication.setId(publicationDetails.getId());
	   publication.setTitre(publicationDetails.getTitre());
	   publication.setPub(publicationDetails.getPub());
        
        final Publication updatedPublication = repository.save(publication);
        return ResponseEntity.ok(updatedPublication);
   }

}
