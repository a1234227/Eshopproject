package tw.com.eshop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import tw.com.eshop.dbutil.dbutils;
import tw.com.eshop.entity.Product;

public class productdaoimpl extends DAO<Product> {

	// 資料庫欄位名稱字串
	String product_enum_id = "product_enum_id";
	String product_enum_name = "product_enum_name";
	String pro_id = "pro_id";
	String pro_name = "pro_name";
	String pro_price = "pro_price";
	String pro_desc = "pro_desc";
	String pro_image = "pro_image";
	String pro_stock = "pro_stock";

	// 新增商品
	@Override
	public Integer addreturn(Product t) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			conn = dbutils.getdb().getConnection();
			String addsql = "insert into product(pro_name,pro_price,pro_desc,pro_image,pro_stock,product_enum_id) values(?,?,?,?,?,?);";
			ps = conn.prepareStatement(addsql);
			ps.setString(1, t.getPro_name());
			ps.setBigDecimal(2, t.getPro_price());
			ps.setString(3, t.getPro_desc());
			ps.setString(4, t.getPro_image());
			ps.setInt(5, t.getPro_stock());
			ps.setInt(6, t.getEnum_id());
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

	// 查詢(以名稱)商品

	// 查詢商品(總類或品名)

	public ArrayList<Product> query(Integer selecttype, String inputlabel) {
		Integer enum_name = 1;
		Integer product_name = 2;
		Integer product_id = 3;
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Product> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			if (selecttype == enum_name) {
				String queryenumsql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id where product_enum.product_enum_name like ?;";
				ps = conn.prepareStatement(queryenumsql);
			} else if (selecttype == product_name) {
				String querypro_namesql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id where product.pro_name like ?;";
				ps = conn.prepareStatement(querypro_namesql);
			} else if (selecttype == product_id) {
				String querypro_namesql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id where product.pro_id = ?;";
				ps = conn.prepareStatement(querypro_namesql);
			}
			if (selecttype == enum_name || selecttype == product_name) {
				ps.setString(1, "%" + inputlabel + "%");
			} else if (selecttype == product_id) {
				ps.setInt(1, Integer.parseInt(inputlabel));
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setEnum_id(rs.getInt(product_enum_id));
				product.setEnum_name(rs.getString(product_enum_name));
				product.setPro_id(rs.getInt(pro_id));
				product.setPro_name(rs.getString(pro_name));
				product.setPro_price(rs.getBigDecimal(pro_price));
				product.setPro_desc(rs.getString(pro_desc));
				product.setPro_image(rs.getString(pro_image));
				product.setPro_stock(rs.getInt(pro_stock));
				result.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;

	}

	// 查詢全部商品
	@Override
	public ArrayList<Product> queryAll() {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Product> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
			String queryallsql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id;";
			ps = conn.prepareStatement(queryallsql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setEnum_id(rs.getInt(product_enum_id));
				product.setEnum_name(rs.getString(product_enum_name));
				product.setPro_id(rs.getInt(pro_id));
				product.setPro_name(rs.getString(pro_name));
				product.setPro_price(rs.getBigDecimal(pro_price));
				product.setPro_desc(rs.getString(pro_desc));
				product.setPro_image(rs.getString(pro_image));
				product.setPro_stock(rs.getInt(pro_stock));
				result.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;
	}

	// 刪除商品
	@Override
	public void delete(Integer id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dbutils.getdb().getConnection();
			String deletesql = "delete from product where pro_id = ?;";
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

	// 修改商品
	@Override
	public void update(Product t) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dbutils.getdb().getConnection();
			String updatesql = "update product set product_enum_id = ? , pro_name = ? , pro_desc = ? , pro_price = ? , pro_stock = ? , pro_image = ? where pro_id = ?;";
			ps = conn.prepareStatement(updatesql);
			ps.setInt(1, t.getEnum_id());
			ps.setString(2, t.getPro_name());
			ps.setString(3, t.getPro_desc());
			ps.setBigDecimal(4, t.getPro_price());
			ps.setInt(5, t.getPro_stock());
			ps.setString(6, t.getPro_image());
			ps.setInt(7, t.getPro_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

	}

	public Product query_lite(String id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		try {
			conn = dbutils.getdb().getConnection();
			String querypro_namesql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id where product.pro_id = ?;";
			ps = conn.prepareStatement(querypro_namesql);
			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setPro_name(rs.getString(pro_name));
				product.setPro_image(rs.getString(pro_image));
				product.setPro_price(rs.getBigDecimal(pro_price));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return product;

	}
	
	public ArrayList<Product> query_search(String inputlabel) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Product> result = new ArrayList<>();
		ResultSet rs = null;

		try {
			conn = dbutils.getdb().getConnection();
				String querypro_namesql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id where product.pro_name like ?;";
				ps = conn.prepareStatement(querypro_namesql);
				ps.setString(1, "%" + inputlabel + "%");
			
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setEnum_id(rs.getInt(product_enum_id));
				product.setEnum_name(rs.getString(product_enum_name));
				product.setPro_id(rs.getInt(pro_id));
				product.setPro_name(rs.getString(pro_name));
				product.setPro_price(rs.getBigDecimal(pro_price));
				product.setPro_desc(rs.getString(pro_desc));
				product.setPro_image(rs.getString(pro_image));
				product.setPro_stock(rs.getInt(pro_stock));
				result.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;

	}
	
	public ArrayList<Product> query_search(int min,int max) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Product> result = new ArrayList<>();
		ResultSet rs = null;
		BigDecimal min_t=new BigDecimal(min);
		BigDecimal max_t=new BigDecimal(max);

		try {
			conn = dbutils.getdb().getConnection();
			 
				String querypro_namesql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id where product.pro_price between ? and ?;";
				ps = conn.prepareStatement(querypro_namesql);
				ps.setBigDecimal(1, min_t);
				ps.setBigDecimal(2, max_t);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setEnum_id(rs.getInt(product_enum_id));
				product.setEnum_name(rs.getString(product_enum_name));
				product.setPro_id(rs.getInt(pro_id));
				product.setPro_name(rs.getString(pro_name));
				product.setPro_price(rs.getBigDecimal(pro_price));
				product.setPro_desc(rs.getString(pro_desc));
				product.setPro_image(rs.getString(pro_image));
				product.setPro_stock(rs.getInt(pro_stock));
				result.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutils.getdb().Close(ps);
			dbutils.getdb().Close(conn);
		}

		return result;

	}
	
	public ArrayList<Product> query_search(String inputlabel,int min,int max) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Product> result = new ArrayList<>();
		ResultSet rs = null;
		BigDecimal min_t=new BigDecimal(min);
		BigDecimal max_t=new BigDecimal(max);

		try {
			conn = dbutils.getdb().getConnection();
			 
				String querypro_namesql = "select * from product inner join product_enum on product.product_enum_id=product_enum.product_enum_id where product.pro_name like ? and product.pro_price between ? and ?;";
				ps = conn.prepareStatement(querypro_namesql);
				ps.setString(1, "%"+inputlabel+"%");
				ps.setBigDecimal(2, min_t);
				ps.setBigDecimal(3, max_t);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setEnum_id(rs.getInt(product_enum_id));
				product.setEnum_name(rs.getString(product_enum_name));
				product.setPro_id(rs.getInt(pro_id));
				product.setPro_name(rs.getString(pro_name));
				product.setPro_price(rs.getBigDecimal(pro_price));
				product.setPro_desc(rs.getString(pro_desc));
				product.setPro_image(rs.getString(pro_image));
				product.setPro_stock(rs.getInt(pro_stock));
				result.add(product);
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
