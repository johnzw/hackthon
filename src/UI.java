import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.google.gson.Gson;

public class UI extends JFrame implements MouseListener {

	// these are ui components
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem itemFire, itemTreasure;
	GameSetting gameSetting;
	private ServerSocket serverSocket;
	private static final int SERVERPORT = 10000;
	Thread serverThread = null;
	public static int HEIGHT = 600;
	public static int WIDTH = 650;
	
	//it's for test
	public int testnumberOfCom =0;

	// constructor, basically setting the UIs
	public UI() {
		super();

		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		centered(this);
		gameSetting = new GameSetting();

		this.setVisible(true);

		// create menubar and menu
		menuBar = new JMenuBar();
		menu = new JMenu("Item");
		menuBar.add(menu);

		// create menu item itemFire
		itemFire = new JMenuItem("Fire");
		itemFire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSetting.setAddFireEnabled(true);
			}
		});
		menu.add(itemFire);
		// create menu item itemTreasure
		itemTreasure = new JMenuItem("Treasure");
		itemTreasure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameSetting.setAddTreasureEnabled(true);
			}
		});
		menu.add(itemTreasure);

		// set menu bar to frame
		this.setJMenuBar(menuBar);
		this.addMouseListener(this);
		
		//startInternetService();
	}

	private void centered(Container container) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int w = container.getWidth();
		int h = container.getHeight();
		container.setBounds((screenSize.width - w) / 2,
				(screenSize.height - h) / 2, w, h);
	}
	
	public void startInternetService(){
		this.serverThread = new Thread(new ServerThread());
		this.serverThread.start();
	}

	public static void main(String[] args) {
		UI ui = new UI();
		
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<100;i++){
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ui.test(100.00000+0.00001*i, 100.00000+0.00001*i);
		}
	}

	public void paint(int x, int y) {
		Graphics g = this.getGraphics();
		if (gameSetting.isAddFireEnabled()) {
			g.setColor(Color.red);
			g.fillOval(x, y, 30, 30);
			g.dispose();
			gameSetting.addFire(new Fire(x, y));
			gameSetting.setAddFireEnabled(false);
		} 
		else if (gameSetting.isAddTreasureEnabled()) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Engravers MT", Font.BOLD, 1000));
			g.fillOval(x, y, 30, 30);

			g.dispose();
			gameSetting.addTreasure(new Treasure(x, y));
			gameSetting.setAddTreasureEnabled(false);
		}
	}
	
	public void repaint(int x, int y){
		Graphics g = this.getGraphics();
		if (gameSetting.isAddFireEnabled()) {
			g.setColor(Color.red);
			g.fillOval(x, y, 30, 30);
			g.dispose();
			
			gameSetting.setAddFireEnabled(false);
		} 
		if (gameSetting.isAddTreasureEnabled()) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Engravers MT", Font.BOLD, 1000));
			g.fillOval(x, y, 30, 30);

			g.dispose();
			
			gameSetting.setAddTreasureEnabled(false);
		}
		if(gameSetting.isDrawPlayerEnabled()){
			g.setColor(Color.gray);
			g.setFont(new Font("Engravers MT", Font.BOLD, 1000));
			g.fillOval(x, y, 30, 30);

			g.dispose();
		}
	}
	public void reDraw(){
		Graphics g = this.getGraphics();
		g.clearRect(0, menuBar.getHeight(), WIDTH, HEIGHT);
		
		//draw fire
		ArrayList<Fire> fire = gameSetting.getFires();
		for(int i=0; i<fire.size();i++){
			gameSetting.setAddFireEnabled(true);
			repaint(fire.get(i).getLocation().getX(), fire.get(i).getLocation().getY());
		}
		
		//draw treasure
		ArrayList<Treasure> treasure = gameSetting.getTreasures();
		for(int i=0; i<treasure.size();i++){
			gameSetting.setAddTreasureEnabled(true);
			repaint(treasure.get(i).getLocation().getX(), treasure.get(i).getLocation().getY());
		}
		
		//draw player
		Player player = gameSetting.getPlayer();
		System.out.println(player.getLocation().getX()+","+ player.getLocation().getY());
		gameSetting.setDrawPlayerEnabled(true);
		repaint(player.getLocation().getX(), player.getLocation().getY());
		gameSetting.setDrawPlayerEnabled(false);
		
	}

	class ServerThread implements Runnable {
		// invoked when thread start
		public void run() {
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(SERVERPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (!Thread.currentThread().isInterrupted()) {
				try {
					socket = serverSocket.accept();
					CommunicationThread commThread = new CommunicationThread(
							socket);
					new Thread(commThread).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void test(double x, double y){
		testnumberOfCom++;
		Cordinate c = new Cordinate(x,y);
		
		Gson gson = new Gson();
		String read = gson.toJson(c);
		LogicProcess.compute(gameSetting, read, testnumberOfCom);
		gameSetting = LogicProcess.gameSetting;
		reDraw();
		System.out.println(gameSetting.warning);
	}
	class CommunicationThread implements Runnable {
		private long numberOfCom =0;
		private Socket clientSocket;
		private BufferedReader input;
		public CommunicationThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
			try {
				this.input = new BufferedReader(new InputStreamReader(
						this.clientSocket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					String read = input.readLine();
					numberOfCom++;
					LogicProcess.compute(gameSetting, read, numberOfCom);
					gameSetting = LogicProcess.gameSetting;
					reDraw();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		paint(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
