package cs5530;

import java.sql.*;

public class Customers {

	public Customers() {
	}

	public String registerCustomer(String loginName, String password,
			Statement stmt) {

		// Create mySQL statement.
		String sql = "insert into Customers (loginName,password) values ('"
				+ loginName + "','" + password + "')";
		String output = "";

		int updateQuery = 0;
		System.out.println("executing " + sql);

		try {
			updateQuery = stmt.executeUpdate(sql);

			if (updateQuery != 0) {
				output += "Registered successfully and " + updateQuery
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

	public void getUserData(String loginName, Statement stmt) {

		String sql = "select * from Customers where loginName='" + loginName
				+ "'";

		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.printf("%-30s %-30s %-30s %-30s %-50s %-30s %n", "Name",
					"Login Name", "Password", "Credit Card Number", "Address",
					"Phone Number");
			while (rs.next()) {
				System.out.printf("%-30s %-30s %-30s %-30s %-50s %-30s %n",
						rs.getString("name"), rs.getString("loginName"),
						rs.getString("password"),
						rs.getString("creditCardNumber"),
						rs.getString("address"), rs.getString("phoneNumber"));
			}
			System.out.println("");

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
			}
		}
	}

	public void getOtherUsersData(String loginName, Statement stmt) {

		String sql = "select * from Customers where loginName!='" + loginName
				+ "'";

		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.printf("%-30s %-30s %n", "cid", "Name");
			while (rs.next()) {
				System.out.printf("%-30s %-30s %n", rs.getString("cid"),
						rs.getString("name"));
			}
			System.out.println("");

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
			}
		}
	}
}
