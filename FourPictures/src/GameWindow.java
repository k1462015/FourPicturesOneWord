import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameWindow extends JFrame {
	JPanel mainPanel;
	JPanel northPanel;
	JPanel centerPanel;
	JPanel southPanel;
	JLabel revealedWord;
	FourPictures game;
	ArrayList<JButton> buttonArray;
	int nextLevel = 0;
	ArrayList<FourPictures> levels;
	JButton next;
	private static final long serialVersionUID = 1L;

	public GameWindow(ArrayList<FourPictures> levels) {
		System.out.println("Current Level: " + nextLevel);
		this.levels = levels;
		
		//Increments nextLevel ready for when next button is pressed
		nextLevel++;
		
		//Sets current level to first level
		this.game = levels.get(0);
		initUI();
	}

	private void initUI() {
		// Sets up mainJFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 700);
		setTitle("Four Pictures, One Word");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		add(mainPanel);
		
		//Creates north Panel with guess and revealedWord
		makeNorth();
		//Creates center panel for images
		try{
			makeCenter();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Creates bottom panel for keyboard and next button
		makeSouth();
		pack();

	}

	public void makeNorth(){
		// Initializes northPanel and adds to mainPanel
		mainPanel.add(northPanel = new JPanel());

		// Adds JLabel with label "Guessed"
		JLabel guessed = new JLabel("Guessed: ", SwingConstants.LEFT);
		guessed.setFont(new Font("Arial", Font.BOLD, 40));
		northPanel.add(guessed);

		// Adds Revealed Words
		String rWord = "";
		for (String s : game.getRWord()) {
			rWord += s;
		}
		revealedWord = new JLabel(rWord);
		revealedWord.setFont(new Font("Arial", Font.BOLD, 40));
		northPanel.add(revealedWord);

	}
	
	/**
	 * Adds four pictures in a 2x2 Grid
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void makeCenter() throws MalformedURLException, IOException {
		// Create centerPanel
		centerPanel = new JPanel(new GridLayout(2, 2));
		mainPanel.add(centerPanel);
		String[] fileN = game.getFileNames();
		for (int i = 0; i < fileN.length; i++) {
			System.out.println(fileN[i]);
			// Creates image from reading file path
			System.out.println("Reading URL link");
			Image image = ImageIO.read(new URL(fileN[i]));
			// Resizes all images
			Image newimg = image.getScaledInstance(250, 250,
					java.awt.Image.SCALE_SMOOTH);
			JLabel tempLabel = new JLabel(new ImageIcon(newimg));
			centerPanel.add(tempLabel);
		}

	}

	public void makeSouth() {
		// Initializes south Panel and adds to Main Panel
		southPanel = new JPanel(new GridLayout(2, 5,2,2));
		mainPanel.add(southPanel);

		// Creates all JButtons for keyboard
		char[] letters = game.getLetters().toCharArray();
		buttonArray = new ArrayList<JButton>();
		for (int i = 0; i < letters.length; i++) {
			final JButton temp = new JButton(letters[i] + "");

			temp.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					temp.setVisible(false);
					String s = temp.getText();
					revealedWord.setText(game.updateRWord(s));
					checkAllCorrect();

				}

			});
			buttonArray.add(temp);

		}

		// Creates hint JButton
		final JButton hint = new JButton("?");
		hint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hint.setVisible(false);
				for (JButton temp : buttonArray) {
					// Retrieves one possible hint letter
					// Then searches remaining buttons
					// And makes matching button invisible
					if (temp.getText().equals(game.remainingLetter()[0])) {
						System.out.println(game.remainingLetter()[0]);
						String s = temp.getText();
						revealedWord.setText(game.updateRWord(s));
						checkAllCorrect();
						temp.setVisible(false);
						break;
					}
				}

			}

		});
		buttonArray.add(hint);
		// Adds all buttons to the southPanel
		for (JButton b : buttonArray) {
			southPanel.add(b);
		}
		// Makes next button and adds to mainPanel
		JPanel nButtonPanel = new JPanel(new BorderLayout());
		nButtonPanel.add(makeNext(), BorderLayout.CENTER);
		mainPanel.add(nButtonPanel);

	}

	public void checkAllCorrect() {
		if (game.checkAllCorrect()) {
			southPanel.removeAll();
			JLabel correct = new JLabel("Correct!");
			correct.setHorizontalAlignment(0);
			correct.setFont(new Font("Arial", Font.BOLD, 30));
			southPanel.add(correct);
			next.setEnabled(true);
			pack();
		}
	}

	public JButton makeNext() {
		// Makes next button
		next = new JButton("Next");
		next.setHorizontalAlignment(0);
		next.setFont(new Font("Arial", Font.BOLD, 30));
		next.setEnabled(false);
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				nextLevel();

			}

		});
		return next;

	}

	public void nextLevel() {
		// Checks if any more levels available
		if (nextLevel == levels.size()) {
			JOptionPane.showMessageDialog(GameWindow.this,
					"No more levels");
		} else {
			System.out.println("Current Level: " + nextLevel);
			// Sets current level to next level
			this.game = levels.get(nextLevel);
			// Clears current mainPanel
			this.remove(mainPanel);
			// Runs UI building again
			initUI();
			repaint();
			nextLevel++;
		}
	}

}
