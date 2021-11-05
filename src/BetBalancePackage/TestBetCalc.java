package BetBalancePackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestBetCalc {
	
	double minBalance;
	MockGenerator myRandom;
	BetBalanceCalc myBetBalance;
	
	@Before
	public void preConditions() {
		minBalance = -10;
		myRandom = new MockGenerator();
		myBetBalance = new BetBalanceCalc(minBalance, myRandom);
	}
	
	@Test
	public void addsMoneyCorrectly() {		
		myBetBalance.addMoney(20);
		assertEquals(20, myBetBalance.getCurrentBalance(), .001);
	}
	
	
	@Test(expected = NegativeNumberException.class)
	public void addNegativeMoneyException() {
		myBetBalance.addMoney(-20);
	}
	
	@Test
	public void belowMinBetReturnFalse() {
		assertFalse(myBetBalance.canBet(50));
	}
	
	@Test
	public void permittedBetReturnsTrue() {
		assertTrue(myBetBalance.canBet(5));
	}
	
	@Test
	public void betOnNumberWinReturnsCorrect() {
		myRandom.setRandom(3);
		assertEquals(190, myBetBalance.betOnANumber(10, 1, 20, 3), .001);
	}
	
	@Test
	public void betOnNumberLoseReturnsCorrect() {
		myRandom.setRandom(5);
		assertEquals(-10, myBetBalance.betOnANumber(10, 1, 20, 7), .001);
	}
	
	@Test(expected = InvalidProbabilityException.class)
	public void betOnIllegalProbabilityException() {
		myBetBalance.betOnProbability(10, -1);
	}
	
	@Test
	public void betOnProbWinReturnsCorrect() {
		myRandom.setRandom(32);
		assertEquals(10, myBetBalance.betOnProbability(10, .5), .001);
	}
	
	@Test
	public void betOnProbLoseReturnsCorrect() {
		myRandom.setRandom(32);
		assertEquals(-10, myBetBalance.betOnProbability(10, .2), .001);
	}
	
	@Test
	public void betOnNumberNegAmntReturn0() {
		myRandom.setRandom(6);
		assertEquals(0, myBetBalance.betOnANumber(-10, 1, 20, 5), .001);

	}
	
	@Test
	public void betOnProbNegAmntReturn0() {
		myRandom.setRandom(50);
		assertEquals(0, myBetBalance.betOnProbability(-10, .8), .001);

	}
	
	//checks if balance updates correctly after going through the bet num class
	@Test
	public void betOnNumWinBalanceUpdatesCorrect() {
		myRandom.setRandom(5);
		myBetBalance.betOnANumber(10, 1, 20, 5);
		assertEquals(190, myBetBalance.getCurrentBalance(), .001);
	}
	
	@Test
	public void betOnNumLoseBalanceUpdatesCorrect() {
		myRandom.setRandom(5);
		myBetBalance.betOnANumber(10, 1, 20, 7);
		assertEquals(-10, myBetBalance.getCurrentBalance(), .001);
	}
	
	@Test
	public void betOnProbBalanceWinUpDatesCorrect() {
		myRandom.setRandom(32);
		myBetBalance.betOnProbability(10, .5);
		assertEquals(10, myBetBalance.getCurrentBalance(), .001);
	}
	
	@Test
	public void betOnProbLoseBalanceUpdatesCorrect() {
		myRandom.setRandom(32);
		myBetBalance.betOnProbability(10, .2);
		assertEquals(-10, myBetBalance.getCurrentBalance(), .001);
	}
	
	
	
	
	
	
	
	
	
}
