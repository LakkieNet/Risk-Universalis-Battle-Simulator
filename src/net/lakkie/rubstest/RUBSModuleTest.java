package net.lakkie.rubstest;

public abstract class RUBSModuleTest {

	private String testID;

	public RUBSModuleTest(String testID) {
		this.testID = testID;
	}
	
	public abstract void run(String[] args) throws Exception;

	public String getTestID() {
		return this.testID;
	}

	public void setTestID(String testID) {
		this.testID = testID;
	}
	
}
