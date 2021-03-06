package be.vdab.servlets.artikels;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.ArtikelGroepService;

/**
 * Servlet implementation class PerArtikelGroepServlet
 */
@WebServlet("/artikels/perartikelgroep.htm")
public class PerArtikelGroepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/perartikelgroep.jsp";
	private final transient ArtikelGroepService artikelGroepService = new ArtikelGroepService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String artikelgroepid =request.getParameter("id");
		if (artikelgroepid!=null){
			request.setAttribute("artikelgroep",
					artikelGroepService.read(Integer.parseInt(artikelgroepid)));
		}
		request.setAttribute("artikelgroepen", artikelGroepService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}


}
