package cs5530;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rates {

	public Rates() {
	}

	public String leaveRating(String userLoginName, String fid, String rating,
			Statement stmt) {

		// Get customerID
		OrdersPlaced ordersPlaced = new OrdersPlaced();
		String cid = ordersPlaced.getCustomerID(userLoginName, stmt);

		// Create mySQL statement.
		String sql = "insert into Rates (cidRate,fidRate,rating) values ('"
				+ cid + "','" + fid + "','" + Integer.parseInt(rating) + "')";
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
