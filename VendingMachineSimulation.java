import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * This class is the testing class for the vending machine. It has 4 options of drinks to select.
 * User can pay with credit or debit card that is linked already in the database of this machine.
 * By default Credit cards start from 7777 to 7786. 2 cards are invalid card numbers: 7779 and 7782.
 * By default Credit cards start from 7787 to 7796. 2 cards do not have enough balance: 7790 and 7794.
 * By default all the cards have pin number: 1111
 * User can either be a customer or an operator to restock the machine.
 * In order to restock the machine, it has to be shut down once which requires a pin. By default that pin is 1111.
 * Before shutting down, the machine prints the result of sales.
 * All the messages and questions are displayed to the user with the help of jOptionPane	
 * @author Harmanjit Randhawa
 *
 */
public class VendingMachineSimulation {
	public static final int CAPACITY = 10;
	public static final int restockingPIN = 1111;
	public static ArrayList<Credit> creditList = new ArrayList<Credit> (CAPACITY);
	public static ArrayList<Debit> debitList = new ArrayList<Debit> (CAPACITY);
	public static ArrayList<Drinks> coffeeList = new ArrayList<Drinks> (CAPACITY);
	public static ArrayList<Drinks> waterList = new ArrayList<Drinks> (CAPACITY);
	public static ArrayList<Drinks> teaList = new ArrayList<Drinks> (CAPACITY);
	public static ArrayList<Drinks> colaList = new ArrayList<Drinks> (CAPACITY);
	public static String COCO_COLA = " Coco Cola, 255 mL, 340, 1.25 ";
	public static String COFFEE = " Starbucks Coffee, 350 mL, 100, 2.5 ";
	public static String ROIBOS_TEA = " Roibos Tea , 300 mL, 50, 3 ";
	public static String WATER = " Kasani Mineral Water , 200 mL, 0, 2 ";
	
	
	public static void main ( String[] args){
		int num = 10;
		int option;
		boolean validPayment = false;
		boolean validRun = false;
		boolean machineRunning = true;
		double amount ;
		double total = 0;
		
		initialiseList( num );
		displayDrinks();
	
		while ( machineRunning ){
			
			option = Integer.parseInt(drinkSelection() );//gets the row of the drink 
			amount = getPrice( option);
			do {	//charge customer for product 
				printMsg( "Amount to be paid is " + amount );
				int confirmation = Integer.parseInt( getInput ( " Press 1 to confirm or 2 to cancel " ));
				if ( confirmation == 1 ){//customer confirms to buy 
					validPayment = transaction( amount );
					validRun = validPayment;
					if ( validPayment == true )//if transaction goes through then we receive the money
						total += amount ;
				}
				else //customer cancels the transaction 
					validRun = true ;
			}
			while ( !validRun );
			
			if ( validPayment ){
				removeDrink( option );
				printMsg(" Enjoy your drink ");
			}
			//time for next user 
			option = Integer.parseInt( getInput( " Press 1 to buy or 2 to restock "));			
			if ( option == 1 ){//customer wants to buy 
				machineRunning = true ;
			}
			else {//its time to restock 
				int pin = Integer.parseInt( getInput( " Please enter the pin to restock "));
				if ( pin == restockingPIN ){	
					restock();
					printMsg( " Total sales of the day is : $" + total );
					machineRunning = false;
				}
				else {
					printMsg( " Wrong Pin ");
				}
			}
		}
	}	
	/**
	 * This method initialises all the lists of credit, debit, coffee, water, tea and cola list.
	 * It randomly sets 2 debit and credit cards to invalid state.
	 * @param num Number of items in each list 
	 */
	public static void initialiseList( int num ){
		for ( int i =0; i< num; i++ ){
			creditList.add( new Credit() );
			coffeeList.add( new Drinks( COFFEE ) );
			waterList.add( new Drinks( WATER ) );
			teaList.add( new Drinks( ROIBOS_TEA) );
			colaList.add( new Drinks( COCO_COLA) );
		}
		for ( int i =0; i< num; i++ ){	
			debitList.add( new Debit() );
		}
		//Setting 2 random credit cards to invalid
		creditList.get(2).setValid(false);
		creditList.get(5).setValid(false);
		//Setting 2 random debit cards to invalid
		debitList.get(3).setAmount(0);
		debitList.get(7).setAmount(0);
	}
	/**
	 * This method will add new items to each arraylist if the number of items are less than capacity
	 */
	public static void restock(){
		int n1 = coffeeList.size();
		int n2 = colaList.size();
		int n3 = teaList.size();
		int n4 = waterList.size();
		
		if ( n1 < CAPACITY  ){
			for ( int i = n1; i < CAPACITY ; i++){
				coffeeList.add( new Drinks( COFFEE ) );
			}
		}
		if ( n2 < CAPACITY  ){
			for ( int i = n2; i < CAPACITY ; i++){
				colaList.add( new Drinks( COCO_COLA ) );
			}
		}
		if ( n3 < CAPACITY  ){
			for ( int i = n3; i < CAPACITY ; i++){
				teaList.add( new Drinks( ROIBOS_TEA ) );
			}
		}
		if ( n4 < CAPACITY  ){
			for ( int i = n4; i < CAPACITY ; i++){
				waterList.add( new Drinks( WATER ) );
			}
		}
	}
	/**
	 * This method asks user for payment option and card details. 
	 * If the transaction is successful it returns true else false
	 * @param amount This is the price of the drink selected 
	 * //@preconditions User only selects the given options.
	 * @return Returns true if transaction is successful else false
	 */
	public static boolean transaction( double amount ){
		//display payment option
		int paymentOption = Integer.parseInt(getInput("How would you like to pay? Press 1 for credit or 2 for debit "));
		//ask for card number and pin
		int cardNumber = Integer.parseInt(getInput("Please enter the card number ") );
		int pin = Integer.parseInt(getInput(" Please enter the 4 digit security pin ") );
		//search for that card in the array list, match pin with it, check validity
		if ( paymentOption == 1){//user selected credit card
			return checkCreditValidity( cardNumber, pin, amount );
		}
		else {//user selected debit card
			return checkDebitValidity( cardNumber, pin, amount );
		}
	}
	/**
	 * This method displays the string parameter and asks the user to input some value
	 * @param str This is the question asked to user
	 * //@preconditions User only selects the given options.
	 * @return Returns the input by user
	 */
	public static String getInput(String str ){
		String input = JOptionPane.showInputDialog(null, str , "Input", JOptionPane.QUESTION_MESSAGE);
		return input;
	}
	/**
	 * This method looks for the card number in the arraylist of credit cards. 
	 * Once found it calls for the transaction method in the credit card class.
	 * @param cardNumber Account number for the user's credit card
	 * @param pin Pin for that card
	 * @param amount Amount of the drink selected
	 * @return Returns true if transaction is processed otherwise returns false
	 */
	public static boolean checkCreditValidity ( int cardNumber, int pin, double amount ){
		for ( Credit c : creditList ){
			if ( c.getAccountNumber() == cardNumber ){//this card is the user's card
				return c.transaction(pin, amount);
			}
		}
		printMsg( " Card not found in our databse. Please enter another card from range of 7777- 7796");
		return false; // card was not found
	}
	/**
	 * This method looks for the card number in the arraylist of debit cards. 
	 * Once found it calls for the transaction method in the credit card class.
	 * @param cardNumber Account number for the user's credit card
	 * @param pin Pin for that card
	 * @param amount Amount of the drink selected
	 * @return Returns true if transaction is processed otherwise returns false
	 */
	public static boolean checkDebitValidity ( int cardNumber, int pin, double amount ){
		for ( Debit d : debitList ){
			if ( d.getAccountNumber() == cardNumber ){//this card is the user's card
				return d.transaction(pin, amount);
			}
		}
		return false; // card was not found
	}
	/**
	 * Asks user to select one of the 4 drinks
	 * //@preconditions User only selects the given options.
	 * @return row number for the selected drink 
	 */
	public static String drinkSelection(){
		return getInput( " Enter 1 for Coffee: $" + coffeeList.get(0).getPrice() + 
				"\n 2 for water: $"+ waterList.get(0).getPrice() + 
				"\n 3 for tea: $" + teaList.get(0).getPrice()+ 
				"\n 4 for coco cola: $" + colaList.get(0).getPrice() );

	}
	/**
	 * This method gets the price of the drink 
	 * @param row This is the number of the row of the drink selected by the user 
	 * @return Returns the price of the particular drink
	 */
	public static double getPrice( int row ){
		double amount = 0;
		switch ( row ){
		case 1: {
			amount = coffeeList.get(0).getPrice() ;
			break;
			}
		case 2: {
			amount = waterList.get(0).getPrice() ;
			break;	
			}
		case 3:{
			amount = teaList.get(0).getPrice() ;
			break;
			}
		case 4: {
			amount = colaList.get(0).getPrice() ;
			break;
			}
		}
		return amount;
	}
	/**
	 * This method is used to remove a drink after the transaction is successful 
	 * @param option This is the drink to be removed
	 */
	public static void removeDrink( int option ){
		switch ( option ){
		case 1: {
			coffeeList.remove(0);
			break;
			}
		case 2: {
			waterList.remove(0);
			break;	
			}
		case 3:{
			teaList.remove(0);
			break;
			}
		case 4: {
			colaList.remove(0);
			break;
			}
		}
	}
	/**
	 * This method displays the number of drinks in each row of the vending machine
	 */
	public static void displayDrinks(){
		System.out.println( " Number of Coffee's in row 1 : " + coffeeList.size() );
		System.out.println( " Number of Mineral Water's in row 2 : " + waterList.size() );
		System.out.println( " Number of Roibos Tea's in row 3 : " + teaList.size() );
		System.out.println( " Number of Coco Cola's in row 4 : " + colaList.size() + "\n" );
	}
	/**
	 * Prints the argument using jOptionPane
	 * @param str This is the string to be dispayed 
	 */
	public static void printMsg( String str ){
		JOptionPane.showMessageDialog(null, str);
	}
}
		


