package com.kobil.cloud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.dama.model.PersistenceService;
import com.dama.model.User;
import com.google.gson.Gson;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;

@WebServlet("/getAllUsers")
public class UserListViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = CloudLoggerFactory.getLogger(UserListViewServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Getting all users");
		List<User> result = new ArrayList<User>();
		PersistenceService ps = new PersistenceService();
		try {
			result = ps.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("All users, {}", result);
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(result));
	}

}
