/**
 * This class defines an instance of the drinks class. It will be used to declare other classes like Mineral water, Coco Cola, Roibos Tea, Coffee 
 * @author Harmanjit Randhawa
 *
 */
public class Drinks {
	private  String name ;
	private  String capacity  ;
	private String price ;
	private  String calories  ;
	public Drinks( String str ){
		String[] arr = str.split(", ");
		name = arr[0];
		capacity = arr[1];
		calories = arr[2];
		price = arr[3];	
	}
	
	/**
	 * THis method returns the details of the class
	 * @return Details of the class which includes name, price and calories
	 */
	public String toString(){
		return "Name = " + name + "\n capacity + " + capacity + " \nPrice = " + price + "\n Calories = " + calories  ;
	}
	/**
	 * This method returns the price of the drink
	 * @return Price of the drink
	 */
	public double getPrice(){
		return Double.parseDouble( price );
	}

}
