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
    
    // 기존 생성자와 연결 코드 등을 포함한 나머지 부분은 유지하고, 아래의 메소드를 추가
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 사용자 아이디 가져오기
    public String getLoggedInUserId() {
        return "leeminjun";
    }
}
