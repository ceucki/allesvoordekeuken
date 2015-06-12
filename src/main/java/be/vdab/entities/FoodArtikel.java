package be.vdab.entities;

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
		this.houdbaarheid = houdbaarheid;
	}
}
