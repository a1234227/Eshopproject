package tw.com.eshop.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "/mainlock", urlPatterns = "/admin/*")
public class mainlock extends BaseFilter {

	private static final long serialVersionUID = 1L;
	HttpSession httpsession;

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String correnturi = request.getRequestURI();
		httpsession = request.getSession();
		String adminname = null;
		
		try {
			adminname = httpsession.getAttribute("adminname").toString();
		} catch (NullPointerException e) {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		}

		if (adminname != null && !adminname.equals("")) {
			chain.doFilter(request, response);
		} else if (correnturi.toString().contains(".css") || correnturi.toString().contains(".js")
				|| correnturi.toString().contains(".jpg") || correnturi.toString().contains(".png")
				|| correnturi.toString().contains(".do")) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}

	}

}