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
@Table(name= "Experience")
public class Experience {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXP_ID")
	private long id;
	
	@Column(name = "EXP_TITRE")
	private String titre;
	
	@Column(name = "EXP_ECOLE")
	private String ecole;
	
	@Column(name = "EXP_PERIODE")
	private String periode;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User user;
	
	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Experience(long id, String titre, String ecole, String periode) {
		super();
		this.id = id;
		this.titre = titre;
		this.ecole = ecole;
		this.periode = periode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Experience [id=" + id + ", titre=" + titre + ", ecole=" + ecole + ", periode=" + periode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ecole, id, periode, titre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Experience other = (Experience) obj;
		return Objects.equals(ecole, other.ecole) && id == other.id && Objects.equals(periode, other.periode)
				&& Objects.equals(titre, other.titre);
	}
	
	

}
