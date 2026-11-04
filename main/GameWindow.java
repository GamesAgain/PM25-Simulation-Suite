package main;

import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends JFrame {
    public int width= 1280, height=720;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private CreatorPanel creatorPanel;

    private ImageIcon gameImageIcon;
    public GameWindow(){
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        creatorPanel = new CreatorPanel(this);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(menuPanel,"Menu");
        mainPanel.add(gamePanel,"Game");
        mainPanel.add(creatorPanel,"Creator");
        setTitle("PM2.5 Simulation Suite");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setIconImage(getIconImage());
        setFocusable(true);
        add(mainPanel); 
        showMenuPanel();
    }

    public void showGamePanel(){
        cardLayout.show(mainPanel, "Game");
    }

    public void showMenuPanel(){
        cardLayout.show(mainPanel, "Menu");
    }

    public void showCreatorPanel(){
        cardLayout.show(mainPanel, "Creator");
    }

    @Override
    public Image getIconImage() {
        gameImageIcon = new ImageIcon(getClass().getResource("/image/GameIcon.png"));
        return gameImageIcon.getImage();
    }

    public GamePanel getGamePanel(){
        return this.gamePanel;
    }
}
