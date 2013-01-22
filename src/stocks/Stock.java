package stocks;

public class Stock {

	
	private float price;
	private String name;
	
	public Stock(float price, String name){
		this.price = price;
		this.name = name;
	}
	
	public float getPrice(){
		return price;
	}
	
	public String getName(){
		return name;
	}
}
