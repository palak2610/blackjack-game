package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

public class RemovePlayerListener implements ActionListener {

	private GameEngine model;
	private AppFrame appFrame;
	
	
	public RemovePlayerListener(AppFrame appFrame,GameEngine model){
		this.appFrame = appFrame;
		this.model = model;
	}
	
	
	/**
	 * Listener for remove player button
	 * @author pratap
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {		
		removePlayer();
		
	}
	
	
	/**
	 * Check if atleast one player has been added to the game
	 * @author pratap
	 * @return
	 */
	private boolean isPlayerAdded(){
		if(ControllerUtils.isPlayerPresent()){
			return true;
		}
		return false;
			
	}
	
	/**
	 * Removes the player from the game
	 * @author pratap
	 */
	private void removePlayer(){
		if(isPlayerAdded()){// check if player has been added in the game
			//Get the player object from model
			Player player = model.getPlayer(ControllerUtils.getPlayerID());
			String playerName = player.getPlayerName();
			//removes the player from model
			model.removePlayer(player);
			//show the removal message in dialog
			JOptionPane.showMessageDialog(null,playerName+" has been removed successfully!");
			ControllerUtils.setPlayerPresence(false);
			//clear the existing player detail from the view
			appFrame.getAppPanel().getAppCardPanel().clearAppCardPanel();			

		}
		else
			//Message to show if there is no player to delete
			JOptionPane.showMessageDialog(null,"No Player to delete");
	}

}
