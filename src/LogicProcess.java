import com.google.gson.Gson;

//it is a class providing service to process the string data sever read from client
//try to locate the client's position in the game map
public class LogicProcess {
	public static GameSetting gameSetting =null;
	
	public static void compute(GameSetting  gs, String read, long count){
		//let c to be the Cordinate
		Gson gson = new Gson();
		Cordinate c = gson.fromJson(read, Cordinate.class);
		
		//pass the augment
		//we need to map the Cordinate into the location in the game
		gameSetting = gs;
		if(count == 1){
			//when game starts, player is located in the center of our map
			gameSetting.playerJoin(new Player(c, UI.WIDTH/2, UI.HEIGHT/2));
		}
		else{
			Cordinate initialc = gameSetting.getPlayer().getInitialCordinate();
			
			//main point of this method, to map player in the map according to initial cordinate
			int xDiffer = (int)((c.getX() - initialc.getX()) * 100000);
			int yDiffer = (int)((c.getY() - initialc.getY()) * 100000);
			
			//make player move to the new position
			gameSetting.playerMoving(new Location(UI.WIDTH/2+ 5*(xDiffer/2), UI.HEIGHT/2 +5*(yDiffer/2)));
			
		}
	}
}
