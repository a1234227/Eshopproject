package tw.com.eshop.entity;

import java.util.Arrays;

public class Customer_cart {
	
	private int[] product_id;
	private String account;
	private int[] amount;
	public int[] getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int[] product_id) {
		this.product_id = product_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	public int[] getAmount() {
		return amount;
	}
	public void setAmount(int[] amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Customer_cart [product_id=" + Arrays.toString(product_id) + ", account=" + account + ", amount="
				+ amount + "]";
	}
	
	
	
	
}
