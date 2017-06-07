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
	private static ArrayList<Card> playingHand1 = new ArrayList<Card>();
	private static ArrayList<Card> playingHand2 = new ArrayList<Card>();;
	private static ArrayList<Card> playingHand3 = new ArrayList<Card>();;
	private static ArrayList<Card> playingHand4 = new ArrayList<Card>();;
	private static ArrayList<Card>[] playingHands = (ArrayList<Card>[]) new ArrayList[4];
	private static ArrayList<Card> currentHand;
	
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
	
	public static int remainingInGame()
	{
		int numLeft = 0;
		if(!hand1.gameDone)
			numLeft++;
		if(!hand2.gameDone)
			numLeft++;
		if(!hand3.gameDone)
			numLeft++;
		if(!hand4.gameDone)
			numLeft++;
		
		return numLeft;
	}
	
	public static int startingPlayer(Hand w, Hand x, Hand y, Hand z)
	{
		if(w.starts())
			return 0;
		if(x.starts())
			return 1;
		if(y.starts())
			return 2;
		else
			return 3;
	}
	
	
	public static int getInput(int start, int remaining, boolean first)
	{
		System.out.println("First: " + first);
		//think about changing start index from 0 instead of 1
		int numberOfInputs = 0;
		int rankMin = -1;
		if(start != 4 && hands.get(start).roundDone == true)
		{
			start++;
		}
		while(start < 4 && hands.get(start).roundDone == false)
		{
			System.out.println("First while loop");
			if(first == false)
			{
				System.out.println("Hand to beat: ");
				for(int i = 0; i < currentHand.size(); i++)
				{
					System.out.println(currentHand.get(i));
				}
			}

			hands.get(start).sortByValue();
			hands.get(start).print();
			System.out.println("Player " + (start + 1) + ", select card to play");
			playingHands[start] = hands.get(start).getHand();
			while(getRank(playingHands[start]) <= rankMin && hands.get(start).roundDone == false)
			{

				//checks if the hand played is a single, can only play single against single
				if(rankMin == 0 && getRank(playingHands[start]) != 0 && hands.get(start).roundDone == false)
				{
					System.out.println("Current round is singles, enter a card or pass");
					playingHands[start] = hands.get(start).getHand();
					break;

				}
				//Checks if the and is a pair, other users can only enter another pair
				if(rankMin == 1 && getRank(playingHands[start]) > 1 && hands.get(start).roundDone == false)
				{
					System.out.println("Current round is pairs, enter a pair or pass");
					playingHands[start] = hands.get(start).getHand();
					break;
				}
				//checks if the highest card of the hand is higher than the highest card of last hand played
				if(rankMin > 1 && playingHands[start].get(0).getValue() == currentHand.get(0).getValue())
				{
					if(playingHands[start].get(0).getSuit() < currentHand.get(0).getSuit())
					{
						System.out.println("Hand does not beat the last one played, re-pick or pass");
						playingHands[start] = hands.get(start).getHand();
					}
				}
				else if(rankMin > 1 && playingHands[start - 1].get(0).getValue() < currentHand.get(0).getValue())
				{
					System.out.println("Hand does not beat the last one played, re-pick or pass");
					playingHands[start] = hands.get(start).getHand();
				}
				else
					hands.get(start).roundDone = true;
			}
			if(hands.get(start).roundDone == false)
			{
				currentHand = playingHands[start];
			}
			
			if(first == true)
			{
				rankMin = getRank(playingHands[start]);
				first = false;
			}
			if(hands.get(start).roundDone == true)
			{
				remaining--;
			}
			numberOfInputs++;
			start++;
			
		}
		if(start == 4 && numberOfInputs <= remaining)
		{
			start = 0;
		}
		
		while(numberOfInputs <= remaining && hands.get(start).roundDone == false)
		{
			System.out.println("Second while loop");
			if(first == false)
			{
				System.out.println("Hand to beat: ");
				for(int i = 0; i < currentHand.size(); i++)
				{
					System.out.println(currentHand.get(i));
				}
			}
			hands.get(start).sortByValue();
			hands.get(start).print();
			System.out.println("Player " + (start + 1) + ", select card to play");
			playingHands[start] = hands.get(start).getHand();
			while(getRank(playingHands[start]) <= rankMin && hands.get(start).roundDone == false)
			{
				//checks if the hand played is a single, can only play single against single
				if(rankMin == 0 && getRank(playingHands[start]) != 0 && hands.get(start).roundDone == false)
				{
					System.out.println("Current round is singles, enter a card or pass");
					playingHands[start] = hands.get(start).getHand();
					break;

				}
				//Checks if the and is a pair, other users can only enter another pair
				if(rankMin == 1 && getRank(playingHands[start]) > 1 && hands.get(start).roundDone == false)
				{
					System.out.println("Current round is pairs, enter a pair or pass");
					playingHands[start] = hands.get(start).getHand();
					break;
				}
				//checks if the highest card of the hand is higher than the highest card of last hand played
				if(rankMin > 1 && playingHands[start].get(0).getValue() == currentHand.get(0).getValue())
				{
					if(playingHands[start].get(0).getSuit() < currentHand.get(0).getSuit())
					{
						System.out.println("Hand does not beat the last one played, re-pick or pass");
						playingHands[start] = hands.get(start).getHand();
					}
				}
				else if(rankMin > 1 && playingHands[start - 1].get(0).getValue() < currentHand.get(0).getValue())
				{
					System.out.println("Hand does not beat the last one played, re-pick or pass");
					playingHands[start] = hands.get(start).getHand();
				}
				else
					hands.get(start).roundDone = true;
			}
			
			if(hands.get(start).roundDone == false)
			{
				currentHand = playingHands[start];
			}
			
			if(hands.get(start).roundDone == true)
			{
				remaining--;
			}
			
			if(first == true)
			{
				rankMin = getRank(playingHands[start]);
				first = false;
			}
			numberOfInputs++;
			start++;
		}

		
		System.out.println("End of inputs, winner is: " + start);
		return start;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Starting a game of pusoy");
		boolean first = true;
		startGame();
		System.out.print(playingHand1.size());
		while(remainingInGame() > 1)
		{
			playRound(first);
			System.out.println("New round");
			for(int i = 0; i < 4; i++)
			{
				hands.get(i).roundDone = false;
			}
		}	
	}
	
	private static int playRound(boolean first)
	{

		do
		{	
			winner = getInput(winner, remainingInRound(), first);
		} while(remainingInRound() > 1);
		System.out.println("Round done");
		return winner;
	}
	
	
	private static void startGame()
	{
		Random random  = new Random();
		Deck deck = new Deck();
		
	    hands.add(hand1);
	    hands.add(hand2);
	    hands.add(hand3);
	    hands.add(hand4);
	    
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


	private static int getRank(ArrayList<Card> playingHand) 
	{
		if(playingHand != null)
			playingHand.sort(Card.CardComparator);

		int rank = 0;  //assume its a BUST
		
		//check for pair
		System.out.println( );
		if(playingHand.size() == 2)
		{
			for(int i = 0; i < playingHand.size() - 1; i++)
			    if(playingHand.get(i).getValue() == playingHand.get(i + 1).getValue()) 
			    {
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
