package Scrabble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JFrame implements ActionListener{

	private JPanel TheBoard = new JPanel(new GridLayout(15,15)); // the board itself
	private JPanel MenuButton = new JPanel(new GridLayout(1,3));	//the menu buttons
	private JPanel LettersInPanel = new JPanel(new GridLayout (1,7));	//the letters below
	private JPanel LetterBackup = new JPanel(new GridLayout(1,7));		//used for player 1 in backup situations
	private JPanel [] PlayerLetterArray = new JPanel [2];			//only used for player 2
	private Letters Letters = new Letters();		//calling the letters within the bag
	private box b = new box();		//calling the tiles and how they should be placed
	private JButton [][] boxes = new JButton[15][15];	//the boxes on the board themselves
	private JButton[] letterButtons = new JButton[7];	//backups
	private JButton[] letterButtons2 = new JButton[7];	//more backups
	private JCheckBox[] letterCheckBoxes = new JCheckBox[7];	//this is used for letter reshuffling
	private int []letterIndexes = new int [7];		//not in use any longer
	private JButton cancelButton = new JButton("Cancel");	//cancel button
	private JButton endButton = new JButton ("End Move");	//end move button
	private JButton reshuffleLetters = new JButton("Redraw Letters");	//redraw button	
	private JButton reshuffleDone = new JButton("Redraw Done");		//end of redraw
	private String LetterToPlace = "";		// letters that will be placed
	private int LetterToRemove = -1;		//placeholder
	private int [] LettersPlaced = new int [7]; 	//letters placed in the board
	private int [][] boxesSetBeforeCancel = {{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};	//where are my boxes used for directional checking ane whatnot
	//need a double array to get both elements
	private int buttonsclicked = 0;		//or number of tiles placed
	private int [] direction = new int [2];	//x and y coordinated
	private boolean isVertical = false;	//is the word going up and down
	private boolean isHorizontal = false;	//left to right
	private boolean firstmove = true;		//has the first move been played
	Players [] player = new Players [2];	//number of players
	int playerCounter = 0;				//shuffles between 1 and 0
	int PlayerletterIndex[][] = new int[2][7];	//no longer in use
	private boolean Adjacent = false;		//is the word adjacent to another
	private boolean centerIsTaken = false;		//is the center taken
	private boolean hValidWord = true;		//are the horizonatal words valid
	private boolean vValidWord = true;		//are the vertical words valid
	
	//MenuOptions Menu = new MenuOptions();
	private void Squares()//setting up the board
	{
		for(int i =0; i<2; i++)
		{
			player[i] = new Players();
			PlayerLetterArray[i] = new JPanel(new GridLayout(1,7));
		}
			
		for(int i = 0; i<15; i++)
		{
			for(int j = 0 ; j<15; j++)
			{
				boxes[i][j] = new JButton(b.getString(i, j));
				boxes[i][j].setForeground(Color.WHITE);
				boxes[i][j].addActionListener(this);
				boxes[i][j].setBackground(b.getcolor(i, j));
				TheBoard.add(boxes[i][j]);
				
			}
		}
	}//setting up the menu buttons
	private void MenuButtons()
	{
		
		
		cancelButton.addActionListener(this);
		endButton.addActionListener(this);
		reshuffleLetters.addActionListener(this);
		
		
		MenuButton.add(cancelButton);
		MenuButton.add(endButton);
		MenuButton.add(reshuffleLetters);
		
	}
	//setting up the letter buttons
	private void Letters()
	{
		for(int i =0 ; i<7; i++)
		{
			if(Letters.getDistributionSize() > 0)
			{
				letterButtons[i] = new JButton(Letters.getRandomLetter());
				letterIndexes[i] = Letters.index;
				letterButtons[i].addActionListener(this);
				LettersInPanel.add(letterButtons[i]);
				LettersPlaced[i] = -1;
			}
		}
		//System.out.println("Original Component Count" + LettersInPanel.getComponentCount());
	}
	//drawing the board
	public Board()
	{
		reshuffleDone.addActionListener(this);
		this.Squares();
		this.MenuButtons();
		this.Letters();
		
		this.add(TheBoard, BorderLayout.CENTER);
		this.add(MenuButton, BorderLayout.NORTH);
		this.add(LettersInPanel, BorderLayout.SOUTH);
		this.setTitle("Player 1 Moving... Score Player 1: " + player[0].getScore() + "   Player 2: " + player[1].getScore());
		
	
	}
	//lets begin
	public static void main(String [] args)
	{
		Board Window = new Board();
		Window.setSize(950,600);
		Window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i< 15; i ++)
		{
			for(int j =0; j<15; j++)
			{
				if(e.getSource() == boxes[i][j]) //if a box has been clicked
				{
					if(LetterToPlace != "") // if the letter button is clicked if not then nothing
					{
						MenuButton.remove(reshuffleLetters);
						Color brown = new Color(197,161,125);
						if(boxes[i][j].getBackground().equals(brown))
						{
							JOptionPane.showMessageDialog(TheBoard, "Invalid Move. There is a tile already there");
							break;
						}
						if(buttonsclicked == 0) //looking for adjancies
						{
							direction[0] = i;
							direction[1] = j;
							if((i<14 && boxes[i+1][j].getBackground().equals(brown)) || (i>0 &&boxes[i-1][j].getBackground().equals(brown)) || (j<14 && boxes[i][j+1].getBackground().equals(brown)) || (j>0 && boxes[i][j-1].getBackground().equals(brown)))
							{
								Adjacent = true;
							}
						}
						else if(buttonsclicked == 1)//checking for adjancies and directions
						{
							if((i  == direction[0]) && j>0 &&(boxes[i][j-1].getBackground().equals(brown)))
							{
								if((boxes[i][j+1].getBackground().equals(brown) && j<14)|| (boxes[i+1][j].getBackground().equals(brown)&& i<14) || (boxes[i-1][j].getBackground().equals(brown)&& i>0))
									Adjacent = true;
								isHorizontal = true;
							}
							else if((j  == direction[1]) && i>0 &&(boxes[i-1][j].getBackground().equals(brown)))
							{
								isVertical = true;
								if((boxes[i+1][j].getBackground().equals(brown) && i<14)|| (boxes[i-1][j].getBackground().equals(brown)&& i>0) || (boxes[i][j+1].getBackground().equals(brown)&& j<14))
									Adjacent = true;
							}
							else{
								JOptionPane.showMessageDialog(TheBoard, "Invalid Move. You must move from left to right or up to down!");
								break;
								}
						}
						
						else if(buttonsclicked > 1)//checking now just for adjancies
						{
							if(isHorizontal)
							{
								if((boxes[i][j+1].getBackground().equals(brown) && j<14)|| (boxes[i+1][j].getBackground().equals(brown)&& i<14) || (boxes[i-1][j].getBackground().equals(brown)&& i>0))
									Adjacent = true;
								if(i  != direction[0] || (j>0 && !boxes[i][j-1].getBackground().equals(brown)))
								{
									////System.out.printf("direction[0] = %d    i - clicked = %d   \n", direction[0], i - buttonsclicked);
									JOptionPane.showMessageDialog(TheBoard, "Invalid Move. You must move from left to right!");
									break;
								}
							}
							else if(isVertical)
							{
								if(j  != direction[1] || (i> 0 && !boxes[i-1][j].getBackground().equals(brown)))
								{
									JOptionPane.showMessageDialog(TheBoard, "Invalid Move. You must move from up to down!");	
									break;
								}
								if((boxes[i+1][j].getBackground().equals(brown) && i<14)|| (boxes[i-1][j].getBackground().equals(brown)&& i>0) || (boxes[i][j+1].getBackground().equals(brown)&& j<14))
									Adjacent = true;
							}
								
						}
						boxes[i][j].setText(LetterToPlace);//if everything up above is good
						boxes[i][j].setBackground(new Color(197,161,125));
						boxes[i][j].setForeground(Color.BLACK);
						LetterToPlace = "";//reset it
						if(playerCounter ==1)
							LettersInPanel.remove(letterButtons2[LetterToRemove]);
						else
							LettersInPanel.remove(letterButtons[LetterToRemove]);
						LettersPlaced[buttonsclicked] = LetterToRemove;
						LetterToRemove = -1;
						boxesSetBeforeCancel[buttonsclicked][0] = i;
						boxesSetBeforeCancel[buttonsclicked++][1] = j;
					}
					this.revalidate();//update the board
					this.repaint();
				}
					
			}
		}
		for(int i =0; i<7; i++)
		{
			if(e.getSource() == letterButtons[i])
			{
				LetterToPlace = letterButtons[i].getText();//put this in the letter to place
				//System.out.println(letterButtons[i].getText());
				LetterToRemove = i;
			}
			if(e.getSource() == letterButtons2[i])
			{
				LetterToPlace = letterButtons2[i].getText();
				//System.out.println(letterButtons2[i].getText());
				LetterToRemove = i;
			}
		}
		if(e.getSource() == reshuffleLetters)//letter reshuffling
		{
			String [] LetterString = new String [7];
			int counter = LettersInPanel.getComponentCount();
			LettersInPanel.removeAll(); //get rid of the menu buttons and replace them with checkboxes based on the player counter
			for(int i =0; i < counter; i++)
			{
				if(playerCounter == 0)
					letterCheckBoxes[i] = new JCheckBox(letterButtons[i].getText());
				else
					letterCheckBoxes[i] = new JCheckBox(letterButtons2[i].getText());
				LettersInPanel.add(letterCheckBoxes[i]);
			}
			MenuButton.add(reshuffleDone);
			MenuButton.remove(reshuffleLetters);
			MenuButton.remove(cancelButton);
			MenuButton.remove(endButton);
			this.revalidate();
			this.repaint();
			
		}
		if(e.getSource() == reshuffleDone)//put the buttons bacl on the board 
		{
			String [] LetterString = new String [7];
			int counter = LettersInPanel.getComponentCount();
			LettersInPanel.removeAll();
			PlayerLetterArray[playerCounter].removeAll();
			PlayerLetterArray[playerCounter].removeAll();
			if(playerCounter == 0)
				LetterBackup.removeAll();
			for(int i =0; i< counter; i++)
			{
				if(letterCheckBoxes[i].isSelected())
				{
					Letters.revalidate(letterCheckBoxes[i].getText());
					letterCheckBoxes[i] = new JCheckBox(Letters.getRandomLetter());
					letterIndexes[i]= Letters.index;//not in use
				}
			}
			for(int i =0; i<counter; i++)
			{
				if(playerCounter == 0)
				{	
					letterButtons[i] = new JButton(letterCheckBoxes[i].getText());
					LettersInPanel.add(letterButtons[i]);
					LetterBackup.add(letterButtons[i]);
					//PlayerLetterArray[playerCounter].add(letterButtons[i]);
				}
				else
				{	
					letterButtons2[i] = new JButton(letterCheckBoxes[i].getText());
					LettersInPanel.add(letterButtons2[i]);
					PlayerLetterArray[playerCounter].add(letterButtons2[i]);
				}
			}
			MenuButton.remove(reshuffleDone);
			MenuButton.add(reshuffleLetters);
			MenuButton.add(cancelButton);
			MenuButton.add(endButton);
			this.revalidate();
			this.repaint();
			e.setSource(endButton);//let it fall through
			
		}
		ENDER: if(e.getSource() == endButton)
		{
			int [] valueArray = new int [7];
			int finalValue = 0;
			int finalMultiplier = 1;
			boolean getMoreValues = true;
			Color brown = new Color(197,161,125);
			ArrayList <String> words = new ArrayList<String>();
			//JOptionPane.showMessageDialog(TheBoard, LetterBackup.getComponentCount());
			String InvalidWord = "";
			hValidWord = true;
			vValidWord = true;
			try{
			for(int i =0; i<15; i++)//checking in my dictionary for the words check all of ther horizontal words first then the vertical words
			{
				for(int j =0; j<15; j++)
				{
					File file = new File(getClass().getResource("american-english").toURI());
					Scanner in = new Scanner(file);
					String s = "";
					while(boxes[i][j].getBackground().equals(brown))
					{
						s += boxes[i][j].getText();
						j++;
					}
					if(s!="" && s.length() > 1)
					{
						s = s.toLowerCase();
						int checker =0;
						while(in.hasNextLine())
						{String str = in.nextLine();
							if(str.equals(s))
							{
								checker = 1;
								break;
							}	
						}
						if(checker!= 1)
						{
							InvalidWord=s;
							InvalidWord = s;
							hValidWord = false;
							break;
						}
					}
					in.close();
				}
				if(hValidWord == false)
					break;
			}}catch(IOException e1){Thread.currentThread().getStackTrace();} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
			for(int j =0; j<15; j++)
			{
				if(hValidWord == false)
					break;
				for(int i =0; i<15; i++)
				{
					File file = new File(getClass().getResource("american-english").toURI());
					Scanner in = new Scanner(file);
					String s = "";
					while(boxes[i][j].getBackground().equals(brown))
					{
						s += boxes[i][j].getText();
						i++;
					}
					if(s!="" && s.length() > 1)
					{
						
						s = s.toLowerCase();
						int checker =0;
						while(in.hasNextLine())
						{String str = in.nextLine();
							if(str.equals(s))
							{
						
								checker =1;
						
								break;
							}	
						}
						if(checker!= 1)
						{
							InvalidWord=s;
						
							InvalidWord = s;
							vValidWord = false;
							break;
						}
					}
					in.close();
				}
				if(vValidWord == false)
					break;
				
			}}catch(IOException e1){Thread.currentThread().getStackTrace();} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(!hValidWord || !vValidWord) //if the word is not valid set to cancel and let it fall through
			{
				JOptionPane.showMessageDialog(TheBoard, "The word placed is invalid " + InvalidWord);	
				e.setSource(cancelButton);
				hValidWord = true;
				vValidWord = true;
				break ENDER;
			}
			if(!centerIsTaken && buttonsclicked == 1 && !boxes[7][7].getText().equals("A")) // checking for first move only letter that works is A
			{
				JOptionPane.showMessageDialog(TheBoard, "The word placed is invalid");
				e.setSource(cancelButton);
				break ENDER;
			}
			
			
			if(buttonsclicked>0 && !Adjacent && centerIsTaken) // checking for adjancies
			{
				JOptionPane.showMessageDialog(TheBoard, "Invalid Move. You must play adjacent to one another!");
				e.setSource(cancelButton);
				break ENDER;
			}
			if(!boxes[7][7].getBackground().equals(brown) && buttonsclicked > 0) // checking for the center tile
			{
				JOptionPane.showMessageDialog(TheBoard, "Invalid Move. The Center Tile Must Be Taken");
				e.setSource(cancelButton);
				break ENDER;
			}
			else if(buttonsclicked> 0)
			{
				centerIsTaken = true;
			}
			isVertical = false;
			isHorizontal = false;
			for( int i =0; i< 7; i++)//SCORING
			{
				getMoreValues = true;
				int x = boxesSetBeforeCancel[i][0];
				int y = boxesSetBeforeCancel[i][1];
				if(x== -1)
					break;
				
				int xx = x;
				int yy = y;
				
				while(boxes[xx][yy-1].getBackground().equals(brown))
				{
					////System.out.println("running");
					for(int k =0;k <7; k++)
					{
						if (boxesSetBeforeCancel[k][1] == yy-1)
						{
							getMoreValues = false;
						}
					}
					if(getMoreValues)
					{
						finalValue+=Letters.getValue(boxes[xx][yy-1].getText());
						if(yy-1<0)
							break;
						yy--;
					}
					else
						break;
				}
				while(boxes[xx-1][yy].getBackground().equals(brown))
				{
					//System.out.println("running");
					for(int k =0;k <7; k++)
					{
						if (boxesSetBeforeCancel[k][0] == xx-1)
						{
							getMoreValues = false;
						}
					}
					if(getMoreValues)
					{
						finalValue+=Letters.getValue(boxes[xx-1][yy].getText());
						if(xx-1 <0)
							break;
						xx--;
					}
					else
						break;
				}
				if(x != -1)
				{
					String textinBox = boxes[x][y].getText();
					int value = Letters.getValue(textinBox);
					value *= b.getLetterMultiplier(x, y);
					//System.out.println("value is " +value);
					finalMultiplier *= b.getWordMultiplier(x,y);
					valueArray[i] = value;
				}
				else
					break;
			}
			for(int i =0; i<7; i++)
			{
				finalValue += valueArray[i];
				boxesSetBeforeCancel[i][0]=-1;
				boxesSetBeforeCancel[i][1]=-1;
				buttonsclicked = 0;
			}
			finalValue*= finalMultiplier;
			int score = player[playerCounter].getScore()+finalValue;
			player[playerCounter].setScore(score);
			//System.out.println("final value = " +finalValue);
			
			if(playerCounter == 0) //REDRAWING AND REPOSITIONING THE BOARD
			{
				//JOptionPane.showMessageDialog(TheBoard, "before first move" + LetterBackup.getComponentCount());
				if(firstmove)
				{
					for(int i =0; i<7; i++)
					{
						letterButtons2[i] = new JButton(Letters.getRandomLetter());
						letterButtons2[i].addActionListener(this);
						PlayerletterIndex[1][i]= Letters.index;
						PlayerLetterArray[1].add(letterButtons2[i]);
					}
					
					////System.out.println("first move running");
				
				//JOptionPane.showMessageDialog(TheBoard, "before boolean adder" + LetterBackup.getComponentCount());
				for(int i =0; i<7; i++)
				{
					boolean adder = true;
					for(int j =0; j<7; j++)
					{
						if(LettersPlaced[j] == i)
						{
							PlayerletterIndex[0][i] = -1;
							adder = false;
						}
					}
					if(adder){
						LetterBackup.add(letterButtons[i]);
						letterButtons[i].addActionListener(this);
						//System.out.println("Adder is true");
					}
				}
				firstmove = false;
				}
				
				
				playerCounter = 1;
				
				//JOptionPane.showMessageDialog(TheBoard, "before add" + LetterBackup.getComponentCount());
				letterIndexes = PlayerletterIndex[1];
				//JOptionPane.showMessageDialog(TheBoard, "Letter Indexes" + LetterBackup.getComponentCount());
				
				//LettersInPanel.removeAll();
				//JOptionPane.showMessageDialog(TheBoard, "remove all" + LetterBackup.getComponentCount());
				
			//JOptionPane.showMessageDialog(TheBoard, "after add" + LetterBackup.getComponentCount());
				for(int i = 0; i<7; i++)
				{
						if(Letters.getDistributionSize() > 0)
						{
							boolean adder = true;
							for(int j =0; j<7; j++)
							{
								if(LettersPlaced[j] == i)
									adder = false;
							}
							if(!adder)
							{
							letterButtons[i]= new JButton(Letters.getRandomLetter());
							//System.out.println("Giving new letters to letter Buttons");
							PlayerletterIndex[0][i] = Letters.index;
							letterButtons[i].addActionListener(this);
							LetterBackup.add(letterButtons[i]);
							}
						}
				}
				LettersInPanel = PlayerLetterArray[1];
				//JOptionPane.showMessageDialog(TheBoard, "B4 look away" + LetterBackup.getComponentCount());
				this.getContentPane().removeAll();
				this.add(TheBoard,BorderLayout.CENTER);
				this.repaint();
				this.revalidate();	
				JOptionPane.showMessageDialog(TheBoard, "Player 1 look away!!!!");	
				MenuButton.removeAll();
				MenuButton.add(cancelButton);
				MenuButton.add(endButton);
				MenuButton.add(reshuffleLetters);
				this.add(MenuButton,BorderLayout.NORTH);
				this.add(LettersInPanel,BorderLayout.SOUTH);
				this.setTitle("Player 2 moving... Score Player 1: " + player[0].getScore() + "   Player 2: " + player[1].getScore());
				this.repaint();
				//JOptionPane.showMessageDialog(TheBoard, "END of PLayer 1" + LetterBackup.getComponentCount());
				this.revalidate();
				
				for(int i =0; i<7; i++)
				{
					LettersPlaced[i] = -1;
				}
				//System.out.println("Letter backup component count" +LetterBackup.getComponentCount());
			}
			else if(playerCounter == 1)
			{
				//JOptionPane.showMessageDialog(TheBoard, "First" + LetterBackup.getComponentCount());
				
				for(int i =0; i<7; i++)
				{
					boolean adder = true;
					for(int j =0; j<7; j++)
						if(LettersPlaced[j] == i)
						{
							PlayerletterIndex[1][i] = -1;
							adder = false;
						}
					if(adder){
						PlayerLetterArray[1].add(letterButtons2[i]);
						//System.out.println("Adder is true");
					}
				}
				
				//JOptionPane.showMessageDialog(TheBoard, "Second" + LetterBackup.getComponentCount());
				for(int i = 0; i<7; i++)
				{
						if(Letters.getDistributionSize() > 0)
						{
							boolean adder = true;
							for(int j =0; j<7; j++)
							{
								if(LettersPlaced[j] == i)
									adder = false;
							}
							if(!adder)
							{
							//System.out.println("adder is false in the second set oh my gosh");
							letterButtons2[i]= new JButton(Letters.getRandomLetter());
							PlayerletterIndex[1][i] = Letters.index;
							letterButtons2[i].addActionListener(this);
							PlayerLetterArray[1].add(letterButtons2[i]);
							}
						}
				}
				
				letterIndexes = PlayerletterIndex[0];
				
				
				LetterBackup.setVisible(true);
				
				playerCounter = 0;
				
				for(int i =0; i <7; i++)
				{
					letterButtons[i].addActionListener(this);
					//System.out.println(letterButtons[i].getText());
				}
				//System.out.println(LettersInPanel.getComponentCount());
				LettersInPanel = LetterBackup;	
				this.getContentPane().removeAll();
				this.add(TheBoard,BorderLayout.CENTER);		
				this.repaint();
				this.revalidate();	
				JOptionPane.showMessageDialog(TheBoard, "Player 2 look away!!!!");
				MenuButton.removeAll();
				MenuButton.add(cancelButton);
				MenuButton.add(endButton);
				MenuButton.add(reshuffleLetters);
				this.add(MenuButton,BorderLayout.NORTH);
				this.add(LettersInPanel,BorderLayout.SOUTH);
				//this.add(LettersInPanel,BorderLayout.SOUTH);
				this.setTitle("Player 1 moving... Score Player 1: " + player[0].getScore() + "   Player 2: " + player[1].getScore());
				this.repaint();
				this.revalidate();	
				
				
				for(int i =0; i<7; i++)
				{
					LettersPlaced[i] = -1;
				}
			}
			int boxesonboard = 0;
			for(int i = 0; i<15; i++)
			{
				for(int j = 0 ; j<15; j++)
				{
					if(boxes[i][j].getBackground().equals(brown))
						boxesonboard++;
					
				}
			}
			if(boxesonboard==99)
			{
				int player1 = player[0].getScore();
				int player2 = player[1].getScore();
				int winner =1;
				if(player1<player2)
					winner=2;
				JOptionPane.showMessageDialog(TheBoard, "THATS THE GAME!!!!!\n Player 1 with a score of" + player1+ "\nPlayer2 with a score of" + player2 + "\nPlayer" + winner+ "wins! Congratulations!");
			}
			Adjacent = false;
		}
		if(e.getSource() == cancelButton)
		{
			LettersInPanel.removeAll();
			if(playerCounter == 0)
			{
				for(int i =0; i < 7; i++)
				{
					LettersInPanel.add(letterButtons[i]);
					if(boxesSetBeforeCancel[i][0] != -1)
					{
						int x = boxesSetBeforeCancel[i][0];
						int y = boxesSetBeforeCancel[i][1];
						boxes[x][y].setText((b.getString(x, y)));
						boxes[x][y].setForeground(Color.WHITE);
						boxes[x][y].setBackground(b.getcolor(x, y));
					}
					boxesSetBeforeCancel[i][0] = -1;
					boxesSetBeforeCancel[i][1] = -1;
					LettersPlaced[i] = -1;
				}
			}
			else if (playerCounter == 1)
			{
				for(int i =0; i < 7; i++)
				{
					LettersInPanel.add(letterButtons2[i]);
					if(boxesSetBeforeCancel[i][0] != -1)
					{
						int x = boxesSetBeforeCancel[i][0];
						int y = boxesSetBeforeCancel[i][1];
						boxes[x][y].setText((b.getString(x, y)));
						boxes[x][y].setForeground(Color.WHITE);
						boxes[x][y].setBackground(b.getcolor(x, y));
					}
					boxesSetBeforeCancel[i][0] = -1;
					boxesSetBeforeCancel[i][1] = -1;
					LettersPlaced[i] = -1;
				}
			}
			
			MenuButton.removeAll();
			MenuButton.add(cancelButton);
			MenuButton.add(endButton);
			MenuButton.add(reshuffleLetters);
			
			isHorizontal = false;
			isVertical = false;
			buttonsclicked = 0;
			this.revalidate();
			this.repaint();
			Adjacent = false;
		}
	}//end of action performed
}

