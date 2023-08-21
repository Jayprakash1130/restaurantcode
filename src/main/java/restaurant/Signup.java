package restaurant;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import dao.Mydao;
import dto.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/signup")
public class Signup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Name = req.getParameter("name");
		long Mobile = Long.parseLong(req.getParameter("mob"));
		String Email = req.getParameter("mail");
		String Password = req.getParameter("pass");
		String Gender = req.getParameter("gen");
		String Country = req.getParameter("con");
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		int age = Period.between(dob, LocalDate.now()).getYears();
		Part pic = req.getPart("pic");
		byte[] picture = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
          
		Mydao dao = new Mydao();
		
		if(dao.fetchbyEmail(Email)==null && dao.fetchbyMobile(Mobile)==null)
		{
			
		//loading values inside object
		Customer cus = new Customer();
		cus.setAge(age);
		cus.setCon(Country);
		cus.setDob(dob);
		cus.setGen(Gender);
		cus.setMail(Email);
		cus.setMobile(Mobile);
		cus.setName(Name);
		cus.setPass(Password);
		cus.setPicture(picture);

		//persisting
		
		dao.save(cus);
		resp.getWriter().print("<h1 style='color:green'>Account created Successfully</h1>");
		req.getRequestDispatcher("Login.html").include(req, resp);
		}
//		System.out.println(Name);
//		System.out.println(Mobile);
//		System.out.println(Email);
//		System.out.println(Password);
//		System.out.println(Gender);
//		System.out.println(Country);
//		System.out.println(dob);
//		System.out.println(age);
//		System.out.println(pic);
		
		else {
			resp.getWriter().print("<h1 style='color:orange'>Email and Mobile should be unique</h1>");}
		req.getRequestDispatcher("Signup.html").include(req, resp);
	}

}
