
public class Location {
	private int x;
	private int y;
	
	public Location(int x, int y){
		this.x =x;
		this.y =y;
	}
	
	public long distanceSquared(Location loc){
		return (loc.getX()-this.getX())*(loc.getX()-this.getX())+
				(loc.getY()-this.getY())*(loc.getY()-this.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
