package kh.mclass.jdbc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.mclass.jdbc.modle.service.DeptService;
import kh.mclass.jdbc.modle.vo.Dept;

/**
 * Servlet implementation class DeptController
 */
@WebServlet("/DeptController")   //서버 경로 변경가능
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Controller 역할
		//1. view 을 선택
		request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
		//request.getRequestDispatcher("/bfile/b.jsp").forward(request, response); 두개를 같이 쓸수 없음(둘중하나만 선택해서 열림)
//		 view 역할을 JSP 파일에서 함
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		상대경로와 절대경로 둘다 접근할 수 있음 ( 확장자가 보이지 않게 열리도록 하는게 정상적인 방법)
//		controller가 view 파일을 열어주기때문에
//		http://localhost:8080/JavaClassWeb/DeptController
//		http://localhost:8080/JavaClassWeb/NewFile.jsp
		//2. DB에서 명령어를 통해(Service-DAO 호출) 결과값을 가져옴
		DeptService service = new DeptService();
		List<Dept> result = service.selectList();
		System.out.println(result);
		//request.getRequestDispatcher("/DeptList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
