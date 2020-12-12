package helper;

public class TestCaseResult {

	/*
	 * instance variable that 
	 */
	String runTestCaseName;
	String statusOfTestCaseExecuted;
	String testCaseExecutedTime;
	String Comments;

	/*
	 * Parameterised Constructor 
	 */
	public TestCaseResult(String runTestCaseName, String statusOfTestCaseExecuted, String testCaseExecutedTime,
			String comments) {
		super();
		this.runTestCaseName = runTestCaseName;
		this.statusOfTestCaseExecuted = statusOfTestCaseExecuted;
		this.testCaseExecutedTime = testCaseExecutedTime;
		Comments = comments;
	}

	/*
	 * Default Constructor 
	 */
	public TestCaseResult() {

	}

	/*
	 * Getter and Setter for all the instance Variable
	 */
	public String getRunTestCaseName() {
		return runTestCaseName;
	}

	public void setRunTestCaseName(String runTestCaseName) {
		this.runTestCaseName = runTestCaseName;
	}

	public String getStatusOfTestCaseExecuted() {
		return statusOfTestCaseExecuted;
	}

	public void setStatusOfTestCaseExecuted(String statusOfTestCaseExecuted) {
		this.statusOfTestCaseExecuted = statusOfTestCaseExecuted;
	}

	public String getTestCaseExecutedTime() {
		return testCaseExecutedTime;
	}

	public void setTestCaseExecutedTime(String testCaseExecutedTime) {
		this.testCaseExecutedTime = testCaseExecutedTime;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Comments == null) ? 0 : Comments.hashCode());
		result = prime * result + ((runTestCaseName == null) ? 0 : runTestCaseName.hashCode());
		result = prime * result + ((statusOfTestCaseExecuted == null) ? 0 : statusOfTestCaseExecuted.hashCode());
		result = prime * result + ((testCaseExecutedTime == null) ? 0 : testCaseExecutedTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestCaseResult other = (TestCaseResult) obj;
		if (Comments == null) {
			if (other.Comments != null)
				return false;
		} else if (!Comments.equals(other.Comments))
			return false;
		if (runTestCaseName == null) {
			if (other.runTestCaseName != null)
				return false;
		} else if (!runTestCaseName.equals(other.runTestCaseName))
			return false;
		if (statusOfTestCaseExecuted == null) {
			if (other.statusOfTestCaseExecuted != null)
				return false;
		} else if (!statusOfTestCaseExecuted.equals(other.statusOfTestCaseExecuted))
			return false;
		if (testCaseExecutedTime == null) {
			if (other.testCaseExecutedTime != null)
				return false;
		} else if (!testCaseExecutedTime.equals(other.testCaseExecutedTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TestCaseResult [runTestCaseName=" + runTestCaseName + ", statusOfTestCaseExecuted="
				+ statusOfTestCaseExecuted + ", testCaseExecutedTime=" + testCaseExecutedTime + ", Comments=" + Comments
				+ "]";
	}

}
