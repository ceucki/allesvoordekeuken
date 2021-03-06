package be.vdab.dao;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Artikel;

public class ArtikelDAO extends AbstractDAO {

	public Artikel read(long id) {
		return getEntityManager().find(Artikel.class, id);
	}

	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}

	public List<Artikel> zoekWoordOpNaam(String woord) {

		return getEntityManager()
				.createNamedQuery("Artikel.zoekWoordOpNaam", Artikel.class)
				.setParameter("woord", '%' + woord + '%').getResultList();
	}

	public void algemenePrijsverhoging(BigDecimal factor) {
		getEntityManager().createNamedQuery("Docent.algemeneOpslag")
				.setParameter("factor", factor).executeUpdate();
	}

	public List<Artikel> findAll() {
		return getEntityManager().createNamedQuery("Artikel.findAll",
				Artikel.class).getResultList();
	}

	public List<Artikel> findArtikelInArtikelGroep(int artikelgroepid) {
		return getEntityManager()
				.createNamedQuery("Artikel.findArtikelInArtikelGroep",
						Artikel.class)
				.setParameter("artikelgroepid", artikelgroepid)
				.setHint(
						"javax.persistence.loadgraph",
						getEntityManager().createEntityGraph(
								"Artikel.metArtikelGroep")).getResultList();
	}
	
	public List<Artikel> findAllArtikelsAndArtikelGroep(){
		return getEntityManager().createNamedQuery("Artikel.findAll", Artikel.class).setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph("Artikel.metArtikelGroep")).getResultList();
	}
}
