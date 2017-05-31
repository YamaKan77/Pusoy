package pusoy;

public class Pusoy 
{
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

}
