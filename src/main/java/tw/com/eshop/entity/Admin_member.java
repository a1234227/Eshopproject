package tw.com.eshop.entity;

public class Admin_member {
	
	private int admin_id;
	private String admin_name;
	private String admin_account;
	private String admin_password;
	private int admin_active;
	private String admin_phone;
	
	
	//空建構子
	public Admin_member() {
		super();
	}
	
	
	//Admin_member類別 傳入ID+姓名+帳號+密碼+是否激活+電話的建構子
	public Admin_member(int admin_id, String admin_name, String admin_account, String admin_password, int admin_active,
			String admin_phone) {
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.admin_account = admin_account;
		this.admin_password = admin_password;
		this.admin_active = admin_active;
		this.admin_phone = admin_phone;
	}



	//Admin_member類別 傳入姓名+帳號+密碼+電話的建構子
	public Admin_member(String admin_name, String admin_account,
			String admin_password,String admin_phone) {
		super();
		this.admin_name = admin_name;
		this.admin_account = admin_account;
		this.admin_password = admin_password;
		this.admin_phone=admin_phone;
	}
	
	//getter 與 setter
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public int getAdmin_active() {
		return admin_active;
	}
	public void setAdmin_active(int admin_active) {
		this.admin_active = admin_active;
	}

	public String getAdmin_phone() {
		return admin_phone;
	}

	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}

	//toString
	@Override
	public String toString() {
		return "Admin_member [admin_id=" + admin_id + ", admin_name=" + admin_name
				+ ", admin_account=" + admin_account + ", admin_password=" + admin_password + ", admin_active="
				+ admin_active + ", admin_phone=" + admin_phone + "]";
	}
	
	
	
	

}
