package code;

public class Create_Schema extends DB_connection {

	static String schema_name = "signup"; // 스키마 이름
	private boolean schema_exists = false; // 스키마 존재 여부를 나타내는 변수
	// 스키마 존재 확인을 위한 SQL 쿼리
	private String check_schema = "SELECT SCHEMA_NAME FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = '" + schema_name + "'";
	
	// 생성자
	Create_Schema() throws Exception {
		// 스키마 존재 여부 확인
		if (stmt.execute(check_schema))
			if (stmt.getResultSet().next())
				schema_exists = true;

		if (!schema_exists) { // 스키마가 존재하지 않으면 스키마 생성
			// 로컬 파일 로드를 허용하기 위한 설정
			stmt.execute("CREATE SCHEMA `" + schema_name + "` DEFAULT CHARACTER SET utf8;");
			stmt.execute("SET GLOBAL local_infile = 1"); 
			System.out.println("스키마 생성");
		}
	}
	
}
