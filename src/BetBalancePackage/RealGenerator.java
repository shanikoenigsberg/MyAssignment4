package BetBalancePackage;
import java.util.Random;

public class RealGenerator implements IRandomValueGenerator{
	
	private Random generator;
	
	public RealGenerator() {
		generator = new Random();
	}
	
	public int getRandom(int start, int stop) {
		return start + generator.nextInt(stop - start + 1);
	}
}
