package be.vdab.services;

import java.util.List;

import be.vdab.dao.ArtikelGroepDAO;
import be.vdab.entities.ArtikelGroep;

public class ArtikelGroepService {
private final ArtikelGroepDAO artikelGroepDAO = new ArtikelGroepDAO();

public ArtikelGroep read(int id) {
	return artikelGroepDAO.read(id);
}

public List<ArtikelGroep> findAll() {
	
	return artikelGroepDAO.findAll();
}
}
