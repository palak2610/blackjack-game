package view;

import javax.swing.JButton;
import javax.swing.JToolBar;
import controller.AddPlayerListener;
import controller.GameHandler;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class AppToolBar extends JToolBar{
	
	
	private JButton addPlayerButton,playGameButton;
	private AppFrame appFrame;
	
	public AppToolBar(AppFrame appFrame,GameEngine model){
		this.appFrame = appFrame;
		//Creating toolbar buttons
		addPlayerButton = new JButton("Add Player");
		playGameButton = new JButton("Play Game");
				
		add(addPlayerButton);
		add(playGameButton);
		
		//Listener for Add Player button
		addPlayerButton.addActionListener(new AddPlayerListener(appFrame,model));
		//Listener for Play Game button
		playGameButton.addActionListener(new GameHandler(AppToolBar.this.appFrame,model));
	}
	
	
	
}
