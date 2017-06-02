package pusoy;

import java.lang.reflect.Array;
import java.util.*;
import pusoy.Card;

public class Hand 
{
	Scanner scan = new Scanner(System.in);
	
	ArrayList<Card> hand;
	boolean roundDone;
	boolean gameDone;
	boolean start;
	
	public Hand()
	{
		hand = new ArrayList<Card>(13);
		roundDone = false;
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
		{
			gameDone = false;
			return gameDone;
		}
		else
		{
			gameDone = true;
			return gameDone;
		}
	}
	
	public void print()
	{
		System.out.println("\n");
		for(int i = 0; i < hand.size(); i++)
		{
			System.out.printf("%-3d", (i + 1));
			hand.get(i).printCard();
			System.out.println();
		}
	}
	
	public ArrayList<Card> getHand()
	{
		ArrayList<Integer> playingHandInt = new ArrayList<Integer>();
		ArrayList<Card> playingHand = new ArrayList<Card>();
		int choice = 0;
		System.out.println("1. Select cards to play\n" + 
						   "2. Pass");
		choice = scan.nextInt();
		if(choice == 1)
		{
			int count = 0;
			System.out.print("Which card(s) do you want to play(-1 for no other card): ");
			for(int i = 0; i < 5; i++)
			{
				int num = scan.nextInt();
				playingHandInt.add(num);
	    		count++;
		    }
		    for(int i = 0; i < playingHandInt.size(); i++)
		    {
		    	if(playingHandInt.get(i)!= -1)
		    	{
		    		playingHand.add(hand.get(playingHandInt.get(i) - 1));
		    		hand.remove(playingHandInt.get(i));
		    	}
		    }
		}
		if(choice == 2)
		{
			roundDone = true;
		}roundDone = true;
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
