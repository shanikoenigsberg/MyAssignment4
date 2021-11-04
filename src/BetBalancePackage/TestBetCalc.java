package BetBalancePackage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestBetCalc {
	
	private double minBalance;
	private MockGenerator myRandom;
	private BetBalanceCalc myBetBalance;
	
	@Before
	public void preConditions() {
		minBalance = -10;
		myRandom = new MockGenerator();
		myBetBalance = new BetBalanceCalc(minBalance, myRandom);
	}
	
	@Test
	public void addsMoneyCorrectly() {		
		myBetBalance.addMoney(20);
		assertEquals(20, myBetBalance.getCurrentBalance(), .01);
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
	public void betOnNumberWinCalcCorrect() {
		myRandom.setRandom(3);
		assertEquals(950, myBetBalance.betOnANumber(50, 1, 20, 3), .01);
	}
	
	@Test
	public void betOnNumberLoseCalcCorrect() {
		myRandom.setRandom(5);
		assertEquals(-50, myBetBalance.betOnANumber(50, 1, 2, 7), .01);
	}
	
	@Test(expected = InvalidProbabilityException.class)
	public void betOnIllegalProbabilityException() {
		myBetBalance.betOnProbability(10, -1);
	}
	
	@Test
	public void betOnProbWinCalcCorrect() {
		myRandom.setRandom(32);
		assertEquals(10, myBetBalance.betOnProbability(10, .5), .01);
	}
	
	@Test
	public void betOnProbLoseCalcCorrect() {
		myRandom.setRandom(32);
		assertEquals(-10, myBetBalance.betOnProbability(10, .2), .01);
	}
	
	@Test
	public void betOnNegNumberReturn0() {
		myRandom.setRandom(6);
		assertEquals(0, myBetBalance.betOnANumber(-10, 1, 20, 5), .01);

	}
	
	@Test
	public void betOnNegNumberProbReturn0() {
		myRandom.setRandom(50);
		assertEquals(0, myBetBalance.betOnProbability(-10, .8), .01);

	}
	
	
	
	
	
	
}
