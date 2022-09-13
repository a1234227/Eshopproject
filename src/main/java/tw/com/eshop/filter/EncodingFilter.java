package tw.com.eshop.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 處理中文亂碼，轉碼成UTF-8
 */
@WebFilter(filterName = "/encodingFilter", urlPatterns = "/*")
public class EncodingFilter extends BaseFilter {

	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String correnturi = request.getRequestURI();

		if (correnturi.toString().contains(".css") || correnturi.toString().contains(".js")
				|| correnturi.toString().contains(".jpg") || correnturi.toString().contains(".png")
				|| correnturi.toString().contains(".do")) {
			chain.doFilter(request, response);
		}else{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			chain.doFilter(request, response);
		}

		
	}

}
