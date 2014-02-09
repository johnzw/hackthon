import java.util.ArrayList;


public class GameSetting {
	private ArrayList<Fire> fires;
	private ArrayList<Treasure> treasures;
	private boolean addFireEnabled = false;
	private boolean addTreasureEnabled = false;
	private boolean drawPlayerEnabled = false;
	public boolean isDrawPlayerEnabled() {
		return drawPlayerEnabled;
	}

	public void setDrawPlayerEnabled(boolean drawPlayerEnabled) {
		this.drawPlayerEnabled = drawPlayerEnabled;
	}

	private Player player = null;
	public static String warning = "";
	
	public GameSetting(){
		fires = new ArrayList<Fire>();
		treasures = new ArrayList<Treasure>();
	}
	
	public void playerCatchFire(){
		player.catchFire();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void playerMoving(Location loc){
		player.MoveTo(loc);
		
		//check if player is getting near to fire
		warning ="";
		for(int i =0; i< fires.size(); i++){
			long distance = player.getLocation().distanceSquared(fires.get(i).getLocation());
			
			if (distance< 3000 && distance >= 900){
				warning = " NearFire"+(i+1);
			}
			else if(distance < 900){
				warning = warning +" IntoFire" +(i+1);
				this.playerCatchFire();
			}
		}
		
		//check if player is getting near to treasure
		for(int i =0; i< treasures.size(); i++){
			long distance = player.getLocation().distanceSquared(treasures.get(i).getLocation());
			
			if (distance< 3000 && distance >= 900){
				warning = warning+ " NearTreasure" + (i+1);
			}
			else if(distance <900){
				warning = warning +" IntoTreasure" + (i+1);
				this.playerGrabFortune(treasures.get(i).getMoney());
			}
		}
			
		if(warning.equals("")){
			warning = "Nothing";
		}
	}

	public void playerGrabFortune(int money){
		player.addFortune(money);
	}
	
	public void playerJoin(Player player){
		this.player = player;
	}
	
	public void addFire(Fire f){
		fires.add(f);
	}
	
	public void addTreasure(Treasure t){
		treasures.add(t);
	}

	public ArrayList<Fire> getFires() {
		return fires;
	}

	public ArrayList<Treasure> getTreasures() {
		return treasures;
	}

	public boolean isAddFireEnabled() {
		return addFireEnabled;
	}

	public boolean isAddTreasureEnabled() {
		return addTreasureEnabled;
	}

	public void setAddFireEnabled(boolean addFireEnabled) {
		this.addFireEnabled = addFireEnabled;
	}

	public void setAddTreasureEnabled(boolean addTreasureEnabled) {
		this.addTreasureEnabled = addTreasureEnabled;
	}
	
	public void blockAdding(){
		this.addFireEnabled = false;
		this.addTreasureEnabled = false;
	}
	
}
