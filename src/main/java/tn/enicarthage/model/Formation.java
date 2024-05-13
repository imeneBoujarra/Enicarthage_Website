package tn.enicarthage.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "Formation")
public class Formation {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FORM_ID")
	private long id;
	
	@Column(name = "FORM_NOM")
	private String nom;
	
	@Column(name = "FORM_ECOLE")
	private String ecole;
	
	@Column(name = "FORM_PERIODE")
	private String periode;
	
	@Column(name = "FORM_CONTENU")
	private String contenu;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
	
	

	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Formation(long id, String nom, String ecole, String periode, String contenu, User user) {
		super();
		this.id = id;
		this.nom = nom;
		this.ecole = ecole;
		this.periode = periode;
		this.contenu = contenu;
		this.user = user;
	}







	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
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



	public String getEcole() {
		return ecole;
	}



	public void setEcole(String ecole) {
		this.ecole = ecole;
	}



	public String getPeriode() {
		return periode;
	}



	public void setPeriode(String periode) {
		this.periode = periode;
	}



	public String getContenu() {
		return contenu;
	}



	public void setContenu(String contenu) {
		this.contenu = contenu;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Formation [id=" + id + ", nom=" + nom + ", ecole=" + ecole + ", periode=" + periode + ", contenu="
				+ contenu + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(contenu, ecole, id, nom, periode);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formation other = (Formation) obj;
		return Objects.equals(contenu, other.contenu) && Objects.equals(ecole, other.ecole) && id == other.id
				&& Objects.equals(nom, other.nom) && Objects.equals(periode, other.periode);
	}
	
	
	

}
