import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	JMenuItem menuItem;
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
		
		//add menu to menubar
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Fire");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gameSetting.setAddFireEnabled(true);
			}
		});
		
		menu.add(menuItem);
		this.setJMenuBar(menuBar);
		
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


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
