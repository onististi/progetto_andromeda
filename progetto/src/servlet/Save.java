package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classi.TicketSalvatiClass;

/**
 * Servlet implementation class Save
 */
@WebServlet("/tratte_preferite")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Save() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketSalvatiClass Salva = new TicketSalvatiClass();
		HttpSession session = request.getSession();
		
		try {		
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Salva.carica(Integer.parseInt(session.getAttribute("id_utente").toString())));
		} catch (NumberFormatException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketSalvatiClass Salva = new TicketSalvatiClass();
		HttpSession session = request.getSession();
		try {
			if(request.getParameter("message").equals("salva")) {
				//System.out.println("salva");
				Salva.salva((int) session.getAttribute("id_utente"), request.getParameter("partenza"), request.getParameter("arrivo"));
			}else if(request.getParameter("message").equals("elimina")){
				//System.out.println("elimina");
				Salva.elimina((int) session.getAttribute("id_utente"), request.getParameter("partenza"), request.getParameter("arrivo"));
			
			}
		} catch (ClassNotFoundException e){e.printStackTrace();}
	}
}