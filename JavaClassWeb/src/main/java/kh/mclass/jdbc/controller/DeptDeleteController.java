package kh.mclass.jdbc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.jdbc.model.service.DeptService;
import kh.mclass.jdbc.model.vo.Dept;

/**
 * Servlet implementation class DeptDeletController
 */
@WebServlet("/dept/delete")
public class DeptDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//?no=52&a=20&b=안녕
		String no = request.getParameter("no");   //리턴은 항상 String으로
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		
		System.out.println("no : " + no);
		System.out.println("a : " + a);
		System.out.println("b : " + b);
		
		
		int deptno= Integer.parseInt(a);
		DeptService service = new DeptService();
		int result = service.delete(deptno);
		if(result > 0) {
			List<Dept> volist = service.selectList();
			request.setAttribute("DeptData1", volist);
			request.getRequestDispatcher("/views/deptlist.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "삭제 중 오류가 발생했습니다.");
			request.getRequestDispatcher("/views/errorPage.jsp").forward(request, response);
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
