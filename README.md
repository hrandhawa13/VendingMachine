# VendingMachine
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
