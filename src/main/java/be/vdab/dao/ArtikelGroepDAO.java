package be.vdab.dao;

import java.util.List;

import be.vdab.entities.ArtikelGroep;

public class ArtikelGroepDAO extends AbstractDAO{

	
	public ArtikelGroep read(int id) {
		return getEntityManager().find(ArtikelGroep.class, id);
	}

	public List<ArtikelGroep> findAll() {
		
		return getEntityManager().createNamedQuery("ArtikelGroep.findAll", ArtikelGroep.class).getResultList();
	}
}
