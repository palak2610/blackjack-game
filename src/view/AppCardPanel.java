package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AppCardPanel extends JPanel{
	private JLabel playerCardDetailsLabel,houseCardDetailsLabel,playerDetailsLabel;
		 
	public AppCardPanel(){
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		//Labels for displaying cards and player details
		playerCardDetailsLabel = new JLabel("<html>");
		houseCardDetailsLabel = new JLabel("<html>");
		playerDetailsLabel = new JLabel();
		
		add(playerDetailsLabel);
		add(playerCardDetailsLabel);
		add(houseCardDetailsLabel);
	}
	
	/**
	 * Display each dealt of player
	 * @author pratap
	 * @param status
	 */
	public void updatePlayerCardDetails(String status){
		String listOfCards = playerCardDetailsLabel.getText();
		listOfCards +="<br>"+status;
		playerCardDetailsLabel.setText(listOfCards);
	}
	
	/**
	 * Display each dealt of house
	 * @author pratap
	 * @param status
	 */
	public void updateHouseCardDetails(String status){
		String listOfCards = houseCardDetailsLabel.getText();
		listOfCards += "<br>"+status;
		houseCardDetailsLabel.setText(listOfCards);		
	}
	
	
	/**
	 * Displays player details with playerID, player name ,and point details
	 * 
	 * @author pratap
	 * @param playerID
	 * @param playerName
	 * @param availPoints
	 * @param betAmount
	 */	
	public void updatePlayerDetails(String playerID, String playerName, int availPoints,int betAmount){
		String playerDetails = "<html>Player Name: "+playerName+" ID: "+playerID+" <br>Available Points: "+availPoints+"<br>Bet Points: "+betAmount;
		playerDetailsLabel.setText(playerDetails);
	}
	
	
	/**
	 * Clear the card view for the next round of game
	 * @author pratap
	 */
	public void clearAppCardPanel(){
		playerDetailsLabel.setText("<html>");
		playerCardDetailsLabel.setText("<html>");
		houseCardDetailsLabel.setText("<html>");	
	}

	
	
}
