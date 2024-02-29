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
 * Servlet implementation class DeptController
 */
@WebServlet("/dept/list") // 서버 경로 변경가능
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeptController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 자료형 기억하기
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request 메소드 확인
//		System.out.println("=====request.getParameterNames() - 예) 카테고리별 검색(직업, 연봉, 지역)========");
//		Enumeration<String> enumNames = request.getParameterNames();
//		//System.out.println(enumNames);
//		while (enumNames.hasMoreElements()) {
//			// System.out.println(enumNames.nextElement()); //결과 -> 넘겨 받은 이름이 무엇인지 알 수 있음
//			String name = enumNames.nextElement();
//			System.out.println(request.getParameter(name));
//
//		}
//		System.out.println("======request.getParameterValues() +  Arrays.asList()와 많이 사용- 선호 장르 선택, 재료선택========");
//		// ?genre=a1&genre=b1&genre=c1
//		String[] genreArr = request.getParameterValues("genre");
//		List<String> genreList = Arrays.asList(genreArr);
//		System.out.println(genreList);
//
//		System.out.println("=========getParameterMap()==========");
//		Map<String, String[]> paraMap = request.getParameterMap();
//		Set<String> keyset = paraMap.keySet();
//		for(String key : keyset) {
//			String[] valueArr = paraMap.get(key);
//			List<String> valueList = Arrays.asList(valueArr);
//			System.out.println(valueList);
//		}
//		System.out.println("===================");
		
		
		// 한글 깨짐 해결방법 -> web.xml에 써도됨
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");

		// Controller 역할(순서는 아님)

		// request.getRequestDispatcher("/bfile/b.jsp").forward(request, response); 두개를
		// 같이 쓸수 없음(둘중하나만 선택해서 열림)
//		 view 역할을 JSP 파일에서 함
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		상대경로와 절대경로 둘다 접근할 수 있음 ( 확장자가 보이지 않게 열리도록 하는게 정상적인 방법)
//		controller가 view 파일을 열어주기때문에
//		http://localhost:8080/JavaClassWeb/DeptController
//		http://localhost:8080/JavaClassWeb/NewFile.jsp
		// 2. DB에서 명령어를 통해(Service-DAO 호출) 결과값을 가져옴
		DeptService service = new DeptService();
		List<Dept> result = service.selectList();
		//System.out.println(result);

		if (result == null) { // null 오류 제어
			request.setAttribute("msg", "부서 조회를 할 수 없습니다. 잠시 후 시스템 확인 후 다시 해주세요.");
			request.getRequestDispatcher("/WEB-INF/lib/views/errorPage.jsp").forward(request, response);
		} else {

			// 3. view에 데이터 전달
			request.setAttribute("deptlist", result); /// views/deptlist.jsp에 전달 됨
//			request.setAttribute("DeptData2", "서버에서 값 가져와서 출력중...");
//			request.setAttribute("DeptData3", 1234);
			// 1. view 을 선택
			// 맨 마지막 줄에 들어와서 값을 보냄
			request.getRequestDispatcher("/WEB-INF/lib/views/deptlist.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
