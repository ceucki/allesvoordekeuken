package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int houdbaarheid;

	public int getHoudbaarheid() {
		return houdbaarheid;
	}

	public void setHoudbaarheid(int houdbaarheid) {
		if(! isHoudbaarheidValid(houdbaarheid)){
			throw new IllegalArgumentException();
		}
		this.houdbaarheid = houdbaarheid;
	}
	
	public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs,ArtikelGroep artikelgroep, int houdbaarheid){
		super(naam, aankoopprijs, verkoopprijs,artikelgroep);
		setHoudbaarheid(houdbaarheid);				
	}
	protected FoodArtikel(){};
	
	public static boolean isHoudbaarheidValid(int houdbaarheid){
		return houdbaarheid >= 1;		
	}
}
