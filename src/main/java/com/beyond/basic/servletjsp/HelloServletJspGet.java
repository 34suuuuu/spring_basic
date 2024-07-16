package com.beyond.basic.servletjsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	@WebServlet("/hello/servlet/jsp/post")
	public class HelloServletJspGet extends HttpServlet {
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// src/main/webapp폴더를 찾아가는 것으로 약속
			req.setAttribute("myData", "hello world java");
			req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
	}
}
