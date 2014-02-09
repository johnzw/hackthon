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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class UI extends JFrame implements MouseListener{
	
	//these are ui components
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem itemFire, itemTreasure;
	GameSetting gameSetting;
	
	//constructor, basically setting the UIs
	public UI(){
		super();
		
		this.setSize(650,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		centered(this);
		gameSetting = new GameSetting();
		
		this.setVisible(true);
		
		//create menubar and menu
		menuBar = new JMenuBar();
		menu = new JMenu("Item");
		menuBar.add(menu);
		
		//create menu item itemFire
		itemFire = new JMenuItem("Fire");
		itemFire.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameSetting.setAddFireEnabled(true);
			}
		});
		menu.add(itemFire);
		//create menu item itemTreasure
		itemTreasure = new JMenuItem("Treasure");
		itemTreasure.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameSetting.setAddTreasureEnabled(true);
			}
		});
		menu.add(itemTreasure);
		
		//set menu bar to frame
		this.setJMenuBar(menuBar);
		this.addMouseListener(this);
		
	}
	

	private void centered(Container container) {  
        Toolkit toolkit = Toolkit.getDefaultToolkit();  
        Dimension screenSize = toolkit.getScreenSize();  
        int w = container.getWidth();  
        int h = container.getHeight();  
        container.setBounds((screenSize.width - w) / 2,  
                (screenSize.height - h) / 2, w, h);  
    }
	
	public static void main(String [] args){
		UI ui = new UI();
	}
	
	public void paint(int x, int y){
		Graphics g = this.getGraphics();
		if(gameSetting.isAddFireEnabled()){
			g.setColor(Color.red);
			g.drawOval(x, y, 10, 10);
			g.dispose();
			gameSetting.addFire(new Fire(x ,y));
			gameSetting.blockAdding();
		}
		else if(gameSetting.isAddTreasureEnabled()){
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Engravers MT", Font.BOLD, 1000));
			g.drawOval(x, y, 10, 10);
			g.dispose();
			gameSetting.addTreasure(new Treasure(x,y));
			gameSetting.blockAdding();
			
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
