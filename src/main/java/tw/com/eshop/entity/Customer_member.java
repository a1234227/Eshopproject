package tw.com.eshop.entity;

public class Customer_member {
	
	private int cust_id;
	private String cust_name;
	private String cust_account;
	private String cust_password;
	private String cust_address;
	
	//空建構子
	public Customer_member() {
		super();
	}
	
	
	
	//Customer_member類別 傳入ID+姓名+帳號+密碼+地址的建構子
	public Customer_member(int cust_id, String cust_name, String cust_account, String cust_password,
			String cust_address) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_account = cust_account;
		this.cust_password = cust_password;
		this.cust_address = cust_address;
	}




	//Customer_member類別 傳入姓名+帳號+密碼+地址的建構子
	public Customer_member(String cust_name, String cust_account, String cust_password, String cust_address) {
		super();
		this.cust_name = cust_name;
		this.cust_account = cust_account;
		this.cust_password = cust_password;
		this.cust_address = cust_address;
	}


	//getter 跟 setter
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_account() {
		return cust_account;
	}
	public void setCust_account(String cust_account) {
		this.cust_account = cust_account;
	}
	public String getCust_password() {
		return cust_password;
	}
	public void setCust_password(String cust_password) {
		this.cust_password = cust_password;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	
	//toString
	@Override
	public String toString() {
		return "Customer_member [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_account=" + cust_account
				+ ", cust_password=" + cust_password + ", cust_address=" + cust_address + "]";
	}
	
	
	
	

}
