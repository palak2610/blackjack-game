package model.implementation;

import java.util.Deque;
import java.util.LinkedList;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {
	
	private Suit suit;
	private Value value;
	private int score;
	

	public PlayingCardImpl(Suit suit, Value value, int score){
		this.suit = suit;
		this.value = value;
		this.score = score;
	}
	
	
	@Override
	public Suit getSuit() { return suit; }

	@Override
	public Value getValue() { return value;	}

	@Override
	public int getScore() { return score; }
	
	
	/**
	 * Overrides the string representation of object and displays the card details
	 * @author pratap
	 */
	public String toString() { return getSuit()+" "+getValue()+" - "+getScore()+" point(s)"; }
	
	
	/**
	 * Creates the deck of 52 cards.
	 * @author pratap	
	 * @return
	 */
	public static Deque<PlayingCard> createPlayingDeck(){
		 Deque<PlayingCard> playingDeck = new LinkedList<PlayingCard>();
		
		//Initiating the deck by looping through suit and value.		
		for(PlayingCard.Suit suit : PlayingCard.Suit.values()){
			for(PlayingCard.Value value: PlayingCard.Value.values()){
				int score = getCardScore(value);
				
				assert(score > 0 && score < 11): "Score of the card is not correct";
				playingDeck.add(new PlayingCardImpl(suit,value, score));
			}
		}
		
		assert(playingDeck.size() == 52): "Deck is not loaded correctly with 52 cards";
		
		return playingDeck;
	}
	
	
	/**
	 * Get the score of the card based on the enum Value of the card.
	 * @author pratap
	 * @param value
	 * @return
	 */
	private static int getCardScore(PlayingCard.Value value){
		switch(value){
		
			case Ace:	 return 1;
			case Two:  	 return 2;
			case Three:  return 3;
			case Four: 	 return 4;
			case Five:   return 5;
			case Six:    return 6;
			case Seven:  return 7;
			case Eight:  return 8;
			case Nine:   return 9;
			case Ten:    return 10;
			case Queen:  return 10;
			case Jack:   return 10;
			case King:   return 10;
			default:     return 0;
		
		}
		
	}	

}
