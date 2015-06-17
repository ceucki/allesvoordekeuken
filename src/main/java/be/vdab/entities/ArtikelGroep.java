package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artikelgroepen")
 
public class ArtikelGroep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String naam;

	@OneToMany(mappedBy = "artikelGroep")
	private Set<Artikel> artikelen;

	public Set<Artikel> getArtikelen() {
		return Collections.unmodifiableSet(artikelen);
	}

	public int getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ArtikelGroep)) {
			return false;
		}
		ArtikelGroep andereArtikelgroep = (ArtikelGroep) object;
		return naam.equalsIgnoreCase(andereArtikelgroep.naam);
	}

	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}

}
