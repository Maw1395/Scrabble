package Scrabble;

import java.util.Random;

public class Letters {
	protected int index = -1;
	public Letters()
	{
		setDistribution();
	}
	private static boolean [] Distribution = new boolean [100];
	private static void setDistribution()
	{
		for(int i =0; i< 99; i ++)
			Distribution[i] = true;
	}
	public String getRandomLetter()
	{
		int [] LetterArray = new int [100];
		int k = 0;
		for(int i = 0; i < 99; i++)
			if(Distribution[i])
				LetterArray[k++] = i;
		if (k ==0)
				return "";
		Random random = new Random();
		int chosenOne = random.nextInt(k);
		chosenOne = LetterArray[chosenOne];
		Distribution[chosenOne] = false;
		index = chosenOne;
		return getLetter(chosenOne);
		
	}
	public int getDistributionSize()
	{
		int size = 0;
		for(int i = 0; i < 100; i++)
			if(Distribution[i])
				size++;
		return size;
	}
	public String getLetter(int i)
	{
		/*
		 0-11 = E
		 12-20 = A
		 21-29 = I
		 30-37 = 0
		 38-43 = N
		 44-49 = R
		 50-55 = T
		 56-59 = L
		 60-63 = S
		 64-67 = U
		 68-71 = D
		 72-74 = G
		 75-76 = B
		 77-78 = C
		 79-81 = M
		 82-83 = P
		 84-85 = F
		 86-87 = H
		 88-89 = V
		 90-91 = W
		 92-93 = Y
		 94 = K
		 95 = J
		 96 = X
		 97 = Q
		 98 = Z
		 
		 */
		if(i < 12)
			return "E";
		else if(i < 21)
			return "A";
		else if(i < 30)
			return "I";
		else if(i < 38)
			return "O";
		else if(i < 44)
			return "N";
		else if(i < 50)
			return "R";
		else if(i < 56)
			return "T";
		else if(i < 60)
			return "L";
		else if(i < 64)
			return "S";
		else if(i < 68)
			return "U";
		else if(i < 72)
			return "D";
		else if(i < 75)
			return "G";
		else if(i < 77)
			return "B";
		else if(i < 79)
			return "C";
		else if(i < 82)
			return "M";
		else if(i < 84)
			return "P";
		else if(i < 86)
			return "F";
		else if(i < 88)
			return "H";
		else if(i < 90)
			return "V";
		else if(i < 92)
			return "W";
		else if(i < 94)
			return "Y";
		else if(i < 95)
			return "K";
		else if(i < 96)
			return "J";
		else if(i < 97)
			return "X";
		else if(i < 98)
			return "Q";
		else  if (i ==98)
			return "Z";
		else
			return "";
	}
	public void revalidate(String s)
	{
		if(s == "E")
			for(int i =0; i<12; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "A")
			for(int i =12; i<21; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "I")
			for(int i =21; i<30; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "O")
			for(int i =30; i<38; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "N")
			for(int i =38; i<44; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "R")
			for(int i =44; i<50; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "T")
			for(int i =50; i<56; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "L")
			for(int i =56; i<60; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "S")
			for(int i =60; i<64; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "U")
			for(int i =64; i<68; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "D")
			for(int i =68; i<72; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "G")
			for(int i =72; i<75; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "B")
			for(int i =75; i<77; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "C")
			for(int i =77; i<79; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "M")
			for(int i =79; i<82; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "P")
			for(int i =82; i<84; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "F")
			for(int i =84; i<86; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "H")
			for(int i =86; i<88; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "V")
			for(int i =88; i<90; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "W")
			for(int i =90; i<92; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "Y")
			for(int i =92; i<94; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "K")
			for(int i =94; i<95; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "J")
			for(int i =95; i<96; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "X")
			for(int i =96; i<97; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "Q")
			for(int i =97; i<98; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
		else if(s == "Z")
			for(int i =98; i<99; i++)
			{
				if (!Distribution[i])
				{
					Distribution[i]=true;
					break;
				}
				
			}
	}
	public int getValue(String s)
	{
		if(s == "L" || s=="U" ||s == "S" || s=="N" || s == "R" 
				|| s=="T" ||s == "O" || s=="A" || s == "I" || s=="E")
			return 1;
		else if(s == "D" || s=="G")
			return 2;
		else if (s== "B" || s == "C" || s =="M" || s=="P")
			return 3;
		else if (s== "F" || s =="H" || s=="V" || s=="W")
			return 4;
		else if (s== "K")
			return 5;
		else if (s=="J" || s =="X")
			return 8;
		else
			return 10;
	}
}
