package Scrabble;

import java.awt.Color;

public class box {
	
	public class Letter{
		private char lexico;
		private int value;
		
		public Letter(char lex, int vle)
		{
			lexico = lex;
			value = vle;
		}
		public boolean isBlank()
		{
			if(lexico == ' ');
			return true;
		}
		public int getValue()
		{
			return value;
		}
		public String toString()
		{
			return "" + lexico;
		}
	}
	protected Letter letter = null;
	protected int posx;
	protected int posy;
	
	public box()
	{
		
	}
	public box(int x, int y)
	{
		posx = x;
		posy = y;
	}
	
	public box(Letter l)
	{
		letter = l;
	}
	
	
	//for the colors of the board
	public Color getcolor(int i, int j)
	{
		if( ((i == 0 || i == 7 || i== 14 )&& (j == 0 || j== 14))|| ((j == 7) && (i == 0 || i == 14)))
		 return Color.RED;
		else if ( ((i == 3 || i ==11)&&(j==1 || j ==7 || j== 14)) || ((i == 0 || i ==7 || i ==14) && (j == 3 || j == 11)) || ((i == 2 || i == 6 || i ==8 || i ==12) 
				&& (j == 6 || j == 8)) || (i ==6 || i ==8) &&(j ==2 || j == 12))
			return Color.CYAN;
		int jj = 1;
		for( int ii = 1; ii< 5; ii++)
		{
			
				if((i == ii && j ==jj)|| (i==14-ii && j ==jj) || (i == ii && j== 14-jj) ||(i == 14-ii && j == 14-jj))
					return Color.PINK;
				jj++;
		}
		if( ( (i==1 || i ==13 || i == 5 || i == 9) && (j == 5 || j == 9) ) || ((i == 5 || i ==9) && (j == 1 || j ==13)))
			return Color.BLUE;
		else if(i ==7 && j ==7)
		{
			Color greyYellow = new Color(255,204,102);
			return greyYellow;
		}
		return Color.DARK_GRAY;
	}
	public String getString(int i, int j)
	{
		if( ((i == 0 || i == 7 || i== 14 )&& (j == 0 || j== 14))|| ((j == 7) && (i == 0 || i == 14)))
		 return "TW";
		else if ( ((i == 3 || i ==11)&&(j==1 || j ==7 || j== 14)) || ((i == 0 || i ==7 || i ==14) && (j == 3 || j == 11)) || ((i == 2 || i == 6 || i ==8 || i ==12) 
				&& (j == 6 || j == 8)) || (i ==6 || i ==8) &&(j ==2 || j == 12))
			return "DL";
		int jj = 1;
		for( int ii = 1; ii< 5; ii++)
		{
			
				if((i == ii && j ==jj)|| (i==14-ii && j ==jj) || (i == ii && j== 14-jj) ||(i == 14-ii && j == 14-jj))
					return "DW";
				jj++;
		}
		if( ( (i==1 || i ==13 || i == 5 || i == 9) && (j == 5 || j == 9) ) || ((i == 5 || i ==9) && (j == 1 || j ==13)))
			return "TL";
		
		return "";
	}
	public int getLetterMultiplier(int i, int j)
	{
		if ( ((i == 3 || i ==11)&&(j==1 || j ==7 || j== 14)) || ((i == 0 || i ==7 || i ==14) && (j == 3 || j == 11)) || ((i == 2 || i == 6 || i ==8 || i ==12) 
				&& (j == 6 || j == 8)) || (i ==6 || i ==8) &&(j ==2 || j == 12))
			return 2;
		else if( ( (i==1 || i ==13 || i == 5 || i == 9) && (j == 5 || j == 9) ) || ((i == 5 || i ==9) && (j == 1 || j ==13)))
			return 3;
		return 1;
	}
	public int getWordMultiplier(int i, int j)
	{
		int jj =1;
		for( int ii = 1; ii< 5; ii++)
		{
			
				if((i == ii && j ==jj)|| (i==14-ii && j ==jj) || (i == ii && j== 14-jj) ||(i == 14-ii && j == 14-jj))
					return 2;
				jj++;
		}
		if( ((i == 0 || i == 7 || i== 14 )&& (j == 0 || j== 14))|| ((j == 7) && (i == 0 || i == 14)))
			 return 3;
		else
			return 1;
	}
}
