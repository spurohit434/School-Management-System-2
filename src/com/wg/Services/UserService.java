package com.wg.Services;

import java.sql.SQLException;
import java.util.List;

import com.wg.DAO.UserDAO;
import com.wg.Model.User;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
	}

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void addUser(User user) {
		try {
			userDAO.addUser(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public User getUserById(String userId) {
		User user = null;
		try {
			user = userDAO.getUserById(userId);
			if (user == null) {
				System.out.println("user null from dao:");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getClassDetails(int standard) {
		List<User> list = null;
		try {
			list = userDAO.getClassDetails(standard);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean deleteUser(String id) {
		User user = null;
		try {
			user = userDAO.getUserById(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (user != null) {
			try {
				userDAO.deleteUser(id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			System.out.println("User not found Exception");
		}
		return false;
	}

	public boolean authenticateUser(String username, String password, String role) {
		User user = null;
		try {
			user = userDAO.getUserByUsername(username);
			if (user.getUserId() == null) {
				System.out.println("User not found");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String checkName = user.getUsername();
		String checkPass = user.getPassword();
		String role1 = user.getRole().name();

		if (checkName.equals(username) && checkPass.equals(password) && role1.equals(role)) {
			System.out.println("Authentication successful");
			return true;
		} else if (checkName != username) {
			System.out.println("Enter correct Username");
			return false;
		} else if (checkPass != password) {
			System.out.println("Enter correct password");
			return false;
		} else if (role1 != role) {
			System.out.println("Enter correct role");
			return false;
		} else {
			System.out.println("Authentication Unsuccessful");
			return false;
		}
	}

	public List<User> getAllUser() {
		List<User> users = null;
		try {
			users = userDAO.getAllUser();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User getUserByUsername(String username) {
		User user = null;
		try {
			user = userDAO.getUserByUsername(username);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void updateUser(User user, String userId, String columnToUpdate) {
		try {
			userDAO.updateUser(user, userId, columnToUpdate);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}