package com.kh.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.practice.model.vo.Member;

@WebServlet("/search")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Member> mockList = new ArrayList<>();

	public MemberSearchServlet() {
		super();
	}

	@Override
	public void init() {
		// MOCK 회원 데이터
		mockList.add(new Member("홍은동", 25, "남자"));
        mockList.add(new Member("김영심", 19, "여자"));
        mockList.add(new Member("이은수", 30, "남자"));
        mockList.add(new Member("심하늘", 22, "여자"));
        mockList.add(new Member("정심훈", 45, "남자"));
        mockList.add(new Member("심은성", 4, "남자"));
        mockList.add(new Member("나수지", 33, "여자"));
        mockList.add(new Member("유상성", 21, "남자"));
        mockList.add(new Member("한지은", 28, "여자"));
        mockList.add(new Member("고심현", 40, "남자"));
        mockList.add(new Member("abc", 40, "남자"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트에서 전달한 이름이 포함된 회원을 mockList에서 조회한 후 새로운
		// List에 담은 후 , 적절한 scope에 담아 result.jsp로 응답처리 위임할 것.
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		List<Member> list = new ArrayList<>();
		
		for (Member m : mockList) {
			if (m.getName().contains(name)) {
				list.add(m);
			}
		}
		
		request.setAttribute("list", list);
		/*
		 * html 작성을 jsp에게 위임
		 * 매개변수로 요청을 위임할 jsp의 경로를 추가.
		 * getRequestDispatcher("jsp의 주소 || 서블릿의 url 패턴(/first, /second, /life")
		 * 지정된 서블릿을 호출하여 요청처리를 위임하고, 최초 client가 요청했던 url에는 변화가 없다.
		 */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
		dispatcher.forward(request, response); // 데이터 그대로 담아서 전송
	}

}
