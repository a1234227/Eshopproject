package tw.com.eshop.servlet.product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eshop.dao.productdaoimpl;
import tw.com.eshop.entity.Product;

@WebServlet("/admin/procrud")
public class ProductCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final int DUPLICATEERROR = 999;
		// 接收value作用不同的功能
		String value = request.getParameter("buttonAction");
		// --------------------------------新增商品----------------------------------/
		if (value.equals("add")) {
			int enum_id = Integer.parseInt(request.getParameter("enum_id"));
			String pro_name = request.getParameter("pro_name");
			BigDecimal pro_price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("pro_price")));
			String pro_desc = request.getParameter("pro_desc");
			int pro_stock = Integer.parseInt(request.getParameter("pro_stock"));
			String pro_image = request.getParameter("pro_image");
			
			productdaoimpl proadd = new productdaoimpl();
			Product pro = new Product(enum_id, pro_name, pro_price, pro_desc, pro_stock, pro_image);
			int flag= proadd.addreturn(pro);
			if (flag == DUPLICATEERROR) {
				response.sendRedirect("product_add.jsp?add=duplicate");
				return;
			}
			if (flag == 1) {
				response.sendRedirect("product_add.jsp?add=yes");
				return;
			} else {
				response.sendRedirect("product_add.jsp?add=no");
				return;
			}
		}
		// --------------------------------刪除商品----------------------------------/
		if (value.equals("delete")) {
			int pro_id = Integer.parseInt(request.getParameter("pro_id"));
			productdaoimpl prodelete = new productdaoimpl();
			prodelete.delete(pro_id);
			try {
				prodelete.delete(pro_id);
				response.sendRedirect("product_search.jsp?delete=yes");
			} catch (Exception e) {
				response.sendRedirect("product_search.jsp?delete=no");
				e.printStackTrace();
			}
		}
		// --------------------------------修改商品----------------------------------/
		if (value.equals("update")) {
			int pro_id = Integer.parseInt(request.getParameter("pro_id"));
			int enum_id = Integer.parseInt(request.getParameter("enum_id"));
			String pro_name = request.getParameter("pro_name");
			BigDecimal pro_price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("pro_price")));
			String pro_desc = request.getParameter("pro_desc");
			int pro_stock = Integer.parseInt(request.getParameter("pro_stock"));
			String pro_image = request.getParameter("pro_image");
			
			Product pd = new Product(enum_id, pro_id, pro_name, pro_price, pro_desc, pro_stock, pro_image);
			
			try {
				productdaoimpl pdi = new productdaoimpl();
				pdi.update(pd);
				request.setAttribute("proList", pd);
				response.sendRedirect("product_search.jsp?update=yes");// 成功回傳給前台
			} catch (Exception e) {
				response.sendRedirect("product_search.jsp?update=no");// 失敗回傳給前台
				e.printStackTrace();
			}
			
			
		}
		// --------------------------------查詢商品----------------------------------/
		if (value.equals("search")) {
			String barvalue = request.getParameter("barvalue");
			String inputlabel = request.getParameter("inputlabel");
			int seletetype = 0;
			if (barvalue.equals("種類")) {
				seletetype = 1;
			} else if (barvalue.equals("品名")) {
				seletetype = 2;
			}	
			productdaoimpl proimpl = new productdaoimpl();
			ArrayList<Product> proList=proimpl.query(seletetype,inputlabel);
			request.setAttribute("proList", proList);
			request.getRequestDispatcher("product_condition_search.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
