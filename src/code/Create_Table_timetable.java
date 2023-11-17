package code;

public class Create_Table_timetable extends Create_Schema {
	
	Create_Table_timetable() throws Exception {
		String table_name = "timetable";
		String check_table = "CHECK TABLE " + schema_name + "." + table_name;
		boolean table_exists = false;

		new DB_connection();
		new Create_Schema();

		// timetable_list 테이블 존재 여부 확인
		table_exists = false;
		if (stmt.execute(check_table))
			if (stmt.getResultSet().next())
				table_exists = true;

		if(!table_exists) { // 테이블이 존재하지 않으면 테이블 생성
			stmt.execute("CREATE TABLE `" + schema_name + "`.`" + table_name + "` (\r\n"
					+ "`id` INT PRIMARY KEY AUTO_INCREMENT, \r\n"
					+ "`user_id` INT, \r\n"
					+ "`major` VARCHAR(30) NOT NULL, \r\n"
					+ "`num` INT NOT NULL PRIMARY KEY, \r\n"
					+ "`class` INT NOT NULL, \r\n"
					+ "`subject` VARCHAR(100) NOT NULL, \r\n"
					+ "`course` VARCHAR(20) NOT NULL, \r\n"
					+ "`score` DOUBLE NOT NULL, \r\n"
					+ "`time` VARCHAR(20) NOT NULL,\r\n"
					+ "`lectureroom` VARCHAR(25) NOT NULL");

			System.out.println("timetable 테이블 생성");
		}
	}
	
	public static void main(String args[]) {
		try {
			new Create_Table_timetable();
			System.out.println("성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
	}
	
}
