package kh.mclass.jdbc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.jdbc.model.service.DeptService;
import kh.mclass.jdbc.model.service.EmpService;
import kh.mclass.jdbc.model.service.SalgradeService;
import kh.mclass.jdbc.model.vo.Dept;
import kh.mclass.jdbc.model.vo.Emp;
import kh.mclass.jdbc.model.vo.Salgrade;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/all/list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptService service1 = new DeptService();
		List<Dept> deptlist = service1.selectList();
		System.out.println(deptlist);
		
		
		EmpService service2 = new EmpService();
		List<Emp> emplist = service2.selectList();
		System.out.println(emplist);
		
		
		SalgradeService service3 = new SalgradeService();
		List<Salgrade> sallist = service3.selectList();
		System.out.println(sallist);
		
		
		if(deptlist == null || emplist == null || sallist == null) {  //null 오류 제어
			request.setAttribute("msg", "부서 조회를 할 수 없습니다. 잠시 후 시스템 확인 후 다시 해주세요."); 
			request.getRequestDispatcher("/WEB-INF/lib/views/errorPage.jsp").forward(request, response);
		}else {

			//3. view에 데이터 전달
			request.setAttribute("deptlist", deptlist);  ///views/deptlist.jsp에 전달 됨
			request.setAttribute("emplist", emplist); 
			request.setAttribute("sallist", sallist); 

			//1. view 을 선택
			//맨 마지막 줄에 들어와서 값을 보냄
			request.getRequestDispatcher("/WEB-INF/lib/views/allList.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
