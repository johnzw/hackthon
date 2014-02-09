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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UI extends JFrame implements MouseListener {

	// these are ui components
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem itemFire, itemTreasure;
	GameSetting gameSetting;
	private ServerSocket serverSocket;
	private static final int SERVERPORT = 10000;
	Thread serverThread = null;

	// constructor, basically setting the UIs
	public UI() {
		super();

		this.setSize(650, 600);
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
		
		startInternetService();
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
	}

	public void paint(int x, int y) {
		Graphics g = this.getGraphics();
		if (gameSetting.isAddFireEnabled()) {
			g.setColor(Color.red);
			g.drawOval(x, y, 10, 10);
			g.dispose();
			gameSetting.addFire(new Fire(x, y));
			gameSetting.blockAdding();
		} else if (gameSetting.isAddTreasureEnabled()) {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Engravers MT", Font.BOLD, 1000));
			g.drawOval(x, y, 10, 10);

			g.dispose();
			gameSetting.addTreasure(new Treasure(x, y));
			gameSetting.blockAdding();

		}
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

	class CommunicationThread implements Runnable {
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
