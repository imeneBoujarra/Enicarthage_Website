package tn.enicarthage.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.enicarthage.model.Cours;
import tn.enicarthage.model.Experience;
import tn.enicarthage.model.Formation;
import tn.enicarthage.model.Publication;
import tn.enicarthage.model.User;

public class MyUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private Long id;

	private String username;


	
	@JsonIgnore
	private String password;
	private String role;
	
	  private String nom;
			private String prenom;		
			private int tel;		
			private String filename;		
			private String specialite;
			private String resume;		
			
			private List<Formation> form;
			private List<Experience> exp;
			private List<Publication> pub;
			private List<Cours> cour;
			
	/*private boolean isActive;*/
	private List<GrantedAuthority> authorities;



	public MyUserDetails(Long id, String username, String email, String password, String role, String nom,
			String prenom, int tel, String filename, String specialite, String resume, List<Formation> form,
			List<Experience> exp, List<Publication> pub, List<Cours> cour, List<GrantedAuthority> authorities) {
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
		this.pub = pub;
		this.cour = cour;
		this.authorities = authorities;
	}



	public MyUserDetails(User user) {
				this.id = user.getId();
				this.username =user.getUsername();
				this.password= user.getPassword();
				this.role =user.getRole();
				this.email = user.getEmail();
				this.nom = user.getNom();
				this.prenom = user.getPrenom();
				this.tel = user.getTel();
				this.filename = user.getFilename();
				this.specialite = user.getSpecialite();
				this.resume = user.getResume();
				this.form = user.getForm();
				this.exp = user.getExp();
				this.pub = user.getPub();
				this.cour = user.getCour();
				
				
				this.authorities = Arrays.stream(user.getRole().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
	}



	/*public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}*/


	public List<GrantedAuthority> getAutorities() {
		return authorities;
	}


	public void setAutorities(List<GrantedAuthority> autorities) {
		this.authorities = autorities;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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



	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

	
}
