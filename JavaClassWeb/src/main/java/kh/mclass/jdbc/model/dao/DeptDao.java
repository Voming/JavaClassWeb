package kh.mclass.jdbc.model.dao;

//import static kh.mclass.jdbc.common.JdbcTemplate.*;
import static kh.mclass.jdbc.common.JdbcTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.mclass.jdbc.modle.vo.Dept;

public class DeptDao {
//	private int deptno;
//	private String dname;
//	private String loc;
	public Dept selectOne(Connection conn, int deptno) {
		Dept result = null;

		String sql = "select * from dept where deptno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {   //찾은게 있다면
				result = new Dept();
				result.setDeptno(rs.getInt("deptno"));
				result.setDname(rs.getString("dname"));
				result.setLoc(rs.getString("loc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return result;
	}

	

	public List<Dept> selectList(Connection conn) {
		String sql = "select * from dept";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Dept> result = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			result = new ArrayList<Dept>();

			while (rs.next()) {
				Dept vo = new Dept();
				vo.setDeptno(rs.getInt("deptno"));
				vo.setDname(rs.getString("dname"));
				vo.setLoc(rs.getString("loc"));

				result.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return result;
	}

	public int insert(Connection conn, Dept vo) {
		String sql = "insert into dept values(?, ?, ?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getDeptno());
			pstmt.setString(2, vo.getDname());
			pstmt.setString(3, vo.getLoc());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int delete(Connection conn, String dname) {
		String sql = "delete from dept where dname = ?";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int update(Connection con, Dept vo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "TODO";
		try {
			pstmt = con.prepareStatement(sql);
			//TODO
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
