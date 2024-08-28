package com.wg.Model;

public class Issue {

	private String issueID;
	private String message;
	private String userId;
	private IssuesStatus status;

	public Issue() {
	}

	public Issue(String issueID, String message, String userId, IssuesStatus status) {
		this.issueID = issueID;
		this.message = message;
		this.userId = userId;
		this.status = status;
	}

	public String getIssueID() {
		return issueID;
	}

	public void setIssueID(String issueID) {
		this.issueID = issueID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IssuesStatus getStatus() {
		return status;
	}

	public void setStatus(IssuesStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Issue{" + "issueID='" + issueID + '\'' + ", message='" + message + '\'' + ", userId='" + userId + '\''
				+ ", status=" + status + '}';
	}

}
