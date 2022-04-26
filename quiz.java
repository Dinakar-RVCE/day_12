package com.org.gpact.day12;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.cj.protocol.Resultset;

public class quiz {

	public final int result = 1;

	public Connection conn() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loading....");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admin", "root","admin");
			System.out.println("Connection establishing");

			return con;
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("connect is not established...");
			return null;
		}
	}

	public ResultSet question() {
		Connection con = conn();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from sportscars");
//			rs.next();
//			System.out.println(rs.getInt(1) + " " + rs.getString(2));
			return rs;
			
		} catch (Exception e) {
			return null;
		}
	}
	
	public void update(int qid) {
		Connection con = conn();
		try {
			PreparedStatement pstmt = con.prepareStatement("update sportscars set result=? where id=?");
			pstmt.setInt(1, result);
			pstmt.setInt(2, qid);
			boolean res = pstmt.execute();
			if (res == false) {
				System.out.println("updated");
			} else {
				System.out.println("no updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Reset() {
		Connection con = conn();
		try {
			Statement st = con.createStatement();
			st.executeUpdate("update quizlet set result=0;");
			System.out.println("Score reset was successfull !!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void Points() {
		Connection con = conn();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select sum(result) from quizlet;");
			rs.next();
			int points = rs.getInt(1);
			System.out.println("Your Current Score is: "+points);
			if(points >=4) 
			{
				System.out.println("Exemplary");
			}
			else {
				System.out.println("Try Again anytime :D");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		quiz qz = new quiz();
		ResultSet rs = qz.question();
		try {
			System.out.println("=======The Quiz has Commenced=========");
			System.out.println("---------Booting up special questions--------");
			System.out.println("HERE WE GO");
			for(int i=1; i<=4; i++) {
				rs.next();
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
				String ans1 = sc.next();
				
				if(ans1.equals(rs.getString(3))) {
					System.out.println("BINGO! The right answer is .... "+rs.getString(3));
					qz.update(i);
				}
				else {
					System.out.println("So close, wrong answer :(");
					
				}
			}
			
			qz.Points();
			qz.Reset();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}