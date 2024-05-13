package tn.enicarthage.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.enicarthage.domaine.Response;
import tn.enicarthage.exception.RessourceNotFoundException;
import tn.enicarthage.model.Bourse;
import tn.enicarthage.model.Emploi;
import tn.enicarthage.repository.EmploiRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EmploiController {
	@Autowired
	private EmploiRepository repository;
	@Autowired  ServletContext contextt;
	
	@GetMapping("/emplois")	
		public List<Emploi> getAllEmploi(){	
			System.out.println("get all Emplois...");
			List<Emploi> emplois= new ArrayList<>();
			repository.findAll().forEach(emplois::add);
			return emplois;		
	}
	
   @GetMapping("/emplois/{id}")
	   public ResponseEntity<Emploi> getEmploiById(@PathVariable(value = "id") Long emploiId)
	   throws RessourceNotFoundException {
	   Emploi emploi= repository.findById(emploiId).orElseThrow(()-> new RessourceNotFoundException("Emploi not found for this id :: "+ emploiId));
		   return ResponseEntity.ok().body(emploi);
   }

   @PostMapping("/emplois")
    public ResponseEntity<Response> createEmploi(@RequestParam("file") MultipartFile file,
 		   @RequestParam("emploi") String emploi) throws JsonParseException, JsonMappingException, Exception{
 		 System.out.println("Ok .............");
 		Emploi empl = new ObjectMapper().readValue(emploi, Emploi.class);
 	        boolean isExit = new File(contextt.getRealPath("/Images/")).exists();
 	        if (!isExit)
 	        {
 	        	new File (contextt.getRealPath("/Images/")).mkdir();
 	        	System.out.println("mk dir.............");
 	        }
 	        String filename = file.getOriginalFilename();
 	        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
 	        File serverFile = new File (contextt.getRealPath("/Images/"+File.separator+newFileName));
 	        try
 	        {
 	        	System.out.println("Image");
 	        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
 	        	 
 	        }catch(Exception e) {
 	        	e.printStackTrace();
 	        }

 	       
 	        empl.setFilename(newFileName);
 	       Emploi emplo = repository.save(empl);
 	        if (emplo != null)
 	        {
 	        	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
 	        }
 	        else
 	        {
 	        	return new ResponseEntity<Response>(new Response ("Emploi not saved"),HttpStatus.BAD_REQUEST);	
 	        }
    }
   
   @GetMapping(path="/Imgemplois/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
	   Emploi Emploi = repository.findById(id).get();
		 return Files.readAllBytes(Paths.get(contextt.getRealPath("/Images/")+Emploi.getFilename()));
	 }
   
   @DeleteMapping("/emplois/{id}")
   public Map<String, Boolean> deleteEmploi(@PathVariable(value = "id") Long emploiId)
   throws RessourceNotFoundException {
	   Emploi emploi= repository.findById(emploiId)
			   .orElseThrow(()-> new RessourceNotFoundException("Emploi not found for this id ::" + emploiId));
	  repository.delete(emploi);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/emplois/delete")
   public ResponseEntity<String> deleteAllEmplois(){
	   System.out.println("Delete All Emplois...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All Emplois have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/emplois/{id}")
   public ResponseEntity<Emploi> updateEmploi(@PathVariable("id") long id, @RequestBody Emploi Emploi) {
	    System.out.println("Update Emploi with ID = " + id + "...");
	    Optional<Emploi> EmploiInfo = repository.findById(id);
	    if (EmploiInfo.isPresent()) {
	    	Emploi emploi = EmploiInfo.get();
	    	 	emploi.setId(Emploi.getId());
	  	   		emploi.setTitre(Emploi.getTitre());
	  	   		emploi.setFilename(Emploi.getFilename());
	  	   		emploi.setDescrip(Emploi.getDescrip());

	      return new ResponseEntity<>(repository.save(Emploi), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
   }
}
  /* public ResponseEntity<Emploi> updateEmploi(@PathVariable(value = "id") Long emploiId,
     @Valid @RequestBody Emploi emploiDetails) throws RessourceNotFoundException {
	   Emploi emploi = repository.findById(emploiId)
        .orElseThrow(() -> new RessourceNotFoundException("Emploi not found for this id :: " + emploiId));

	   emploi.setId(emploiDetails.getId());
	   emploi.setTitre(emploiDetails.getTitre());
	   emploi.setFilename(emploiDetails.getFilename());
	   emploi.setDescrip(emploiDetails.getDescrip());

        
        final Emploi updatedEmploi = repository.save(emploi);
        return ResponseEntity.ok(updatedEmploi);
   }*/


