package tn.enicarthage.model;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name= "Bourse")
public class Bourse {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOURSE_ID")
	private long id;
	
	@Column(name = "BOURSE_TITRE")
	private String titre;
	
	@Column(name = "BOURSE_DESCRIP", length = 65555)
	private String descrip;
	
	@Column(name = "BOURSE_FILE", length = 65555)
	private String filename;

	public Bourse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Bourse(long id, String titre, String descrip, String filename) {
		super();
		this.id = id;
		this.titre = titre;
		this.descrip = descrip;
		this.filename = filename;
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



	public String getDescrip() {
		return descrip;
	}



	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}






	public String getFilename() {
		return filename;
	}



	public void setFilename(String filename) {
		this.filename = filename;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Bourse [id=" + id + ", titre=" + titre + ", descrip=" + descrip + ", filename=" + filename + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(descrip, filename, id, titre);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bourse other = (Bourse) obj;
		return Objects.equals(descrip, other.descrip) && Objects.equals(filename, other.filename) && id == other.id
				&& Objects.equals(titre, other.titre);
	}




	
	
	

}
