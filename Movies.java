package cs5530;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Movies {

	private HashMap<String, String> currentMovieList = new HashMap<String, String>();

	public Movies() {
	}

	public void getMovies(String genre, Statement stmt) {
		// clear currentMovieList
		currentMovieList.clear();
		String sql = null;

		if (genre.length() != 0) {
			sql = "select * from Movies where genre like '%" + genre + "%'";
		} else {
			sql = "select * from Movies";
		}

		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.printf("%-50s %-30s %-30s %n", "Title", "ISBN", "Genre");
			while (rs.next()) {
				currentMovieList.put(rs.getString("ISBN"),
						rs.getString("title"));

				System.out.printf("%-50s %-30s %-30s %n",
						rs.getString("title"), rs.getString("ISBN"),
						rs.getString("genre"));

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

	public void getMovies(Statement stmt) {
		// clear currentMovieList
		currentMovieList.clear();
		String sql = null;

		sql = "select * from Movies";

		ResultSet rs = null;
		System.out.println("executing " + sql);
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("");
			System.out.printf("%-50s %-30s %-30s %-30s %n", "Title", "ISBN",
					"Genre", "Inventory");
			while (rs.next()) {
				currentMovieList.put(rs.getString("ISBN"),
						rs.getString("title"));

				System.out.printf("%-50s %-30s %-30s %-30s %n",
						rs.getString("title"), rs.getString("ISBN"),
						rs.getString("genre"), rs.getString("inventory"));

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

	public String addMovie(String title, String inventory, String directors,
			String actors, String rating, String yearOfProduction,
			String price, String format, String keywords, String genre,
			Statement stmt) {

		// Create mySQL statement.
		String sql = "insert into Movies (title,inventory,directors,actors,rating,yearOfProduction,price,format,keywords,genre) values "
				+ "('"
				+ title
				+ "','"
				+ inventory
				+ "','"
				+ directors
				+ "','"
				+ actors
				+ "','"
				+ rating
				+ "','"
				+ yearOfProduction
				+ "','"
				+ price
				+ "','"
				+ format
				+ "','"
				+ keywords
				+ "','"
				+ genre
				+ "')";
		String output = "";

		int updateQuery = 0;
		System.out.println("executing " + sql);

		try {
			updateQuery = stmt.executeUpdate(sql);

			if (updateQuery != 0) {
				output += "Added new movie successfully and " + updateQuery
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

	public String increaseInventory(String ISBN, String count, Statement stmt) {
		// Create mySQL statement.
		String sql = "update Movies set inventory=inventory + "
				+ Integer.parseInt(count) + " where ISBN = "
				+ Integer.parseInt(ISBN);
		String output = "";

		int updateQuery = 0;
		System.out.println("executing " + sql);

		try {
			updateQuery = stmt.executeUpdate(sql);

			if (updateQuery != 0) {
				output += "Successfully increased inventory and " + updateQuery
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
	
	public void browseMovies(){
		
	}

}
