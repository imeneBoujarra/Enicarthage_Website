package tn.enicarthage.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Utilisateur",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"
				+ ""),
		@UniqueConstraint(columnNames = "email") 
	})
public class User {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	  @NotBlank
		@Size(max = 20)
	  private String username;
	  @NotBlank
	  @Size(max = 20)
	  @Email
	  private String email;
	  private String password;
	  private String role;
	  private String nom;
		private String prenom;		
		private int tel;		
		private String filename;		
		private String specialite;
		private String resume;		
		
	/*	@OneToMany(cascade = CascadeType.ALL)
		private Set<Formation> form;
			
		@OneToMany(cascade = CascadeType.ALL)
		private Set<Experience> exp;
		
		@OneToMany(cascade = CascadeType.ALL)
		private Set<Publication> pub;
		
		@OneToMany(cascade = CascadeType.ALL)
		private Set<Cours> cour;
		*/
		@OneToMany(mappedBy = "user", cascade = {
		        CascadeType.ALL
		    })
		    private List < Formation > form;
		
		@OneToMany(mappedBy = "user", cascade = {
		        CascadeType.ALL
		    })
		    private List < Experience > exp;
		
		@OneToMany(mappedBy = "user", cascade = {
		        CascadeType.ALL
		    })
		    private List < Cours > cour;
		
		@OneToMany(mappedBy = "user", cascade = {
		        CascadeType.ALL
		    })
		    private List < Publication > pub;
		
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public List<Cours> getCour() {
		return cour;
	}
	public void setCour(List<Cours> cour) {
		this.cour = cour;
	}
	public List<Publication> getPub() {
		return pub;
	}
	public void setPub(List<Publication> pub) {
		this.pub = pub;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
				+ role + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", filename=" + filename
				+ ", specialite=" + specialite + ", resume=" + resume + ", form=" + form + ", exp=" + exp + ", pub="
				+ pub + ", cour=" + cour + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 20) @Email String email,
			String password, String role, String nom, String prenom, int tel, String filename, String specialite,
			String resume, List<Formation> form, List<Experience> exp, List<Cours> cour, List<Publication> pub) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.filename = filename;
		this.specialite = specialite;
		this.resume = resume;
		this.form = form;
		this.exp = exp;
		this.cour = cour;
		this.pub = pub;
	}
	
	
}
