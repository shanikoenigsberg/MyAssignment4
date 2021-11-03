package BetBalancePackage;

public class MockGenerator implements IRandomValueGenerator{
	
	private int randomNum;

	public MockGenerator() {
		
	}
	
	public void setRandom(int num) {
		randomNum = num;
	}
	
	public int getRandom(int start, int stop) {
		return randomNum;
	}
	

}
