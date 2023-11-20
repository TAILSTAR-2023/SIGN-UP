package code;

public class Create_Table_student_management extends Create_Schema {

    // 생성자
    public Create_Table_student_management() throws Exception {
        // 테이블 이름
        String table_name = "student_management";

        // 테이블 존재 여부 확인을 위한 SQL 쿼리
        String check_table = "CHECK TABLE " + schema_name + "." + table_name;

        // 테이블 존재 여부를 나타내는 변수
        boolean table_exists = false;

        // 데이터베이스 연결 및 스키마 생성
        new DB_connection();
        new Create_Schema();

        // student_management 테이블 존재 여부 확인
        table_exists = false;
        if (stmt.execute(check_table))
            if (stmt.getResultSet().next())
                table_exists = true;

        // 테이블이 존재하지 않으면 테이블 생성
        if (!table_exists) {
            stmt.execute("CREATE TABLE `" + schema_name + "`.`" + table_name + "` (\r\n"
                    + "`major` VARCHAR(30) NOT NULL, \r\n"
                    + "`name` VARCHAR(10) NOT NULL, \r\n"
                    + "`grade` INT NOT NULL PRIMARY KEY, \r\n"
                    + "`sID` VARCHAR(10) NOT NULL)");

            System.out.println("student_management 테이블 생성");
        }
    }

    // main 메서드
    public static void main(String args[]) {
        try {
            new Create_Table_student_management();
            System.out.println("성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("실패");
        }
    }

}
