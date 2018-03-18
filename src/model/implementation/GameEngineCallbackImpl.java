package model.implementation;

import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingUtilities;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.AppCardPanel;
import view.AppFrame;

public class GameEngineCallbackImpl implements GameEngineCallback,Observer {
	private AppFrame appFrame;
	private Player currentPlayer;
	private PlayingCard currentPlayingCard;
	
	//Final constants to handle model and update view accordingly
	private final int PLAYER_DEALT = 1;
	private final int PLAYER_BUST = 2;
	private final int PLAYER_RESULT = 3;
	private final int HOUSE_DEALT = 4;
	private final int HOUSE_BUST = 5;
	
	
	/**
	 * Constructor that takes view as parameter.
	 * @author pratap
	 * @param appFrame
	 */
	public GameEngineCallbackImpl(AppFrame appFrame ) { this.appFrame = appFrame; }
	
	
	/**
	 * Returns the AppCardPanel reference internally
	 * @see view.AppCardPanel
	 * @author pratap
	 * @return
	 */
	private  AppCardPanel getView(){
		
		assert(appFrame.getAppPanel().getAppCardPanel() != null):"View should be instantiated";
		return appFrame.getAppPanel().getAppCardPanel();
	}
	

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		//update view for each dealt(without bust) of player
		getView().updatePlayerCardDetails(card.toString());
	}
	

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		//update view if the player is busted. Ask view to decorate with red color
		getView().updatePlayerCardDetails("<font color=\"red\">"+player.getPlayerName()+" BUSTED with card -  "+card.toString()+"</font>");
	}

		
	@Override
	public void result(Player player, int result, GameEngine engine) {
		//Update view the final result once the all the player dealt is done, and decorate with blue color
		getView().updatePlayerCardDetails("<font color=\"blue\">Final points for "+player.getPlayerName()+" is "+player.getResult()+"</font>");
	}
	
	
	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		//Update view for each dealt(without bust) of house
		getView().updateHouseCardDetails(card.toString());		
	}

	
	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		//Update view if the house is busted. Ask view to decorate with red color
		getView().updateHouseCardDetails("<font color=\"red\"> House BUSTED with card - "+card.toString()+"</font>");
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		//Update view the final result once the all the house dealt is done, and decorate with blue color
		getView().updateHouseCardDetails("<font color=\"blue\">Final points for House is "+result+"</font>");
	}
	
	/**
	 * Displays the final points of player once the game is finished
	 * @author pratap
	 */
	public void displayFinalResult(){
		//Update view the final points of the player once the game is completed
		getView().updateHouseCardDetails("<br>Game End: "+currentPlayer);
	}
	
	
	/**
	 * Overridden observer method to call view based on model action
	 * @author pratap
	 */
	@Override
	public void update(Observable o, Object arg1) {
		GameEngine gameEngine = (GameEngine)o;
		
		SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					//Assigns the current player
					if(arg1 instanceof Player)
						currentPlayer = (Player)arg1;
					
					//Assigns the current playing card
					else if(arg1 instanceof PlayingCard)
						currentPlayingCard = (PlayingCard)arg1;
					
					//Check for the model action
					else if(arg1 instanceof Integer){
					 		int argument = (Integer)arg1;
							
					 		//Whenever player is dealt except for bust card
					 		if(argument == PLAYER_DEALT)
								nextCard(currentPlayer,currentPlayingCard,gameEngine);
							
					 		//When player is busted
					 		else if(argument == PLAYER_BUST )
								bustCard(currentPlayer,currentPlayingCard,gameEngine);
							
					 		//When player completes their turn
					 		else if(argument == PLAYER_RESULT)
								result(currentPlayer,currentPlayer.getResult(),gameEngine);
							
					 		//Whenever house is dealt except for bust card
					 		else if(argument == HOUSE_DEALT)
								nextHouseCard(currentPlayingCard,gameEngine);
							
					 		//When hosue is busted
					 		else if(argument == HOUSE_BUST)
								houseBustCard(currentPlayingCard,gameEngine);
							
					 		//When house completes their turn
					 		else {
								houseResult((Integer)arg1,gameEngine);
								displayFinalResult();
							}
					}
				}
		});
			
	}
	
	
	
	

}
