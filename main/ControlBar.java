package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import font.SetFont;

public class ControlBar extends JPanel{
    private JLabel gameName;
    private JButton exitButton;
    private JButton setMinMaxButton;
    private JButton hideWindowButton;
    private Color myDarkGrey = new Color(57, 68, 76);
    public ControlBar(GameWindow gameWindow){
        int frameWidth = gameWindow.getWidth();
        setLayout(null);
        setBounds(0, 0,frameWidth, 30);
        setBackground(new Color(57, 68, 76));


        gameName = new JLabel("PM2.5 Simulation Suite");
        gameName.setBounds(10, 0,500,30);
        gameName.setForeground(Color.WHITE);
        gameName.setFont(new SetFont().getFont(12f));

        exitButton = new JButton("✖");
        exitButton.setBounds(1230,0,50,30);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setBackground(myDarkGrey);
        exitButton.setForeground(Color.WHITE);

        setMinMaxButton = new JButton("❑");
        setMinMaxButton.setBounds(1180,0,50,30);
        setMinMaxButton.setBorderPainted(false);
        setMinMaxButton.setFocusPainted(false);
        setMinMaxButton.setBackground(myDarkGrey);
        setMinMaxButton.setForeground(Color.WHITE);

        hideWindowButton = new JButton("━");
        hideWindowButton.setBounds(1130,0,50,30);
        hideWindowButton.setBorderPainted(false);
        hideWindowButton.setFocusPainted(false);
        hideWindowButton.setBackground(myDarkGrey);
        hideWindowButton.setForeground(Color.WHITE);

        add(gameName);
        add(exitButton);
        add(setMinMaxButton);
        add(hideWindowButton);

        //เมื่อกดปุ่ม "━" พักหน้าโปรแกรมไว้
        hideWindowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hideWindowButton.setBackground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hideWindowButton.setBackground(myDarkGrey);
            }
        });

        hideWindowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setExtendedState(JFrame.ICONIFIED);
            }
            
        });

        //เมื่อกดปุ่ม "❑" จะปรับลด/ขยาย
        setMinMaxButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setMinMaxButton.setBackground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setMinMaxButton.setBackground(myDarkGrey);
            }
        });
        setMinMaxButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameWindow.isMaximized) {
                    gameWindow.setNormalScreen();
                    setMinMaxButton.setText("❐");
                }
                else
                {
                    gameWindow.setMaxScreen();
                    setMinMaxButton.setText("❑");
                }
            }
            
        });

        //เมื่อกดปุ่ม "✖" จะกลับไปหน้า Menu(หน้าแรก)
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(myDarkGrey);
            }
        });
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.showMenuPanel();
                gameWindow.getGamePanel().restartGame();
            }
            
        });

        gameWindow.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = gameWindow.getWidth();
                
                setBounds(0, 0, frameWidth, 30); // ความกว้างเต็มหน้าจอ, ความสูง 30
                
                // ปรับขนาดและตำแหน่งของปุ่มใน controlPanel
                exitButton.setBounds(frameWidth - 50, 0, 50, 30);
                setMinMaxButton.setBounds(frameWidth - 100, 0, 50, 30);
                hideWindowButton.setBounds(frameWidth - 150, 0, 50, 30);
            }
        });
    }
    public ControlBar(GameWindow gameWindow,boolean isEnd){
        int frameWidth = gameWindow.getWidth();
        setLayout(null);
        setBounds(0, 0,frameWidth, 30);
        setBackground(new Color(57, 68, 76));


        gameName = new JLabel("PM2.5 Simulation Suite");
        gameName.setBounds(10, 0,500,30);
        gameName.setForeground(Color.WHITE);
        gameName.setFont(new SetFont().getFont(12f));

        exitButton = new JButton("✖");
        exitButton.setBounds(1230,0,50,30);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setBackground(myDarkGrey);
        exitButton.setForeground(Color.WHITE);

        setMinMaxButton = new JButton("❑");
        setMinMaxButton.setBounds(1180,0,50,30);
        setMinMaxButton.setBorderPainted(false);
        setMinMaxButton.setFocusPainted(false);
        setMinMaxButton.setBackground(myDarkGrey);
        setMinMaxButton.setForeground(Color.WHITE);

        hideWindowButton = new JButton("━");
        hideWindowButton.setBounds(1130,0,50,30);
        hideWindowButton.setBorderPainted(false);
        hideWindowButton.setFocusPainted(false);
        hideWindowButton.setBackground(myDarkGrey);
        hideWindowButton.setForeground(Color.WHITE);

        add(gameName);
        add(exitButton);
        add(setMinMaxButton);
        add(hideWindowButton);

        //เมื่อกดปุ่ม "━" พักหน้าโปรแกรมไว้
        hideWindowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hideWindowButton.setBackground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hideWindowButton.setBackground(myDarkGrey);
            }
        });

        hideWindowButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.setExtendedState(JFrame.ICONIFIED);
            }
            
        });

        //เมื่อกดปุ่ม "❑" จะปรับลด/ขยาย
        setMinMaxButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setMinMaxButton.setBackground(Color.GREEN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setMinMaxButton.setBackground(myDarkGrey);
            }
        });
        setMinMaxButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameWindow.isMaximized) {
                    gameWindow.setNormalScreen();
                    setMinMaxButton.setText("❐");
                }
                else
                {
                    gameWindow.setMaxScreen();
                    setMinMaxButton.setText("❑");
                }
            }
            
        });

        //เมื่อกดปุ่ม "✖" จะกลับไปหน้า Menu(หน้าแรก)
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(myDarkGrey);
            }
        });
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });

        gameWindow.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int frameWidth = gameWindow.getWidth();
                setBounds(0, 0, frameWidth, 30); // ความกว้างเต็มหน้าจอ, ความสูง 30
                
                // ปรับขนาดและตำแหน่งของปุ่มใน controlPanel
                exitButton.setBounds(frameWidth - 50, 0, 50, 30);
                setMinMaxButton.setBounds(frameWidth - 100, 0, 50, 30);
                hideWindowButton.setBounds(frameWidth - 150, 0, 50, 30);
            }
        });
    }
}
