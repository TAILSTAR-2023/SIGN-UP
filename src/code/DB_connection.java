package code;

import java.sql.*;

public class DB_connection {
    static String url = "jdbc:mysql://127.0.0.1/signup?serverTimezone=UTC&allowLoadLocalInfile=true";
    static String user = "root";
    static String password = "1234";
    public Connection conn;
    public Statement stmt;

    // 생성자 수정
    public DB_connection(String url, String user, String password) throws SQLException {
        this.url = url;
        this.user = user;
        this.password = password;
        this.conn = DriverManager.getConnection(url, user, password);
    }

    // DB 초기 세팅
    DB_connection() throws SQLException {
//        String url = "jdbc:mysql://127.0.0.1/signup?serverTimezone=UTC&allowLoadLocalInfile=true";
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }

    public static void main(String[] args) {
        try {
            // 여기서 Connection 객체를 생성할 때 예외 처리를 수행합니다.
            DB_connection s = new DB_connection();
            System.out.println("연결 성공");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
}
