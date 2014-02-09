import java.util.Random;


public class Fire {
	
	private Location location;
	private int size;
	public static final int BIG = 3;
	public static final int MEDIUM = 2;
	public static final int SMALL = 1;
	
	public Fire(int x, int y){
		location = new Location(x ,y);
		sizeGenerator();
	}
	
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void sizeGenerator(){
		Random rand = new Random();
		size = rand.nextInt(3)+1;
	}
	

}
