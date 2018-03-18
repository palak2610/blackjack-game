package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppCardPanel;
import view.AppFrame;

public class GameHandler implements ActionListener {
	
	private AppFrame appFrame;
	private AppCardPanel cardView;
	private GameEngine model;	
	private int betPoints;
	
	
	public GameHandler(AppFrame appFrame,GameEngine model)
	{
		this.model = model;	
		this.appFrame = appFrame;
	}
	

	
	/**
	 * Listener to get the bet point and start the game
	 * @author pratap
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(isPlayerAdded()){//check if player has been added.
			Player currentPlayer = getPlayer(); //Get the player from model
			if(checkPointsBalance(currentPlayer)){
				showBetDialog(); //show the dialog to get the bet points
				if(checkBetDialog()){
						//Thread to start the game
						new Thread(){
							public void run(){								
								//start the game
								playGame(currentPlayer,betPoints);
							}
						}.start();						
				}
			}
		}
		else //Display error message if the player is not added
			JOptionPane.showMessageDialog(null,"Please add the player to play the game");
	}
	
	
		
	
	/**
	 * Run the game from the controller
	 * @author pratap
	 * @param currentPlayer
	 * @param betPoints
	 */
	private void playGame(Player currentPlayer,int betPoints){					
			if(currentPlayer.placeBet(betPoints)){
				cardView =  appFrame.getAppPanel().getAppCardPanel();
				cardView.clearAppCardPanel(); //clear the view
				cardView.updatePlayerDetails(currentPlayer.getPlayerId(),currentPlayer.getPlayerName(),currentPlayer.getPoints(),currentPlayer.getBet());
				model.dealPlayer(currentPlayer, 500);
				model.dealHouse(500);
				model.calculateResult();
				resetBet(); //clear the cache of bet amount
			}
			else
				//Bet points was unsuccessfull
				JOptionPane.showMessageDialog(null,"Bet points cannot be greater than available points");
	}
	
	
	/**
	 * Get the player from model to play the game
	 * @author pratap
	 * @return
	 */
	private Player getPlayer(){
		return model.getPlayer(ControllerUtils.getPlayerID());
		
	}
	
	
	/**
	 * Check if the player has been added
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
	 * Display message to get the bet point from user
	 * @author pratap
	 */
	private void showBetDialog(){
		try{
		betPoints = Integer.valueOf(JOptionPane.showInputDialog("Enter Bet"));}
		catch(Exception e){}		
	}
	
	
	/**
	 * Check if the entered bet point is valid
	 * @author pratap
	 * @return
	 */
	private boolean checkBetDialog(){
		
		if(betPoints > 0)
			return true;
		else 
			showErrorDialog();
		return false;		
	}
	
	
	/**
	 * Display error message if the bet point is invalid
	 * @author pratap
	 */
	private void showErrorDialog(){
		JOptionPane.showMessageDialog(null,"Bet details are not stored. Please enter Bet greater than 0");
	}
	
	
	/**
	 * Reset(clear cache) the bet amount once the game is played
	 * @author pratap	
	 */
	private void resetBet(){
		betPoints = 0;
	}
	
	
		
	/**
	 * Check if the player has enough balance points to play the game
	 * @author pratap
	 * @param currentPlayer
	 * @return
	 */
	private boolean checkPointsBalance(Player currentPlayer){
		if(currentPlayer.getPoints() > 0)
			return true;
		
		else
			JOptionPane.showMessageDialog(null,"You do not have enough points to play");
		
		return false;
			
		
	}
	
	
}
