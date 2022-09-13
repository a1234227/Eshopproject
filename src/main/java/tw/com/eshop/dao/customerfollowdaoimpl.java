package tw.com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tw.com.eshop.dbutil.dbutils;
import tw.com.eshop.entity.Customer_cart;

public class customerfollowdaoimpl extends DAO<Customer_cart> {

	@Override
	public ArrayList<Customer_cart> query(String account) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Customer_cart> result = new ArrayList<>();
		ArrayList<Integer> buffer = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			String queryproductidsql = "select * from customer_follow where account_follow like ?;";
			ps = conn.prepareStatement(queryproductidsql);
			ps.setString(1, account);

			rs = ps.executeQuery();
			while (rs.next()) {
				buffer.add(rs.getInt("product_follow"));
			}
			int[] arr = new int[buffer.size()];
			for (int i = 0; i < buffer.size(); i++) {
				arr[i] = buffer.get(i);
			}
			Customer_cart cart = new Customer_cart();

			cart.setAccount(account);
			cart.setProduct_id(arr);
			result.add(cart);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

	@Override
	public ArrayList<Customer_cart> queryAll() {
		return null;
	}

	/* 這邊還沒做  請不要刪掉 */
//	public int delete1(Customer_cart t) {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		ArrayList<Integer> delete_id = new ArrayList<>();
//		int result1 = 0;
//		int result2 = 0;
//		t.getProduct_id();
//		try {
//			conn = dbutils.getdb().getConnection();
//			String queryidsql = "SELECT id_follow FROM customer_follow where account_follow=?;";
//			ps = conn.prepareStatement(queryidsql);
//			ps.setString(1, t.getAccount());
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				delete_id.add(rs.getInt("id_follow"));
//			}
//			String deletesql = "delete FROM customer_follow where id_follow=?;";
//			for (int i = 0; i < delete_id.size(); i++) {
//				ps = conn.prepareStatement(deletesql);
//				ps.setInt(1, delete_id.get(i));
//				ps.execute();
//			}
//			String addsql = "insert into customer_follow(account_follow,product_follow) value(?,?);";
//			for (int i = 0; i < t.getProduct_id().length; i++) {
//				ps = conn.prepareStatement(addsql);
//				ps.setString(1, t.getAccount());
//				ps.setInt(2, t.getProduct_id()[i]);
//				ps.execute();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbutils.getdb().Close(ps);
//			dbutils.getdb().Close(conn);
//		}
//		if (result1 == 1 && result2 == 1) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}

	public int update1(Customer_cart t) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> delete_id = new ArrayList<>();
		int result1 = 0;
		int result2 = 0;
		t.getProduct_id();
		try {
			conn = dbutils.getdb().getConnection();
			String queryidsql = "SELECT id_follow FROM customer_follow where account_follow=?;";
			ps = conn.prepareStatement(queryidsql);
			ps.setString(1, t.getAccount());
			rs = ps.executeQuery();
			while (rs.next()) {
				delete_id.add(rs.getInt("id_follow"));
			}
			String deletesql = "delete FROM customer_follow where id_follow=?;";
			for (int i = 0; i < delete_id.size(); i++) {
				ps = conn.prepareStatement(deletesql);
				ps.setInt(1, delete_id.get(i));
				ps.execute();
			}
			String addsql = "insert into customer_follow(account_follow,product_follow) value(?,?);";
			for (int i = 0; i < t.getProduct_id().length; i++) {
				ps = conn.prepareStatement(addsql);
				ps.setString(1, t.getAccount());
				ps.setInt(2, t.getProduct_id()[i]);
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}
		if (result1 == 1 && result2 == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public void update(Customer_cart t) {

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
