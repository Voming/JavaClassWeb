package kh.mclass.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate { // 중복되는 connection, close 함
	// 싱글톤을 닮았지만 conn을 빠르게 close해야 되어서 싱클톤은 아님
	private JdbcTemplate() {
		// 생성자 private 가능
	}

	public static Connection getConnection() { // static이 중요 new하지 않고 호출하기 위함ㄴ
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott", "TIGER");

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
