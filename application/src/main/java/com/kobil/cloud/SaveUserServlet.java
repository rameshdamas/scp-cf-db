package com.kobil.cloud;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dama.model.PersistenceService;
import com.dama.model.User;
import com.google.gson.Gson;

@WebServlet("/saveUser")
public class SaveUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		System.out.println("User save running!");
		PersistenceService ps = new PersistenceService();
		User user = new User();
		user.setEmailAddress("test_user-" + System.currentTimeMillis() + "@gmail.com");
		try {
			ps.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(user));
	}

}
