package tw.com.eshop.entity;

import java.math.BigDecimal;

public class Product {
	
	private int enum_id;
	private String enum_name;
	private int pro_id;
	private String pro_name;
	private BigDecimal pro_price;
	private String pro_desc;
	private int pro_stock;
	private String pro_image;
	
	//空建構子
	public Product() {
		super();
	}
	
	
	
	//Product類別 傳入類別代碼+產品id+商品名稱+價格+商品詳細+庫存+圖片網址的建構子
	public Product(int enum_id, int pro_id, String pro_name, BigDecimal pro_price, String pro_desc, int pro_stock,
			String pro_image) {
		super();
		this.enum_id = enum_id;
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.pro_price = pro_price;
		this.pro_desc = pro_desc;
		this.pro_stock = pro_stock;
		this.pro_image = pro_image;
	}




	//Product類別 傳入類別代碼+商品名稱+價格+商品詳細+庫存+圖片網址的建構子
	public Product(int enum_id, String pro_name, BigDecimal pro_price, String pro_desc, int pro_stock, String pro_image) {
		super();
		this.enum_id = enum_id;
		this.pro_name = pro_name;
		this.pro_price = pro_price;
		this.pro_desc = pro_desc;
		this.pro_stock = pro_stock;
		this.pro_image = pro_image;
	}
	
	
	//Product類別 全部變數建構子
	public Product(int enum_id, String enum_name, int pro_id, String pro_name, BigDecimal pro_price, String pro_desc,
			int pro_stock, String pro_image) {
		super();
		this.enum_id = enum_id;
		this.enum_name = enum_name;
		this.pro_id = pro_id;
		this.pro_name = pro_name;
		this.pro_price = pro_price;
		this.pro_desc = pro_desc;
		this.pro_stock = pro_stock;
		this.pro_image = pro_image;
	}



	//getter 與 setter
	public int getEnum_id() {
		return enum_id;
	}
	public void setEnum_id(int enum_id) {
		this.enum_id = enum_id;
	}
	
	public String getEnum_name() {
		return enum_name;
	}



	public void setEnum_name(String enum_name) {
		this.enum_name = enum_name;
	}



	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public BigDecimal getPro_price() {
		return pro_price;
	}
	public void setPro_price(BigDecimal pro_price) {
		this.pro_price = pro_price;
	}
	public String getPro_desc() {
		return pro_desc;
	}
	public void setPro_desc(String pro_desc) {
		this.pro_desc = pro_desc;
	}
	public String getPro_image() {
		return pro_image;
	}
	public void setPro_image(String pro_image) {
		this.pro_image = pro_image;
	}
	
	public int getPro_stock() {
		return pro_stock;
	}
	public void setPro_stock(int pro_stock) {
		this.pro_stock = pro_stock;
	}
	
	//toString
	@Override
	public String toString() {
		return "Product [enum_id=" + enum_id + ", pro_id=" + pro_id + ", pro_name=" + pro_name + ", pro_price="
				+ pro_price + ", pro_desc=" + pro_desc + ", pro_stock=" + pro_stock + ", pro_image=" + pro_image + "]";
	}
	
	
	
	

}
