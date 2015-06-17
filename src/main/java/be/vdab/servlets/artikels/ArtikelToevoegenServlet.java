package be.vdab.servlets.artikels;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.entities.ArtikelGroep;
import be.vdab.entities.FoodArtikel;
import be.vdab.entities.NonFoodArtikel;
import be.vdab.services.ArtikelGroepService;
import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ArtikelToevoegenServlet
 */
@WebServlet("/artikels/toevoegen.htm")
public class ArtikelToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final transient ArtikelService artikelService = new ArtikelService();
	private static final transient ArtikelGroepService artikelGroepService = new ArtikelGroepService();
	private static final String REDIRECT_URL = "%s/artikels/zoekenopnummer.htm?id=%d";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("artikelgroepen", artikelGroepService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Artikel.isNaamValid(naam)) {
			fouten.put("naam", "verplicht");
		}
		BigDecimal aankoopprijs = null;
		try {
			aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
			if (!Artikel.isAankoopprijsValid(aankoopprijs)) {
				fouten.put("aankoopprijs", "tik een positief getal");
			}
		} catch (NumberFormatException ex) {
			fouten.put("aankoopprijs", "tik een positief getal");

		}
		BigDecimal verkoopprijs = null;
		try {
			verkoopprijs = new BigDecimal(request.getParameter("verkoopprijs"));
			if (!Artikel.isVerkoopprijsValid(verkoopprijs)) {
				fouten.put("verkoopprijs", "tik een positief getal");
			}
		} catch (NumberFormatException ex) {
			fouten.put("verkoopprijs", "tik een positief getal");
		}
		if (!Artikel.isVerkoopprijsGroterDanAankoopprijs(aankoopprijs,
				verkoopprijs)) {
			fouten.put("verkoopprijsKleinerDanAankoopprijs",
					"geef een nieuwe verkoopprijs is");
		}
		String artikelgroepid = request.getParameter("artikelgroepen");
		if (artikelgroepid==null){
			fouten.put("artikelgroepen", "verplicht");
		}
		if (fouten.isEmpty()) {
			int houdbaarheid = 0;
			int garantie = 0;
			String soort = request.getParameter("soort");
			if (soort == null) {
				fouten.put("soort", "maak een keuze");
			} else {
				switch (soort) {
				case "F":
					try {
						houdbaarheid = Integer.parseInt(request
								.getParameter("houdbaarheid"));
						if (!FoodArtikel.isHoudbaarheidValid(houdbaarheid)) {
							fouten.put("houdbaarheid", "tik een positief getal");
						}
					} catch (NumberFormatException ex) {
						fouten.put("houdbaarheid", "tik een positief getal");
					}
					break;
				case "NF":
					try {
						garantie = Integer.parseInt(request
								.getParameter("garantie"));
						if (!NonFoodArtikel.isGarantieValid(garantie)) {
							fouten.put("garantie",
									"tik een positief getal of 0");
						}
					} catch (NumberFormatException ex) {
						fouten.put("garantie", "tik een positief getal of 0");
					}
					break;
				default:
					fouten.put("soort", "maak een keuze");
				}
			}

			Artikel artikel;
			ArtikelGroep artikelgroep
			= artikelGroepService.read(Integer.parseInt(artikelgroepid));
			
			if ("F".equals(soort)) {
				artikel = new FoodArtikel(naam, aankoopprijs, verkoopprijs, artikelgroep,
						houdbaarheid);
				
			} else {
				artikel = new NonFoodArtikel(naam, aankoopprijs, verkoopprijs, artikelgroep,
						garantie);
			}
			
			artikel.setArtikelGroep(artikelGroepService.read(Integer.parseInt(artikelgroepid)));
			
			artikelService.create(artikel);
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_URL, request.getContextPath(), artikel.getId())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
