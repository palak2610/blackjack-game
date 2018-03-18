package model.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * 
 * @author pratap
 *
 */
public class GameEngineImpl extends Observable implements GameEngine {
	
	private Deque<PlayingCard> playingDeck;	
	private List<Player> listOfPlayers;
	private int houseResult;
	private final int RESET = 0;
	//Final constants to handle model and update view accordingly
	private final int PLAYER_DEALT = 1;
	private final int PLAYER_BUST = 2;
	private final int PLAYER_RESULT = 3;
	private final int HOUSE_DEALT = 4;
	private final int HOUSE_BUST = 5;
	//participant type to differentiate between players and house
	private final int PLAYER = 1;
	private final int HOUSE = 2;
	
	
	public GameEngineImpl(){		
		//create a deck
		loadPlayingDeck();
		listOfPlayers = new ArrayList<Player>();
	}
	
		

	@Override
	public void dealPlayer(Player player, int delay) {		
		int playerResult;
		
		Player currentPlayer = getPlayer(player.getPlayerId());
		assert(player.getBet() > 0) : "Bet amount should be greater than 0";
		assert(currentPlayer != null): "Player is not valid";
		assert(player.getPoints() >= 0) : "Player cannot play due to lack of points";
		
		setChanged();
		notifyObservers(player);//Notifies Observer the current player
		
		player.setResult(RESET);
		playerResult = player.getResult();
		assert(playerResult == 0):"Player result failed to reset";
		playerResult = dealParticipant(PLAYER, delay);
		assert(playerResult <= 21 && playerResult > 11): "Player result should be between 11 and 22";	
		player.setResult(playerResult);
		
		setChanged(); //Observer behavior		
		notifyObservers(new Integer(PLAYER_RESULT)); //Notifies Observer after player turn is completed		
	}

	
	@Override
	public void dealHouse(int delay) {
		//reset house result before dealing house
		resetHouseResult();
		assert(houseResult == 0 ): "House result failed to reset";
		houseResult = dealParticipant(HOUSE, delay);
		assert(houseResult <= 21 && houseResult > 11): "House result should be between 11 and 22";
		
		setChanged(); //Observer behavior
		notifyObservers(new Integer(houseResult)); //Notifies Observer with house result
	}

	
	/**
	 * Deal both player and house internally, and notifies observers based on type of person
	 * @author pratap
	 * @param personType
	 * @param delay
	 * @return
	 */
	private int dealParticipant(int personType,int delay){
		int participantResult = 0;
		int tempLevel = 0;
		PlayingCard currentPlayingCard;
		
		//Play until bust
		while(participantResult < GameEngine.BUST_LEVEL){
			try {Thread.sleep(delay); } catch (InterruptedException e) {e.printStackTrace();}
			//Check if the deck is not empty, and reload deck if empty
			checkPlayingDeck();
			//Get the playing card from deck
			currentPlayingCard = playingDeck.getFirst();
			//Assertion to check if playing card is valid
			assert(currentPlayingCard != null): "Playing card failed to assign";
			//removing from the deck
			playingDeck.removeFirst();
			
			setChanged();
			notifyObservers(currentPlayingCard); //Notifies Observers about playing card
			
			//assign the score to temporary variable
			tempLevel = currentPlayingCard.getScore();
			//check if it is busted
			if(!checkBustLevel(participantResult+tempLevel)){
				participantResult += tempLevel; //add points if its not busted
				
				setChanged(); //Observer behavior
				if(personType == PLAYER)
					notifyObservers(new Integer(PLAYER_DEALT)); //Notifies observer for each dealt of player
				
				else if(personType == HOUSE)
					notifyObservers(new Integer(HOUSE_DEALT));	//Notifies observer for each dealt of house
				
			}
			//If the score is busted
			else{
				
				setChanged(); //Observer behavior
				if(personType == PLAYER)
					notifyObservers(new Integer(PLAYER_BUST)); //Notifies observer if player is busted
				
				else if(personType == HOUSE)
					notifyObservers(new Integer(HOUSE_BUST)); //Notifies observer if house is busted
				
				break;
			}			
		}		
		return participantResult; //returns the final score of participant
	}
	
	
	
	@Override
	public void addPlayer(Player player)
	{	//Get the size before adding player
		int currentPlayerList = listOfPlayers.size();
		listOfPlayers.add(player);
		//comparing the size after adding player
		assert(listOfPlayers.size() == currentPlayerList+1): "Fail to add Player";		
	}

	
	@Override
	public Player getPlayer(String id) {
		for(Player player : listOfPlayers)
			if(player.getPlayerId().equals(id))
				return player;
			
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {		
			return listOfPlayers.remove(player);			
		}
	

	@Override
	public void calculateResult() {
		
		//check if the house result is valid before calculating winner
		assert(houseResult <= 21 && houseResult > 11): "House result should be between 11 and 22";
		
		//Looping through all the players
		for(Player player : listOfPlayers){
			if(player.getBet() > 0){ //Check to consider correct player for calculating
				//Get the point result 
				int playerResult = player.getResult();
				//Get the total points of the player, excluding bet point
				int playerPoints = player.getPoints();
				
				//check if the player result is valid before calculating winner
				assert(playerResult <= 21 && playerResult > 11): "Player result should be between 11 and 22";	
				
				//Player Wins scenario
				if(playerResult > houseResult){
					//Add the double amount of bet points to the player
					player.setPoints(player.getPoints()+(2*player.getBet()));	
					assert(player.getPoints() > playerPoints): "Points has not been added to the player";				
				}
				
				//Player Loses scenario
				else if(playerResult < houseResult){
					//check if player total points is not changed after losing bet
					assert(player.getPoints() == playerPoints): "Points has not been updated correctly to the player";				
				}
				
				//Draw scenario
				else if(playerResult == houseResult){
					//Give the bet points back to player
					player.setPoints(player.getPoints()+player.getBet());
					assert(player.getPoints() > playerPoints): "Points has not been added to the player";
					
				}
			}
			//reset the bet point to player.
			player.resetBet();			
		}		
	}

	
	
	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		addObserver((Observer) gameEngineCallback);		
	}

	
	@Override
	public void removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		deleteObserver((Observer) gameEngineCallback);		
	}
	

	@Override
	public Collection<Player> getAllPlayers() {	return listOfPlayers; }
	
	
	@Override
	public boolean placeBet(Player player, int bet) {	return player.placeBet(bet); }

	
	@SuppressWarnings("unchecked")
	@Override
	public Deque<PlayingCard> getShuffledDeck() {		
		Collections.shuffle((List<PlayingCard>)playingDeck);
		return playingDeck;
	}
	
	
	
	/**
	 * Check the playingDeck and Reload the deck if the deck is empty.
	 * @author pratap
	 */
	private void checkPlayingDeck(){
		if(playingDeck.size()==0)
			loadPlayingDeck();			
	}
	
	
	
	/**
	 * Reset the house result for each time before starting game
	 * @author pratap
	 */
	private void resetHouseResult(){
		houseResult = 0;
	}
	

	
	/**
	 * Check if the current point does not bust, for both players and house
	 * @author pratap
	 * @param point
	 * @return
	 */
	private boolean checkBustLevel(int point){
		
		if(point > GameEngine.BUST_LEVEL)
			return true;
		
		return false;		
	}
	
	
	
	/**
	 * Load the deck when game is initialized and deck becomes empty.
	 * @author pratap
	 */
	private void loadPlayingDeck(){
		playingDeck = PlayingCardImpl.createPlayingDeck();	
		playingDeck = getShuffledDeck();
	}
	
}
