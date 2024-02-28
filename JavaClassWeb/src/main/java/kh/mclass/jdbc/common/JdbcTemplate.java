package kh.mclass.jdbc.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTemplate { // 중복되는 connection, close 함
	// 싱글톤을 닮았지만 conn을 빠르게 close해야 되어서 싱클톤은 아님
	private JdbcTemplate() {
		// 생성자 private 가능
	}

	public static Connection getConnection() { // static이 중요 new하지 않고 호출하기 위함ㄴ
		Connection conn = null;
		Properties prop = new Properties();
		try {
			// JdbcTemplate 위치의 리소스를 가져와               /*URL 형태로 리턴해줌*/
			String currnetPath = JdbcTemplate.class.getResource("").getPath();/*String 형태로 변환*/
			System.out.println(currnetPath);
			///C:/Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/JavaClassWeb/WEB-INF/classes/kh/mclass/jdbc/common/
			//.metadata아래에 위치함
			prop.load(new FileReader(currnetPath+"driver.properties"));
			System.out.println(prop.getProperty("jdbc.url"));
			
			Class.forName(prop.getProperty("jdbc.driver"));
			conn = DriverManager.getConnection(prop.getProperty("jdbc.url"), 
					prop.getProperty("jdbc.username"), 
					prop.getProperty("jdbc.password"));
			
			
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott", "TIGER");*/


			if (conn != null) {
				System.out.println("연결 완료");
			} else {
				System.out.println("연결 실패");
			}
			// conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void autoCommit(Connection con, boolean autocommit) {
		try {
			if (con != null) {
				con.setAutoCommit(autocommit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection con) {
		try {
			if (con != null)
				con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
		try {
			if (con != null)
				con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {

		}
	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {

		}
	}

//	public static void close(PreparedStatement pstmt) {   // 다형성 때문에 Statement로 close가 되기 때문에 또 구현할 필요 없음
//		try {
//			if(pstmt != null) pstmt.close();
//		} catch (Exception e) {
//			
//		}
//	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {

		}
	}
}
