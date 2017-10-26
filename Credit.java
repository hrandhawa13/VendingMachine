/**
 * This is the sub class of the Bank Account class. It is the account of the credit card. 
 * It will have a boolean state determined by the bank.
 * @author Harmanjit Randhawa
 */
public class Credit extends BankAccount{
	private double amountOwe;
	/**
	 * Defualt Constructor: It creates an account number and a random Pin Number. 
	 */
	public Credit(){
		accountNum = firstNumber++;
		setPin();
		accountValid = true ;
		amountOwe =0;
	}
	/**
	 * This method sets the state of the account to be either true or false
	 * @param state This is the state of the account to be set 
	 */
	public void setValid( boolean state ){
		accountValid = state ;
	}
	/** 
	 * This method withdraws the amount if the pin entered is correct and the account is in valid state
	 * @param pinNumber This is the pin entered by user
	 * @param amount This is the amount to be withdrawn 
	 * @return Returns true if the transaction is successful and returns false otherwise
	 */
	public boolean transaction ( int pinNumber , double amount ){
		if ( pinNumber == pin ){
			if ( accountValid ){
				amountOwe += amount;
				return true;
			}
			printMsg( " Account is not valid ");
			return false;
		}
		printMsg( " Wrong Pin ");
		return false;
	}
}
