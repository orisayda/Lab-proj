package iBookServer;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import Entities.BookET;
import Entities.ReaderET;
import Entities.ReviewET;
import Entities.UserET;
import ocsf.server.ConnectionToClient;

public class mysqlConnection {
	Connection con;
	private serverUI window;
	//final private String url = "jdbc:mysql://localhost/database";
	//final private String username = "root";
	//final private String password = "1234";
	final private String url = "jdbc:mysql://localhost:3306/test";
	final private String username;
	final private String password;
	
	
	/**
	 * Constructor
	 */
	public mysqlConnection(serverUI window,String un,String pw) {
		username=new String(un);
		password=new String(pw);
		boolean catched=false;
		this.window = window;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();//Register for the jdbc driver.
		} catch (Exception ex) {/* handle the error */
		}

		try {
			con = DriverManager.getConnection(url, username, password);
			display("SQL Server connecton created.");
		} catch (SQLException ex) {/* handle any errors */
			display("SQLException: " + ex.getMessage());
			catched=true;
		}
		if(!catched){
			
			  window.lblSQL.setText("Online");
			  window.lblSQL.setForeground(Color.GREEN);
		}else{
			window.lblSQL.setText("Offline");
			  window.lblSQL.setForeground(Color.RED);
		}
	}
	
public Object login(Object obj) {
		
		UserET returnObj = (UserET) obj;
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM user WHERE userName = ? AND passWord = ?");
			pStmt.setString(1, returnObj.getUserName());
			pStmt.setString(2, returnObj.getPassWord());
			ResultSet rs = pStmt.executeQuery();

			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No user
										// found).
				display("no user in the ResultSet of login.");
				return "3";

			} else {
				pStmt = con
						.prepareStatement("SELECT * FROM user WHERE userName = ? AND passWord = ? AND status = 0");
				pStmt.setString(1, returnObj.getUserName());
				pStmt.setString(2, returnObj.getPassWord());
				rs = pStmt.executeQuery();

				if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
											// user found).
					display("user is already logged in the system.");
					return "1";
				}

				else if (rs.next()) { // Build the entity and return it to the
							returnObj.SetFromSQL(rs.getInt(1),rs.getInt(4),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
										// server and then to the client.
					rs.close();
					
					PreparedStatement rStmt = con
							.prepareStatement("UPDATE user SET status = 1 WHERE userName = ?");
					rStmt.setString(1, returnObj.getUserName());
					rStmt.executeUpdate();

					
					return returnObj;
				}
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}// End login	
	
