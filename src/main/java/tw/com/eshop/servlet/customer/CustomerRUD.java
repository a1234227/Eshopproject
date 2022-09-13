package tw.com.eshop.servlet.customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eshop.dao.customermemberdaoimpl;
import tw.com.eshop.entity.Customer_member;

@WebServlet("/admin/custrud")
public class CustomerRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收value作用不同的功能
		String value = request.getParameter("buttonAction");
		// --------------------------------刪除顧客----------------------------------/
				if (value.equals("delete")) {
					int cust_id = Integer.parseInt(request.getParameter("cust_id"));
					customermemberdaoimpl custdelete = new customermemberdaoimpl();
					custdelete.delete(cust_id);
					try {
						custdelete.delete(cust_id);
						response.sendRedirect("customer_search.jsp?delete=yes");
					} catch (Exception e) {
						response.sendRedirect("customer_search.jsp?delete=no");
						e.printStackTrace();
					}
				}
		
		// --------------------------------修改顧客----------------------------------/
		if (value.equals("update")) {
			int cust_id = Integer.parseInt(request.getParameter("cust_id"));
			String cust_name = request.getParameter("cust_name");
			String cust_account = request.getParameter("cust_account");
			String cust_password = request.getParameter("cust_password");
			String cust_address = request.getParameter("cust_address");
			Customer_member cm = new Customer_member(cust_id, cust_name, cust_account, cust_password, cust_address);
			try {
				customermemberdaoimpl cmadd = new customermemberdaoimpl();		
				cmadd.update(cm);
				response.sendRedirect("customer_search.jsp?update=yes");
			} catch (Exception e) {
				response.sendRedirect("customer_search.jsp?update=no");
				e.printStackTrace();
			}
		}
		// --------------------------------查詢顧客----------------------------------/
		if (value.equals("search")) {
			String barvalue = request.getParameter("barvalue");
			String inputlabel = request.getParameter("inputlabel");
			int seletetype = 0;
			if (barvalue.equals("姓名")) {
				seletetype = 1;
			} else if (barvalue.equals("編號")) {
				seletetype = 2;
			} else if (barvalue.equals("帳號")) {
				seletetype = 3;
			}else if (barvalue.equals("地址")) {
				seletetype = 4;
			}

			customermemberdaoimpl cu = new customermemberdaoimpl();
			ArrayList<Customer_member> custList=cu.query(seletetype,inputlabel);
			 
			request.setAttribute("custList", custList);
			request.getRequestDispatcher("customer_condition_search.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
