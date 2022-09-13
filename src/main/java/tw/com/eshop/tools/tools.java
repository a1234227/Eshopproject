package tw.com.eshop.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import tw.com.eshop.dbutil.dbutils;

public class tools {

	Connection conn = null;
	PreparedStatement ps = null;
	Statement st = null;
	ResultSet rs = null;

	public static tools gettools() {
		return new tools();
	}

	// 員工登入檢查
	public int adminlogincheck(String adminloginaccountinput, String adminloginpasswordinput) {
		int status = 0;
		final int loginfail = -1;

		try {
			conn = dbutils.getdb().getConnection();
			String loginchecksql = "select * from admin_member where admin_account='" + adminloginaccountinput
					+ "' and admin_password='" + adminloginpasswordinput + "';";
			st = conn.createStatement();
			rs = st.executeQuery(loginchecksql);
			if (rs.next()) {
				status=rs.getInt("admin_active");
				return status;
			}else {
				return loginfail;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(st);
			dbutils.getdb().Close(conn);
		}
		return status;

	}

	// 取得現在登入者姓名
	public String getcurrentadminname(String adminloginaccountinput, String adminloginpasswordinput) {
		String adminname = "";
		String admin_name = "admin_name";
		try {
			conn = dbutils.getdb().getConnection();
			String loginchecksql = "select admin_name from admin_member where admin_account='" + adminloginaccountinput
					+ "' and admin_password='" + adminloginpasswordinput + "';";
			st = conn.createStatement();
			rs = st.executeQuery(loginchecksql);
			if (rs.next()) {
				adminname = rs.getString(admin_name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(st);
			dbutils.getdb().Close(conn);
		}

		return adminname;
	}

	// 顧客登入檢查
	public boolean customerlogincheck(String customerloginaccountinput, String customerloginpasswordinput) {
		boolean status = false;

		try {
			conn = dbutils.getdb().getConnection();
			String loginchecksql = "select * from customer_member where cust_account='" + customerloginaccountinput
					+ "' and cust_password='" + customerloginpasswordinput + "';";
			st = conn.createStatement();
			rs = st.executeQuery(loginchecksql);
			if (rs.next()) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(st);
			dbutils.getdb().Close(conn);
		}
		return status;

	}

	// 取得現在登入者姓名
	public String getcurrentcustomername(String customerloginaccountinput, String customerloginpasswordinput) {
		String customername = "";
		String cust_name = "cust_name";

		try {
			conn = dbutils.getdb().getConnection();
			String loginchecksql = "select cust_name from customer_member where cust_account='" + customerloginaccountinput
					+ "' and cust_password='" + customerloginpasswordinput + "';";
			st = conn.createStatement();
			rs = st.executeQuery(loginchecksql);
			if (rs.next()) {
				customername = rs.getString(cust_name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(st);
			dbutils.getdb().Close(conn);
		}
		return customername;

	}

	// 進行登入失敗次數紀錄，達5次時將帳號鎖定並紀錄解鎖時間
	public String loginrestriction(String loginaccountinput) {
		final int derestrict = 0;
		final int restrict = 1;
		final int accountnotvaild = -1;
		
		String result = "";
		//確認帳號狀態，若無此帳號或帳號已是鎖定狀態則不做後續處理。
		int restrictcheck = loginrestrictcheck(loginaccountinput);
		if (restrictcheck == accountnotvaild) {
			result = "accountnotvaild";
			return result;
		} else if (restrictcheck == restrict) {
			result = "restricted";
			return result;
		} 
		
		//若有此帳號且尚未鎖定，進行後續處理
		if (restrictcheck == derestrict) {
			//從資料庫抓取該帳號登入錯誤次數
			try {
				conn = dbutils.getdb().getConnection();
				String checkloginwrongtimesql = "select admin_wronglogintime from admin_member where admin_account = ?;";
				ps = conn.prepareStatement(checkloginwrongtimesql);
				ps.setString(1, loginaccountinput);
				rs = ps.executeQuery();
				if (rs.next()) {
					//加上本次登入失敗，故實際登入錯誤次數為資料庫內數值+1
					int wrongtime = rs.getInt("admin_wronglogintime") + 1;
					//若小於5次，update資料庫內的登入錯誤數值
					if (wrongtime < 5) {
						String addwrongtimesql = "update admin_member set admin_wronglogintime = ? where admin_account = ?;";
						ps = conn.prepareStatement(addwrongtimesql);
						ps.setInt(1, wrongtime);
						ps.setString(2, loginaccountinput);
						ps.executeUpdate();
						result = "wrongtime:" + wrongtime;
					//若已達5次，則帳號鎖定，紀錄帳號鎖定時間，並重製資料庫內登入錯誤數值
					} else if (wrongtime == 5) {
						Instant instant = Instant.now();
						Timestamp currenttime = Timestamp.from(instant);
						String giverestrictsql = "update admin_member set admin_restrict = ? , admin_restricttime = ? , admin_wronglogintime = '0' where admin_account = ?";
						ps = conn.prepareStatement(giverestrictsql);
						ps.setInt(1, restrict);
						ps.setTimestamp(2, currenttime);
						ps.setString(3, loginaccountinput);
						ps.executeUpdate();
						result = "restrict";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbutils.getdb().Close(ps);
				dbutils.getdb().Close(conn);
			}
		}

		return result;
	}

	//確認帳號鎖定狀態
	public int loginrestrictcheck(String loginaccountinput) {
		int admin_restrict = 0;
		final int accountnotvaild = -1;

		try {
			conn = dbutils.getdb().getConnection();
			String checkadminrestrictsql = "select admin_restrict from admin_member where admin_account = ?;";
			ps = conn.prepareStatement(checkadminrestrictsql);
			ps.setString(1, loginaccountinput);
			rs = ps.executeQuery();
			if (rs.next()) {
				admin_restrict = rs.getInt("admin_restrict");
			} else {
				//無此帳號存在
				return accountnotvaild;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return admin_restrict;
	}

	//確認鎖定帳號是否解鎖
	public int restrictunlockcheck(String loginaccountinput,int restrictminutes) {
		final int derestrict = 0;
		final int restrict = 1;
		final int accountnotvaild = -1;
		
		//確認帳號狀態 如果無此帳號則直接return
		int restrictstatus = loginrestrictcheck(loginaccountinput);
		if (restrictstatus == accountnotvaild) {
			return accountnotvaild;
		}else if(restrictstatus == derestrict) {
			return derestrict;			
		}
		
		//如帳號為上鎖，則做後續處理
		Instant instant = Instant.now();
		Timestamp currenttime = Timestamp.from(instant);
		Timestamp restricttime = getrestricttimestamp(loginaccountinput);
		long restricttimelong = restricttime.getTime();
		long delayminuteslong = restrictminutes * 60 * 1000;
		Timestamp derestricttime = new Timestamp(restricttimelong + delayminuteslong);

		//判斷帳號是否已經過了解鎖時間，如是則解鎖並清空鎖定時間，否則回傳仍上鎖
		if (restrictstatus == restrict && derestricttime.before(currenttime)) {
			try {
				conn = dbutils.getdb().getConnection();
				String derestrictsql = "update admin_member set admin_restrict = ? , admin_restricttime = ? where admin_account = ?";
				ps = conn.prepareStatement(derestrictsql);
				ps.setInt(1, derestrict);
				ps.setTimestamp(2, null);
				ps.setString(3, loginaccountinput);
				ps.executeUpdate();
				return derestrict;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbutils.getdb().Close(ps);
				dbutils.getdb().Close(conn);
			}
		} 
		return restrict;
	}

	//取得資料庫內鎖定時間之內部方法
	Timestamp getrestricttimestamp(String loginaccountinput) {
		Timestamp restrict_timestamp = null;

		try {
			conn = dbutils.getdb().getConnection();
			String getrestricttimestampsql = "select admin_restricttime from admin_member where admin_account = ?";
			ps = conn.prepareStatement(getrestricttimestampsql);
			ps.setString(1, loginaccountinput);
			rs = ps.executeQuery();
			if (rs.next()) {
				restrict_timestamp = rs.getTimestamp("admin_restricttime");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return restrict_timestamp;
	}
	
	public void clearloginwrongtime(String loginaccountinput) {
		try {
			conn = dbutils.getdb().getConnection();
			String clearwrongtimesql = "update admin_member set admin_wronglogintime = ? where admin_account = ?";
			ps = conn.prepareStatement(clearwrongtimesql);
			ps.setInt(1, 0);
			ps.setString(2, loginaccountinput);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}
		
	}
	

}
