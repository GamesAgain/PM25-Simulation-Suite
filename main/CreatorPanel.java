package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreatorPanel extends JPanel {
    private ControlBar controlBar;
    private JLabel t1;
    private JLabel t2;
    private JLabel t3;
    private JButton menuButton;
    public CreatorPanel(GameWindow gameWindow){
        controlBar = new ControlBar(gameWindow);
        t1 = new JLabel(new ImageIcon(getClass().getResource("/Creator/FARM.gif")));
        t2 = new JLabel(new ImageIcon(getClass().getResource("/Creator/GAMES.gif")));
        t3 = new JLabel(new ImageIcon(getClass().getResource("/Creator/SKYWAVE101.gif")));
        ImageIcon menuIcon = new ImageIcon(getClass().getResource("/button/MenuButton.png"));
        menuButton = new JButton(menuIcon);
        menuButton.setBounds(65, 662, 170, 50);

        setLayout(null);
        t1.setBounds(350, 350, 200, 300);
        t2.setBounds(555, 347, 200, 300);
        t3.setBounds(760, 350, 200, 300);
        add(t1);
        add(t2);
        add(t3);
        add(controlBar);
        add(menuButton);

        menuButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.showMenuPanel();
            }
            
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image backgroundImage = new ImageIcon(getClass().getResource("/Creator/BG.gif")).getImage();
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}