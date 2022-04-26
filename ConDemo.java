package com.org.gpact.day12;
import java.sql.Connection;
import java.sql.*;
public class ConDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loading...");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/genpactdb","root","Password");
			
			System.out.println("Connection established....");
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("Select * from emp_info");
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
				
				
			}
			st.close();
			con.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		

	}

}