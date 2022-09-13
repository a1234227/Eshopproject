package tw.com.eshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private FilterConfig fConfig;

	public void destroy() {
	}

	/*
	 * 轉換ServletRequest與ServletResponse 變成HttpServletRequest跟HttpServletResponse
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		doFilter(request, response, chain);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		init();
	}

	// 初始化運行 複寫子
	public void init() {
	}

	// 繼承後須實作
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException;

	// 供子類別調用config 進行呼叫Filter 初始化變數(web.xml中)
	public FilterConfig getFilterConfig() {
		return fConfig;
	}

}
