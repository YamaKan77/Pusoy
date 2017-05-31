package pusoy;

import java.lang.reflect.Array;
import java.util.*;
import pusoy.Card;

public class Hand 
{
	Scanner scan = new Scanner(System.in);
	public final static int ROYAL_FLUSH = 7;
	public final static int STRAIGHT_FLUSH = 6;
	public final static int FOUR_KIND = 5;
	public final static int FULL_HOUSE = 4;
	public final static int FLUSH = 3;
	public final static int STRAIGHT = 2;
	public final static int PAIR = 1;
	public final static int SINGLE = 0;
	
	
	ArrayList<Card> hand;
	boolean done;
	boolean start;
	
	public Hand()
	{
		hand = new ArrayList<Card>(13);
		done = false;
		start = false;
	}
	
	public Hand(ArrayList<Card> x)
	{
		hand = x;
	}
	
	public void addCard(Card c)
	{
		hand.add(c);
	}
	
	public void sortByValue()
	{
		hand.sort(Card.CardComparator);
	}
	
	public void sortBySuit()
	{
		hand.sort(Card.SuitComparator);
	}
	
	
	public boolean isDone()
	{
		if(hand.size() > 0)
			return false;
		else
			return true;
	}
	
	public void print()
	{
		System.out.println("\n");
		for(int i = 1; i <= hand.size(); i++)
		{
			System.out.println(i + ". " + hand.get(i-1).getValueAsString()+ " " + hand.get(i-1).getSuitAsString());
		}
	}
	
	public Card[] getHand()
	{
		int[] playingHandInt = new int[5];
		Card[] playingHand = new Card[5];
		int choice = 0;
		System.out.println("\n1. Show hand sorted by value\n" +
						   "2. Show hand sorted by suit\n" + 
						   "3. Select cards to play\n" + 
						   "4. Pass");
		choice = scan.nextInt();
		if(choice == 1)
		{
			hand.sort(Card.CardComparator);
			print();
		}
		if(choice == 2)
		{
			hand.sort(Card.SuitComparator);
			print();
		}
		if(choice == 3)
		{
			int count = 0;
			System.out.println("Enter the card(s) that you want to select: ");
		    while(scan.hasNextInt())
		    {
		    	playingHandInt[count] = scan.nextInt();
		    	count++;
		    }
		    for(int i = 0; i < playingHandInt.length; i++)
		    {
		    	playingHand[i] = hand.get(playingHandInt[i] - 1);
		    }
		}
		if(choice == 4)
		{
			done = true;
		}
		return playingHand;
	}
	
	public boolean starts()
	{
		for(Card c : hand)
		{
			if(c.getSuit() == 0 && c.getValue() == 3)
			{
				start = true;
			}
		}
		return start;
	}
	
}
