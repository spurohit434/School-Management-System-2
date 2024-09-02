package com.wg.Model;

public class Marksheet {
	String userId;
	Result result; // enumvals
	double percentage;
	int standard;

	public Marksheet(String userId, Result result, double percentage, int standard) {
		super();
		this.userId = userId;
		this.result = result;
		this.percentage = percentage;
		this.standard = standard;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	@Override
	public String toString() {
		return "MyClass{" + "userId='" + userId + '\'' + ", result=" + result + ", percentage=" + percentage
				+ ", standard=" + standard + '}';
	}
}
