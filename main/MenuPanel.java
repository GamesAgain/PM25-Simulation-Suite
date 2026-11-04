package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{

    //สร้าง Object รูปภาพพื้นหลัง
    private ImageIcon backgroundImageIcon = new ImageIcon("image\\MenuBG.png");
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
    private JButton setMinMaxButton;
    
    public MenuPanel(GameWindow gameWindow){
        setLayout(null);
        creatorButton();
        add(startButtonPanel);
        add(creatorButtonPanel);
        add(exitButtonPanel);
        add(setMinMaxPanel);

        //เพิ่ม ComponentListener ดักจับการ Resized ของ Component
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
        
    }

    // //ตั้งรูป Background ของ MenuPanel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    //Method สร้างปุ่ม
    private void creatorButton(){
        String fontPath = "font\\punk kid.ttf";
         //สร้าง Object Panel
       startButtonPanel = new MyButtonPanel("Start",true);
       creatorButtonPanel = new MyButtonPanel("Creator",true);
       exitButtonPanel = new MyButtonPanel("Exit",true);
       setMinMaxPanel = new MyButtonPanel("❑",false);

        //สร้าง Object Button
       startButton = startButtonPanel.getButton();
       creatorButton = creatorButtonPanel.getButton();
       exitButton = exitButtonPanel.getButton();
       setMinMaxButton = setMinMaxPanel.getButton();
       setMinMaxPanel.setBounds(1225, 5, 47, 47);
       setMinMaxButton.setBounds(0, 0, 45, 45);
       
        //กำหนด Font ของ Button
       startButtonPanel.setFont(fontPath);
       creatorButtonPanel.setFont(fontPath);
       exitButtonPanel.setFont(fontPath);
       
       
       

     
    }

    //Method ปรับขนาด Components ให้เหมาะสมกับขนาดหน้าจอ
    private void resizeComponents() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        int panelWidth = (frameWidth / 4)+5;
        int panelHeight = (int)(frameHeight / 8.235)+5;

        int centerX = frameWidth / 2;
        int centerY = frameHeight / 2;

        int buttonWidth = frameWidth / 4;
        int buttonHeight = (int)(frameHeight / 8.235);

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
}