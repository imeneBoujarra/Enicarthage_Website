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
import tn.enicarthage.model.Emploi;
import tn.enicarthage.model.Enseignant;
import tn.enicarthage.repository.EnseignantRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class EnseignantController {

	@Autowired
	private EnseignantRepository repository;
	@Autowired  ServletContext context;

	
	@GetMapping("/enseignants")
	public List<Enseignant> getAllEnseignant(){		
		List<Enseignant> enseignants= new ArrayList<>();
		return repository.findAll();
		
	}
	
   @GetMapping("/enseignants/{id}")
	   public ResponseEntity<Enseignant> getEnseignantById(@PathVariable(value = "id") Long enseignantId)
	   throws RessourceNotFoundException {
	   Enseignant enseignant= repository.findById(enseignantId).orElseThrow(()-> new RessourceNotFoundException("enseignant not found for this id :: "+ enseignantId));
		   return ResponseEntity.ok().body(enseignant);
   }

   @GetMapping("/enseignants/5/{email}")
	  public   ResponseEntity<Enseignant> getEnseignantByEmail(@PathVariable String email) 
		  throws RessourceNotFoundException {
	   Enseignant Enseignant = repository.findByEmail(email)
				  .orElseThrow(() -> new RessourceNotFoundException(" Enseignant not found for this id : "));
		   return ResponseEntity.ok().body(Enseignant);
	  }
   
   @PostMapping("/enseignants")
   public ResponseEntity<Response> createEnseignant(@RequestParam("file") MultipartFile file,
		   @RequestParam("enseignant") String enseignant) throws JsonParseException, JsonMappingException, Exception{
		 System.out.println("Ok .............");
		 Enseignant empl = new ObjectMapper().readValue(enseignant, Enseignant.class);
	        boolean isExit = new File(context.getRealPath("/Images/")).exists();
	        if (!isExit)
	        {
	        	new File (context.getRealPath("/Images/")).mkdir();
	        	System.out.println("mk dir.............");
	        }
	        String filename = file.getOriginalFilename();
	        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
	        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
	        try
	        {
	        	System.out.println("Image");
	        	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
	        	 
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }

	       
	        empl.setFilename(newFileName);
	        Enseignant emplo = repository.save(empl);
	        if (emplo != null)
	        {
	        	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
	        }
	        else
	        {
	        	return new ResponseEntity<Response>(new Response ("enseignant not saved"),HttpStatus.BAD_REQUEST);	
	        }
   }
   
   @GetMapping(path="/Imgemp/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
	   Enseignant Enseignant = repository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Enseignant.getFilename()));
	 }
   
   @DeleteMapping("/enseignants/{id}")
   public Map<String, Boolean> deleteEnseignant(@PathVariable(value = "id") Long enseignantId)
   throws RessourceNotFoundException {
	   Enseignant enseignant= repository.findById(enseignantId)
			   .orElseThrow(()-> new RessourceNotFoundException("enseignant not found for this id ::" + enseignantId));
	  repository.delete(enseignant);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/enseignants/delete")
   public ResponseEntity<String> deleteAllEnseignants(){
	   System.out.println("Delete All enseignants...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All enseignants have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/enseignants/{id}")
   public ResponseEntity<Enseignant> updateEnseignant(@PathVariable(value = "id") Long enseignantId,
     @Valid @RequestBody Enseignant enseignantDetails) throws RessourceNotFoundException {
	   Enseignant enseignant = repository.findById(enseignantId)
        .orElseThrow(() -> new RessourceNotFoundException("enseignant not found for this id :: " + enseignantId));

        enseignant.setNom(enseignantDetails.getNom());
        enseignant.setForm(enseignantDetails.getForm());
        enseignant.setExp(enseignantDetails.getExp());
        enseignant.setPub(enseignantDetails.getPub());
        enseignant.setCour(enseignantDetails.getCour());
        enseignant.setId(enseignantDetails.getId());
        enseignant.setPrenom(enseignantDetails.getPrenom());
        enseignant.setEmail(enseignantDetails.getEmail());
        enseignant.setTel(enseignantDetails.getTel());
        enseignant.setFilename(enseignantDetails.getFilename());
        enseignant.setSpecialite(enseignantDetails.getSpecialite());
        enseignant.setResume(enseignantDetails.getResume());
        enseignant.setPwd(enseignantDetails.getPwd());
        
        final Enseignant updatedEnseignant = repository.save(enseignant);
        return ResponseEntity.ok(updatedEnseignant);
   }


	
}
