package tw.com.eshop.servlet.product;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eshop.dao.productenumdaoimpl;
import tw.com.eshop.entity.Product_enum;

@WebServlet("/admin/prokindcud")
public class ProductKindCUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final int DUPLICATEERROR = 999;
		// 接收value作用不同的功能
		String value = request.getParameter("buttonAction");
		// --------------------------------新增產品種類----------------------------------/
		
		if (value.equals("add")) {
			String enum_name = request.getParameter("enum_name");
			Product_enum pro = new Product_enum(enum_name);
			
			productenumdaoimpl proenimpl = new productenumdaoimpl();
			int flag= proenimpl.addreturn(pro);
			if (flag == DUPLICATEERROR) {
				response.sendRedirect("product_kind.jsp?add=duplicate");
				return;
			}
			if (flag == 1) {
				response.sendRedirect("product_kind.jsp?add=yes");
				return;
			} else {
				response.sendRedirect("product_kind.jsp?add=no");
				return;
			}
		}
		// --------------------------------刪除產品種類----------------------------------/
		if (value.equals("delete")) {
			int enum_id = Integer.parseInt(request.getParameter("enum_id"));
			productenumdaoimpl proenimpl = new productenumdaoimpl();
			proenimpl.delete(enum_id);
			try {
				proenimpl.delete(enum_id);
				response.sendRedirect("product_kind.jsp?delete=yes");
			} catch (Exception e) {
				response.sendRedirect("product_kind.jsp?delete=no");
				e.printStackTrace();
			}
		}
		// --------------------------------修改產品種類----------------------------------/
		if (value.equals("update")) {
			int enum_id = Integer.parseInt(request.getParameter("enum_id"));
			String enum_name = request.getParameter("enum_name");
			Product_enum proen = new Product_enum(enum_id, enum_name);
			try {
				productenumdaoimpl proenimpl = new productenumdaoimpl();
				proenimpl.update(proen);
				response.sendRedirect("product_kind.jsp?update=yes");
			} catch (Exception e) {
				response.sendRedirect("product_kind.jsp?update=no");
				e.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
