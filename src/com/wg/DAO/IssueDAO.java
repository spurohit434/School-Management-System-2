package com.wg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wg.Model.Issue;
import com.wg.Model.IssuesStatus;
import com.wg.config.DatabaseConnection;

public class IssueDAO {
	private Connection connection;

	public IssueDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	private String issueID;
//	private String message;
//	private String userId;
//	private IssuesStatus status;

	public void raiseIssue(Issue issue) {
		String insertSQL = "INSERT INTO Issue (issueID, message, userId, status) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
			preparedStatement.setString(1, issue.getIssueID());
			preparedStatement.setString(2, issue.getMessage());
			preparedStatement.setString(3, issue.getUserId());
			preparedStatement.setString(4, issue.getStatus().name()); // Convert enum to string

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				System.out.println("Issue was not raised.");
			} else {
				System.out.println("Issue raised successfully.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException("Error raising issue", e);
		}
	}

	public void resolveIssue(String userId) {
		String updateSQL = "UPDATE Issue SET status = ? WHERE userId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
			preparedStatement.setString(1, "RESOLVED");
			preparedStatement.setString(2, userId);

			System.out.println("Executing SQL: " + updateSQL);
			System.out.println("With parameters: status='Approved', userId=" + userId);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				System.out.println("No records update.");
			} else {
				System.out.println("Issue successfully resolved for user ID: " + userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new SQLException("Error updating leave request status", e);
		}
	}

	public List<Issue> checkIssueStatus(String userId) {
		List<Issue> issueList = new ArrayList<>();
		String query = "SELECT * FROM Issue where userId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Issue issue = new Issue();
					issue.setIssueID(resultSet.getString("issueId"));
					issue.setMessage(resultSet.getString("message"));
					issue.setUserId(resultSet.getString("userId"));
					issue.setStatus(IssuesStatus.valueOf(resultSet.getString("status")));
					issueList.add(issue);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception for debugging
		}
		return issueList;
	}

	public List<Issue> viewAllIssues() {
		List<Issue> issueList = new ArrayList<>();
		// List<Issue> issueList = null;
		String query = "SELECT * FROM Issue";
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				Issue issue = new Issue();
				issue.setIssueID(resultSet.getString("issueId"));
				issue.setMessage(resultSet.getString("message"));
				issue.setUserId(resultSet.getString("userId"));
				issue.setStatus(IssuesStatus.valueOf(resultSet.getString("status")));
				issueList.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception for debugging
		}
		return issueList;
	}

}
