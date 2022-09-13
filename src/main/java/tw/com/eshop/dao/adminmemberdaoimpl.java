package tw.com.eshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import tw.com.eshop.dbutil.dbutils;
import tw.com.eshop.entity.Admin_member;

public class adminmemberdaoimpl extends DAO<Admin_member> {

	// 資料庫欄位名稱字串
	String admin_id = "admin_id";
	String admin_name = "admin_name";
	String admin_account = "admin_account";
	String admin_password = "admin_password";
	String admin_active = "admin_active";
	String admin_phone = "admin_phone";

	// 新增管理員
	@Override
	public Integer addreturn(Admin_member t) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = dbutils.getdb().getConnection();
			String addsql = "insert into admin_member(admin_name,admin_account,admin_password,admin_phone) values(?,?,?,?);";
			ps = conn.prepareStatement(addsql);
			ps.setString(1, t.getAdmin_name());
			ps.setString(2, t.getAdmin_account());
			ps.setString(3, t.getAdmin_password());
			ps.setString(4, t.getAdmin_phone());
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

	// 查詢管理員(以名稱)名單
	public ArrayList<Admin_member> query(Integer selecttype,String inputlabel) {
		Integer name=1;
		Integer id=2;
		Integer account=3;
		Integer phone=4;
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Admin_member> result = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			conn = dbutils.getdb().getConnection();
			if(selecttype == name) {
				String querynamesql ="select * from admin_member where admin_name like ?;";
				ps = conn.prepareStatement(querynamesql);
				ps.setString(1, "%"+inputlabel+"%");
			}else if(selecttype == id) {
				String queryidsql ="select * from admin_member where admin_id = ?;";
				ps = conn.prepareStatement(queryidsql);
				ps.setInt(1, Integer.parseInt(inputlabel));
			}else if(selecttype == account) {
				String queryaccountsql ="select * from admin_member where admin_account = ?;";
				ps = conn.prepareStatement(queryaccountsql);
				ps.setString(1, inputlabel);
			}else if(selecttype == phone) {
				String queryphonesql ="select * from admin_member where admin_phone　= ?;";
				ps = conn.prepareStatement(queryphonesql);
				ps.setString(1, inputlabel);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Admin_member admin = new Admin_member();
				admin.setAdmin_id(rs.getInt(admin_id));
				admin.setAdmin_name(rs.getString(admin_name));
				admin.setAdmin_account(rs.getString(admin_account));
				admin.setAdmin_password(rs.getString(admin_password));
				admin.setAdmin_active(rs.getInt(admin_active));
				admin.setAdmin_phone(rs.getString(admin_phone));
				result.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}
		return result;
	}

	// 查詢全部管理員
	@Override
	public ArrayList<Admin_member> queryAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Admin_member> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			String queryallsql = "select * from admin_member;";
			ps = conn.prepareStatement(queryallsql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Admin_member admin = new Admin_member();
				admin.setAdmin_id(rs.getInt(admin_id));
				admin.setAdmin_name(rs.getString(admin_name));
				admin.setAdmin_account(rs.getString(admin_account));
				admin.setAdmin_password(rs.getString(admin_password));
				admin.setAdmin_active(rs.getInt(admin_active));
				admin.setAdmin_phone(rs.getString(admin_phone));
				result.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

	// 刪除管理員(以ID)
	@Override
	public void delete(Integer id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dbutils.getdb().getConnection();
			String deletesql ="delete from admin_member where admin_id = ?;";
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

	// 修改管理員資料
	@Override
	public void update(Admin_member t) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dbutils.getdb().getConnection();
			String updatesql = "update admin_member set admin_name = ? , admin_account = ? , admin_password = ? , admin_phone = ? , admin_active = ? where admin_id = ?;";
			ps = conn.prepareStatement(updatesql);
			ps.setString(1, t.getAdmin_name());
			ps.setString(2, t.getAdmin_account());
			ps.setString(3, t.getAdmin_password());
			ps.setString(4, t.getAdmin_phone());
			ps.setInt(5, t.getAdmin_active());
			ps.setInt(6, t.getAdmin_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

	}

}
