package com.App;

import java.sql.SQLException;

import com.wg.Controller.UserController;
import com.wg.DAO.UserDAO;
import com.wg.Services.UserService;
import com.wg.UI.StartMenu;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		UserService service = new UserService(dao);
		UserController controller = new UserController(service);
		StartMenu menu = new StartMenu(controller);

		menu.showStartMenu();
	}
}