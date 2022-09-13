package tw.com.eshop.servlet.function;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eshop.dao.adminmemberdaoimpl;
import tw.com.eshop.entity.Admin_member;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String index = "index.jsp?";
		final int DUPLICATEERROR = 999;
		// 接收到的值來決定使用哪個按鈕的功能
		String value = (String) request.getParameter("buttonAction");
		// 取消按鈕
		if (value.equals("cancel")) {
			response.sendRedirect(index);
		}
		// 註冊按鈕
		else if (value.equals("register")) {
			// 接收到的帳號密碼
			String userName = request.getParameter("employeeName");
			String userAccount = request.getParameter("employeeAccount");
			String password = request.getParameter("employeePassword");
			String userPhone = request.getParameter("employeePhone");
			if(userName!=""||userAccount!=""||password!=""||userPhone!="") {
				// 連結資料庫 匯入資料庫
				adminmemberdaoimpl adimp = new adminmemberdaoimpl();
				int flag;
				flag = adimp.addreturn(new Admin_member(userName, userAccount, password, userPhone));
				if (flag == DUPLICATEERROR) {
					//匯入失敗，MYSQL內有重複的資料了
					response.sendRedirect("register.jsp?add=duplicate");
					return;
				} 
				else if (flag == 1) {
					// 註冊成功，返回登陸畫面
					request.setAttribute("userAccount", userAccount);
					request.setAttribute("password", password);
					request.getRequestDispatcher(index+"register=yes").forward(request, response);//回傳註冊的帳號密碼給index.jsp顯示使用
					return;
				} else {
					// 失敗向前台傳輸失敗訊息
					response.sendRedirect("register.jsp?add=no");
					return;
				}
			}else {
				//有欄位沒有填寫 回傳失敗訊息
				response.sendRedirect("register.jsp?add=empty");
				return;
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
