

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
	JPanel top;
	JLabel guessed;
	JLabel rWord;
	JPanel mid;
	JPanel bot;
	FourPictures game;
	
	private static final long serialVersionUID = 1L;
	
	public GameWindow (FourPictures game) {
		this.game = game;
		initUI();
	}

	private void initUI() {
		top = new JPanel();
		guessed = new JLabel("Guessed: ");
		
		top.setFont(new Font("Arial", Font.BOLD, 30));
		guessed.setFont(new Font("Arial", Font.BOLD, 30));
		
		
		top.add(guessed);
		String[] revealedWord = game.getRWord();
		System.out.println(revealedWord.toString());
		rWord = new JLabel(game.getRWord().toString()+"");
		top.add(rWord);
		
		
		// Create middle
		mid = new JPanel(new GridLayout(2,2));
		
		//JLabel background = new JLabel(new ImageIcon(imgBackground));
		BufferedImage img = null;
		String[] fileN = game.getFileNames();
		for(int i = 0;i < fileN.length;i++){
			ImageIcon icon = new ImageIcon(fileN[i]);
			JLabel tempLabel = new JLabel();
			tempLabel.setIcon(icon);
			mid.add(tempLabel);
		}
		
		// Create bottom
		bot = new JPanel(new GridLayout(2,5));
		
		String[] letters = game.getLetters();
		for(int i = 0;i < game.getLetters().length;i++){
			final JButton temp = new JButton(letters[i]+"");
			temp.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					temp.setVisible(false);
				}
				
			});
			bot.add(temp);
			
		}
		
		//Add all Panels and JLabels
		add(top, BorderLayout.NORTH);
		add(mid,BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		// Set frame properties
		setSize(600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}
}
