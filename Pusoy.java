package pusoy;

import java.util.*;

import cardGame.HigherCard1.Rotation;
import pusoy.Card;
import pusoy.Hand;

public class Pusoy 
{
	public final static int ROYAL_FLUSH = 7;
	public final static int STRAIGHT_FLUSH = 6;
	public final static int FOUR_KIND = 5;
	public final static int FULL_HOUSE = 4;
	public final static int FLUSH = 3;
	public final static int STRAIGHT = 2;
	public final static int PAIR = 1;
	public final static int SINGLE = 0;
	
	boolean roundPass1 = false, roundPass2 = false, roundPass3 = false, roundPass4 = false;
	
	
	Scanner scan = new Scanner(System.in);
	static int winner;
    static boolean done1 = false, done2 = false, done3 = false, done4 = false;
	private static Hand hand1 = new Hand();
	private static Hand hand2 = new Hand();
	private static Hand hand3 = new Hand();
	private static Hand hand4 = new Hand();
	private static ArrayList<Hand> hands = new ArrayList<Hand>();
	private static ArrayList<Card> playingHand1;
	private static ArrayList<Card> playingHand2;
	private static ArrayList<Card> playingHand3;
	private static ArrayList<Card> playingHand4;
	private static ArrayList<Card>[] playingHands = (ArrayList<Card>[]) new ArrayList[4];
	
	
	public static int remainingInRound()
	{
		int numLeft = 0;
		if(!hand1.roundDone)
			numLeft++;
		if(!hand2.roundDone)
			numLeft++;
		if(!hand3.roundDone)
			numLeft++;
		if(!hand4.roundDone)
			numLeft++;
		
		return numLeft;
	}
	
	public static int remainingInGame(boolean w, boolean x, boolean y, boolean z)
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
	
	public static void getInput(int start, int remaining)
	{
		int numberOfInputs = 0;
		int rankMin = 0;
		boolean first = true;
		if(first == true && hands.get(start - 1).roundDone == false)
		{
			hands.get(start - 1).sortByValue();
			hands.get(start - 1).print();
			System.out.println("Player " + start + ", select card to play");
			playingHands[start - 1] = hands.get(start - 1).getHand();
			numberOfInputs++;
			rankMin = getRank(playingHands[start-1]);
			start++;
			first = false;
		}
		while(start < 5 && hands.get(start - 1).roundDone == false)
		{
			hands.get(start - 1).sortByValue();
			hands.get(start - 1).print();
			System.out.println("Player " + start + ", select card to play");
			playingHands[start - 1] = hands.get(start - 1).getHand();
			if(getRank(playingHands[start - 1]) < rankMin)
			{
				System.out.println("Hand does not beat the last one played, re-pick or pass");
				playingHands[start - 1] = hands.get(start - 1).getHand();
			}
			numberOfInputs++;
			start++;
			
		}
		if(start == 5 && numberOfInputs < remaining)
		{
			start = 1;
		}
		while(numberOfInputs < remaining && hands.get(start - 1).roundDone == false)
		{
			hands.get(start - 1).sortByValue();
			hands.get(start - 1).print();
			System.out.println("Player " + start + ", select card to play");
			playingHands[start - 1] = hands.get(start - 1).getHand();
			numberOfInputs++;
			start++;
		}
		
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
		
	    hands.add(hand1);
	    hands.add(hand2);
	    hands.add(hand3);
	    hands.add(hand4);
	    Card[] playedHand1 = new Card[5];
	    Card[] playedHand2 = new Card[5];
	    Card[] playedHand3 = new Card[5];
	    Card[] playedHand4 = new Card[5];
	    playingHands[0] = playingHand1;
	    playingHands[1] = playingHand2;
	    playingHands[2] = playingHand3;
	    playingHands[3] = playingHand4;
	    
	    
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
		while(remainingInRound() > 1)
		{
			int roundHandSize = 0;
			getInput(winner, remainingInRound());
			

			
		}
		
		return 1;
	}
	
	private static int getRank(ArrayList<Card> playingHand) 
	{
		playingHand.sort(Card.CardComparator);

		int pairIndex = -1;
		int rank = 0;  //assume its a BUST
		
		//check for pair
		if(playingHand.size() == 2)
		{
			for(int i = 0; i < 4; i++)
			    if(playingHand.get(i).getValue() == playingHand.get(i + 1).getValue()) 
			    {
					pairIndex = i;
					rank = 1;
					i = 4;
			    }
		}

		if(playingHand.size() == 5)
		{
			//check for 3 of a kind or full house
			for(int i = 0; i < 3; i++)
			    if(playingHand.get(i).getValue() == playingHand.get(i + 1).getValue() && playingHand.get(i + 1).getValue() == playingHand.get(i + 2).getValue()) 
			    {
			    	if(i==0 && playingHand.get(3).getValue() == playingHand.get(4).getValue() || i==2 && playingHand.get(0).getValue() == playingHand.get(1).getValue())
			    		rank = 4;
			    }
			
			//check for 4 of a kind
			for(int i = 0; i < 2; i++)
			    if(playingHand.get(i).getValue() == playingHand.get(i + 1).getValue() && playingHand.get(i + 1).getValue() == playingHand.get(i + 2).getValue() &&
			    		playingHand.get(i + 2).getValue() == playingHand.get(i + 3).getValue()) {
				rank = 5;
			    }
	
			//check for straight
			if((playingHand.get(4).getValue() - playingHand.get(0).getValue() == 4) ||
			       (playingHand.get(3).getValue() - playingHand.get(0).getValue() == 3 && playingHand.get(4).getValue() == 15 && playingHand.get(0).getValue() == 3)) {
				rank = 4;
			    }
	
			//check for flush (if we haven't already found any pairs)
			boolean flush;
			if(rank == 0 || rank == 4) {
			    flush = true;
			    for(int i = 0; i < 4; i++)
				if(playingHand.get(i).getSuit() != playingHand.get(i).getSuit())
				    flush = false;
			    if(flush && rank == 4)
			    	rank = 6; //straight flush!
			    else if(flush)
			    	rank = 3;
			}
			    
			//check for royal flush (if it's a straight flush)
			if(rank == 6 && playingHand.get(4).getValue() == 14 && playingHand.get(0).getValue() == 10)
			    rank = 7; //royal flush!
		}
		
		return rank;
	    }//end evalRank
	
}
