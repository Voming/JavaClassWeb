package kh.mclass.jdbc.model.dao;

import static kh.mclass.jdbc.common.JdbcTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.mclass.jdbc.modle.vo.Salgrade;

public class SalgradeDao {
//	private int grade;
//	private int losal;
//	private int hisal;
	public Salgrade selectOne(Connection conn, int grade) {
		Salgrade result = null;

		String sql = "select * from salgrade where grade = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, grade);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {   //찾은게 있다면
				result = new Salgrade();
				result.setGrade(rs.getInt("grade"));
				result.setLosal(rs.getInt("losal"));
				result.setHisal(rs.getInt("hisal"));
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

	

	public List<Salgrade> selectList(Connection conn) {
		String sql = "select * from salgrade";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Salgrade> list = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			list = new ArrayList<Salgrade>();
			while (rs.next()) {
				Salgrade vo = new Salgrade();

				vo.setGrade(rs.getInt("grade"));
				vo.setLosal(rs.getInt("losal"));
				vo.setHisal(rs.getInt("hisal"));

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return list;
	}

	public int insert(Connection conn, Salgrade vo) {
		String sql = "insert into salgrade values(?, ?, ?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, vo.getGrade());
			pstmt.setInt(2, vo.getLosal());
			pstmt.setInt(3, vo.getHisal());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt); // conn의 close 처리는 밖에서
		}
		return result;
	}

	public int delete(Connection conn, int grade) {
		String sql = "delete from salgrade where grade = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, grade);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt); // conn의 close 처리는 밖에서
		}
		return result;
	}

	public int update(Connection con, Salgrade vo) {
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
