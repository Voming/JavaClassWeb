package kh.mclass.jdbc.model.dao;
//Data Access Object

import static kh.mclass.jdbc.common.JdbcTemplate.close;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kh.mclass.jdbc.modle.vo.Emp;

public class EmpDao {
	public List<Emp> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;

		List<Emp> empList = null;
		try {
			stmt = conn.createStatement();

			String sql = "select * from emp";
			rset = stmt.executeQuery(sql);

			empList = new ArrayList<Emp>();
			while (rset.next()) {
				Emp emp = new Emp();

				emp.setEmpno(rset.getInt("empNo"));
				emp.setEname(rset.getString("ename"));
				emp.setJob(rset.getString("job"));
				emp.setMgr(rset.getInt("mgr"));
				emp.setHiredate(rset.getDate("hiredate"));
				emp.setSal(rset.getDouble("sal"));
				emp.setComm(rset.getInt("comm"));
				emp.setDeptno(rset.getInt("deptno"));

				empList.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(conn);
		}
		return empList;
	}

	public int insertEmp(Connection conn, Emp emp) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			String sql = "insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) " // 빈칸 주의
					+ "values (?,?,?,?,SYSDATE,?,?,?)"; // PreparedStatement는 ?로 받을 값을 미리 표현

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, emp.getEmpno()); // 1부터 시작함
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setDouble(5, emp.getSal());
			pstmt.setDouble(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptno());

			result = pstmt.executeUpdate(); // 여기 () 에는 sql을 넣지 않음

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deletEmp(Connection conn, String ename) {
		String sql = "delete from emp where ename = ?";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ename);

			result = pstmt.executeUpdate(); // 여기 () 에는 sql을 넣지 않음

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Emp> selectList_() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		List<Emp> empList = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // lib, jar, class 확인 / jar 파일은 클래스들의 묶음(Java Library)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott", "TIGER"); // URL(IP+Port),
																											// User,
																											// Password
			// 기본 계정 접근 할때 sys as sysdba
			if (conn != null) {
				System.out.println("연결 완료");
			} else {
				System.out.println("연결 실패");
			}

			stmt = conn.createStatement(); // Statement를 먼저 만들고 사용해야한다

			// String sql = "select * from emp";
			String sql = "select * from emp where deptno = 20";
			rset = stmt.executeQuery(sql);
			// 이 위치에서 new 해서 list에 담아줌
			empList = new ArrayList<Emp>();
			while (rset.next()) {
				// emp생성
				Emp emp = new Emp(); // 생성한 이후에 넣어줘야 NullPointException 발생 안함
				// emp 값 채우기
				emp.setEmpno(rset.getInt("empNo"));
				emp.setEname(rset.getString("ename"));
				emp.setJob(rset.getString("job"));
				emp.setMgr(rset.getInt("mgr"));
				emp.setHiredate(rset.getDate("hiredate"));
				emp.setSal(rset.getDouble("sal"));
				emp.setComm(rset.getInt("comm"));
				emp.setDeptno(rset.getInt("deptno"));
				// 리스트에 emp 넣기
				empList.add(emp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return empList;
	}

	public int insertEmp_(Emp emp) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // lib, jar, class확인
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott", "TIGER");

			if (conn != null) {
				System.out.println("연결 완료");
			} else {
				System.out.println("연결 실패");
			}

			stmt = conn.createStatement(); // createStatement()을 사용함 / prepareStatement(sql)이 아님
			// 문자열이 들어갈 경우 ''가 필요함
			String sql = "insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) " + "values ("
					+ emp.getEmpno() + ", '" + emp.getEname() + "', '" + emp.getJob() + "', " + emp.getMgr() + ", "
					+ "SYSDATE, " + emp.getSal() + ", " + emp.getComm() + ", " + emp.getDeptno() + ")";
			System.out.println(sql);

			result = stmt.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public int insertEmp_2(Emp emp) {
		Connection conn = null;
		PreparedStatement pstmt = null; // PreparedStatement
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // lib, jar, class확인
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott", "TIGER");

			if (conn != null) {
				System.out.println("연결 완료");
			} else {
				System.out.println("연결 실패");
			}
			// PreparedStatement는 '' 대신 ?위치홀더 쿼리 스트링을 사용가능
			String sql = "insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) " // 빈칸 주의
					+ "values (?,?,?,?,SYSDATE,?,?,?)"; // PreparedStatement는 ?로 받을 값을 미리 표현

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, emp.getEmpno()); // 1부터 시작함
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setDouble(5, emp.getSal());
			pstmt.setDouble(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptno());

			result = pstmt.executeUpdate(); // 여기 () 에는 sql을 넣지 않음

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public int deletEmp_(String ename) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // lib, jar, class확인
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "scott", "TIGER");

			if (conn != null) {
				System.out.println("연결 완료");
			} else {
				System.out.println("연결 실패");
			}

			String sql = "delete from emp where ename = ?"; // PreparedStatement는 ?로 받을 값을 미리 표현
			// pstmt 생성후 ?위치 홀더 쿼리 스트림에 값 채우기
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ename);

			result = pstmt.executeUpdate(); // 여기 () 에는 sql을 넣지 않음

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
