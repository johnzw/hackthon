
public class Player {
	private Location location;
	private Cordinate initialCordinate;
	private final static int FULLHP = 100;
	private final static int ZEROHP = 0;
	private int HP;
	private int Fortune;
	
	public Player(Cordinate cordinate, int x, int y){
		this.initialCordinate = cordinate;
		location = new Location(x, y);
		HP = FULLHP;
		Fortune = 0;
	}
	
	public Cordinate getInitialCordinate() {
		return initialCordinate;
	}

	public void catchFire(){
		HP = HP -2;
	}
	
	public void addFortune(int money){
		Fortune = Fortune + money;
	}

	public int getFortune() {
		return Fortune;
	}

	public void setFortune(int fortune) {
		Fortune = fortune;
	}

	public Location getLocation() {
		return location;
	}

	public void MoveTo(Location location) {
		this.location = location;
	}

	public int getHP() {
		return HP;
	}
	
	
}
