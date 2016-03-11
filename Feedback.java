package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Feedback {

	public Feedback() {
	}

	public String leaveFeedback(String ISBN, String comment,
			String feedbackScore, String loginName, Statement stmt) {

		// Get the date
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		String dateLeft = df.format(new Date());

		// Get customerID
		OrdersPlaced ordersPlaced = new OrdersPlaced();
		String cid = ordersPlaced.getCustomerID(loginName, stmt);

		// Create mySQL statement.
		String sql = "insert into Feedback (ISBN,cid,comments,cdate,feedbackScore) values ('"
				+ ISBN
				+ "','"
				+ cid
				+ "','"
				+ comment
				+ "','"
				+ dateLeft
				+ "','" + feedbackScore + "')";
		String output = "";

		int updateQuery = 0;
		System.out.println("executing " + sql);

		try {
			updateQuery = stmt.executeUpdate(sql);

			if (updateQuery != 0) {
				output += "Added feedback successfully and " + updateQuery
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

	public void getFeedbacks(String loginName, Statement stmt) {

		// Get customerID
		OrdersPlaced ordersPlaced = new OrdersPlaced();
		String cid = ordersPlaced.getCustomerID(loginName, stmt);

		String sql = "select * from Feedback where cid!=" + cid;

		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.printf("%-10s %-100s %n", "Fid", "Comments");
			while (rs.next()) {

				System.out.printf("%-10s %-100s %n", rs.getString("fid"),
						rs.getString("comments"));

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
	
	public String getUsefulFeedbacks(int topN, Statement stmt) {

		String output = "";

		String sql = "select F.cid,F.fid, F.comments, F.feedbackScore, R.fidRate,R.rating,AVG(R.rating) AS average from Feedback F, Rates R where F.fid = R.fidRate group by fidRate order by average desc limit 0,3";

		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.printf("%-10s %-100s %n", "Fid", "Comments");
			while (rs.next()) {

				System.out.printf("%-10s %-100s %n", rs.getString("fid"),
						rs.getString("comments"));

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
		return output;
	}
}
