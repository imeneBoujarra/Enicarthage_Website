package tn.enicarthage.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name= "Enseignant")
public class Enseignant {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENS_ID")
	private long id;
	
	@Column(name = "ENS_NOM")
	private String nom;
	
	@Column(name = "ENS_PRENOM")
	private String prenom;
	
	@Column(name = "ENS_EMAIL")
	private String email;
	
	@Column(name = "ENS_TEL")
	private int tel;
	
	@Column(name = "ENS_FILE", length = 65555)
	private String filename;
	
	@Column(name = "ENS_SPECIALITE")
	private String specialite;
	
	@Column(name = "ENS_RESUME")
	private String resume;
	
	@Column(name = "ENS_PWD")
	private String pwd;
	
	@OneToMany(cascade = CascadeType.ALL)
    //@JsonManagedReference
	private Set<Formation> form;
		
	@OneToMany(cascade = CascadeType.ALL)
   // @JsonManagedReference
	private Set<Experience> exp;
	
	@OneToMany(cascade = CascadeType.ALL)
  //  @JsonManagedReference
	private Set<Publication> pub;
	
	@OneToMany(cascade = CascadeType.ALL)
   // @JsonManagedReference
	private Set<Cours> cour;
	
	public Enseignant() {
		super();
	}


	public Enseignant(long id, String nom, String prenom, String email, int tel, String filename, String specialite,
			String resume, String pwd, Set<Formation> form, Set<Experience> exp, Set<Publication> pub,
			Set<Cours> cour) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.filename = filename;
		this.specialite = specialite;
		this.resume = resume;
		this.pwd = pwd;
		this.form = form;
		this.exp = exp;
		this.pub = pub;
		this.cour = cour;
	}


	public Set<Formation> getForm() {
		return form;
	}


	public void setForm(Set<Formation> form) {
		this.form = form;
	}


	public Set<Experience> getExp() {
		return exp;
	}


	public void setExp(Set<Experience> exp) {
		this.exp = exp;
	}


	public Set<Publication> getPub() {
		return pub;
	}


	public void setPub(Set<Publication> pub) {
		this.pub = pub;
	}


	public Set<Cours> getCour() {
		return cour;
	}


	public void setCour(Set<Cours> cour) {
		this.cour = cour;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	@Override
	public String toString() {
		return "Enseignant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel
				+ ", filename=" + filename + ", specialite=" + specialite + ", resume=" + resume + ", pwd=" + pwd
				+ ", form=" + form + ", exp=" + exp + ", pub=" + pub + ", cour=" + cour + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cour, email, exp, filename, form, id, nom, prenom, pub, pwd, resume, specialite, tel);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enseignant other = (Enseignant) obj;
		return Objects.equals(cour, other.cour) && Objects.equals(email, other.email) && Objects.equals(exp, other.exp)
				&& Objects.equals(filename, other.filename) && Objects.equals(form, other.form) && id == other.id
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom)
				&& Objects.equals(pub, other.pub) && Objects.equals(pwd, other.pwd)
				&& Objects.equals(resume, other.resume) && Objects.equals(specialite, other.specialite)
				&& tel == other.tel;
	}


	


}
