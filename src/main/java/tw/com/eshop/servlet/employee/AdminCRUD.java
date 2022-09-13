package tw.com.eshop.servlet.employee;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eshop.dao.adminmemberdaoimpl;
import tw.com.eshop.entity.Admin_member;

@WebServlet("/admin/admincrud")
public class AdminCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final int DUPLICATEERROR = 999;
		// 接收value作用不同的功能
		String value = request.getParameter("buttonAction");
		// --------------------------------新增員工----------------------------------/	
		if (value.equals("add")) {
			String admin_name = request.getParameter("admin_name");
			String admin_account = request.getParameter("admin_account");
			String admin_password = request.getParameter("admin_password");
			String admin_phone = request.getParameter("admin_phone");
			adminmemberdaoimpl amadd = new adminmemberdaoimpl();
			int flag = amadd.addreturn(new Admin_member(admin_name, admin_account, admin_password, admin_phone));
			if (flag == DUPLICATEERROR) {
				response.sendRedirect("employee_add.jsp?add=duplicate");
				return;
			}
			if (flag == 1) {
				response.sendRedirect("employee_add.jsp?add=yes");
				return;
			} else {
				response.sendRedirect("employee_add.jsp?add=no");
				return;
			}
		}
		// --------------------------------刪除員工----------------------------------/
		if (value.equals("delete")) {
			int admin_id = Integer.parseInt(request.getParameter("admin_id"));
			adminmemberdaoimpl admindelete = new adminmemberdaoimpl();
			admindelete.delete(admin_id);
			try {
				admindelete.delete(admin_id);
				response.sendRedirect("employee_search.jsp?delete=yes");
			} catch (Exception e) {
				response.sendRedirect("employee_search.jsp?delete=no");
				e.printStackTrace();
			}
		}
		// --------------------------------修改員工----------------------------------/
		if (value.equals("update")) {
			int admin_id = Integer.parseInt(request.getParameter("admin_id"));
			String admin_name = request.getParameter("admin_name");
			String admin_account = request.getParameter("admin_account");
			String admin_password = request.getParameter("admin_password");
			String admin_phone = request.getParameter("admin_phone");
			int admin_active = Integer.parseInt(request.getParameter("admin_active"));
			
			Admin_member am = new Admin_member(admin_id, admin_name, admin_account, admin_password, admin_active,
					admin_phone);
			try {
				adminmemberdaoimpl adupdate = new adminmemberdaoimpl();
				adupdate.update(am);
				request.setAttribute("adminList", am);
				response.sendRedirect("employee_search.jsp?update=yes");
			} catch (Exception e) {
				response.sendRedirect("employee_search.jsp?update=no");
				e.printStackTrace();
			}
		}
		// --------------------------------查詢員工----------------------------------/
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
			} else if (barvalue.equals("電話")) {
				seletetype = 4;
			}
			adminmemberdaoimpl ad = new adminmemberdaoimpl();
			 ArrayList<Admin_member> adminList=ad.query(seletetype,inputlabel);

			request.setAttribute("adminList", adminList);
			request.getRequestDispatcher("employee_condition_search.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
