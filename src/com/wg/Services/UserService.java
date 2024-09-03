package com.wg.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.wg.DAO.UserDAO;
import com.wg.Helper.LoggingUtil;
import com.wg.Helper.PasswordUtil;
import com.wg.Helper.UnauthenticatedException;
import com.wg.Model.User;

public class UserService {
	private UserDAO userDAO;
	Logger logger = LoggingUtil.getLogger(UserService.class);

	public UserService() {
	}

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void addUser(User user) {
		try {
			userDAO.addUser(user);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return;
	}

	public User getUserById(String userId) {
		User user = null;
		try {
			user = userDAO.getUserById(userId);
			if (user == null) {
				System.out.println("User not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getClassDetails(int standard) {
		List<User> list = null;
		try {
			list = userDAO.getClassDetails(standard);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteUser(String id) {
		User user = null;
		try {
			user = userDAO.getUserById(id);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		if (user == null) {
			System.out.println("User not found");
			return false;
		}
		if (user.getRole().toString().equalsIgnoreCase("Admin")) {
			System.out.println("Admin cannot be deleted");
			return false;
		}
		try {
			try {
				userDAO.deleteUser(id);
				return true;
			} catch (ClassNotFoundException e) {
				logger.severe(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public User authenticateUser(String username, String password) {
		User user = null;
		Logger logger = LoggingUtil.getLogger(UserService.class);

		try {
			user = userDAO.getUserByUsername(username);
			if (user.getUserId() == null) {
				System.out.println("User not found");
				logger.info("User Authentication Failed!! \n Username: " + username);
				throw new UnauthenticatedException("Invalid Credentials!");
			} else {
				String checkName = user.getUsername();
				String checkPass = user.getPassword();

				if (checkName.equals(username) && PasswordUtil.checkPassword(password, checkPass)) {
					logger.info("User Authentication Success!! \n Username: " + username);
					System.out.println("Authentication successful");
					return user;
				} else if (checkPass != password) {
					System.out.println("Enter correct password");
					return null;
				} else {
					System.out.println("Authentication Unsuccessful");
					return null;
				}
			}
		} catch (ClassNotFoundException | SQLException | UnauthenticatedException | NullPointerException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllUser() {
		List<User> users = null;
		try {
			users = userDAO.getAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return users;
	}

	public User getUserByUsername(String username) {
		User user = null;
		try {
			user = userDAO.getUserByUsername(username);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	public void updateUser(User user, String userId, String columnToUpdate) {
		try {
			userDAO.updateUser(user, userId, columnToUpdate);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}
}