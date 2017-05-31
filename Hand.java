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
	
	public Hand()
	{
		hand = new ArrayList<Card>(13);
		done = false;
	}
	
	public Hand(ArrayList<Card> x)
	{
		hand = x;
	}
	
	public void addCard(Card c)
	{
		hand.add(c);
	}
	
	public boolean isDone(ArrayList<Card> hand)
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
	
	public int[] getHand()
	{
		int[] playingHand = new int[5];
		int choice = 0;
		int[] cards = new int[5];
		System.out.println("1. Show hand sorted by value" +
						   "2. Show hand sorted by suit" + 
						   "3. Select cards to play" + 
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
			System.out.println("Enter the card(s) that you want to select: ");
			String x = scan.nextLine();
		    String[] num = x.split(" ");
		    
		    for(int i = 0; i < num.length; i++)
		    {
		    	playingHand[i] = Integer.parseInt(num[i]);
		    }
		    
		}
		if(choice == 4)
		{
			done = true;;
		}
		return playingHand;
	}
	
	
}
