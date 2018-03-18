package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.implementation.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppDialog;
import view.AppFrame;

public class AddPlayerListener implements ActionListener {
	
	private GameEngine model;
	private AppFrame appFrame;
	private AppDialog appDialog;
	
	
	public AddPlayerListener(AppFrame appFrame,GameEngine model){
		this.appFrame = appFrame;
		this.model = model;
	}
	
	
	/**
	 * ActionListeners for Add Player button(Common listeners for both menu item and tool bar button)
	 * @author pratap
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(checkDuplicatePlayers()){//check for the existing player
			showPlayerDialog(); //show the dialog for user inputs
			addPlayer(); //Create player objects and add it to GameEngine
		}		
	}

	/**
	 * Displays dialog box for user input
	 * @author pratap
	 */
	private void showPlayerDialog(){
		appDialog = new AppDialog(); //Creating the dialog
		JOptionPane.showConfirmDialog(null,appDialog,"Enter Player Details",JOptionPane.OK_CANCEL_OPTION);		
	}
	
	
	/**
	 * Test if the user entered name and points are correct
	 * @author pratap
	 * @return
	 */
	private boolean checkPlayerDetails(){
		if(appDialog!= null)
			if(appDialog.checkPlayerName() && appDialog.checkPlayerPoints()){
				return true;			
			}
		JOptionPane.showMessageDialog(null,"Player Details are not stored. Please enter correct details");
		return false;
	}
	
	/**
	 * Test if the player has been already added. Restrict user to add more than one player
	 * based on assginment 1.
	 * @author pratap
	 * @return
	 */
	private boolean checkDuplicatePlayers(){
		if(!ControllerUtils.isPlayerPresent())
			return true;
		
		else
		JOptionPane.showMessageDialog(null,"<html>Player has been already added for assigment 1. <br> "
				+ " You can delete the player from drop down menu!");	
		return false;
	}
	
	
	/**
	 * Creates the player objects and add it to the model
	 * Displays the player details in view if player is added successfully
	 * @author pratap
	 */
	private void addPlayer(){
		if(checkPlayerDetails()){
			
				String playerID = "PID"+(model.getAllPlayers().size()+1);
				//add player to the gameEngine
				model.addPlayer(new SimplePlayer(playerID,appDialog.getPlayerName(),appDialog.getPlayerPoints()));
				ControllerUtils.setPlayerID(playerID);
				ControllerUtils.setPlayerPresence(true);
				Player addedPlayer = model.getPlayer(ControllerUtils.getPlayerID());
				JOptionPane.showMessageDialog(null,addedPlayer.getPlayerName() +" has been added successfully!");
				//Clear existing view to display the added player
				appFrame.getAppPanel().getAppCardPanel().clearAppCardPanel();
				//display the added player in the view
				appFrame.getAppPanel().getAppCardPanel().updatePlayerDetails(addedPlayer.getPlayerId(),
						addedPlayer.getPlayerName(), addedPlayer.getPoints(), addedPlayer.getBet());
				
		}
	}
	
	
	
	
	
	

	
}
