package tw.com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import tw.com.eshop.dbutil.dbutils;
import tw.com.eshop.entity.Product_enum;

public class productenumdaoimpl extends DAO<Product_enum> {
	
	// 資料庫欄位名稱字串
		String product_enum_id = "product_enum_id";
		String product_enum_name = "product_enum_name";

	@Override
	public Integer addreturn(Product_enum t) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			conn = dbutils.getdb().getConnection();
			String addsql = "insert into product_enum(product_enum_name) values(?);";
			ps = conn.prepareStatement(addsql);
			ps.setString(1, t.getEnum_name());
			result = ps.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			final int duplicateInsert = 999;// 資料庫已經有重複的資料時回傳錯誤
			result = duplicateInsert;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}
		return result;
	}

	@Override
	public ArrayList<Product_enum> query(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Product_enum> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			String querynamesql = "select * from product_enum where product_enum_name = ?;";
			ps = conn.prepareStatement(querynamesql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product_enum product_enum = new Product_enum();
				product_enum.setEnum_id(rs.getInt(product_enum_id));
				product_enum.setEnum_name(rs.getString(product_enum_name));
				result.add(product_enum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

	@Override
	public ArrayList<Product_enum> queryAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Product_enum> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			String queryallsql = "select * from product_enum ORDER BY product_enum_id;";
			ps = conn.prepareStatement(queryallsql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product_enum product_enum = new Product_enum();
				product_enum.setEnum_id(rs.getInt(product_enum_id));
				product_enum.setEnum_name(rs.getString(product_enum_name));
				result.add(product_enum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

	@Override
	public void delete(Integer id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dbutils.getdb().getConnection();
			String deletesql = "delete from product_enum where product_enum_id = ?;";
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

	@Override
	public void update(Product_enum t) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dbutils.getdb().getConnection();
			String updatesql = "update product_enum set product_enum_name = ? where product_enum_id = ?;";
			ps = conn.prepareStatement(updatesql);
			ps.setString(1, t.getEnum_name());
			ps.setInt(2, t.getEnum_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}
		
	}

}
