package pusoy;

import java.util.*;

import cardGame.HigherCard1.Rotation;
import pusoy.Card;
import pusoy.Hand;

public class Pusoy 
{
	Scanner scan = new Scanner(System.in);
	static int winner;
    static boolean done1 = false, done2 = false, done3 = false, done4 = false;
	private static Hand hand1 = new Hand();
	private static Hand hand2 = new Hand();
	private static Hand hand3 = new Hand();
	private static Hand hand4 = new Hand();
	private static Hand[] hands = new Hand[4];
	
	public static int remaining(boolean w, boolean x, boolean y, boolean z)
	{
		int numLeft = 0;
		if(!w)
			numLeft++;
		if(!x)
			numLeft++;
		if(!y)
			numLeft++;
		if(!z)
			numLeft++;
		
		return numLeft;
	}
	
	public static int startingPlayer(Hand w, Hand x, Hand y, Hand z)
	{
		if(w.starts())
			return 1;
		if(x.starts())
			return 2;
		if(y.starts())
			return 3;
		else
			return 4;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Starting a game of pusoy");
		
		startGame();
		
		playRound();
		
	}
	
	private static void startGame()
	{
		Random random  = new Random();
		Deck deck = new Deck();
		
	    hands[0] = hand1;
	    hands[1] = hand2;
	    hands[2] = hand3;
	    hands[3] = hand4;
	    Card[] playedHand1 = new Card[5];
	    Card[] playedHand2 = new Card[5];
	    Card[] playedHand3 = new Card[5];
	    Card[] playedHand4 = new Card[5];
	    
	    int score1 = 0, score2 = 0, score3 = 0, score4 = 0;
	    
	    int x = random.nextInt(10);
	    for(int i = 0; i < x; i++)
	    {
	    	deck.shuffle();
	    }
	    
	    int dealTo = 0;
	    while(deck.cardsLeft() != 0)
	    {
	    	if(dealTo == 0)
	    	{
	    		hand1.addCard(deck.dealCard());;
	    		dealTo++;
	    	}
	    	if(dealTo == 1)
	    	{
	    		hand2.addCard(deck.dealCard());
	    		dealTo++;
	    	}
	    	if(dealTo == 2)
	    	{
	    		hand3.addCard(deck.dealCard());
	    		dealTo++;
	    	}
	    	if(dealTo == 3)
	    	{
	    		hand4.addCard(deck.dealCard());
	    		dealTo = 0;
	    	}
	    }
	    
	    hand1.sortByValue();
	    hand2.sortByValue();
	    hand3.sortByValue();
	    hand4.sortBySuit();
	    
	    done1 = hand1.isDone();
	    done2 = hand2.isDone();
	    done3 = hand3.isDone();
	    done4 = hand4.isDone();
	    
	    winner = startingPlayer(hand1, hand2, hand3, hand4);
	}

	private static int playRound()
	{
		while(remaining(done1, done2, done3, done4) > 1)
		{
			Card[] playingHand1 = new Card[5];
			Card[] playingHand2 = new Card[5];
			Card[] playingHand3 = new Card[5];
			Card[] playingHand4 = new Card[5];
			
			playingHand1 = hand1.getHand();
			
		}
		
		return 1;
	}
}
