package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.valueobjects.Korting;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name = "soort")
public abstract class Artikel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	
	@ElementCollection
	@CollectionTable(name = "kortingen", joinColumns = @JoinColumn(name = "artikelid"))
	@OrderBy("artikelid")
	private Set<Korting> kortingen;

	protected Artikel() {
	};

	public Set<Korting> getKortingen() {
		return Collections.unmodifiableSet(kortingen);
	}

	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
		kortingen = new LinkedHashSet<>();
	}

	public static boolean isNaamValid(String naam) {
		return naam != null && !naam.isEmpty();
	}

	public static boolean isAankoopprijsValid(BigDecimal aankoopprijs) {
		return aankoopprijs != null
				&& aankoopprijs.compareTo(BigDecimal.ZERO) >= 0;
	}

	public static boolean isVerkoopprijsValid(BigDecimal verkoopprijs) {
		return verkoopprijs != null
				& verkoopprijs.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public static boolean isVerkoopprijsGroterDanAankoopprijs(BigDecimal aankoopprijs, BigDecimal verkoopprijs){
		return verkoopprijs.compareTo(aankoopprijs) >=0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public  BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}

	public void setAankoopprijs(BigDecimal aankoopprijs) {
		if (! isAankoopprijsValid(aankoopprijs)){
			throw new IllegalArgumentException();
		}
		this.aankoopprijs = aankoopprijs;
	}

	public BigDecimal getVerkoopprijs() {
		if (! isVerkoopprijsValid(verkoopprijs)){
			throw new IllegalArgumentException();
		}
		return verkoopprijs;
	}

	public void setVerkoopprijs(BigDecimal verkoopprijs) {
		this.verkoopprijs = verkoopprijs;
	}

	public BigDecimal getWinstPercentage() {
		return verkoopprijs.subtract(aankoopprijs)
				.divide(aankoopprijs, 2, RoundingMode.HALF_UP)
				.multiply(BigDecimal.valueOf(100));
	}
}
