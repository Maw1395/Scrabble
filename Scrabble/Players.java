package Scrabble;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Players {
	public Players()
	{
		score =0;
		for(int i =0; i<7; i++)
			LettersInPanel.add(new JButton ());
		LettersInPanel.setVisible(true);
	}
	public void setScore (int d)
	{
		score  = d;
	}
	public int score = 0;
	JPanel LettersInPanel = new JPanel(new GridLayout (1,7));
	public int getScore()
	{
		return score;
	}
}
