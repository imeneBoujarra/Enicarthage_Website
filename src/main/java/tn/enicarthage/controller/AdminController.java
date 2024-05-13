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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.enicarthage.exception.RessourceNotFoundException;
import tn.enicarthage.model.Admin;
import tn.enicarthage.repository.AdminRepository;

@RestController
@RequestMapping("/api")
public class AdminController {
	
	@Autowired
	private  AdminRepository repository;
	
	@GetMapping("/admins")
	public List<Admin> getAllAdmins(){		
		List<Admin> admins= new ArrayList<>();
		return repository.findAll();
		
	}
	
   @GetMapping("/admins/{id}")
	   public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") Long adminId)
	   throws RessourceNotFoundException {
	   Admin admin= repository.findById(adminId).orElseThrow(()-> new RessourceNotFoundException("Admin not found for this id :: "+ adminId));
		   return ResponseEntity.ok().body(admin);
   }

   @PostMapping("/admins")
   public Admin createAdmin(@Valid @RequestBody Admin admin) {
	   return repository.save(admin);
   }
   
   @DeleteMapping("/admins/{id}")
   public Map<String, Boolean> deleteAdmin(@PathVariable(value = "id") Long adminId)
   throws RessourceNotFoundException {
	   Admin admin= repository.findById(adminId)
			   .orElseThrow(()-> new RessourceNotFoundException("Admin not found for this id ::" + adminId));
	  repository.delete(admin);
	   Map<String, Boolean> response = new HashMap<>();
	   response.put("deleted", Boolean.TRUE);
			   return response;
}
   
   @DeleteMapping("/admins/delete")
   public ResponseEntity<String> deleteAllAdmins(){
	   System.out.println("Delete All Admins...");
	   repository.deleteAll();
	   return new ResponseEntity<>("All Admins have been deleted:", HttpStatus.OK);
}
   
   @PutMapping("/admins/{id}")
   public ResponseEntity<Admin> updateAdmin(@PathVariable(value = "id") Long adminId,
     @Valid @RequestBody Admin adminDetails) throws RessourceNotFoundException {
	   Admin admin = repository.findById(adminId)
        .orElseThrow(() -> new RessourceNotFoundException("Admin not found for this id :: " + adminId));

	   admin.setId(adminDetails.getId());
	   admin.setNom(adminDetails.getNom());
	   admin.setPrenom(adminDetails.getPrenom());
	   admin.setEmail(adminDetails.getEmail());
	   admin.setTel(adminDetails.getTel());
	   admin.setPwd(adminDetails.getPwd());

        final Admin updatedAdmin = repository.save(admin);
        return ResponseEntity.ok(updatedAdmin);
   }



}
