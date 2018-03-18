package model.implementation;

import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String playerId;
	private String playerName;
	private int totalPoints;
	private int bet;
	private int playerResult;
	
	
	public SimplePlayer(String id,String playerName,int points){
		//set of assertions to check the empty details before creating player object
		assert(id!= null):"PlayerID should not be empty";
		assert(playerName!= null):"Player Name should not be empty";
		assert(points > 0):"Player points should be greater than Zero";
		
		//sets the id,name, and points of player.
		setPlayerId(id);
		setPlayerName(playerName);
		setPoints(points);		
	}

	
	@Override
	public String getPlayerName() {	return playerName;	}

	@Override
	public void setPlayerName(String playerName) { this.playerName = playerName; }

	@Override
	public int getPoints() { return totalPoints; }

	@Override
	public void setPoints(int points) {	this.totalPoints = points;}

	/**
	 * Setter of playerID 
	 * @author pratap
	 * @param playerId
	 */
	private void setPlayerId(String playerId) {	this.playerId = playerId;	}
	

	@Override
	public String getPlayerId() { return playerId;}


	@Override
	public boolean placeBet(int bet) {
		
		assert(bet > 0): "Bet amount should be greater than 0";
		assert(bet <= totalPoints) : "Bet amount should not greater than total available points";
		
		//Assigns bet only if bet is not greater than total points
		if(totalPoints >= bet){
			this.bet = bet;
			this.totalPoints = this.totalPoints-this.bet; //removing bet amount from the point
			return true;
		}else{
			return false;
			}
	}


	@Override
	public int getBet() { return bet;}

	
	@Override
	public void resetBet() { bet = 0;}

	
	@Override
	public int getResult() { return playerResult;	}

	
	@Override
	public void setResult(int result) { playerResult = result; }
	
	
	
	/**
	 * Overrides the string representation of object and displays the player details
	 */
	@Override
	public String toString(){return "Name: "+playerName+", ID: "+playerId+", points: "+totalPoints; 	}
}
