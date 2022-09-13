package tw.com.eshop.servlet.function;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eshop.tools.readconfig;
import tw.com.eshop.tools.tools;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 頁面
		String mainpage = "admin/mainpage.jsp";
		String registerpage = "/register.jsp";
		// 接收到的帳號密碼
		String username = request.getParameter("employeeAccount");
		String password = request.getParameter("employeePassword");
		// 接收到的值來決定使用哪個按鈕的功能
		String value = (String) request.getParameter("buttonAction");
		// 登入功能的final常數
		final int derestrict = 0;
		final int restrict = 1;
		final int loginfail = -1;
		final int active = 1;
		final int deactive = 0;
		// 定義session
		HttpSession httpSession = request.getSession();

		// 登入按鈕
		if (value.equals("login")) {
			// 確認帳號密碼是否正確
			int status = tools.gettools().adminlogincheck(username, password);
			// 帳號密碼皆正確但尚未啟用權限
			if (status == deactive) {
				response.sendRedirect("index.jsp?error=deactive");
				return;
				// 帳號密碼皆正確且權限為開啟，後續需要判斷帳號是否被登入限制鎖住。
			} else if (status == active) {
				int restrictstatus = tools.gettools().loginrestrictcheck(username);
				// 如帳號無鎖
				if (restrictstatus == derestrict) {
					// 清除之前的登入失敗紀錄
					tools.gettools().clearloginwrongtime(username);
					// 取得登入者姓名
					String name = tools.gettools().getcurrentadminname(username, password);
					request.setAttribute("adminname", name);
					// 將使用者名稱用session傳給mainpage

					httpSession.setAttribute("adminname", name);
					response.sendRedirect(mainpage);
					return;
					// 如帳號有鎖
				} else {
					// 確認是否已經過了解鎖時間(從config.xml抓設定)
					String configxmlPath = httpSession.getServletContext().getRealPath("/")+"/admin/config.xml";
					int restrictminutes = readconfig.getreadconfig(configxmlPath).readrestrictminutes();
					// 如果抓回來的值不合法，報錯
					if (restrictminutes < 0) {
						response.sendRedirect("index.jsp?error=config.admin");
						return;
					}
					int unlockcheck = tools.gettools().restrictunlockcheck(username, restrictminutes);
					// 成功解鎖，回到正常登入程序
					if (unlockcheck == derestrict) {
						String name = tools.gettools().getcurrentadminname(username, password);
						request.setAttribute("adminname", name);
						// 將使用者名稱用session傳給mainpage
						httpSession.setAttribute("adminname", name);
						response.sendRedirect(mainpage);
						return;
						// 解鎖失敗 回傳error參數給頁面，彈出錯誤視窗
					} else if (unlockcheck == restrict) {
						response.sendRedirect("index.jsp?error=restricted");
						return;
					}
				}
				// 登入失敗(該帳號輸入密碼錯誤或沒有權限登入)的狀況 進行帳號登入失敗次數記錄 超過5次即鎖定
			} else if (status == loginfail) {
				String fail = tools.gettools().loginrestriction(username);
				// 無此帳號，回傳error參數給頁面，彈出錯誤視窗
				if (fail.equals("accountnotvaild")) {
					response.sendRedirect("index.jsp?error=accountnotvaild");
					return;
					// 帳號早已被鎖，回傳error參數給頁面，彈出錯誤視窗
				} else if (fail.equals("restricted")) {
					response.sendRedirect("index.jsp?error=restricted");
					return;
					// 帳號未被鎖且登入失敗未達5次，進行記錄登入失敗次數並回傳error參數給頁面，彈出錯誤視窗
				} else if (fail.contains("wrongtime:")) {
					Integer chance = 5 - Integer.parseInt(fail.replace("wrongtime:", ""));
					response.sendRedirect("index.jsp?error=chance:" + chance);
					return;
					// 本次登入失敗使次數達5次，帳號鎖住,回傳error參數給頁面，彈出錯誤視窗
				} else if (fail.equals("restrict")) {
					response.sendRedirect("index.jsp?error=restrict");
					return;
				}
			}
		}

		// 註冊按鈕
		else if (value.equals("register")) {
			// 註冊按鈕的重定向
			response.sendRedirect(request.getContextPath() + registerpage);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
