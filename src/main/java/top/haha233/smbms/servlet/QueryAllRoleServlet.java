package top.haha233.smbms.servlet;

import top.haha233.smbms.service.RoleService;
import top.haha233.smbms.service.impl.RoleServiceImpl;
import top.haha233.smbms.util.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QueryAllRoleServlet",urlPatterns = {"/api/user/query"})
public class QueryAllRoleServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		RoleService rs = new RoleServiceImpl();
		response.getWriter().print(rs.queryAllRole());
	}
}
