package code;

public class Create_Table_student_join extends Create_Schema {

	public Create_Table_student_join() throws Exception {
		String table_name = "student_join";
		String check_table = "CHECK TABLE " + schema_name + "." + table_name;
		boolean table_exists = false;

		new DB_connection();
		new Create_Schema();

		// student_join 테이블 존재 여부 확인
		table_exists = false;
		if (stmt.execute(check_table))
			if (stmt.getResultSet().next())
				table_exists = true;
		if(!table_exists) { // 테이블이 존재하지 않으면 테이블 생성
	         stmt.execute("CREATE TABLE `" + schema_name + "`.`" + table_name + "` (\r\n"
	               + "`name` VARCHAR(7) NOT NULL, \r\n"
	               + "`id` VARCHAR(20) NOT NULL, \r\n"
	               + "`pw` VARCHAR(20) NOT NULL, \r\n"
	               + "`pwck` VARCHAR(20) NOT NULL, \r\n"
	               + "`tel` VARCHAR(15) NOT NULL, \r\n"
	               + "`email` VARCHAR(30) NOT NULL)");

	         System.out.println("student_join 테이블 생성");
	      }
	}
	
	public static void main(String args[]) {
		try {
			new Create_Table_student_join();
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
	}
	
}
