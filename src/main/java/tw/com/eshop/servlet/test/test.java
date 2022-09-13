package tw.com.eshop.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eshop.tools.readconfig;


@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw =response.getWriter();
		HttpSession httpsession=request.getSession();
		String configxmlPath = httpsession.getServletContext().getRealPath("/")+"/admin/config.xml";
		int i=readconfig.getreadconfig(configxmlPath).readrestrictminutes();
		pw.print(i);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
