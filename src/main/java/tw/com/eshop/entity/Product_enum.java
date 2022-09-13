package tw.com.eshop.entity;

public class Product_enum {
	
	private int enum_id;
	private String enum_name;
	
	
	public Product_enum() {
		super();
	}
	
	
	
	
	public Product_enum(String enum_name) {
		super();
		this.enum_name = enum_name;
	}




	public Product_enum(int enum_id, String enum_name) {
		super();
		this.enum_id = enum_id;
		this.enum_name = enum_name;
	}


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
	@Override
	public String toString() {
		return "Product_enum [enum_id=" + enum_id + ", enum_name=" + enum_name + "]";
	}
	
	

}
