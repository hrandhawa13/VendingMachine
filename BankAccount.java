import javax.swing.JOptionPane;

/**
 * This class is the super class for bank accounts. It will have subclasses of Debit and credit
 * @author Harmanjit Randhawa
 *
 */
public class BankAccount {
	protected static int firstNumber = 7777;
	protected int accountNum ;
	protected int pin;
	protected boolean accountValid;
	
	protected BankAccount(){
		//This is just the protected constructor which does nothing. It wont allow us to make any instance of this class 
	}
	/**
	 * It returns whether the credit card is valid or not 
	 * @return Returns the valid state of the credit card
	 */
	public boolean isValid(){
		return accountValid;
	}
	
	/**
	 * It sets the 4 digit pin for the account. 
	 */
	protected void setPin(){
		// pin is set to 1111 for all the accounts initially
		pin = 1111;
		//pin = genRandom( 0001, 9999 );
	}
	
	/**
	 * This method returns the account number of the bank account 
	 * @return Account number of the bank account 
	 */
	public int getAccountNumber (){
		return accountNum;
	}
	/**
	 * Prints the argument using jOptionPane
	 * @param str This is the string to be dispayed 
	 */
	public static void printMsg( String str ){
		JOptionPane.showMessageDialog(null, str);
	}
}
