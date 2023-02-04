package org.unibl.etf.ip.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.ip.model.beans.AdminBean;
import org.unibl.etf.ip.model.beans.CategoryBean;
import org.unibl.etf.ip.model.beans.StatsBean;
import org.unibl.etf.ip.model.beans.UserBean;
import org.unibl.etf.ip.model.dto.Attribute;
import org.unibl.etf.ip.model.dto.Category;
import org.unibl.etf.ip.model.dto.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "index.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (action == null || action.equals(""))
			address = "index.jsp";
		else if (action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username == null || password == null) {
				address = "index.jsp";
			} else {
				AdminBean adminBean = new AdminBean();
				if (adminBean.login(username, password)) {
					session.setAttribute("adminBean", adminBean);
					session.removeAttribute("loginMessage");
					session.setAttribute("categoryBean", new CategoryBean());
					address = "/WEB-INF/pages/categories.jsp";
				} else {
					session.setAttribute("loginMessage", "Prijava neuspješna.");
				}
			}
		} else {
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if (adminBean == null || !adminBean.isLoggedIn()) {
				session.invalidate();
				address = "index.jsp";
			} else {
				if (action.equals("addCategory")) {
					String name = request.getParameter("name");
					if (name == null) {
						address = "/WEB-INF/pages/addCategory.jsp";
					} else {
						int parentId = Integer.parseInt(request.getParameter("parentId"));
						int noAttributes = Integer.parseInt(request.getParameter("noAttributes"));
						CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
						categoryBean.setNewCategory(new Category(0, name, parentId));
						session.setAttribute("noAttributes", noAttributes);
						address = "WEB-INF/pages/addAttributes.jsp";
					}
				} else if (action.equals("addAttributes")) {
					if (session.getAttribute("noAttributes") == null) {
						address = "WEB-INF/pages/addAttributes.jsp";
					} else {
						int noAttributes = (int) session.getAttribute("noAttributes");
						CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
						categoryBean.setNewAttributes(new ArrayList<>());
						for (int i = 0; i < noAttributes; i++) {
							String name = request.getParameter("name" + i);
							String type = request.getParameter("type" + i);
							if (name == null || type == null) {
								address = "/WEB-INF/pages/categories.jsp";
								break;
							}
							categoryBean.getNewAttributes()
									.add(new Attribute(0, name, type, categoryBean.getNewCategory().getId()));
							address = "/WEB-INF/pages/categories.jsp";
						}
						categoryBean.add();
					}
				} else if (action.equals("updateCategory")) {
					CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("id"));
						address = "WEB-INF/pages/updateCategory.jsp";
						if (!categoryBean.getById(id))
							address = "WEB-INF/pages/categories.jsp";
					} else {
						String name = request.getParameter("name");
						if (name == null) {
							address = "WEB-INF/pages/categories.jsp";
						} else {
							int id = categoryBean.getUpdateCategory().getId();
							int parentId = categoryBean.getUpdateCategory().getParentId();
							categoryBean.update(id, new Category(id, name, parentId));
							address = "WEB-INF/pages/categories.jsp";
						}
					}
				} else if (action.equals("deleteCategory")) {
					if (request.getParameter("id") == null) {
						address = "/WEB-INF/pages/categories.jsp";
					} else {
						int id = Integer.parseInt(request.getParameter("id"));
						CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
						categoryBean.delete(id);
						address = "/WEB-INF/pages/categories.jsp";
					}
				} else if (action.equals("addUser")) {
					if (request.getParameter("firstName") == null) {
						address = "WEB-INF/pages/addUser.jsp";
					} else {
						String firstName = request.getParameter("firstName");
						String lastName = request.getParameter("lastName");
						String username = request.getParameter("username");
						String password = request.getParameter("password");
						String avatarURL = request.getParameter("avatarUrl");
						String email = request.getParameter("email");
						String contactPhone = request.getParameter("contactPhone");
						String location = request.getParameter("location");
						if (lastName == null || username == null || password == null || email == null
								|| contactPhone == null || location == null) {
							address = "WEB-INF/pages/addUser.jsp";
						} else {
							UserBean userBean = (UserBean) session.getAttribute("userBean");
							userBean.add(new User(0, firstName, lastName, username, password, avatarURL, email, true,
									contactPhone, location, false));
							address = "WEB-INF/pages/users.jsp";
						}
					}
				} else if (action.equals("updateUser")) {
					UserBean userBean = (UserBean) session.getAttribute("userBean");
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("id"));
						address = "WEB-INF/pages/updateUser.jsp";
						if (!userBean.getById(id))
							address = "WEB-INF/pages/users.jsp";
					} else {
						String firstName = request.getParameter("firstName");
						String lastName = request.getParameter("lastName");
						String username = request.getParameter("username");
						String password = request.getParameter("password");
						String avatarURL = request.getParameter("avatarUrl");
						String email = request.getParameter("email");
						String contactPhone = request.getParameter("contactPhone");
						String location = request.getParameter("location");
						if (firstName == null || lastName == null || username == null || email == null
								|| contactPhone == null || location == null) {
							address = "WEB-INF/pages/users.jsp";
						} else {
							boolean activated = request.getParameter("activated") == null ? false : true;
							boolean deleted = request.getParameter("deleted") == null ? false : true;
							userBean.update(userBean.getUser().getId(),
									new User(userBean.getUser().getId(), firstName, lastName, username, "", avatarURL,
											email, activated, contactPhone, location, deleted),
									password);
							address = "WEB-INF/pages/users.jsp";
						}
					}
				} else if (action.equals("deleteUser")) {
					if (request.getParameter("id") == null) {
						address = "/WEB-INF/pages/users.jsp";
					} else {
						int id = Integer.parseInt(request.getParameter("id"));
						UserBean userBean = (UserBean) session.getAttribute("userBean");
						userBean.delete(id);
						address = "/WEB-INF/pages/users.jsp";
					}
				} else if (action.equals("categories")) {
					address = "/WEB-INF/pages/categories.jsp";
				} else if (action.equals("users")) {
					address = "/WEB-INF/pages/users.jsp";
					if (session.getAttribute("userBean") == null)
						session.setAttribute("userBean", new UserBean());
				} else if (action.equals("stats")) {
					address = "/WEB-INF/pages/stats.jsp";
					if (session.getAttribute("statsBean") == null)
						session.setAttribute("statsBean", new StatsBean());
				} else if (action.equals("logout")) {
					session.invalidate();
					address = "index.jsp";
				}
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
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
