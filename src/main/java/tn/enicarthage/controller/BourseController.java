package tn.enicarthage.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.ServletContext;
import javax.validation.Valid;

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
import tn.enicarthage.repository.BourseRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BourseController {
	@Autowired
	private BourseRepository repository;
	@Autowired  ServletContext context;
	
	@GetMapping("/bourses")
	public List<Bourse> getAllBourse(){	
		System.out.println("get all bourses...");
		List<Bourse> bourses= new ArrayList<>();
		repository.findAll().forEach(bourses::add);
		return bourses;
		
	}
	
   @GetMapping("/bourses/{id}")
	   public ResponseEntity<Bourse> getBourseById(@PathVariable(value = "id") Long bourseId)
	   throws RessourceNotFoundException {
	   Bourse bourse= repository.findById(bourseId).orElseThrow(()-> new RessourceNotFoundException("Bourse not found for this id :: "+ bourseId));
		   return ResponseEntity.ok().body(bourse);
   }

   @PostMapping("/bourses")
  // public Bourse createBourse(@Valid @RequestBody Bourse bourse) {
	//   return repository.save(bourse);
   public ResponseEntity<Response> createBourse(@RequestParam("file") MultipartFile file,
		   @RequestParam("bourse") String bourse) throws JsonParseException, JsonMappingException, Exception{
		 System.out.println("Ok .............");
	        Bourse bours = new ObjectMapper().readValue(bourse, Bourse.class);
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

	       
	        bours.setFilename(newFileName);
	        Bourse bour = repository.save(bours);
	        if (bour != null)
	        {
	        	return new ResponseEntity<Response>(new Response (""),HttpStatus.OK);
	        }
	        else
	        {
	        	return new ResponseEntity<Response>(new Response ("Bourse not saved"),HttpStatus.BAD_REQUEST);	
	        }
   }
   
   @GetMapping(path="/Imgarticles/{id}")
	 public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
	   Bourse Bourse   = repository.findById(id).get();
		 return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+Bourse.getFilename()));
	 }
   
   @DeleteMapping("/bourses/{id}")
   public Map<String, Boolean> deleteBourse(@PathVariable(value = "id") Long bourseId)
   throws RessourceNotFoundException {
	   Bourse bourse= repository.findById(bourseId)
			   .orElseThrow(()-> new RessourceNotFoundException("Bourse not found for this id ::" + bourseId));
	  repository.delete(bourse);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/bourses/delete")
   public ResponseEntity<String> deleteAllBourses(){
	   System.out.println("Delete All Bourses...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All Bourses have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/bourses/{id}")
   public ResponseEntity<Bourse> updateBourse(@PathVariable(value = "id") Long bourseId,
     @Valid @RequestBody Bourse bourseDetails) throws RessourceNotFoundException {
	   Bourse bourse = repository.findById(bourseId)
        .orElseThrow(() -> new RessourceNotFoundException("Bourse not found for this id :: " + bourseId));

	   	bourse.setId(bourseDetails.getId());
	   	bourse.setTitre(bourseDetails.getTitre());
	   	bourse.setFilename(bourseDetails.getFilename());
        bourse.setDescrip(bourseDetails.getDescrip());

        
        final Bourse updatedBourse = repository.save(bourse);
        return ResponseEntity.ok(updatedBourse);
   }

}
