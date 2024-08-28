package com.wg.Controller;

import java.util.List;

import com.wg.Model.Issue;
import com.wg.Services.IssueService;

public class IssueController {
	private IssueService issueService;

	public IssueController() {
	}

	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	public void raiseIssue(Issue issue) {
		issueService.raiseIssue(issue);
	}

	public void resolveIssue(String userId) {
		issueService.resolveIssue(userId);
	}

	public List<Issue> checkIssueStatus(String userId) {
		List<Issue> issues = issueService.checkIssueStatus(userId);
		return issues;
	}

	public List<Issue> viewAllIssues() {
		List<Issue> issues = issueService.viewAllIssues();
		return issues;
	}
}