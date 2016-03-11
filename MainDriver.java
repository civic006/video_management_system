package cs5530;

import java.sql.*;
import java.io.*;

public class MainDriver {

	/**
	 * @param args
	 */
	public static void displayMenu() {
		System.out.println("Video Management System");
		System.out.println("1.\tEnter your onw query:");
		System.out.println("2.\tRegister a new user:");
		System.out.println("3.\tOrder a movie:");
		System.out.println("4.\tPrint my user records:");
		System.out.println("5.\tAdd a new movie to the collection:");
		System.out.println("6.\tUpdate inventory for an existing movie:");
		System.out.println("7.\tLeave feedback for a movie:");
		System.out.println("8.\tRate another persons feedback:");
		System.out.println("9.\tDeclare another user as trusted:");
		System.out.println("10.\tBrowse movies:");
		System.out.println("11.\tFind most useful feedbacks:");
		System.out.println("12.\tSee your buying suggestions:");
		System.out
				.println("13.\tFind 2 degrees of seperation given actors names:");
		System.out.println("14.\tObtain store manager statistics:");
		System.out.println("15.\tSee this weeks users awards:");
		System.out.println("16.\tExit:");
		System.out.println("");
		System.out.println("Please enter your choice:");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println("Example for cs5530");
		Connector con = null;
		String choice;
		String loginName, password;
		String genre, ISBN, orderAmount, orderLoginName;
		String userLoginName;
		String newMovieTitle, inventory, director, actors, movieRating, yearOfProduction, moviePrice, movieFormat, movieGenre, movieKeyword;
		String ISBNInventory;
		String movieComment, movieScore, feedbackLoginName;
		String feedbackRate, feedBackFinalScore;
		String trustedCid;
		String actorName;
		String sql = null;
		int c = 0;
		try {
			// remember to replace the password
			con = new Connector();
			// System.out.println("Database connection established");
			// System.out.println("");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));

			while (true) {
				displayMenu();
				while ((choice = in.readLine()) == null && choice.length() == 0)
					;
				try {
					c = Integer.parseInt(choice);
				} catch (Exception e) {
					continue;
				}
				if (c < 1 | c > 16)
					continue;
				if (c == 1) {
					System.out.println("please enter your query below:");
					while ((sql = in.readLine()) == null && sql.length() == 0)
						System.out.println(sql);
					ResultSet rs = con.stmt.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int numCols = rsmd.getColumnCount();
					while (rs.next()) {
						// System.out.print("cname:");
						for (int i = 1; i <= numCols; i++)
							System.out.print(rs.getString(i) + "  ");
						System.out.println("");
					}
					System.out.println(" ");
					rs.close();
				} else if (c == 2) {
					System.out.println("Please enter a loginName:");
					while ((loginName = in.readLine()) == null
							&& loginName.length() == 0)
						;
					System.out.println("Please enter a password:");
					while ((password = in.readLine()) == null
							&& password.length() == 0)
						;
					Customers customers = new Customers();
					System.out.println(customers.registerCustomer(loginName,
							password, con.stmt));
				} else if (c == 3) {
					System.out
							.println("Please pick a genre(leave blank and press enter for all genres):");
					while ((genre = in.readLine()) == null
							&& genre.length() == 0)
						;
					Movies movies = new Movies();
					movies.getMovies(genre, con.stmt);

					System.out
							.println("Please type the ISBN of the movie you would like to order:");
					while ((ISBN = in.readLine()) == null && ISBN.length() == 0)
						;
					System.out
							.println("Please type the amount you would like to order:");
					while ((orderAmount = in.readLine()) == null
							&& orderAmount.length() == 0)
						;
					System.out.println("Please type your loginName:");
					while ((orderLoginName = in.readLine()) == null
							&& orderLoginName.length() == 0)
						;
					OrdersPlaced ordersPlaced = new OrdersPlaced();
					System.out.println(ordersPlaced.placeOrder(ISBN,
							orderAmount, orderLoginName, movies, con.stmt));
				} else if (c == 4) {
					System.out.println("Please type your loginName:");
					while ((userLoginName = in.readLine()) == null
							&& userLoginName.length() == 0)
						;
					Customers customers = new Customers();
					customers.getUserData(userLoginName, con.stmt);
				} else if (c == 5) {
					System.out.println("Please type the movie title:");
					while ((newMovieTitle = in.readLine()) == null
							&& newMovieTitle.length() == 0)
						;
					System.out
							.println("Please type the inventory we will have on hand:");
					while ((inventory = in.readLine()) == null
							&& inventory.length() == 0)
						;
					System.out
							.println("Please type the director of the movie:");
					while ((director = in.readLine()) == null
							&& director.length() == 0)
						;
					System.out
							.println("Please type the actors of the movie with each followed by a comma:");
					while ((actors = in.readLine()) == null
							&& actors.length() == 0)
						;
					System.out
							.println("Please type the rating of the movie(i.e. G,PG-13,R):");
					while ((movieRating = in.readLine()) == null
							&& movieRating.length() == 0)
						;
					System.out
							.println("Please type the year of production for the movie:");
					while ((yearOfProduction = in.readLine()) == null
							&& yearOfProduction.length() == 0)
						;
					System.out.println("Please type the price of the movie:");
					while ((moviePrice = in.readLine()) == null
							&& moviePrice.length() == 0)
						;
					System.out
							.println("Please type the format of the movie(i.e. DVD, VHS Blue-Ray):");
					while ((movieFormat = in.readLine()) == null
							&& movieFormat.length() == 0)
						;
					System.out
							.println("Please type keywords for the movie with each keyword followed by a comma:");
					while ((movieKeyword = in.readLine()) == null
							&& movieKeyword.length() == 0)
						;
					System.out
							.println("Please type genres for the movie with each genre followed by a comma:");
					while ((movieGenre = in.readLine()) == null
							&& movieGenre.length() == 0)
						;
					Movies movies = new Movies();
					System.out.println(movies.addMovie(newMovieTitle,
							inventory, director, actors, movieRating,
							yearOfProduction, moviePrice, movieFormat,
							movieKeyword, movieGenre, con.stmt));
				} else if (c == 6) {
					Movies movies = new Movies();
					movies.getMovies(con.stmt);
					System.out
							.println("Please type the ISBN of the movie you would like to add inventory to:");
					while ((ISBNInventory = in.readLine()) == null
							&& ISBNInventory.length() == 0)
						;
					System.out
							.println("Please type how much inventory to add:");
					while ((inventory = in.readLine()) == null
							&& inventory.length() == 0)
						;
					System.out.println(movies.increaseInventory(ISBNInventory,
							inventory, con.stmt));
				} else if (c == 7) {
					Movies movies = new Movies();
					movies.getMovies(con.stmt);
					System.out
							.println("Please type the ISBN of the movie you would like to leave feedback for:");
					while ((ISBN = in.readLine()) == null && ISBN.length() == 0)
						;
					System.out
							.println("Please enter your comment about the movie:");
					while ((movieComment = in.readLine()) == null
							&& movieComment.length() == 0)
						;
					System.out
							.println("Please enter your score for the movie from 1 to 10 with 10 being the highest:");
					while ((movieScore = in.readLine()) == null
							&& movieScore.length() == 0)
						;
					System.out.println("Please enter your loginName:");
					while ((feedbackLoginName = in.readLine()) == null
							&& feedbackLoginName.length() == 0)
						;
					Feedback feedback = new Feedback();
					System.out.println(feedback.leaveFeedback(ISBN,
							movieComment, movieScore, feedbackLoginName,
							con.stmt));
				} else if (c == 8) {
					Feedback feedback = new Feedback();
					System.out.println("Please enter your loginName:");
					while ((userLoginName = in.readLine()) == null
							&& userLoginName.length() == 0)
						;
					feedback.getFeedbacks(userLoginName, con.stmt);
					System.out
							.println("Please enter the fid of the feedback you would like to rate:");
					while ((feedbackRate = in.readLine()) == null
							&& feedbackRate.length() == 0)
						;
					System.out
							.println("Please rate the selected feedback on a scale from 0 to 2 with 2 being the most helpful:");
					while ((feedBackFinalScore = in.readLine()) == null
							&& feedBackFinalScore.length() == 0)
						;
					Rates rates = new Rates();
					System.out.println(rates.leaveRating(userLoginName,
							feedbackRate, feedBackFinalScore, con.stmt));
				} else if (c == 9) {
					System.out.println("Please enter your loginName:");
					while ((userLoginName = in.readLine()) == null
							&& userLoginName.length() == 0)
						;
					Customers customers = new Customers();
					customers.getOtherUsersData(userLoginName, con.stmt);
					System.out
							.println("Please select the friend/user cid of who you would like to trust:");
					while ((trustedCid = in.readLine()) == null
							&& trustedCid.length() == 0)
						;
					Trusted trusted = new Trusted();
					System.out.println(trusted.addTrusted(userLoginName,
							trustedCid, con.stmt));
				} else if (c == 10) {
					System.out.println("");
					System.out
							.println("Down for maintenance, please pick another category");
					System.out.println("");
					
					//TODO uncomment.
					// System.out.println("Please enter an actor:");
					// while ((actorName = in.readLine()) == null
					// && actorName.length() == 0)
					// ;
					Movies movies = new Movies();
					movies.browseMovies();
					
					continue;
				} else if (c == 11) {
					System.out.println("");
					System.out
							.println("Down for maintenance, please pick another category");
					System.out.println("");
					continue;
				} else if (c == 12) {
					System.out.println("");
					System.out
							.println("Down for maintenance, please pick another category");
					System.out.println("");
					continue;
				} else if (c == 13) {
					System.out.println("");
					System.out
							.println("Down for maintenance, please pick another category");
					System.out.println("");
					continue;
				} else if (c == 14) {
					System.out.println("");
					System.out
							.println("Down for maintenance, please pick another category");
					System.out.println("");
					continue;
				} else if (c == 15) {
					System.out.println("");
					System.out
							.println("Down for maintenance, please pick another category");
					System.out.println("");
					continue;
				} else {
					System.out.println("GOODBYE!");
					con.stmt.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Cannot connect to database server");
		} finally {
			if (con != null) {
				try {
					con.closeConnection();
					System.out.println("Database connection terminated");
				}

				catch (Exception e) { /* ignore close errors */
				}
			}
		}
	}
}
