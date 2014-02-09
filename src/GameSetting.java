import java.util.ArrayList;


public class GameSetting {
	private ArrayList<Fire> fires;
	private ArrayList<Treasure> treasures;
	private boolean addFireEnabled = false;
	private boolean addTreasureEnabled = false;
	private Player player = null;
	
	public GameSetting(){
		fires = new ArrayList<Fire>();
		treasures = new ArrayList<Treasure>();
	}
	
	public void playerCatchFire(){
		player.catchFire();
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
