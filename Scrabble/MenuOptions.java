package Scrabble;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuOptions implements ActionListener{

	JPanel MenuButton = new JPanel(new GridLayout(1,3));
	public MenuOptions()
	{
		JButton MoveButton = new JButton("Move");
		JButton cancelButton = new JButton("Cancel");
		JButton endButton = new JButton ("End Move");
		MenuButton.add(MoveButton);
		MenuButton.add(cancelButton);
		MenuButton.add(endButton);
	}
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}