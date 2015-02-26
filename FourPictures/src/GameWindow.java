import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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

	public GameWindow(ArrayList<FourPictures> game) {
		System.out.println("Current Level: " + nextLevel);
		levels = game;
		nextLevel++;
		this.game = levels.get(0);
		initUI();
	}

	public void nextLevel() {
		if (nextLevel == levels.size()) {
			JOptionPane.showMessageDialog(GameWindow.this, "No more levels");
		} else {
			System.out.println("Current Level: " + nextLevel);
			this.game = levels.get(nextLevel);
			this.remove(mainPanel);
			initUI();
			repaint();
			nextLevel++;
		}
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
		try {
			makeCenter();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void makeCenter() throws MalformedURLException, IOException {
		// Create centerPanel
		centerPanel = new JPanel(new GridLayout(2, 2));
		mainPanel.add(centerPanel);
		String[] fileN = game.getFileNames();
		for (int i = 0; i < fileN.length; i++) {
			System.out.println(fileN[i]);
			Image image = ImageIO.read(new URL(fileN[i]));
			Image newimg = image.getScaledInstance(250, 250,
					java.awt.Image.SCALE_SMOOTH);
			JLabel tempLabel = new JLabel(new ImageIcon(newimg));
			centerPanel.add(tempLabel);
		}

	}

	public void makeSouth() {
		// Initilises center Panel and adds to Main Panel
		southPanel = new JPanel(new GridLayout(2, 6));
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
				// System.out.println(game.remainingLetter());
				for (JButton temp : buttonArray) {
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
		for (JButton b : buttonArray) {
			southPanel.add(b);
		}
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
		JPanel nButtonPanel = new JPanel(new BorderLayout());
		nButtonPanel.add(next, BorderLayout.CENTER);
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

}
