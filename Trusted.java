package cs5530;

import java.sql.Statement;

public class Trusted {

	public Trusted() {
	}

	public String addTrusted(String loginName, String trustedCid, Statement stmt) {
		// Get customerID
		OrdersPlaced ordersPlaced = new OrdersPlaced();
		String cid = ordersPlaced.getCustomerID(loginName, stmt);

		// Create mySQL statement.
		String sql = "insert into Trusted (cid1,cid2,isTrusted) values ('"
				+ cid + "','" + trustedCid + "','" + 1 + "')";
		String output = "";

		int updateQuery = 0;
		System.out.println("executing " + sql);

		try {
			updateQuery = stmt.executeUpdate(sql);

			if (updateQuery != 0) {
				output += "Added a rating to some feedback and " + updateQuery
						+ " row(s) is inserted.\n";
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

}
