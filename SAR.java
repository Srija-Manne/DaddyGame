import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class SAR extends JApplet implements ActionListener {
	private static final long serialVersionUID = 1L;
	// private JFrame frame;
	private JButton[][] buttons;
	private JLabel statusLabel;
	private JLabel top;
	private char character;
	private int players = 0;
	private JButton b2;

	public SAR() {
		// frame = new JFrame("Tic Tac Toe");
		buttons = new JButton[3][3];
		statusLabel = new JLabel();
		top = new JLabel();
		character = 'X';
		initializeGUI();
	}

	private void initializeGUI() {
		JPanel panel = new JPanel(new GridLayout(3, 3));
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				JButton button = new JButton("-");
				button.addActionListener(this);
				button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 64));
				buttons[row][col] = button;
				panel.add(button);
			}
		}
		statusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		top.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		add(panel, BorderLayout.CENTER);
		add(statusLabel, BorderLayout.SOUTH);
		add(top, BorderLayout.NORTH);
		// pack();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void playMove(int row, int col) {
		if (buttons[row][col].getText().equals("-")) {
			buttons[row][col].setText(Character.toString(character));
			checkGameStatus();
			character = character == 'X' ? 'O' : 'X';
		}
	}

	private void checkGameStatus() {
		// Check rows
		for (int row = 0; row < 3; row++) {
			if (buttons[row][0].getText().equals(buttons[row][1].getText()) &&
					buttons[row][1].getText().equals(buttons[row][2].getText()) &&
					!buttons[row][0].getText().equals("-")) {
				endGame(buttons[row][0].getText() + " wins!");
				if (character == 'X')
					JOptionPane.showMessageDialog(this, "Player 1 won the match");
				else
					JOptionPane.showMessageDialog(this, "Player 2 won the match");
				return;
			}
		}
		// Check columns
		for (int col = 0; col < 3; col++) {
			if (buttons[0][col].getText().equals(buttons[1][col].getText()) &&
					buttons[1][col].getText().equals(buttons[2][col].getText()) &&
					!buttons[0][col].getText().equals("-")) {
				endGame(buttons[0][col].getText() + " wins!");
				if (character == 'X')
					JOptionPane.showMessageDialog(this, "Player 1 won the match");
				else
					JOptionPane.showMessageDialog(this, "Player 2 won the match");

				return;
			}
		}
		// Check diagonals
		if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
				buttons[1][1].getText().equals(buttons[2][2].getText()) &&
				!buttons[0][0].getText().equals("-")) {
			endGame(buttons[0][0].getText() + " wins!");
			if (character == 'X')
				JOptionPane.showMessageDialog(this, "Player 1 won the match");
			else
				JOptionPane.showMessageDialog(this, "Player 2 won the match");

			return;
		}
		if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
				buttons[1][1].getText().equals(buttons[2][0].getText()) &&
				!buttons[0][2].getText().equals("-")) {
			endGame(buttons[0][2].getText() + " wins!");
			if (character == 'X')
				JOptionPane.showMessageDialog(this, "Player 1 won the match");
			else
				JOptionPane.showMessageDialog(this, "Player 2 won the match");

			return;
		}

	}

	private void endGame(String message) {
		for (int row = 0; row < 3; row++) {

			for (int col = 0; col < 3; col++) {
				buttons[row][col].setEnabled(false);
			}
		}
		statusLabel.setText(message);
	}

	public void actionPerformed(ActionEvent e) {

		int rows = 0;
		int col = 0;

		JButton b = (JButton) e.getSource();

		if (b.getText().equals("-") && players < 6) {
			players++;
		}

		if (players <= 6) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] == b) {
						rows = i;
						col = j;
						playMove(rows, col);

					}
				}
			}
			if (players == 6)
				players++;
			// System.out.println("k:"+k+"players:"+players);
		}

		else {

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (buttons[i][j] == b) {
						rows = i;
						col = j;
						if (players % 2 != 0) {
							b2 = (JButton) e.getSource();
							String s = Character.toString(character);
							if (b2.getText().contentEquals(s)) {

								int res = checkButton(rows, col);
								if (res != 1) {

									if (!(b2.getText().equals("-"))) {
										players++;
										checkColor(rows, col);
										top.setText(null);

									}
								} else {
									top.setText("All buttons are occupied select another button");
								}
							}
						} else {
							int check = checkRange(rows, col);
							if (check != 1) {
								players--;
							} else {
								if (!b2.getText().equals("-")) {
									players++;
									if (buttons[rows][col].getText() == "-" || buttons[rows][col].getText() == "")
										playSwap(rows, col);
								}
							}

						}

					}
				}
			}

		}

	}

	private int checkRange(int rows, int col) {
		if (b2 == buttons[0][0]) {
			if ((buttons[rows][col] == buttons[0][1] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[1][0]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[0][1].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[1][0].setBackground(null);

			}
		} else if (b2 == buttons[0][1]) {
			if ((buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[0][2]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[0][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[0][2].setBackground(null);
			}
		} else if (b2 == buttons[0][2]) {
			if ((buttons[rows][col] == buttons[0][1] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[1][2]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[0][1].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[1][2].setBackground(null);

			}
		} else if (b2 == buttons[1][0]) {
			if ((buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][0]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[0][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][0].setBackground(null);
			}
		} else if (b2 == buttons[1][1]) {
			if ((buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[0][1]
					|| buttons[rows][col] == buttons[0][2] || buttons[rows][col] == buttons[1][0]
					|| buttons[rows][col] == buttons[1][2] || buttons[rows][col] == buttons[2][0]
					|| buttons[rows][col] == buttons[2][1] || buttons[rows][col] == buttons[2][2])
					&& (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[0][0].setBackground(null);
				buttons[0][1].setBackground(null);
				buttons[0][2].setBackground(null);
				buttons[1][0].setBackground(null);
				buttons[1][2].setBackground(null);
				buttons[2][0].setBackground(null);
				buttons[2][1].setBackground(null);
				buttons[2][2].setBackground(null);

			}
		} else if (b2 == buttons[1][2]) {
			if ((buttons[rows][col] == buttons[0][2] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][2]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[0][2].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][2].setBackground(null);

			}
		} else if (b2 == buttons[2][0]) {
			if ((buttons[rows][col] == buttons[1][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][1]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[1][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][1].setBackground(null);
			}
		} else if (b2 == buttons[2][1]) {
			if ((buttons[rows][col] == buttons[2][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][2]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {

				buttons[2][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][2].setBackground(null);
			}
		} else if (b2 == buttons[2][2]) {
			if ((buttons[rows][col] == buttons[1][2] || buttons[rows][col] == buttons[2][1]
					|| buttons[rows][col] == buttons[1][1]) && (buttons[rows][col].getText().equals("-"))) {
				return 1;
			} else {
				buttons[1][2].setBackground(null);
				buttons[2][1].setBackground(null);
				buttons[1][1].setBackground(null);
			}
		}
		return 0;
	}

	private int checkButton(int rows, int col) {
		if (b2 == buttons[0][0]) {
			if (buttons[0][1].getText() != "-" && buttons[1][1].getText() != "-" && buttons[1][0].getText() != "-") {
				return 1;
			}
		} else if (b2 == buttons[0][1]) {
			if (buttons[0][0].getText() != "-" && buttons[1][1].getText() != "-" && buttons[0][2].getText() != "-") {
				return 1;
			}
		} else if (b2 == buttons[0][2]) {
			if (buttons[0][1].getText() != "-" && buttons[1][1].getText() != "-" && buttons[1][2].getText() != "-") {
				return 1;
			}
		} else if (b2 == buttons[1][0]) {
			if (buttons[0][0].getText() != "-" && buttons[1][1].getText() != "-" && buttons[2][0].getText() != "-") {
				return 1;
			}
		} else if (b2 == buttons[1][1]) {
			if (buttons[0][0].getText() != "-" && buttons[0][1].getText() != "-" && buttons[0][2].getText() != "-"
					&& buttons[1][0].getText() != "-" && buttons[1][2].getText() != "-"
					&& buttons[2][0].getText() != "-" && buttons[2][1].getText() != "-"
					&& buttons[2][2].getText() != "-") {
				return 1;

			}
		} else if (b2 == buttons[1][2]) {
			if (buttons[0][2].getText() != "-" && buttons[1][1].getText() != "-" && buttons[2][2].getText() != "-") {
				return 1;
			}
		} else if (b2 == buttons[2][0]) {
			if (buttons[1][0].getText() != "-" && buttons[1][1].getText() != "-" && buttons[2][1].getText() != "-") {
				return 1;
			}
		} else if (b2 == buttons[2][1]) {
			if (buttons[2][0].getText() != "-" && buttons[1][1].getText() != "-" && buttons[2][2].getText() != "-") {
				return 1;
			}
		} else if (b2 == buttons[2][2]) {
			if (buttons[1][2].getText() != "-" && buttons[2][1].getText() != "-" && buttons[1][1].getText() != "-") {
				return 1;
			}
		}
		return 0;

	}

	private void checkColor(int rows, int col) {
		if (b2 == buttons[0][0])

		{

			if (buttons[0][1].getText().equals("-"))
				buttons[0][1].setBackground(Color.BLUE);
			else
				buttons[0][1].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);

			if (buttons[1][0].getText().equals("-"))
				buttons[1][0].setBackground(Color.BLUE);
			else
				buttons[1][0].setBackground(null);

		} else if (b2 == buttons[0][1]) {
			if (buttons[0][0].getText().equals("-"))
				buttons[0][0].setBackground(Color.BLUE);
			else
				buttons[0][0].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);

			if (buttons[0][2].getText().equals("-"))
				buttons[0][2].setBackground(Color.BLUE);
			else
				buttons[0][2].setBackground(null);

		} else if (b2 == buttons[0][2]) {
			if (buttons[0][1].getText().equals("-"))
				buttons[0][1].setBackground(Color.BLUE);
			else
				buttons[0][1].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);

			if (buttons[1][2].getText().equals("-"))
				buttons[1][2].setBackground(Color.BLUE);
			else
				buttons[1][2].setBackground(null);

		} else if (b2 == buttons[1][0]) {
			// if(buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[1][1]
			// || buttons[rows][col] == buttons[2][0])
			if (buttons[0][0].getText().equals("-"))
				buttons[0][0].setBackground(Color.BLUE);
			else
				buttons[0][0].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);

			if (buttons[2][0].getText().equals("-"))
				buttons[2][0].setBackground(Color.BLUE);
			else
				buttons[2][0].setBackground(null);

		} else if (b2 == buttons[1][1]) {
			// if(buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[0][1]
			// || buttons[rows][col] == buttons[0][2] || buttons[rows][col] == buttons[1][0]
			// || buttons[rows][col] == buttons[1][2] ||buttons[rows][col] == buttons[2][0]
			// || buttons[rows][col] == buttons[2][1] || buttons[rows][col] == buttons[2][2]
			// )
			if (buttons[0][0].getText().equals("-"))
				buttons[0][0].setBackground(Color.BLUE);
			else
				buttons[0][0].setBackground(null);

			if (buttons[0][1].getText().equals("-"))
				buttons[0][1].setBackground(Color.BLUE);
			else
				buttons[0][1].setBackground(null);

			if (buttons[0][2].getText().equals("-"))
				buttons[0][2].setBackground(Color.BLUE);
			else
				buttons[0][2].setBackground(null);

			if (buttons[1][0].getText().equals("-"))
				buttons[1][0].setBackground(Color.BLUE);
			else
				buttons[1][0].setBackground(null);

			if (buttons[1][2].getText().equals("-"))
				buttons[1][2].setBackground(Color.BLUE);
			else
				buttons[1][2].setBackground(null);

			if (buttons[2][0].getText().equals("-"))
				buttons[2][0].setBackground(Color.BLUE);
			else
				buttons[2][0].setBackground(null);

			if (buttons[2][1].getText().equals("-"))
				buttons[2][1].setBackground(Color.BLUE);
			else
				buttons[2][1].setBackground(null);

			if (buttons[2][2].getText().equals("-"))
				buttons[2][2].setBackground(Color.BLUE);
			else
				buttons[2][2].setBackground(null);

		} else if (b2 == buttons[1][2]) {
			// if(buttons[rows][col] == buttons[0][2] || buttons[rows][col] == buttons[1][1]
			// || buttons[rows][col] == buttons[2][2])
			if (buttons[0][2].getText().equals("-"))
				buttons[0][2].setBackground(Color.BLUE);
			else
				buttons[0][2].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);

			if (buttons[2][2].getText().equals("-"))
				buttons[2][2].setBackground(Color.BLUE);
			else
				buttons[2][2].setBackground(null);

		} else if (b2 == buttons[2][0]) {
			// if(buttons[rows][col] == buttons[1][0] || buttons[rows][col] == buttons[1][1]
			// || buttons[rows][col] == buttons[2][1])
			if (buttons[1][0].getText().equals("-"))
				buttons[1][0].setBackground(Color.BLUE);
			else
				buttons[1][0].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);

			if (buttons[2][1].getText().equals("-"))
				buttons[2][1].setBackground(Color.BLUE);
			else
				buttons[2][1].setBackground(null);

		} else if (b2 == buttons[2][1]) {
			// if(buttons[rows][col] == buttons[2][0] || buttons[rows][col] == buttons[1][1]
			// || buttons[rows][col] == buttons[2][2])
			if (buttons[2][0].getText().equals("-"))
				buttons[2][0].setBackground(Color.BLUE);
			else
				buttons[2][0].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);

			if (buttons[2][2].getText().equals("-"))
				buttons[2][2].setBackground(Color.BLUE);
			else
				buttons[2][2].setBackground(null);

		} else if (b2 == buttons[2][2]) {
			// if(buttons[rows][col] == buttons[1][2] || buttons[rows][col] == buttons[2][1]
			// || buttons[rows][col] == buttons[1][1])
			if (buttons[1][2].getText().equals("-"))
				buttons[1][2].setBackground(Color.BLUE);
			else
				buttons[1][2].setBackground(null);

			if (buttons[2][1].getText().equals("-"))
				buttons[2][1].setBackground(Color.BLUE);
			else
				buttons[2][1].setBackground(null);

			if (buttons[1][1].getText().equals("-"))
				buttons[1][1].setBackground(Color.BLUE);
			else
				buttons[1][1].setBackground(null);
		}
	}

	private void playSwap(int rows, int col) {
		if (b2 == buttons[0][0]) {
			if (buttons[rows][col] == buttons[0][1] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[1][0]) {
				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[0][1].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[1][0].setBackground(null);
			}
		} else if (b2 == buttons[0][1]) {
			if (buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[0][2]) {

				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[0][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[0][2].setBackground(null);
			}
		} else if (b2 == buttons[0][2]) {
			if (buttons[rows][col] == buttons[0][1] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[1][2]) {
				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[0][1].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[1][2].setBackground(null);
			}
		} else if (b2 == buttons[1][0]) {
			if (buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][0]) {
				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[0][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][0].setBackground(null);
			}
		} else if (b2 == buttons[1][1]) {
			if (buttons[rows][col] == buttons[0][0] || buttons[rows][col] == buttons[0][1]
					|| buttons[rows][col] == buttons[0][2] || buttons[rows][col] == buttons[1][0]
					|| buttons[rows][col] == buttons[1][2] || buttons[rows][col] == buttons[2][0]
					|| buttons[rows][col] == buttons[2][1] || buttons[rows][col] == buttons[2][2]) {
				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[0][0].setBackground(null);
				buttons[0][1].setBackground(null);
				buttons[0][2].setBackground(null);
				buttons[1][0].setBackground(null);
				buttons[1][2].setBackground(null);
				buttons[2][0].setBackground(null);
				buttons[2][1].setBackground(null);
				buttons[2][2].setBackground(null);

			}
		} else if (b2 == buttons[1][2]) {
			if (buttons[rows][col] == buttons[0][2] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][2]) {
				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[0][2].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][2].setBackground(null);
			}
		} else if (b2 == buttons[2][0]) {
			if (buttons[rows][col] == buttons[1][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][1]) {
				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[1][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][1].setBackground(null);
			}
		} else if (b2 == buttons[2][1]) {
			if (buttons[rows][col] == buttons[2][0] || buttons[rows][col] == buttons[1][1]
					|| buttons[rows][col] == buttons[2][2]) {
				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[2][0].setBackground(null);
				buttons[1][1].setBackground(null);
				buttons[2][2].setBackground(null);
			}
		} else if (b2 == buttons[2][2]) {
			if (buttons[rows][col] == buttons[1][2] || buttons[rows][col] == buttons[2][1]
					|| buttons[rows][col] == buttons[1][1]) {

				b2.setText("-");
				buttons[rows][col].setText(Character.toString(character));
				buttons[1][2].setBackground(null);
				buttons[2][1].setBackground(null);
				buttons[1][1].setBackground(null);
			}
		}

		checkGameStatus();
		character = character == 'X' ? 'O' : 'X';

	}

	public static void main(String[] args) {
		new SAR();

	}
}