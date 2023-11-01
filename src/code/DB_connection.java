package code;

import java.sql.*;

public class DB_connection {
	static String url = "jdbc:mysql://127.0.0.1/?serverTimezone=UTC&allowLoadLocalInfile=true";
	static String user = "root";
	static String password = "1234";
	public Connection conn;
	public Statement stmt;
//	static String user = "root";
//	static String pass = "1234";
//	public DB_connection() {
//		
//		var con = DriverManager.getConnection()
	// DB 초기 세팅
	DB_connection() throws Exception {
	      conn = DriverManager.getConnection(url, user, password);
	      stmt = conn.createStatement();
	}
	public static void main(String[] args) {
		try {
			new DB_connection();
			System.out.println("연결성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
