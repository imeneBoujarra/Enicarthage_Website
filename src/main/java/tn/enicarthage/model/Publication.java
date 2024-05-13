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
@Table(name= "Publication")
public class Publication {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PUB_ID")
	private long id;
	
	@Column(name = "PUB_TITRE")
	private String titre;
	
	@Column(name = "PUB_PUB")
	private String pub;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
	
	public Publication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Publication(long id, String titre, String pub) {
		super();
		this.id = id;
		this.titre = titre;
		this.pub = pub;
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

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Publication [id=" + id + ", titre=" + titre + ", pub=" + pub + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pub, titre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		return id == other.id && Objects.equals(pub, other.pub) && Objects.equals(titre, other.titre);
	}

	
	

}
