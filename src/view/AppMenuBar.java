package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import controller.AddPlayerListener;
import controller.RemovePlayerListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class AppMenuBar extends JMenuBar {

	private JMenu gameMenu,editMenu,helpMenu;
	private JMenuItem addPlayerMenuItem,removePlayerItem,clearMenuItem, exitGameMenuItem,aboutGameMenuItem;
	
	
	
	public AppMenuBar(AppFrame appFrame,GameEngine model){
		//create main menu		
		gameMenu = new JMenu("Game");
		editMenu = new JMenu("Edit");
		helpMenu = new JMenu("Help");
		//create menuitems for Edit menu
		addPlayerMenuItem = new JMenuItem("Add New Player");
		removePlayerItem = new JMenuItem("Remove Player");
		//create menuitems for Game menu
		clearMenuItem = new JMenuItem("Clear");
		exitGameMenuItem = new JMenuItem("Exit Game");
		//create menuitems for Help menu
		aboutGameMenuItem = new JMenuItem("About Game");
		
		gameMenu.add(clearMenuItem);
		gameMenu.add(exitGameMenuItem);
		
		editMenu.add(addPlayerMenuItem);
		editMenu.add(removePlayerItem);
		
		helpMenu.add(aboutGameMenuItem);
		
		add(gameMenu);
		add(editMenu);
		add(helpMenu);
		
		//Listener for Add Player menuitem
		addPlayerMenuItem.addActionListener(new AddPlayerListener(appFrame,model));
		//Listener for Remove Player menuitem
		removePlayerItem.addActionListener(new RemovePlayerListener(appFrame,model));
		//Listener for aboutGame menuitem
		aboutGameMenuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AppMenuBar.this.showHelpMessage(); //shows the help message
					}	
		});
		
		
		//Listener for ExitGame menuitem
		exitGameMenuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						appFrame.dispose(); //Killing the frame and exiting from game.
						
					}			
		});
		
		
		//Listener for clear menuitem
		clearMenuItem.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						//Clearing the cardview
						appFrame.getAppPanel().getAppCardPanel().clearAppCardPanel();
					}			
		});
		
		
	}
	
		
	/**
	 * Displays Help message for the game
	 * @author pratap
	 * 
	 */
	public void showHelpMessage(){
		JOptionPane.showMessageDialog(null,"<html>Speed21! <br>"
				+ "The rules are simple, the player places "
				+ "a bet <br>and then receives a set of cards "
				+ "from the dealer <br> (from a standard 52 "
				+ "card deck) until they bust<br>"
				+ " at the limit of 21.</html>");
		
		}
}

