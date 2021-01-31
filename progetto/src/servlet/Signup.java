package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UtenteBean;

import classi.UtenteClass;

@WebServlet("/registrazione")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteClass utenteC = new UtenteClass();
		
		String username = request.getParameter("username");
		String password= request.getParameter ("password");
		
		UtenteBean utente = new UtenteBean();
		utente.setUsername(username);
		utente.setPassword(password);
		
		try {
			utenteC.registrazione(utente);
			response.sendRedirect("pages/home.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
