package tn.enicarthage.model;

import java.io.File;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "Cours")
public class Cours {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUR_ID")
	private long id;
	
	@Column(name = "COUR_TITRE")
	private String titre;
	
	@Column(name = "COUR_NIVEAU")
	private String niveau;
	
	@Column(name = "COUR_FICHIER")
	private String fichier;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
	
	public Cours() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cours(long id, String titre, String niveau, String fichier) {
		super();
		this.id = id;
		this.titre = titre;
		this.niveau = niveau;
		this.fichier = fichier;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cours [id=" + id + ", titre=" + titre + ", niveau=" + niveau + ", fichier=" + fichier + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fichier, id, niveau, titre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cours other = (Cours) obj;
		return Objects.equals(fichier, other.fichier) && id == other.id && Objects.equals(niveau, other.niveau)
				&& Objects.equals(titre, other.titre);
	}

	
	

}
