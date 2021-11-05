package BetBalancePackage;

public class BetBalanceCalc {

	private double balance;
	private double minBalance;
	private IRandomValueGenerator myRandom;
	

	public BetBalanceCalc(double minBalance, IRandomValueGenerator myRandom) {
		balance = 0;
		if (minBalance <= 0) {
			this.minBalance = minBalance;
		}
		this.myRandom = myRandom;

	}

	public double getCurrentBalance() {
		return balance;
	}

	public boolean canBet(double amnt) {
		if ((balance - minBalance) >= amnt && amnt >= 0) {
			return true;
		}
		return false;
	}

	public void addMoney(double amnt) {
		if (amnt >= 0) {
			balance += amnt;
		} else {
			throw new NegativeNumberException();
		}
	}

	public double betOnANumber(double amnt, int min, int max, int selectedNumber) {
		double changeInBalance = 0;
		if (canBet(amnt)) {
			if (myRandom.getRandom(min, max) == selectedNumber) {
				// win the bet
				int range = (max - min) + 1;
				changeInBalance = ((range - 1) * amnt);
				balance += changeInBalance;
			} else {
				// lose the bet
				changeInBalance = -1 * (amnt);
				balance += changeInBalance;
			}

			return changeInBalance;
		}
		//if cannot bet 
		return 0;
	}

	public double betOnProbability(double amnt, double p) {
		double changeInBalance = 0;
		if (canBet(amnt)) {
			if (p < 0 || p > 1) {
				throw new InvalidProbabilityException();
			} else {
				// valid probability
				int probability = (int) (p * 100);
				if (myRandom.getRandom(1, 100) <= probability) {
					// win bet
					double calc = Math.pow(p, -1);
					changeInBalance = (calc - 1) * amnt;
					balance += changeInBalance;
				} else {
					// lose bet
					changeInBalance = (-1) * amnt;
					balance += changeInBalance;
				}
			}

			return changeInBalance;
		}
		// if cannot bet
		return 0;
	}

}
