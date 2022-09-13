/*
Copyright (c) 2022, 2022, HFU and/or its affiliates. All rights reserved.
*/
package tw.com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.com.eshop.dbutil.dbutils;
import tw.com.eshop.entity.Customer_cart;
import tw.com.eshop.entity.Customer_order;

/**
 *
 * @author:SU
 * @since:11.0 TODO:
 *
 */
public class customerorderdaoimpl extends DAO<Customer_order> {

	public boolean add1(Customer_cart t) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int no, r = 0;
		boolean result = false;
		ArrayList<Integer> query_order_ids = new ArrayList<>();
		ArrayList<Integer> delete_id = new ArrayList<>();
		ArrayList<Integer> query_stock =new ArrayList<>();
		try {
			conn = dbutils.getdb().getConnection();
			String queryidsql = "SELECT no_order FROM customer_order where account_order=? order by no_order;";
			ps = conn.prepareStatement(queryidsql);
			ps.setString(1, t.getAccount());
			rs = ps.executeQuery();
			while (rs.next()) {
				query_order_ids.add(rs.getInt("no_order"));
			}
			if (query_order_ids.size() == 0) {
				no = 1;
			} else {
				no = (query_order_ids.get(query_order_ids.size() - 1) + 1);
			}
//			String deletesql = "delete FROM customer_cart where id_cart=?;";
//			for (int i = 0; i < delete_id.size(); i++) {
//				ps = conn.prepareStatement(deletesql);
//				ps.setInt(1, delete_id.get(i));
//				ps.execute();
//			}
			String addsql = "insert into customer_order(account_order,product_order,no_order,amount_order) value(?,?,?,?);";
			for (int i = 0; i < t.getProduct_id().length; i++) {
				ps = conn.prepareStatement(addsql);
				ps.setString(1, t.getAccount());
				ps.setInt(2, t.getProduct_id()[i]);
				ps.setInt(3, no);
				ps.setInt(4, t.getAmount()[i]);
				r = ps.executeUpdate();
				if (r == 0) {
					return false;
				}
			}
			String querysql = "SELECT id_cart FROM customer_cart where account_cart=? and product_cart=?;";
			for (int j = 0; j < t.getProduct_id().length; j++) {
				ps = conn.prepareStatement(querysql);
				ps.setString(1, t.getAccount());
				ps.setInt(2, t.getProduct_id()[j]);
				rs = ps.executeQuery();
				while (rs.next()) {
					delete_id.add(rs.getInt("id_cart"));
				}
			}
			String deletesql = "delete FROM customer_cart where id_cart=?;";
			for (int k = 0; k < delete_id.size(); k++) {
				ps = conn.prepareStatement(deletesql);
				ps.setInt(1, delete_id.get(k));
				r = ps.executeUpdate();
				if (r == 0) {
					return false;
				}
			}
			
			String query_stock_sql = "select * FROM product where pro_id=?;";
			for (int j = 0; j <t.getProduct_id().length; j++) {
				ps = conn.prepareStatement(query_stock_sql);
				ps.setInt(1, t.getProduct_id()[j]);
				rs = ps.executeQuery();
				while (rs.next()) {
					query_stock.add(rs.getInt("pro_stock"));
				}
			}
			
			String update_stock_sql = "update product set pro_stock=? where pro_id=?;";
			for (int j = 0; j < query_stock.size(); j++) {
				ps = conn.prepareStatement(update_stock_sql);
				int new_stock=query_stock.get(j)-t.getAmount()[j];
				ps.setInt(1, new_stock);
				ps.setInt(2, t.getProduct_id()[j]);
				r=ps.executeUpdate();
				if (r == 0) {
					return false;
				}
				
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}
		if (r == 1) {
			return result = true;
		} else {
			return result;
		}

	}

	@Override
	public ArrayList<Customer_order> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Customer_order t) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Customer_order> query(String account) {

		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Customer_order> result = new ArrayList<>();
		ArrayList<Integer> ids = new ArrayList<>();
		ArrayList<Integer> ids_per_form = new ArrayList<>();
		ArrayList<Integer> amount_per_form = new ArrayList<>();
		ResultSet rs = null;
		int index;
		try {

			conn = dbutils.getdb().getConnection();
			String queryidsql = "select no_order from customer_order where account_order like ? order by no_order;";
			ps = conn.prepareStatement(queryidsql);
			ps.setString(1, account);

			rs = ps.executeQuery();
			while (rs.next()) {
				ids.add(rs.getInt("no_order"));
			}
			if(ids.size()==0) {
				return result;
			}
			index = ids.get(ids.size()-1);
			System.out.println("index:"+index);
			for (int j = 0; j < index; j++) {
				ids_per_form.removeAll(ids_per_form);
				amount_per_form.clear();
				String queryprosql = "select * from customer_order where account_order like ? and no_order=?";
				System.out.println("counter:"+j);
				ps = conn.prepareStatement(queryprosql);
				ps.setString(1, account);
				ps.setInt(2, j + 1);

				rs = ps.executeQuery();
				while (rs.next()) {
					ids_per_form.add(rs.getInt("product_order"));
					amount_per_form.add(rs.getInt("amount_order"));
				}
				Customer_order order = new Customer_order();
				order.setOrder_id(j+1);
				int[] ids_arr=new int[ids_per_form.size()];
				int[] amt_arr=new int[amount_per_form.size()];
				for(int i=0;i<ids_per_form.size();i++) {
					ids_arr[i]=ids_per_form.get(i);
					amt_arr[i]=amount_per_form.get(i);
				}
				order.setProduct_id(ids_arr);
				order.setAmount(amt_arr);
				System.out.println(order.toString());
				result.add(order);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

}
