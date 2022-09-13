package tw.com.eshop.servlet.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eshop.dao.customermemberdaoimpl;
import tw.com.eshop.entity.Customer_member;
import tw.com.eshop.tools.MD5Tools;
import tw.com.eshop.tools.tools;

@WebServlet("/customer_login")
public class CustomerAndroidLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String account, password, realname, address, value;
	MD5Tools base64=new MD5Tools();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		value = request.getParameter("value");

		// -------------------------------顧客註冊----------------------------------/
		if (value.equals("register")) {
			account = request.getParameter("account");
			password = request.getParameter("password");
			realname = request.getParameter("realname");
			address = request.getParameter("address");
			realname=base64.replacer(realname);
			address=base64.replacer(address);
			customermemberdaoimpl custadd = new customermemberdaoimpl();
			Customer_member cm = new Customer_member(realname, account, password, address);
			int r = custadd.addreturn(cm);
			if (r == 1) {
				response.getWriter().print("OK");
			} else if (r == 999) {
				response.getWriter().print("Duplicate Account");
			} else {
				response.getWriter().print("NotOK");
			}
		}
		// -------------------------------顧客登入----------------------------------/
		if (value.equals("login")) {
			account = request.getParameter("account");
			password = request.getParameter("password");
			
			boolean r = tools.gettools().customerlogincheck(account, password);
			System.out.println(account);
			System.out.println(password);
			if (r == true) {
				response.getWriter().print("OK");
			} else {
				response.getWriter().print("NotOK");
			}
		}

		// -------------------------------取得顧客名稱----------------------------------/
		if (value.equals("getname")) {
			account = request.getParameter("account");
			password = request.getParameter("password");
			tools t = new tools();
			String r = t.getcurrentcustomername(account, password);
			System.out.println(r);
			response.getWriter().print(r);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
