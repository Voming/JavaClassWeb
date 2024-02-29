package kh.mclass.jdbc.model.service;

import static kh.mclass.jdbc.common.JdbcTemplate.autoCommit;
import static kh.mclass.jdbc.common.JdbcTemplate.close;
import static kh.mclass.jdbc.common.JdbcTemplate.commit;
import static kh.mclass.jdbc.common.JdbcTemplate.getConnection;
import static kh.mclass.jdbc.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import kh.mclass.jdbc.model.dao.EmpDao;
import kh.mclass.jdbc.model.vo.Emp;

public class EmpService {
	EmpDao dao = new EmpDao();

	public List<Emp> selectList() {
		Connection conn = getConnection();
		List<Emp> result = dao.selectList(conn);
		close(conn);
		return result;
	}

	public int insert(Emp emp) {
		Connection conn = getConnection();
		int result = -1;
		autoCommit(conn, false);
		result = dao.insertEmp(conn, emp);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int delete(String ename) {
		Connection conn = getConnection();
		int result = -1;
		autoCommit(conn, false);
		result = dao.deletEmp(conn, ename);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
