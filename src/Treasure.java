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
		Random rand = new Random();
		money = rand.nextInt(50)+50;
	}
	
	//getters and setters
	public int getMoney() {
		return money;
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
