import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameWindowDISK extends JFrame {
	JPanel mainPanel;
	JPanel northPanel;
	JPanel centerPanel;
	JPanel southPanel;
	JLabel revealedWord;
	FourPictures game;
	ArrayList<JButton> buttonArray;

	private static final long serialVersionUID = 1L;

	public GameWindowDISK(FourPictures game) {
		this.game = game;
		initUI();
	}

	private void initUI() {
		// Sets up mainJFrame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 700);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		add(mainPanel);

		makeNorth();
		makeCenter();
		makeSouth();
		pack();

	}

	public void makeNorth() {
		// Initilises northPanel and adds to mainPanel
		mainPanel.add(northPanel = new JPanel());

		// Adds JLabel with label "Guessed"
		JLabel guessed = new JLabel("Guessed: ", SwingConstants.LEFT);
		guessed.setFont(new Font("Arial", Font.BOLD, 30));
		northPanel.add(guessed);

		// Adds Revealed Words
		String rWord = "";
		for (String s : game.getRWord()) {
			rWord += s;
		}
		revealedWord = new JLabel(rWord);
		revealedWord.setFont(new Font("Arial", Font.BOLD, 30));
		northPanel.add(revealedWord);

	}

	public void makeCenter() {
		// Create middle
		centerPanel = new JPanel(new GridLayout(2, 2));
		mainPanel.add(centerPanel);
		String[] fileN = game.getFileNames();
		for (int i = 0; i < fileN.length; i++) {
			ImageIcon icon = new ImageIcon(fileN[i]);
			JLabel tempLabel = new JLabel();
			tempLabel.setIcon(icon);
			centerPanel.add(tempLabel);
		}

	}

	public void makeSouth() {
		// Initilises center Panel and adds to Main Panel
		southPanel = new JPanel(new GridLayout(3, 5));
		mainPanel.add(southPanel);
		// Adds all letters bottom Panel
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
		final JButton hint = new JButton("?");
		hint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				hint.setVisible(false);
				//System.out.println(game.remainingLetter());
				for (JButton temp : buttonArray) {
					if (temp.getText().equals(game.remainingLetter()[0])){
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
		for(JButton b:buttonArray){
			southPanel.add(b);
		}
		
	

	}

	public void checkAllCorrect() {
		if (game.checkAllCorrect()) {
			southPanel.removeAll();
			JLabel correct = new JLabel("Correct!");
			correct.setHorizontalAlignment(0);
			correct.setFont(new Font("Arial", Font.BOLD, 30));
			southPanel.add(correct);
		}
	}

}
