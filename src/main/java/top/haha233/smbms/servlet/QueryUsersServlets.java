package top.haha233.smbms.servlet;

import com.google.gson.Gson;
import top.haha233.smbms.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ice_Dog
 */
@WebServlet(name = "QueryUsersServlets", urlPatterns = {"/api/user/fuck"})
public class QueryUsersServlets extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
			ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		String userName = request.getParameter("userName");
		String userRole = request.getParameter("userRole");
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Gson g = new Gson();
		response.getWriter().print(g.toJson(
				new UserServiceImpl().query(userName, userRole, pageSize, pageNum)));
	}
}
