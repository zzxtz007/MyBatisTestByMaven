package top.haha233.smbms.servlet;

import com.google.gson.Gson;
import top.haha233.smbms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/api/user/login"})
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		String userCode = request.getParameter("userCode");
		String passWord = request.getParameter("passWord");
		Gson g = new Gson();
		response.getWriter().print(g.toJson(new UserServiceImpl().login(userCode,passWord,request.getSession())));
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {

	}
}
