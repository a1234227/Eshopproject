/*
Copyright (c) 2022, 2022, HFU and/or its affiliates. All rights reserved.
*/
package tw.com.eshop.entity;

import java.util.Arrays;

/**
 *
 * @author:SU
 * @since:11.0 TODO:
 *
 */
public class Customer_order {
	private String account;
	private int order_id;
	private int[] product_id;
	private int[] amount;

	public int[] getAmount() {
		return amount;
	}

	public void setAmount(int[] amount) {
		this.amount = amount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int[] getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int[] product_id) {
		this.product_id = product_id;
	}

	@Override
	public String toString() {
		return "Customer_order [account=" + account + ", order_id=" + order_id + ", product_id="
				+ Arrays.toString(product_id) + ", amount=" + amount + "]";
	}

	

}
