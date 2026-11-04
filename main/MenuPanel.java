package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{

    private ImageIcon backgroundImageIcon = new ImageIcon(getClass().getResource("/image/MenuBG.png"));
    private Image backgroundImage = backgroundImageIcon.getImage();
    
    //กำหนดตัวแปร Button
    private MyButtonPanel startButtonPanel;
    private MyButtonPanel creatorButtonPanel;
    private MyButtonPanel exitButtonPanel;
    private MyButtonPanel setMinMaxPanel;

    //กำหนดตัวแปร Panel
    private JButton startButton;
    private JButton creatorButton;
    private JButton exitButton;

    private ControlBar controlBar;
    
    public MenuPanel(GameWindow gameWindow){
        controlBar = new ControlBar(gameWindow,true);
        setLayout(null);
        creatorButton();
        

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.showGamePanel();
            }
            
        });
        startButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonMovement(startButton,startButtonPanel,true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonMovement(startButton,startButtonPanel,false);
            }
        });

        creatorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.showCreatorPanel();
            }
            
        });
        creatorButton.addMouseListener(new MouseAdapter() {
           
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonMovement(creatorButton,creatorButtonPanel,true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonMovement(creatorButton,creatorButtonPanel,false);
            }
        });

        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonMovement(exitButton,exitButtonPanel,true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                buttonMovement(exitButton,exitButtonPanel,false);
            }
        });

        //เพิ่ม ComponentListener ดักจับการ Resized ของ Component
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    private void creatorButton(){
        String fontPath = "/font/punk kid.ttf";
       startButtonPanel = new MyButtonPanel("Start",true);
       creatorButtonPanel = new MyButtonPanel("Creator",true);
       exitButtonPanel = new MyButtonPanel("Exit",true);
       setMinMaxPanel = new MyButtonPanel("❑",false);

       startButton = startButtonPanel.getButton();
       creatorButton = creatorButtonPanel.getButton();
       exitButton = exitButtonPanel.getButton();
       
       startButtonPanel.setFont(fontPath);
       creatorButtonPanel.setFont(fontPath);
       exitButtonPanel.setFont(fontPath);

       add(startButtonPanel);
       add(creatorButtonPanel);
       add(exitButtonPanel);
       add(controlBar);
    }

    private void resizeComponents() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        int panelWidth = 325;
        int panelHeight = 92;

        int centerX = frameWidth / 2;
        int centerY = frameHeight / 2;

        int buttonWidth = 320;
        int buttonHeight = 87;

        //กำหนดตำแหน่งของ Panel
        startButtonPanel.setBounds(centerX - panelWidth / 2, (centerY - panelHeight * 2)+155, panelWidth,panelHeight);
        creatorButtonPanel.setBounds(centerX - panelWidth / 2, (centerY - panelHeight / 2)+155, panelWidth, panelHeight);
        exitButtonPanel.setBounds(centerX - panelWidth / 2, (centerY + panelHeight)+155, panelWidth, panelHeight);
        setMinMaxPanel.setLocation(frameWidth-55, 5);

        //กำหนดตำแหน่งของ Button
        startButton.setBounds(0, 0, buttonWidth,buttonHeight);
        creatorButton.setBounds(0, 0, buttonWidth, buttonHeight);
        exitButton.setBounds(0, 0, buttonWidth, buttonHeight);
    }
    private void buttonMovement(JButton button,JPanel panel,boolean check){
        int size=8;
            int location=3;
        if (check) {
            button.setBounds(button.getX()-location,button.getY()-location,button.getWidth()+size, button.getHeight()+size);
            panel.setBounds(panel.getX()-location,panel.getY()-location,panel.getWidth()+size, panel.getHeight()+size);
        }
        else
        {
            button.setBounds(button.getX()+location,button.getY()+location,button.getWidth()-size, button.getHeight()-size);
            panel.setBounds(panel.getX()+location,panel.getY()+location,panel.getWidth()-size, panel.getHeight()-size);
        }
    }
    
}