public Object logout(Object obj) {
	UserET returnObj = (UserET) obj;
	try {
		PreparedStatement rStmt = con.prepareStatement("UPDATE user SET status = 0 WHERE userName = ?");
		rStmt.setString(1, returnObj.getUserName());
		rStmt.executeUpdate();
		rStmt.close();
		display(returnObj.getUserName() + " logged out from the system.");
		return "User logged out of the system";
	} catch (SQLException e) {
		e.printStackTrace();

	}
	return null;
	
}//End logout

	public Object SearchBook(String text,ArrayList<Integer> cb){
		ArrayList<BookET> returnObj=new ArrayList<BookET>();
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM books");
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
				// user found).
				display("No Results");
				return "1";
			}
			else{
				while(rs.next())
					if(rs.getInt(13)==0){
					for(int i=0;i<cb.size();i++){
						if((rs.getString(cb.get(i)).toLowerCase()).contains(text.toLowerCase())){
							i=cb.size()-1;
							returnObj.add(
								new BookET(rs.getInt(1),
										rs.getString(2),rs.getString(3),
										rs.getString(4),rs.getString(5),
										rs.getString(6),rs.getString(7),
										rs.getString(8),rs.getString(9),
										rs.getString(10),rs.getInt(11),
										rs.getInt(12),rs.getInt(13),
										rs.getInt(14),rs.getInt(15)
								));
						}
				}}
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	public Object SearchReview(String text,String cb){
		ArrayList<ReviewET> returnObj=new ArrayList<ReviewET>();
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM review");
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
				// user found).
				display("No Results");
				return "1";
			}
			else{
				while(rs.next()){
					if(rs.getInt(11)==1){
					if((rs.getString(Integer.parseInt(cb)).toLowerCase()).contains(text.toLowerCase())){
						returnObj.add(
								new ReviewET(rs.getInt(1),
										rs.getInt(2),rs.getString(3),
										rs.getString(4),rs.getInt(5),
										rs.getString(6),rs.getString(7),
										rs.getString(8),rs.getString(9),rs.getInt(10),
										rs.getInt(11)	,rs.getInt(12)
								));
				}}}
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}	
	

	
	public int EnablePayment(ArrayList<Object> details,UserET user){
	
		try {
			Statement  rStmt = con.createStatement();
			
			rStmt.executeUpdate("INSERT INTO reader VALUES ("+user.getId()+","
															 +details.get(0)+","
															+"\'"+details.get(1)+"\'"+","
															+"\'"+details.get(2)+"\'"+","
															+"\'"+details.get(3)+"\'"+","
															+"\'"+details.get(4)+"\'"+","
															+"\'"+details.get(5)+"\'"+","
															+0+","+details.get(6)+")");
			
			display(" User sent enable payment");
			/*
			PreparedStatement pStmt = con
					.prepareStatement("UPDATE user SET permission = 2 WHERE userName = ?");
			pStmt.setString(1, user.getUserName());
			pStmt.executeUpdate();
			*/
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
			
		}
		/*
		ReaderET reader=new ReaderET(user,(int)details.get(0),(String)details.get(1),(String)details.get(2),
				(String)details.get(3),(String)details.get(4),(String)details.get(5),(int)0,(int)details.get(6));
		return reader;
		*/
		return 1;
	}
	
	public int GetBook(UserET user,BookET book){
		try {
			Statement  rStmt = con.createStatement();
			
			rStmt.executeUpdate("INSERT INTO reader_book VALUES ("+user.getId()+","
															 +book.getBID()+","
															+"\'"+book.getBTitle()+"\',"+0+")");
			display(" Book added to User");
			PreparedStatement pStmt = con
					.prepareStatement("UPDATE reader SET book_left=book_left-1 WHERE id = ?");
			pStmt.setInt(1, user.getId());
			pStmt.executeUpdate();
			
			pStmt = con
					.prepareStatement("UPDATE books SET numOfPurchace=numOfPurchace+1 WHERE id = ?");
			pStmt.setInt(1, book.getBID());
			pStmt.executeUpdate();
			/*
			PreparedStatement pStmt = con
					.prepareStatement("UPDATE user SET permission = 2 WHERE userName = ?");
			pStmt.setString(1, user.getUserName());
			pStmt.executeUpdate();
			*/
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
			
		}
		
		return 1;
	}
	
	public Object BookList(int id){
		ArrayList<Integer> booklist1=new ArrayList <Integer>();
		ArrayList<String> booklist2=new ArrayList <String>();
		Map<String,Object> booklist=new HashMap<String,Object>();

		try {
			
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM reader_book WHERE id = ? ");
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				if(rs.getInt(4)==0){
				booklist1.add(rs.getInt(2));
				booklist2.add(rs.getString(3));
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
			
		}
		
		booklist.put("int", booklist1);
		booklist.put("String", booklist2);
		return booklist;

	}
	
	public int CheckApplication(int id){
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM reader WHERE id = ? ");
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 3;}
			else {
				if(rs.next()) return rs.getInt(8);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
		return 1;
	}
	
	public int PublishReview(ReviewET review){
		ReviewET reviewET=review;
		try {
			int id=0;
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM review ");
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()){
				id=rs.getInt(1);
			}
			pStmt = con
					.prepareStatement("SELECT * FROM books WHERE id = ? ");
			pStmt.setInt(1, review.getBookId());
			rs = pStmt.executeQuery();
			if(rs.next()){
				reviewET.setAuthor(rs.getString(4));
				reviewET.setBookphoto(rs.getString(8));
			}
			Statement  rStmt = con.createStatement();
			rStmt.executeUpdate("INSERT INTO review VALUES ("+(id+1)+","
															 +reviewET.getBookId()+","
															+"\'"+reviewET.getBookName()+"\'"+","
															+"\'"+reviewET.getAuthor()+"\'"+","
															+reviewET.getUserId()+","
															+"\'"+reviewET.getUserName()+"\'"+","
															+"\'"+reviewET.getUserPhoto()+"\'"+","
															+"\'"+reviewET.getBookphoto()+"\'"+","
															+"\'"+reviewET.getReview()+"\'"+","
															+reviewET.getRate()+","
															+0+","+0+")");
			
			display(" User sent new review");
			PreparedStatement qStmt = con
					.prepareStatement("UPDATE reader_book SET review = 1 WHERE id = ? AND bookId=? ");
			qStmt.setInt(1, reviewET.getUserId());
			qStmt.setInt(2, reviewET.getBookId());
			qStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();	
			return 0;
		}
		return 1;
		
	}
	
	public int HideBook(String bookId, int choice){
		
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM books WHERE id = ? ");
			pStmt.setString(1, bookId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 0;}
			else {
				rs.next();
				if(choice == 1){
					if(rs.getInt(13)==1)return 2;
					else{
						PreparedStatement qStmt = con
								.prepareStatement("UPDATE books SET hidden = 1 WHERE id = ?");
						qStmt.setString(1, bookId);
						qStmt.executeUpdate();
						return 1;
					}
				}
				else if(choice==0){
					if(rs.getInt(13)==0)return 3;
					else{
						PreparedStatement qStmt = con
								.prepareStatement("UPDATE books SET hidden = 0 WHERE id = ?");
						qStmt.setString(1, bookId);
						qStmt.executeUpdate();
						return 4;
					}
				}
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
		return 0;
	}

	public int FreezeUser(String userId,int choice){
		
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM user WHERE id = ? ");
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 0;}
			else {
				rs.next();
				if(choice==1){
					if(rs.getInt(4)>5)return 2;
					else{
				
						PreparedStatement qStmt = con
							.prepareStatement("UPDATE user SET permission = permission+5 WHERE id = ?");
						qStmt.setString(1, userId);
						qStmt.executeUpdate();
						return 1;
					}
				}else if(choice==0){
					if(rs.getInt(4)<5)return 3;
						else{
							PreparedStatement qStmt = con
								.prepareStatement("UPDATE user SET permission = permission-5 WHERE id = ?");
							qStmt.setString(1, userId);
							qStmt.executeUpdate();
							return 4;
						}
				}

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
		return 0;
	}
	
	public int ChangePermission(String userId, int newPer){
		
		try {
			PreparedStatement pStmt = con
					.prepareStatement("SELECT * FROM user WHERE id = ? ");
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { return 0;}
			else{
				PreparedStatement qStmt = con
					.prepareStatement("UPDATE user SET permission = ? WHERE id = ?");
					qStmt.setInt(1, newPer);
					qStmt.setString(2, userId);
					qStmt.executeUpdate();
					return 1;
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;			
		}
	}
	
	public ArrayList<BookET> UserReport(String userId){
		ArrayList<BookET> returnObj = new ArrayList<BookET>();
		try {
			PreparedStatement pStmt = con
				.prepareStatement("SELECT * FROM test.reader_book WHERE id = ? ");
			pStmt.setString(1, userId);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.isBeforeFirst()) { // Checks if ResultSet is empty (No
			// user found).
				display("The user does not purchased books yet");
				return null;
			}
			else{
				while(rs.next())
						returnObj.add(new BookET(rs.getInt(1),
									/*rs.getString(2)*/"Change Here",rs.getString(3)));
			}
			
			rs.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	public void closeSqlConnection(){

		

		boolean catched=false;
		// crashes if the connection is close when method starts
		// from http://stackoverflow.com/questions/2225221/closing-database-connections-in-java
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			
			System.out.println("SQL Connection closed");
			catched=true;
		}
		
		if(!catched){
			  window.lblSQL.setText("Offline");
			  window.lblSQL.setForeground(Color.RED);
		}else{
			window.lblSQL.setText("Online");
			  window.lblSQL.setForeground(Color.GREEN);
		}
	}
	public int AddUser(Object obj) {
		UserET user = (UserET) obj;
		try
		{
			Statement pStmt = con.createStatement();
			pStmt.execute("SELECT MAX(id) FROM user");
			ResultSet rs = pStmt.getResultSet();
			int id=0;
			if(rs.next())
				id=(int) rs.getObject(1);
			id+=1;
			PreparedStatement rStmt = con.prepareStatement("SELECT * FROM user WHERE userName = ?");
			rStmt.setString(1, user.getUserName());
			rs = rStmt.executeQuery();
			if (!rs.isBeforeFirst()) 
			{
				String SQL = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, id);
				pstmt.setString(2, user.getUserName());
				pstmt.setString(3, user.getPassWord());
				pstmt.setInt(4, 1);
				pstmt.setInt(5, 0);
				pstmt.setString(6, user.getFirstName());
				pstmt.setString(7, user.getLastName());
				pstmt.setString(8, user.getEmail());
				pstmt.setString(9, user.getFirstName());
				pstmt.executeUpdate();
				pstmt.close();
				return 1;
			}
			display("The UserName already in the DB.");
			return 0;
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}


	
private void display(String message) {
	window.display(message);
	
}

}


