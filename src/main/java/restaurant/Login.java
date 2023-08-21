package restaurant;

import java.io.IOException;
import dao.Mydao;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Mapping the url
@WebServlet("/login")
public class Login  extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Recieve values from front end
		String email=req.getParameter("emphno");
		String password=req.getParameter("password");
		
		Mydao dao = new Mydao();
		if (email.equals("admin@jsp.com")&& password.equals("admin")) {
			resp.getWriter().print("<h1 style='color:brown'>Login successful</h1>");
			req.getRequestDispatcher("AdminHome.html").include(req, resp);
		}else {
		Customer cus=dao.fetchbyEmail(email);
		if(cus==null) {
			resp.getWriter().print("<h1 style='color:darkyellow'>Invalid Email</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}else {
			if(password.equals(cus.getPass())) {
				resp.getWriter().print("<h1 style='color:blue'>Login Successful</h1>");
				req.getRequestDispatcher("home.html").include(req, resp);
			}else {
				resp.setContentType("text/html");
			resp.getWriter().print("<h1 style='color:darkgreen'>Invalid Password</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
			
		}
	}

}
	}
}
