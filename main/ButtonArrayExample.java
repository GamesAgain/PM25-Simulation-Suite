package main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonArrayExample {
    private static final int ROWS = 10;
    private static final int COLS = 20;
    private static final int ARRAY_SIZE = ROWS * COLS;
    private JButton[] buttons = new JButton[ARRAY_SIZE];

    public ButtonArrayExample() {
        JFrame frame = new JFrame("Button Array Example");
        frame.setLayout(new GridLayout(ROWS, COLS));
        frame.setSize(800, 400); // Adjust size as needed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < ARRAY_SIZE; i++) {
            buttons[i] = new JButton();
            buttons[i].setPreferredSize(new Dimension(30, 30)); // Adjust button size as needed
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].addActionListener(new ButtonActionListener(i));
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    private class ButtonActionListener implements ActionListener {
        private int buttonIndex;

        public ButtonActionListener(int index) {
            this.buttonIndex = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Reset all buttons to default colors
            for (JButton button : buttons) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            // Set the clicked button to CYAN
            buttons[buttonIndex].setBackground(Color.CYAN);

            // Set surrounding buttons to GREEN
            int[] surroundingIndices = getSurroundingIndices(buttonIndex);
            for (int index : surroundingIndices) {
                if (index >= 0 && index < ARRAY_SIZE) {
                    buttons[index].setBackground(Color.GREEN);
                }
            }
        }

        private int[] getSurroundingIndices(int index) {
            int[] surroundingIndices = new int[8];
            int row = index / COLS;
            int col = index % COLS;

            int count = 0;
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i == row && j == col) continue; // Skip the clicked button itself
                    if (i >= 0 && i < ROWS && j >= 0 && j < COLS) {
                        int surroundingIndex = i * COLS + j;
                        if (count < 8) {
                            surroundingIndices[count++] = surroundingIndex;
                        }
                    }
                }
            }

            // Return only the filled part of the array
            int[] result = new int[count];
            System.arraycopy(surroundingIndices, 0, result, 0, count);
            return result;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ButtonArrayExample::new);
    }
}
