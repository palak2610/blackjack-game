package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AppDialog extends JPanel {
	
	private JTextField nameTextBox,pointsTextBox;
	private JLabel nameTextLabel,pointsTextLabel;
	private String playerName;
	private int playerPoints;
	
	
	
	public AppDialog(){
		setLayout(new BorderLayout(5,5));
		//Creating text label for player
		nameTextLabel = new JLabel("Name");
		pointsTextLabel = new JLabel("Points");
		
		//Separate panel for player labels
		JPanel textLabels = new JPanel(new GridLayout(0,1,2,2));
		textLabels.add(nameTextLabel);
		textLabels.add(pointsTextLabel);
		add(textLabels,BorderLayout.WEST);
		
		//creating textboxes for player
		nameTextBox = new JTextField();
		pointsTextBox = new JTextField();
		
		//Separate panel for textboxes
		JPanel textBoxes = new JPanel(new GridLayout(0,1,2,2));
		textBoxes.add(nameTextBox);
		textBoxes.add(pointsTextBox);
		add(textBoxes,BorderLayout.CENTER);
	}
	
	
	
	/**
	 * Getter for player name
	 * @author pratap
	 * @return
	 */
	public String getPlayerName() {	return playerName; }
	
	/**
	 * private setter for player name
	 * @author pratap
	 */
	private void setPlayerName(String playerName) {	this.playerName = playerName; }

	
	/**
	 * Getter for player points
	 * @author pratap
	 * @return
	 */
	public int getPlayerPoints() {	return playerPoints; }
	
	
	/**
	 * Test if the user provided player name is valid
	 * @author pratap
	 * @return
	 */
	public boolean checkPlayerName(){
		String name = nameTextBox.getText();
		if(name != null){
			setPlayerName(name);
			return true;
		}	
		return false;
	}
	
	
	/**
	 * Test if the user provided player point is valid
	 * @author pratap
	 * @return
	 */
	public boolean checkPlayerPoints(){
		String stringPoints = pointsTextBox.getText();
		int playerPoints;
		if(stringPoints != null){
			try{
				playerPoints = Integer.parseInt(stringPoints);
				if(playerPoints > 0){
					this.playerPoints = playerPoints;
					return true;
				}
			}
			catch(Exception e) { return false; }
		}
		return false;
	}
	
	
	
	
	

}
