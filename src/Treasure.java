import java.util.Random;


public class Treasure {
	private int money;
	private Location location;
	//counstrutor
	public Treasure(int x, int y){
		super();
		location = new Location(x,y);
		moneyGenerator();
	}
	
	public void moneyGenerator(){
		//Random rand = new Random();
		money = 50;
	}
	
	//getters and setters
	public int getMoney() {
		int m = money;
		money = 0;
		return m;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	

}
