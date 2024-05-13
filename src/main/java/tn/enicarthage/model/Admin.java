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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name= "Admin")
public class Admin {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADMIN_ID")
	private long id;
	
	@Column(name = "ADMIN_NOM")
	private String nom;
	
	@Column(name = "ADMIN_PRENOM")
	private String prenom;
	
	@Column(name = "ADMIN_EMAIL")
	private String email;
	
	@Column(name = "ADMIN_TEL")
	private int tel;
	
	@Column(name = "ADMIN_PWD")
	private String pwd;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
	private Set<Bourse> bourses;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
	private Set<Emploi> emplois;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Admin(long id, String nom, String prenom, String email, int tel, String pwd, Set<Bourse> bourses,
			Set<Emploi> emplois) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.pwd = pwd;
		this.bourses = bourses;
		this.emplois = emplois;
	}





	public Set<Bourse> getBourses() {
		return bourses;
	}

	public void setBourses(Set<Bourse> bourses) {
		this.bourses = bourses;
	}

	public Set<Emploi> getEmplois() {
		return emplois;
	}

	public void setEmplois(Set<Emploi> emplois) {
		this.emplois = emplois;
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
		return "Admin [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel
				+ ", pwd=" + pwd + ", bourses=" + bourses + ", emplois=" + emplois + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bourses, email, emplois, id, nom, prenom, pwd, tel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(bourses, other.bourses) && Objects.equals(email, other.email)
				&& Objects.equals(emplois, other.emplois) && id == other.id && Objects.equals(nom, other.nom)
				&& Objects.equals(prenom, other.prenom) && Objects.equals(pwd, other.pwd) && tel == other.tel;
	}


	
	
	
}
