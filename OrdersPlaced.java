package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersPlaced {

	public OrdersPlaced() {

	}

	public String placeOrder(String ISBN, String orderAmount,
			String orderLoginName, Movies movies, Statement stmt) {

		// Get customerID.
		String cid = getCustomerID(orderLoginName, stmt);

		// Get the date
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		String dateOrdered = df.format(new Date());

		// Get the orderid
		String oid = getOrderID(stmt);

		String sql = "insert into OrdersPlaced (oid,cid,ISBN,dateOrdered,qty) values ('"
				+ oid
				+ "','"
				+ cid
				+ "','"
				+ ISBN
				+ "','"
				+ dateOrdered
				+ "','" + orderAmount + "')";
		String output = "";

		int updateQuery = 0;
		System.out.println("executing " + sql);
		try {
			updateQuery = stmt.executeUpdate(sql);

			if (updateQuery != 0) {
				output += "Ordered successfully and " + updateQuery
						+ " row(s) is inserted.";
			}

		} catch (Exception e) {
			System.out.println("cannot execute the query");
			System.out.println(e);
			System.out.println(e.getMessage());
		} finally {
			try {
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	public String getCustomerID(String orderLoginName, Statement stmt) {
		String sql = "select cid from Customers where loginName='"
				+ orderLoginName + "'";
		String output = "";
		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += rs.getString("cid");
			}

			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query");
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	private String getOrderID(Statement stmt) {
		String sql = "insert into Orders (oid) values (null)";
		String output = "";

		int updateQuery = 0;
		System.out.println("executing " + sql);
		try {
			updateQuery = stmt.executeUpdate(sql);
			String lastInsertID = getLastInsertID(stmt);
			if (updateQuery != 0) {
				output += lastInsertID;
			}

		} catch (Exception e) {
			System.out.println("cannot execute the query");
			System.out.println(e);
			System.out.println(e.getMessage());
		} finally {
			try {
			} catch (Exception e) {
				System.out.println("cannot close resultset");
			}
		}
		return output;
	}

	private String getLastInsertID(Statement stmt) {
		String sql = "select last_insert_id()";
		String output = "";
		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output += rs.getString("last_insert_id()");
			}

			rs.close();
		} catch (Exception e) {
			System.out.println("cannot execute the query");
			System.out.println(e);
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println("cannot close resultset");
				System.out.println(e);
				System.out.println(e.getMessage());
			}
		}
		return output;
	}

}
