package tn.enicarthage.domaine;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import tn.enicarthage.model.Cours;
import tn.enicarthage.model.Experience;
import tn.enicarthage.model.Formation;
import tn.enicarthage.model.Publication;

public class JwtResponse {
	private String accessToken;
	private Long id;
	private String username;
	private String email;
	private String role;

	  private String nom;
		private String prenom;		
		private int tel;		
		private String filename;		
		private String specialite;
		private String resume;		
		
	    private List <Formation> form;
	    private List <Experience> exp;
	    private List <Publication> pub;
	    private List <Cours> cour;

		
		public JwtResponse(String accessToken, Long id, String username, String email, String role, String nom,
				String prenom, int tel, String filename, String specialite, String resume, List<Formation> form,
				List<Experience> exp, List<Publication> pub, List<Cours> cour) {
			super();
			this.accessToken = accessToken;
			this.id = id;
			this.username = username;
			this.email = email;
			this.role = role;
			this.nom = nom;
			this.prenom = prenom;
			this.tel = tel;
			this.filename = filename;
			this.specialite = specialite;
			this.resume = resume;
			this.form = form;
			this.exp = exp;
			this.pub = pub;
			this.cour = cour;
		}
		public String getAccessToken() {
			return accessToken;
		}
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public int getTel() {
			return tel;
		}
		public void setTel(int tel) {
			this.tel = tel;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public String getSpecialite() {
			return specialite;
		}
		public void setSpecialite(String specialite) {
			this.specialite = specialite;
		}
		public String getResume() {
			return resume;
		}
		public void setResume(String resume) {
			this.resume = resume;
		}
		public List<Formation> getForm() {
			return form;
		}
		public void setForm(List<Formation> form) {
			this.form = form;
		}
		public List<Experience> getExp() {
			return exp;
		}
		public void setExp(List<Experience> exp) {
			this.exp = exp;
		}
		public List<Publication> getPub() {
			return pub;
		}
		public void setPub(List<Publication> pub) {
			this.pub = pub;
		}
		public List<Cours> getCour() {
			return cour;
		}
		public void setCour(List<Cours> cour) {
			this.cour = cour;
		}
		
	

	
	
}
