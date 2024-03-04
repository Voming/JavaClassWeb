package kh.mclass.jdbc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.jdbc.model.service.DeptService;
import kh.mclass.jdbc.model.vo.Dept;

/**
 * Servlet implementation class DeptInsertController
 */
@WebServlet("/dept/insert")
public class DeptInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeptInsertController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// ?deptno=60&dname=KIM&loc=SEOUL
		String deptnoS = request.getParameter("deptno"); // 리턴은 항상 String으로
		String dname = request.getParameter("dname");
		String loc = request.getParameter("loc");

		System.out.println(deptnoS);
		System.out.println(dname);
		System.out.println(loc);

		int deptno = Integer.parseInt(deptnoS);

		DeptService service = new DeptService();
		Dept newD = new Dept(deptno, dname, loc);
		
		int result = service.insert(newD);
		
		
		if (result > 0) {
//			List<Dept> volist = service.selectList();
//			request.setAttribute("DeptData1", volist);
//			request.getRequestDispatcher("/WEB-INF/lib/views/deptlist.jsp").forward(request, response);
			
			response.sendRedirect(  //getContextPath를 포함해서 적어야함
					request.getContextPath()/*하나의 웹서버에 여러개의 프로젝트를 사용할 수 있기 때문 내프로젝트 가르키도록*/ 
					+ "/dept/list");  //Controller servlet 기준
		} else {
			request.setAttribute("msg", "부서를 추가하지 못했습니다.");
			request.getRequestDispatcher("/WEB-INF/lib/views/errorPage.jsp"/*절대위치 제한없이 다양한 URL사용가능*/).forward(request, response);
		}    //webapp이 루트위치
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
