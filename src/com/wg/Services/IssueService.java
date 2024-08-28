package com.wg.Services;

import java.util.List;

import com.wg.DAO.IssueDAO;
import com.wg.Model.Issue;

public class IssueService {
	private IssueDAO issueDAO;

	public IssueService() {
	}

	public IssueService(IssueDAO issueDAO) {
		this.issueDAO = issueDAO;
	}

	public void raiseIssue(Issue issue) {
		issueDAO.raiseIssue(issue);
	}

	public void resolveIssue(String userId) {
		issueDAO.resolveIssue(userId);
	}

	public List<Issue> checkIssueStatus(String userId) {
		List<Issue> issue = issueDAO.checkIssueStatus(userId);
		return issue;
	}

	public List<Issue> viewAllIssues() {
		List<Issue> issue = issueDAO.viewAllIssues();
		return issue;
	}
}
