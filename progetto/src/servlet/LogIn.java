package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UtenteBean;

import classi.UtenteClass;

@WebServlet("/login")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("username");
		String password = request.getParameter("password");
		
		UtenteBean utente = new UtenteBean();
		
		try {
			utente.setUsername(nome);
			utente.setPassword(password);
			
		} catch (Exception e1) {e1.printStackTrace();}
		
		UtenteClass controller = new UtenteClass();
		try {
			
			if(controller.log(utente)==0)
				response.sendRedirect("pages/home.jsp");
			else
				response.sendRedirect("auth/login.html");
				
		} catch (Exception e) {e.printStackTrace();}
	}
}
