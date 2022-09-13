package tw.com.eshop.servlet.product;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tw.com.eshop.dao.customercartdaoimpl;
import tw.com.eshop.dao.customerfollowdaoimpl;
import tw.com.eshop.dao.customerorderdaoimpl;
import tw.com.eshop.dao.productdaoimpl;
import tw.com.eshop.dao.productenumdaoimpl;
import tw.com.eshop.entity.Customer_cart;
import tw.com.eshop.entity.Customer_order;
import tw.com.eshop.entity.Product;
import tw.com.eshop.entity.Product_enum;
import tw.com.eshop.tools.MD5Tools;

@WebServlet("/customer_query")
public class ProductAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MD5Tools base64 = new MD5Tools();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收value作用不同的功能
		String value = request.getParameter("value");

		// --------------------------------取得商品種類----------------------------------/
		if (value.equals("category")) {
			productenumdaoimpl proenum = new productenumdaoimpl();
			ArrayList<Product_enum> proenumList = proenum.queryAll();
			String json = "";
			json += "{";
			for (int i = 0; i < proenumList.size(); i++) {
				String id = String.valueOf(proenumList.get(i).getEnum_id());
				String name = proenumList.get(i).getEnum_name();
				if (i == proenumList.size() - 1) {
					json += "\"" + (i + 1) + "\":{\"id\":\"" + id + "\"" + ",\"name\":\"" + name + "\"}";
				} else {
					json += "\"" + (i + 1) + "\":{\"id\":\"" + id + "\"" + ",\"name\":\"" + name + "\"},";
				}
			}
			json += "}";
			response.getWriter().print(json);
			return;
		}

		// --------------------------------取得主頁商品列表(依分類查詢)----------------------------------/
		if (value.equals("get_list")) {
			String keyword = request.getParameter("keyword");
			keyword=base64.replacer(keyword);
			productdaoimpl pro = new productdaoimpl();
			ArrayList<Product> proList = pro.query(1, keyword);
			String json = "";
			json += "{";
			for (int i = 0; i < proList.size(); i++) {
				String name = proList.get(i).getPro_name();
				String price = proList.get(i).getPro_price().toString();
				String id = String.valueOf(proList.get(i).getPro_id());
				price = price.substring(0, price.length() - 4);
				String img = proList.get(i).getPro_image();
				if (i == proList.size() - 1) {
					json += "\"" + (i + 1) + "\":{\"id\":\"" + id + "\",\"name\":\"" + name + "\"" + ",\"price\":\""
							+ price + "\",\"img\":\"" + img + "\"}";
				} else {
					json += "\"" + (i + 1) + "\":{\"id\":\"" + id + "\",\"name\":\"" + name + "\"" + ",\"price\":\""
							+ price + "\",\"img\":\"" + img + "\"},";
				}
			}
			json += "}";

			response.getWriter().print(json);

			return;
		}
		
		if (value.equals("get_order_list")) {
			Gson gson = new Gson();
			String ids = request.getParameter("ids");
//			String ids="[{\"product_id\":[1,2,3,4,5,6,7,8,9,10],\"account\":\"test\"}]";
//			ids=base64.base64Decryption(ids);
//			ids=URLDecoder.decode(ids,"UTF-8");
			ids=base64.replacer(ids);
//			ids=ids.substring(1, ids.length()-1);
			Customer_cart cc=new Customer_cart();
			cc=gson.fromJson(ids, Customer_cart.class);
			int[] pros =cc.getProduct_id();
			productdaoimpl pro = new productdaoimpl();
			ArrayList<Product> proList = new ArrayList<>();
			for(int i=0;i<pros.length;i++) {
				
				proList.add(pro.query(3, String.valueOf(pros[i])).get(0));
				
			}
			System.out.println(proList.toString());
			String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
			}.getType());
			System.out.println(json);

			response.getWriter().print(json);

			return;
		}

		if (value.equals("get_product")) {
			String id = request.getParameter("id");
			productdaoimpl pro = new productdaoimpl();
			ArrayList<Product> proList = pro.query(3, id);
			Gson gson = new Gson();
			String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
			}.getType());


			response.getWriter().print(json);

			return;
		}

		// --------------------------------取得result頁商品列表(依品名查詢)----------------------------------/
		// 20220907 1430 edit by 鄭紹謙
		if (value.equals("search_product")) {
			String keyword = request.getParameter("keyword");
			String max = request.getParameter("max");
			String min = request.getParameter("min");
			
			int max_t,min_t;
			keyword=base64.replacer(keyword);
			if(max.equals("")) {
				max_t=99999;
			}else {
				max_t=Integer.parseInt(max);
			}
			if(min.equals("")) {
				min_t=0;
			}else {
				min_t=Integer.parseInt(min);				
			}
			
			System.out.println(keyword+" "+min_t+" "+max_t);
			
			if(max_t==0) {
				productdaoimpl pro = new productdaoimpl();
				ArrayList<Product> proList = pro.query_search(keyword, min_t, max_t);
				Gson gson = new Gson();
				String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
				}.getType());
				response.getWriter().print(json);
				return;
			}else if(min_t==0) {
				productdaoimpl pro = new productdaoimpl();
				ArrayList<Product> proList = pro.query_search(keyword, min_t, max_t);
				Gson gson = new Gson();
				String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
				}.getType());
				response.getWriter().print(json);
				return;
				
			}else if(keyword.equals("")) {
				productdaoimpl pro = new productdaoimpl();
				ArrayList<Product> proList = pro.query_search(min_t, max_t);
				Gson gson = new Gson();
				String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
				}.getType());
				response.getWriter().print(json);
				return;
				
			}else if(max_t==0&&min_t==0) {
				productdaoimpl pro = new productdaoimpl();
				ArrayList<Product> proList = pro.query_search(keyword);
				Gson gson = new Gson();
				String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
				}.getType());
				response.getWriter().print(json);
				return;
				
			}else if(keyword.equals("")&&min_t==0) {
				productdaoimpl pro = new productdaoimpl();
				ArrayList<Product> proList = pro.query_search(min_t, max_t);
				Gson gson = new Gson();
				String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
				}.getType());
				response.getWriter().print(json);
				return;
			}else if(keyword.equals("")&&max_t==0) {
				productdaoimpl pro = new productdaoimpl();
				ArrayList<Product> proList = pro.query_search(min_t, max_t);
				Gson gson = new Gson();
				String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
				}.getType());
				response.getWriter().print(json);
				return;
			}else {
				productdaoimpl pro = new productdaoimpl();
				ArrayList<Product> proList = pro.query_search(keyword, min_t, max_t);
				Gson gson = new Gson();
				String json = gson.toJson(proList, new TypeToken<ArrayList<Product>>() {
				}.getType());
				response.getWriter().print(json);
				return;
			}
		}

		if (value.equals("cart_query")) {
			String account = request.getParameter("account");
			customercartdaoimpl cust = new customercartdaoimpl();
			ArrayList<Customer_cart> cart = cust.query(account);
			Gson gson = new Gson();
			String json = gson.toJson(cart, new TypeToken<ArrayList<Customer_cart>>() {
			}.getType());

			response.getWriter().print(json);
			return;
		}

		if (value.equals("cart_renew")) {
			String data = request.getParameter("data");
			System.out.println(data);
			
//			data = base64.base64Decryption(data);
			base64.replacer(data);
			System.out.println(data);
			Gson gson = new Gson();
			Customer_cart cc = gson.fromJson(data, Customer_cart.class);
			customercartdaoimpl cust = new customercartdaoimpl();
			int r = cust.update1(cc);
			response.getWriter().print(r);
			return;
		}
		
		if (value.equals("cart_remove")) {
			String data = request.getParameter("data");
			System.out.println(data);
//			data=URLDecoder.decode(data,"UTF-8");
//			data=base64.base64Decryption(data);
			data=base64.replacer(data);
			System.out.println(data);
			Gson gson = new Gson();
			Customer_cart cc = gson.fromJson(data, Customer_cart.class);
			customercartdaoimpl cust = new customercartdaoimpl();
			boolean r=cust.delete1(cc);
			if(r==true) {
				response.getWriter().print("OK");
			}else {
				response.getWriter().print("NotOK");
			}
			return;
		}

		if (value.equals("follow_query")) {
			String account = request.getParameter("account");
			customerfollowdaoimpl cust = new customerfollowdaoimpl();
			ArrayList<Customer_cart> cart = cust.query(account);
			Gson gson = new Gson();
			String json = gson.toJson(cart, new TypeToken<ArrayList<Customer_cart>>() {
			}.getType());

			response.getWriter().print(json);
			return;
		}

		if (value.equals("follow_renew")) {
			String data = request.getParameter("data");
			System.out.println(data);
			
//			data = base64.base64Decryption(data);
			data=base64.replacer(data);

			System.out.println(data);
			Gson gson = new Gson();
			Customer_cart cc = gson.fromJson(data, Customer_cart.class);
			customerfollowdaoimpl cust = new customerfollowdaoimpl();
			int r = cust.update1(cc);
			response.getWriter().print(r);
			return;
		}
		
		if (value.equals("order_add")) {
			String data = request.getParameter("data");
			String account = request.getParameter("account");
			String amount = request.getParameter("amount");
//			String account = "test";
//			String data="[{\"enum_id\":6,\"enum_name\":\"南北雜貨\",\"pro_id\":11,\"pro_name\":\"牛肉乾\",\"pro_price\":460.000,\"pro_desc\":\"特選上等豬後腿肉，純手工烘烤，不油也不膩，不含防腐劑，保有纖維口感和咬勁，是店內的超人氣商品。\",\"pro_stock\":30,\"pro_image\":\"https://i.imgur.com/hOkOTmW.jpeg\"},{\"enum_id\":2,\"enum_name\":\"飲料\",\"pro_id\":26,\"pro_name\":\"12345\",\"pro_price\":80.000,\"pro_desc\":\"sdf\",\"pro_stock\":55,\"pro_image\":\"\"}]";
			
			data=base64.replacer(data);
//			data=base64.base64Decryption(data);
			amount=base64.replacer(amount);
			Gson gson = new Gson();
			ArrayList<Product>pro=new ArrayList<>();
			ArrayList<Integer>amt=new ArrayList<>();
			pro = gson.fromJson(data, new TypeToken<ArrayList<Product>>() {
			}.getType());
			amt=gson.fromJson(amount, new TypeToken<ArrayList<Integer>>() {
			}.getType());
			int[] container=new int[pro.size()];
			int[] container2=new int[amt.size()];
			for(int i=0;i<pro.size();i++) {
				container[i] = pro.get(i).getPro_id();
				container2[i] = amt.get(i);
			}
			Customer_cart cc=new Customer_cart();
			cc.setAccount(account);
			cc.setProduct_id(container);
			cc.setAmount(container2);
			customerorderdaoimpl cust = new customerorderdaoimpl();
			boolean r = cust.add1(cc);
			if(r==true) {
			response.getWriter().print("OK");
			}else {
				response.getWriter().print("NotOK");
				
			}
			return;
		}
		
		if (value.equals("get_customer_order_list")) {
			String account = request.getParameter("account");
//			String account = "test10";
			Gson gson =new Gson();
//			String account = "test";
//			String data="[{\"enum_id\":6,\"enum_name\":\"南北雜貨\",\"pro_id\":11,\"pro_name\":\"牛肉乾\",\"pro_price\":460.000,\"pro_desc\":\"特選上等豬後腿肉，純手工烘烤，不油也不膩，不含防腐劑，保有纖維口感和咬勁，是店內的超人氣商品。\",\"pro_stock\":30,\"pro_image\":\"https://i.imgur.com/hOkOTmW.jpeg\"},{\"enum_id\":2,\"enum_name\":\"飲料\",\"pro_id\":26,\"pro_name\":\"12345\",\"pro_price\":80.000,\"pro_desc\":\"sdf\",\"pro_stock\":55,\"pro_image\":\"\"}]";
			customerorderdaoimpl cust = new customerorderdaoimpl();
			ArrayList<Customer_order> result = cust.query(account);
			String json = gson.toJson(result, new TypeToken<ArrayList<Customer_order>>() {
			}.getType());
			
			
			response.getWriter().print(json);
			return;
		}
		
		if (value.equals("get_product_lite")) {
			String id = request.getParameter("id");
			productdaoimpl pro = new productdaoimpl();
			Product proList = pro.query_lite(id);
			System.out.println(proList.toString());
			Gson gson = new Gson();
			String json = gson.toJson(proList, Product.class);


			response.getWriter().print(json);

			return;
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
