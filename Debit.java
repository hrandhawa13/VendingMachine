/**
 * This is the sub class of the Bank Account class. It is the account of the debit card. 
 * It will have a boolean state determined by the positive amount of money in the account 
 * @author Harmanjit Randhawa
 */
public class Debit extends BankAccount{
	private int amountAvailable;
	/**
	 * Defualt Constructor: It creates an account number and a random Pin Number. 
	 */
	public Debit(){
		accountNum = firstNumber++;
		setPin();
		accountValid = true ;
		amountAvailable = 500;
	}
	/**
	 * This method is used to set the amount of money in the bank account 
	 * @param amount It is the amount of money that would be available in the account 
	 */
	public void setAmount( int amount ){
		amountAvailable = amount;
	}
	/**
	 * It withdraws an amount of money from the account
	 * @param amount Amount withdrawn from the account
	 * @return True if the transaction is complete else returns false
	 */
	public boolean withdraw ( double amount ){
		if ( amount > amountAvailable ){
			printMsg(" Your debit card doesn't have enough balance for the transaction ");
			accountValid = false;
			return false;
		}
		amountAvailable -= amount;
		return true;
	}
	/** 
	 * This method withdraws the amount if the pin entered is correct
	 * @param pinNumber This is the pin entered by user
	 * @param amount This is the amount to be withdrawn 
	 * @return Returns true if the transaction is successful and returns false otherwise
	 */
	public boolean transaction ( int pinNumber , double amount ){
		if ( pinNumber == pin ){
			return withdraw( amount ); 
		}
		printMsg( " Wrong PIN ");
		return false;
	}
}
