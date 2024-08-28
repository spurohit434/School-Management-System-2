package com.wg.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.wg.DAO.UserDAO;
import com.wg.Helper.LoggingUtil;
import com.wg.Helper.PasswordUtil;
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
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
//		if (user != null) {
//			if (!user.getRole().toString().equals("Admin")) {
//				try {
//					userDAO.deleteUser(id);
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				return true;
//			} else if (user.getRole().toString().equals("Admin")) {
//				System.out.println("Admin can not be deleted");
//				return false;
//			}
//		} else if (user == null) {
//			System.out.println("User does not exist");
//			return false;
//		}
//		return false;
	}

//	public boolean authenticateUser1(String username, String password, String role) {
//		User user = null;
//		Logger logger = LoggingUtil.getLogger(UserService.class);
//
//		try {
//			user = userDAO.getUserByUsername(username);
//			if (user.getUserId() == null) {
//				System.out.println("User not found");
//				logger.info("User Authentication Failed!! \n Username: " + username + "\n Password: " + password);
//				// throw new UnauthenticatedException("Invalid Credentials!");
//				return false;
//			} else {
//				String checkName = user.getUsername();
//				String checkPass = user.getPassword();
//				String role1 = user.getRole().name();
//
//				if (checkName.equals(username) && checkPass.equals(password) && role1.equals(role)) {
//					System.out.println("Authentication successful");
//					return true;
//				} else if (checkName != username) {
//					System.out.println("Enter correct Username");
//					return false;
//				} else if (checkPass != password) {
//					System.out.println("Enter correct password");
//					return false;
//				} else if (role1 != role) {
//					System.out.println("Enter correct role");
//					return false;
//				} else {
//					System.out.println("Authentication Unsuccessful");
//					return false;
//				}
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	public User authenticateUser(String username, String password) {
		User user = null;
		Logger logger = LoggingUtil.getLogger(UserService.class);

		try {
			user = userDAO.getUserByUsername(username);
			if (user.getUserId() == null) {
				System.out.println("User not found");
				logger.info("User Authentication Failed!! \n Username: " + username + "\n Password: " + password);
				// throw new UnauthenticatedException("Invalid Credentials!");
				return null;
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
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