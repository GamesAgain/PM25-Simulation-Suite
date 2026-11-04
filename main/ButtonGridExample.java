package main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonGridExample {
    private static final int GRID_WIDTH = 20;  // จำนวนคอลัมน์
    private static final int GRID_HEIGHT = 10; // จำนวนแถว
    private static final int TOTAL_BUTTONS = GRID_WIDTH * GRID_HEIGHT;
    private static final Color SELECTED_COLOR = Color.CYAN;
    private static final Color NEIGHBOR_COLOR = Color.GREEN;
    private static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Button Grid Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(GRID_HEIGHT, GRID_WIDTH)); // ปรับเป็น 20 คอลัมน์, 10 แถว
            
            JButton[] buttons = new JButton[TOTAL_BUTTONS];
            
            for (int i = 0; i < TOTAL_BUTTONS; i++) {
                buttons[i] = new JButton();
                buttons[i].setBackground(DEFAULT_COLOR);
                buttons[i].addActionListener(new ButtonClickListener(i, buttons));
                buttonPanel.add(buttons[i]);
            }
            
            frame.add(buttonPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    
    private static class ButtonClickListener implements ActionListener {
        private final int buttonIndex;
        private final JButton[] buttons;

        public ButtonClickListener(int buttonIndex, JButton[] buttons) {
            this.buttonIndex = buttonIndex;
            this.buttons = buttons;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (JButton button : buttons) {
                button.setBackground(DEFAULT_COLOR);
            }

            int x = buttonIndex % GRID_WIDTH;
            int y = buttonIndex / GRID_WIDTH;

            // Set the selected button color
            buttons[buttonIndex].setBackground(SELECTED_COLOR);

            // Set the neighbor buttons color
            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx == 0 && dy == 0) continue;

                    int nx = x + dx;
                    int ny = y + dy;

                    if (nx >= 0 && nx < GRID_WIDTH && ny >= 0 && ny < GRID_HEIGHT) {
                        int neighborIndex = ny * GRID_WIDTH + nx;
                        buttons[neighborIndex].setBackground(NEIGHBOR_COLOR);
                    }
                }
            }
        }
    }
}
