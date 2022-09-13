package tw.com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import tw.com.eshop.dbutil.dbutils;
import tw.com.eshop.entity.Customer_member;

public class customermemberdaoimpl extends DAO<Customer_member> {

	// 資料庫欄位名稱字串
	String cust_id = "cust_id";
	String cust_name = "cust_name";
	String cust_account = "cust_account";
	String cust_password = "cust_password";
	String cust_address = "cust_address";

	// 新增會員
		@Override
		public Integer addreturn(Customer_member t) {

			Connection conn = null;
			PreparedStatement ps = null;
			int result = 0;

			try {
				conn = dbutils.getdb().getConnection();
				String addsql = "insert into customer_member(cust_name,cust_account,cust_password,cust_address) value(?,?,?,?);";
				ps = conn.prepareStatement(addsql);
				ps.setString(1, t.getCust_name());
				ps.setString(2, t.getCust_account());
				ps.setString(3, t.getCust_password());
				ps.setString(4, t.getCust_address());
				result = ps.executeUpdate();
			}catch (SQLIntegrityConstraintViolationException e) {
				final int duplicateInsert = 999;// 資料庫已經有重複的資料時回傳錯誤
				result = duplicateInsert;
			}
			catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbutils.getdb().Close(ps);
				dbutils.getdb().Close(conn);
			}
			return result;
		}

	// 查詢(以名稱)會員
	public ArrayList<Customer_member> query(Integer selecttype,String inputlabel) {
		Integer name=1;
		Integer id=2;
		Integer account=3;
		Integer address=4;
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Customer_member> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			if(selecttype == name) {
				String querynamesql = "select * from customer_member where cust_name like ?;";
				ps = conn.prepareStatement(querynamesql);
				ps.setString(1, "%"+inputlabel+"%");
			}else if(selecttype == id) {
				String queryaccountsql = "select * from customer_member where cust_id = ?;";
				ps = conn.prepareStatement(queryaccountsql);
				ps.setInt(1, Integer.parseInt(inputlabel));
			}else if(selecttype == account) {
				String queryaccountsql = "select * from customer_member where cust_account = ?;";
				ps = conn.prepareStatement(queryaccountsql);
				ps.setString(1, inputlabel);
			}else if(selecttype == address) {
				String queryaddresssql = "select * from customer_member where cust_address like ?;";
				ps = conn.prepareStatement(queryaddresssql);
				ps.setString(1, "%"+inputlabel+"%");
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer_member cust = new Customer_member();
				cust.setCust_id(rs.getInt(cust_id));
				cust.setCust_name(rs.getString(cust_name));
				cust.setCust_account(rs.getString(cust_account));
				cust.setCust_password(rs.getString(cust_password));
				cust.setCust_address(rs.getString(cust_address));
				result.add(cust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

	// 查詢全部會員
	@Override
	public ArrayList<Customer_member> queryAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Customer_member> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			String queryallsql = "select * from customer_member;";
			ps = conn.prepareStatement(queryallsql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer_member cust = new Customer_member();
				cust.setCust_id(rs.getInt(cust_id));
				cust.setCust_name(rs.getString(cust_name));
				cust.setCust_account(rs.getString(cust_account));
				cust.setCust_password(rs.getString(cust_password));
				cust.setCust_address(rs.getString(cust_address));
				result.add(cust);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

	// 刪除會員(ID)
	@Override
	public void delete(Integer id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dbutils.getdb().getConnection();
			String deletesql ="delete from customer_member where cust_id = ?;";
			ps = conn.prepareStatement(deletesql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

	}

	// 修改會員資料
	@Override
	public void update(Customer_member t) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dbutils.getdb().getConnection();
			String updatesql = "update customer_member set cust_name = ? , cust_account = ? , cust_password = ? , cust_address = ? where cust_id = ?;";
			ps = conn.prepareStatement(updatesql);
			ps.setString(1, t.getCust_name());
			ps.setString(2, t.getCust_account());
			ps.setString(3, t.getCust_password());
			ps.setString(4, t.getCust_address());
			ps.setInt(5, t.getCust_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

	}

}
