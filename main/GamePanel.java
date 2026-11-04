package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import font.SetFont;

public class GamePanel extends JPanel{
    private boolean stopEvent = false;
    private TimeSystem timeSystem;

    private boolean isStarted = false;//เช็คว่าเกมส์เริ่มอย่ไหม
    private Image checkInfoImage;
    private Cursor checkInfoCursor;
    private Cursor planeCursor;

    //กำหนดตัวแปร ปุ่มสถานะ
    private ImageIcon status0;
    private ImageIcon status1;
    private ImageIcon status2;
    private ImageIcon status3;
    private ImageIcon status4;
    private ImageIcon statusInfo0;
    private ImageIcon statusInfo1;
    private ImageIcon statusInfo2;
    private ImageIcon statusInfo3;
    private ImageIcon statusInfo4;
    private Image backgroundImage;

    //สร้าง Color
    private Color myDarkGrey = new Color(57, 68, 76);
    private Color myLightGrey = new Color(93, 108, 114,200);
    private Color myLightGrey0 = new Color(93, 108, 114);

    //กำหนดตัวแปร Panel ต่างๆ
    private JPanel infoJPanel;
    private JPanel tablePanel;
    private JPanel menuPanel;
    private JPanel eventPanel;
    private ControlBar controlPanel;

    //กำหนดตัวแปร Button และ TextArea ของปุ่ม ใน MenuPanel
    private ImageIcon openFileIcon;
    private ImageIcon simulateIcon;
    private ImageIcon stopIcon;
    private ImageIcon inputIcon;
    private ImageIcon eventIcon;
    private JPanel empty;
    private JLabel menuInput;
    private JLabel menuEvent;

    private JPanel inputDustArea;
    private JButton openFileButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton nextButton;
    private JButton planeButton;
    private JButton rainButton;
    private CardLayout menuLayout;
    private JPanel menuCard;

    //กำหนดตัวแปร Array ของ ปุ่มประชากรแต่พื้นที่
    private String statusShow[];
    private JButton populationฺBox[];
    private float dustLevel[];
    private int populationArray[];
    private float healthyArray[];
    private float patientArray[];
    private float percentArray[];

    //กำหนดตัวแปร  Components ใน Method createInputComponent
    private ImageIcon okIcon;
    private ImageIcon randomIcon;
    private JButton okButton;
    private JButton randomButton;
    private JTextField inputPopulation;

    //กำหนดตัวแปร  Components ใน Method createEventComponent
    private ImageIcon pauseIcon;
    private ImageIcon startIcon;
    private ImageIcon planeIcon;
    private ImageIcon rainIcon;
    public JLabel dayText;
    public JLabel hourText;

    //กำหนดตัวแปร Component ใน Method createMenuPanel
    private JTextField selectFileText;

    //กำหนดตัวแปร Component ใน Method createInfoPanel
    JLabel textJLabel;
    private ImageIcon statusInfomationIcon;
    private JLabel statusInfomation;

    private JLabel areaText;
    private JLabel dustText;
    private JLabel populationText;
    private JLabel healthyText;
    private JLabel patientText;
    private JLabel percentText;

    //กำหนดตัวแปร CardLayout 
    private CardLayout statusLayout;
    private JPanel statusCard;
    private JLabel statusCard0;
    private JLabel statusCard1;
    private JLabel statusCard2;
    private  JLabel statusCard3;
    private JLabel statusCard4;

    //ส่วนของ Animation
    private JLabel eventAnimation;
    private ImageIcon staticIcon; //ตึกเปล่าๆ
    private ImageIcon artificialrainIconlIcon;
    private ImageIcon rainyIcon;
    
    private File selectedFile;
    private int areaIndex;
    public boolean canRainny = true;//ทำให้ฝนตกได้หมด ทำไว้ให้มีดีเลย์ในการเรียกฝน
    public boolean canPlaneEvent = true;//ฝนเทียมติดคลูดาวน์ไหม
    private boolean isPause = false;
    public boolean artIsEnabled = false;

    private Timer planeAnimTimer;
    private Timer rainAnimTimer;

    public GamePanel(GameWindow gameWindow){
        //เพิ่มภาพลง Cursor
        checkInfoImage = new ImageIcon(getClass().getResource("/mouse/cursor/checkInfo.png")).getImage();
        checkInfoCursor = Toolkit.getDefaultToolkit().createCustomCursor(checkInfoImage, new Point(0, 0), "custom cursor");

        Image planeInfoImage = new ImageIcon(getClass().getResource("/mouse/cursor/planeCursor.png")).getImage();
        planeCursor = Toolkit.getDefaultToolkit().createCustomCursor(planeInfoImage, new Point(0, 0), "Plane cursor");

        //เพิ่มภาพ Status แต่ละแบบ
        status0 = new ImageIcon(getClass().getResource("/image/tableType/TYPE0.png"));
        status1 = new ImageIcon(getClass().getResource("/image/tableType/TYPE1.png"));
        status2 = new ImageIcon(getClass().getResource("/image/tableType/TYPE2.png"));
        status3 = new ImageIcon(getClass().getResource("/image/tableType/TYPE3.png"));
        status4 = new ImageIcon(getClass().getResource("/image/tableType/TYPE4.png"));

        //จอเล็ก
        statusInfo0 = new ImageIcon(getClass().getResource("/image/statusType/0.png"));
        statusInfo1 = new ImageIcon(getClass().getResource("/image/statusType/1.png"));
        statusInfo2 = new ImageIcon(getClass().getResource("/image/statusType/2.png"));
        statusInfo3 = new ImageIcon(getClass().getResource("/image/statusType/3.png"));
        statusInfo4 = new ImageIcon(getClass().getResource("/image/statusType/4.png"));
        statusInfomationIcon = new ImageIcon(getClass().getResource("/image/tableType/statusInformation.png"));

        backgroundImage = new ImageIcon(getClass().getResource("/image/GameBG.png")).getImage();

        setSize(gameWindow.width,gameWindow.height);
        setLayout(null);
        //สร้าง Panel ต่างๆ
        createInfoPanel(); //สร้าง Panel ของหน้าแสดงรายละเอียดฝั่งซ้ายสุด
        createTablePanel(); //สร้าง Panel ของหน้าแสดงตาราง 20*10
        createMenuPanel(); //สร้าง Panel ของหน้าเมนูฝั่งขวาสุด
        createEventPanel(); //สร้าง Panel ของหน้าเหตุการณ์ภายในเกม(Event)อยู่ล่างจอ
        createControlPanel(gameWindow); //สร้าง Panel เป็น Bar ข้างบนควมคุมการลดขยายจอ พักจอ และกลับไปหน้าเมนู

        //สร้าง Button
        createMenuButton(); //สร้าง ปุ่มที่หน้า Menu ฝั่งขวา
        setStartGame();//ตั้งค่า Default ของ Game
        populationฺButtonAction(); //เพิ่ม Action ให้ปุ่ม

        timeSystem = new TimeSystem(this);//สร้าง object ระบบเวลา
    }
    //Method สร้าง Panel Infomation ของประชากรในแต่ละช่อง
    private void createInfoPanel(){
        
        statusLayout = new CardLayout();
        statusCard = new JPanel(statusLayout);
        statusCard0 = new JLabel(statusInfo0);
        statusCard1 = new JLabel(statusInfo1);
        statusCard2 = new JLabel(statusInfo2);
        statusCard3 = new JLabel(statusInfo3);
        statusCard4 = new JLabel(statusInfo4);

        statusCard.add(statusCard0,"0");
        statusCard.add(statusCard1,"1");
        statusCard.add(statusCard2,"2");
        statusCard.add(statusCard3,"3");
        statusCard.add(statusCard4,"4");
        

        statusInfomation = new JLabel(statusInfomationIcon);
        

        infoJPanel = new JPanel();
        infoJPanel.setBounds(0, 30, 200, 900);
        infoJPanel.setLayout(null);
        infoJPanel.setBackground(myLightGrey);
        
        statusCard.setBounds(5, 5, infoJPanel.getWidth()-10, (int)(infoJPanel.getHeight()/2.5));

        areaText = new JLabel("null");
        dustText = new JLabel("null");
        populationText = new JLabel("null");
        healthyText = new JLabel("null");
        patientText = new JLabel("null");
        percentText = new JLabel("null");

        areaText.setForeground(Color.WHITE);
        areaText.setFont(new SetFont().getFont(12f));
        areaText.setBounds(135, 40, 80, 20);

        dustText.setForeground(Color.WHITE);
        dustText.setFont(new SetFont().getFont(12f));
        dustText.setBounds(95, 135, 80, 20);

        populationText.setForeground(Color.WHITE);
        populationText.setFont(new SetFont().getFont(12f));
        populationText.setBounds(85, 175, 80, 20);

        healthyText.setForeground(Color.WHITE);
        healthyText.setFont(new SetFont().getFont(12f));
        healthyText.setBounds(100, 215, 80, 20);

        patientText.setForeground(Color.WHITE);
        patientText.setFont(new SetFont().getFont(12f));
        patientText.setBounds(85, 255, 80, 20);

        percentText.setForeground(Color.WHITE);
        percentText.setFont(new SetFont().getFont(12f));
        percentText.setBounds(135, 295, 80, 20);

        textJLabel = new JLabel();
        textJLabel.setBounds(5, 5, infoJPanel.getWidth()-10, (int)(infoJPanel.getHeight()/2.5));
        //เพิ่ม text บนหน้าาโชว์รายละเอียด
        textJLabel.add(areaText);
        textJLabel.add(dustText);
        textJLabel.add(populationText);
        textJLabel.add(healthyText);
        textJLabel.add(patientText);
        textJLabel.add(percentText);

        statusInfomation.setBackground(Color.BLACK);//ใช้บอกว่าแต่ละสีมีคนป่วยกี่ %
        statusInfomation.setBounds(5, statusCard.getHeight()+10, infoJPanel.getWidth()-10, (int)(infoJPanel.getHeight()/2.5-45));

        infoJPanel.add(textJLabel);
        infoJPanel.add(statusCard);
        infoJPanel.add(statusInfomation);

        statusLayout.show(statusCard, "0");
        add(infoJPanel);
    }
    //Method สร้าง Panel ตาราง 20*10 
    private void createTablePanel(){
        populationฺBox = new JButton[200];
        for (int i = 0; i < populationฺBox.length; i++) {
            populationฺBox[i]=new JButton("",status0);
        }
        tablePanel = new JPanel();
        tablePanel.setBounds(205, 30 , 870, 528);
        tablePanel.setLayout(new GridLayout(10,20,5,5));
        tablePanel.setBackground(myLightGrey);
        
        add(tablePanel);
    }
    //Method สร้าง Panel menu ควบคุมโปรแกรมต่างๆ Start,Select FIle
    private void createMenuPanel(){
        menuPanel = new JPanel();
        menuPanel.setBounds(1080, 30, 285, 900);
        menuPanel.setLayout(null);
        menuPanel.setBackground(myLightGrey);
        add(menuPanel);
    }
    //Method สร้าง Panel Event ใช้แสดงหน้า เหตุการณ์ต่างๆ เช่น ฝนเทียม,ฝนธรรมชาติ
    private void createEventPanel(){

        staticIcon = new ImageIcon(getClass().getResource("/Event/StaticEvent.png"));
        artificialrainIconlIcon = new ImageIcon(getClass().getResource("/Event/PlaneEventGif.gif"));
        rainyIcon = new ImageIcon(getClass().getResource("/Event/RainEventGif.gif"));

        eventAnimation = new JLabel(staticIcon);//ตั้งให้เป็นภาพนิ่ง 
        
        eventAnimation.setBounds(5, 5 , 860, 150);

        eventPanel = new JPanel();
        eventPanel.setBounds(205, 563 , 870, 300);
        eventPanel.setLayout(null);
        eventPanel.setBackground(myLightGrey);
        eventPanel.add(eventAnimation);
        add(eventPanel);
    }
    //Method สร้าง Panel Control bar ด้านบนใช้ กลับไปหน้า Memu ขยาย/ย่อจอ และพับจอ
    private void createControlPanel(GameWindow gameWindow){
        controlPanel = new ControlBar(gameWindow);
        add(controlPanel);
    }
    //Method ใส่ Background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    //Method สร้าง Button ของ MenuPanel Inputs,Select File,StartButton,Clear(stop)
    private void createMenuButton(){
        int frameWidth = menuPanel.getWidth();
        menuLayout = new CardLayout();
        menuCard = new JPanel(menuLayout);
        
        
        //นำ Icon แต่ละปุ่มเข้ามา
        openFileIcon = new ImageIcon(getClass().getResource("/button/OpenFileButton.png"));
        simulateIcon = new ImageIcon(getClass().getResource("/button/StartButton.png"));
        stopIcon = new ImageIcon(getClass().getResource("/button/StopButton.png"));

        inputIcon = new ImageIcon(getClass().getResource("/image/menuCard/inputEvent.png"));
        eventIcon = new ImageIcon(getClass().getResource("/image/menuCard/menuEvent.png"));

        //สร้างปุ่ม Start,Stop,Open File
        openFileButton = new JButton(openFileIcon);
        startButton = new JButton(simulateIcon);
        stopButton = new JButton(stopIcon);
        
        menuCard.setBounds(5, 300, 190, 385);
        menuCard.setBackground(myLightGrey0);

        //ทำหน้าไว้สลับใช้งาน
        empty = new JPanel();
        menuInput = new JLabel(inputIcon);
        menuEvent = new JLabel(eventIcon);
        empty.setOpaque(false);

        //เรียกใช้สร้าง Component ของ Event Panel
        createEventComponent(menuEvent);
        createInputComponent(menuInput);


        selectFileText = new JTextField("Select File");
        selectFileText.setBounds(20,0,frameWidth-115,35);
        selectFileText.setFont(new SetFont().getFont(18));
        selectFileText.setForeground(myLightGrey);
        selectFileText.setEditable(false); 
        selectFileText.setOpaque(false); 
        selectFileText.setBorder(null);

        inputDustArea = new JPanel();
        inputDustArea.setLayout(null);
        inputDustArea.setBounds(15,30,frameWidth-115,30);
        inputDustArea.setBackground(Color.LIGHT_GRAY);
        inputDustArea.add(selectFileText);


        //ตั้งตำแหน่งปุ่ม
        openFileButton.setBounds(15, 75, 170, 50);
        startButton.setBounds(15, 150, 170, 50);
        stopButton.setBounds(15, 210, 170, 50);

        menuCard.add(empty,"Empty");
        menuCard.add(menuInput,"Input");
        menuCard.add(menuEvent,"Event");
        menuLayout.show(menuCard, "Empty");

        menuPanel.add(menuCard);
        menuPanel.add(inputDustArea);
        menuPanel.add(openFileButton);
        menuPanel.add(startButton);
        menuPanel.add(stopButton);

        openFileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isStarted) {
                    openFile();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please STOP to OPEN FILE Again", "Warning",JOptionPane.WARNING_MESSAGE);
                }
                
            }
            
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isStarted) {
                    if (!selectFileText.getText().equals("Select File")) {
                        readFile(selectedFile);
                        menuLayout.show(menuCard, "Input");//โชว์หน้า Input
                        isStarted=true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please select file", "Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please STOP to SIMULATE Again", "Warning",JOptionPane.WARNING_MESSAGE);
                }
                
                
            }
            
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();//reset เกมส์
            }
            
        });
    }
    //สร้างองค์ประกอบภายใน Panel Input ช่องใส่ประชากร,ปุ่มเริ่ม,ปุ่มสุ่มประชากร
    private void createInputComponent(JLabel menuInput){
        //สร้าง object Components
        okIcon = new ImageIcon(getClass().getResource("/button/OkButton.png"));
        randomIcon = new ImageIcon(getClass().getResource("/button/RandomButton.png"));
        okButton = new JButton(okIcon);
        randomButton = new JButton(randomIcon);
        inputPopulation = new JTextField();

        inputPopulation.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int population = Integer.parseInt(inputPopulation.getText());
                    if (population>0) {
                        startGame(population,false);
                    menuLayout.show(menuCard, "Event");
                    timeSystem.start();//เริ่มเวลาในเกมส์
                    stopEvent = false;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please enter population from one or more.", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (NumberFormatException x) {
                    if (inputPopulation.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Please enter the population", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please enter the population as an integer.", "Input Error",JOptionPane.ERROR_MESSAGE);
                    } 
                }
            }
            
        });

        okButton.setBounds(153, 95, 26, 17);
        randomButton.setBounds(12, 123, 165, 40);
        inputPopulation.setBounds(80, 94, 75, 22);
        inputPopulation.setBackground(new Color(128, 146, 153));
        inputPopulation.setFont(new SetFont().getFont(14f));
        inputPopulation.setForeground(Color.WHITE);
        inputPopulation.setBorder(null);

        menuInput.add(okButton);
        menuInput.add(randomButton);
        menuInput.add(inputPopulation);

        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int population = Integer.parseInt(inputPopulation.getText());
                    if (population>0) {
                        startGame(population,false);
                    menuLayout.show(menuCard, "Event");
                    timeSystem.start();//เริ่มเวลาในเกมส์
                    stopEvent = false;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please enter population from one or more.", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (NumberFormatException x) {
                    if (inputPopulation.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"Please enter the population", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please enter the population as an integer.", "Input Error",JOptionPane.ERROR_MESSAGE);
                    } 
                }
                
            }
            
        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int max=0;
                int min=0;
                String inMin="0";
                String inMax="0";
                do {
                    try {
                        inMin = JOptionPane.showInputDialog(null,"Set the starting point :","Please define the population range.",JOptionPane.QUESTION_MESSAGE);
                        if (inMin==null) {
                            break;
                        }
                        min=Integer.parseInt(inMin);
                        if (min>0) {
                            do {
                                try {
                                    inMax = JOptionPane.showInputDialog(null,"Set the ending point :","Please define the population range.",JOptionPane.QUESTION_MESSAGE);
                                    if (inMax==null) {
                                        break;
                                    }
                                    max=Integer.parseInt(inMax);
                                    if (max>0) {
                                        if (max>=min) {
                                            for (int i = 0; i < dustLevel.length; i++) {
                                                populationArray[i]=random.nextInt(max - min + 1) + min;
                                            }
                                            startGame(0,true);
                                            menuLayout.show(menuCard, "Event");
                                            timeSystem.start();//เริ่มเวลาในเกมส์
                                            stopEvent = false;
                                            break;
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(null,"The ending point should be more than the starting point.", "Input Error",JOptionPane.ERROR_MESSAGE);
                                            break;
                                        }
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null,"Please enter population from one or more.", "Input Error",JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NumberFormatException x) {
                                    JOptionPane.showMessageDialog(null,"Please enter the population as an integer.", "Input Error",JOptionPane.ERROR_MESSAGE);
                                }
                            } while (inMax!=null);
                            break;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Please enter population from one or more.", "Input Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(null,"Please enter the population as an integer.", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                } while (inMin!=null);
            }
            
        });
    }
    //สร้างองค์ประกอบภายใน Panel Event ปุ่มฝนเทียม,ปุ่มฝนธรรมชาติ,ปุ่มข้ามวัน,ตัวเลขแสดงวันและชั่วโมง
    private void createEventComponent(JLabel menuEvent){

        pauseIcon = new ImageIcon(getClass().getResource("/button/PauseIcon.png"));
        startIcon = new ImageIcon(getClass().getResource("/button/StartIcon.png"));
        planeIcon = new ImageIcon(getClass().getResource("/button/PlaneButton.png"));
        rainIcon = new ImageIcon(getClass().getResource("/button/RainnyButton.png"));

        //แสดง Day ในเกมส์
        dayText = new JLabel("0");
        dayText.setForeground(Color.WHITE);
        dayText.setFont(new SetFont().getFont(16f));
        dayText.setBounds(104, 60, 50, 30);

        //แสดง Hour ในเกมส์
        hourText = new JLabel("0");
        hourText.setForeground(Color.WHITE);
        hourText.setFont(new SetFont().getFont(10f));
        hourText.setBounds(145, 60, 50, 30);

        nextButton = new JButton(pauseIcon);
        planeButton = new JButton(planeIcon);
        rainButton = new JButton(rainIcon);

        //ตั้งตำแหน่งปุ่ม
        nextButton.setBounds(39, 330, 115, 40);
        planeButton.setBounds(25, 99, 144, 69);
        planeButton.setBorderPainted(false);
        planeButton.setBackground(new Color(127,146,153));
        rainButton.setBounds(24, 199, 146, 120);
        rainButton.setBorderPainted(false);
        rainButton.setBackground(new Color(127,146,153));


        menuEvent.add(dayText);
        menuEvent.add(hourText);
        menuEvent.add(rainButton);
        menuEvent.add(planeButton);
        menuEvent.add(nextButton);

        rainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPause) { 
                    if (canRainny&&canPlaneEvent) {
                        rainEvent();
                    }
                    else if (canRainny&&!canPlaneEvent) {
                        JOptionPane.showMessageDialog(null,"Artificial rain is falling", "CD",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"It's raining", "CD",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please PAUSE the time before USE Rainny Event.", "WARNING",JOptionPane.WARNING_MESSAGE);
                }
                
                
            }
            
        });

        planeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (canPlaneEvent&&canRainny) {
                    for (int i = 0; i < populationฺBox.length; i++) {
                        populationฺBox[i].setCursor(planeCursor);
                    }
                    artIsEnabled = true;
                }
                else if (canPlaneEvent&&!canRainny) {
                    JOptionPane.showMessageDialog(null,"It's rainning", "CD",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Artificial rain is falling", "CD",JOptionPane.WARNING_MESSAGE);
                }
            }
            
        });

        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPause) {
                    isPause = false;
                    timeSystem.start();
                    nextButton.setIcon(pauseIcon);
                }
                else
                {
                    isPause = true;
                    timeSystem.stop();
                    nextButton.setIcon(startIcon);
                }
               
            }
            
        });
    }
    private void openFile(){
        //ดึงตำแหน่งที่อยู่ไฟล์ปัจจุบัน
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir")); //เป็น Object ใช้เลือกไฟล์
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile.getName().endsWith(".txt")) {//เช็คว่าใช่ไฟล์ สกุล .txt ไหม
                        readFile(selectedFile);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"The file is not supported,\nPlease input the .txt file.", "Input Error",JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
    }
    private void readFile(File file) {
        //เป็น object ใช้อ่านไฟล์
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line,data="";
            while ((line = reader.readLine()) != null) {    //ยังมีบรรทัดข้อมูลต่อไปไหม ถ้ามีทำงานต่อ
                content.append(line).append("\n");
            }
            data = content.toString();//แปลง เป็น String
            saveData(data);
        } catch (IOException e) {

        }
    }
    private void saveData(String data){
        try {
            statusShow = new String[dustLevel.length];
            populationArray = new int[dustLevel.length];
            healthyArray = new float[dustLevel.length];
            patientArray = new float[dustLevel.length];
            percentArray = new float[dustLevel.length];
        } catch (NullPointerException e) {}
        selectFileText.setText(selectedFile.getName());
        String[] stringArray = data.split("\\s+");
        try {
        dustLevel = new float[stringArray.length]; // สร้าง array ของ int เพื่อเก็บค่าที่แปลงแล้ว        
        for (int i = 0; i < stringArray.length; i++) { // แปลง string แต่ละตัวเป็น int แล้วเก็บใน int array
            if (stringArray[i].isEmpty()==false) {
                dustLevel[i] = Float.parseFloat(stringArray[i]);//มี String ที่เป็น Empty String มั้ย
            }
        }
        haveData();//เรียก Method เช็คว่ามีข้อมูลเข้ามายัง
        } catch (NumberFormatException e) {
            selectFileText.setText("Select File");
            JOptionPane.showMessageDialog(null,"The input should be numbers.", "Input Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    //เช็คว่ามีข้อมูลไหม
    private boolean haveData(){
        return true;
    }

    //กำหนดตารางตอนเริ่มที่ยังไม่มีข้อมูล
    private void setStartGame(){
        for (int i = 0; i < populationฺBox.length; i++) {
            populationฺBox[i].setIcon(status0);
            populationฺBox[i].setBackground(myDarkGrey);
            populationฺBox[i].setBorderPainted(false);
            populationฺBox[i].setFocusPainted(false);
            populationฺBox[i].setCursor(checkInfoCursor);
            tablePanel.add(populationฺBox[i]);
        }
    }
    public void restartGame(){
        stopEvent = true;
        if (planeAnimTimer!=null) {
            if (planeAnimTimer.isRunning()) {
                planeAnimTimer.stop();
            }
        }
        if (rainAnimTimer!=null) {
            if (rainAnimTimer.isRunning()) {
                rainAnimTimer.stop();
            }
        }
        //กำหนดเป็นค่าเริ่มต้น 
        canRainny = true;
        canPlaneEvent = true;
        isPause = false;
        artIsEnabled = false;
        isStarted = false;

        menuLayout.show(menuCard, "Empty");
        timeSystem.restart();//reset เวลา
        eventAnimation.setIcon(staticIcon);
        statusLayout.show(statusCard, "0");
        areaText.setText("null");
        dustText.setText("null");
        populationText.setText("null");
        healthyText.setText("null");
        patientText.setText("null");
        percentText.setText("null");
        nextButton.setIcon(pauseIcon);
        try {
            for (int i = 0; i < dustLevel.length; i++) {
                statusShow[i]="0";
                populationฺBox[i].setIcon(status0);
                populationฺBox[i].setBackground(myDarkGrey);
                dustLevel[i]=0;
                populationArray[i]=0;
                healthyArray[i]=0;
                patientArray[i]=0;
                percentArray[i]=0;
            }
            for (int i = 0; i < populationฺBox.length; i++) {
                populationฺBox[i].setBackground(myDarkGrey);
            }
        } catch (NullPointerException e) {}
        this.revalidate();
        this.repaint();
    }

    //Method เริ่มการจำลอง
    private void startGame(int population,boolean isRandom){ 
        //สร้าง object Array ค่าต่างๆภายในเกมส์

        if (!isRandom) {
            for (int i = 0; i < dustLevel.length; i++) {
                populationArray[i]=population;
            }
        }
        paintGame(populationArray);
        try {
            for (int i = 0; i < dustLevel.length; i++) {
            ImageIcon status = status0;
            try {
                if (statusShow[i].equals("0")) {
                    status = status0;
                }
                else if (statusShow[i].equals("1")) {
                    status = status1;
                }
                else if (statusShow[i].equals("2")) {
                    status = status2;
                }
                else if (statusShow[i].equals("3")) {
                    status = status3;
                }
                else if (statusShow[i].equals("4")) {
                    status = status4;
                }
            } catch (NullPointerException e) {}
            
            populationฺBox[i].setIcon(status);
            populationฺBox[i].setBackground(myDarkGrey);
            populationฺBox[i].setBorderPainted(false);
            populationฺBox[i].setFocusPainted(false);
        }
        } catch (ArrayIndexOutOfBoundsException e) {
            //ข้อมูลเกิน 200
            System.out.println("ข้อมูลเกิน 200 ปุ่ม");
        }
        
    }
    private void paintGame(int populationArray[]){
        String checkStatus = "0";
        try {
            
        } catch (Exception e) {
        }
       for (int i = 0; i < dustLevel.length; i++) {
            if (dustLevel[i]>=0&&dustLevel[i]<=250) {
                if (dustLevel[i] >= 0 && dustLevel[i] <= 50) {
                    checkStatus = "1";
                    int minPercentage = 0;//เปอร์เซ็นต์ต่ำสุด
                    int maxPercentage = 9;//เปอร์เซ็นต์สูงสุด
    
                    // สร้าง Random object เพื่อสุ่มค่าเปอร์เซ็นต์
                    Random random = new Random();
                    percentArray[i] = random.nextInt(maxPercentage - minPercentage + 1) + minPercentage;
    
                    // คำนวณจำนวนคนที่ป่วย
                    patientArray[i] = (int) ((percentArray[i] / 100.0) * populationArray[i]);
                    healthyArray[i] = populationArray[i]-patientArray[i];
                    statusShow[i]=checkStatus; //เก็บว่าจะให้แสดงสีไหน
                }
                else if (dustLevel[i] >= 51 && dustLevel[i] <= 100) {
                    checkStatus = "2";
                    int minPercentage = 10;//เปอร์เซ็นต์ต่ำสุด
                    int maxPercentage = 19;//เปอร์เซ็นต์สูงสุด
    
                    // สร้าง Random object เพื่อสุ่มค่าเปอร์เซ็นต์
                    Random random = new Random();
                    percentArray[i] = random.nextInt(maxPercentage - minPercentage + 1) + minPercentage;
    
                    // คำนวณจำนวนคนที่ป่วย
                    patientArray[i] = (int) ((percentArray[i] / 100.0) * populationArray[i]);
                    healthyArray[i] = populationArray[i]-patientArray[i];
                    statusShow[i]=checkStatus; //เก็บว่าจะให้แสดงสีไหน
                }
                else if (dustLevel[i] >= 101 && dustLevel[i] <= 150) {
                    checkStatus = "3";
                    int minPercentage = 20;//เปอร์เซ็นต์ต่ำสุด
                    int maxPercentage = 29;//เปอร์เซ็นต์สูงสุด
    
                    // สร้าง Random object เพื่อสุ่มค่าเปอร์เซ็นต์
                    Random random = new Random();
                    percentArray[i] = random.nextInt(maxPercentage - minPercentage + 1) + minPercentage;
    
                    // คำนวณจำนวนคนที่ป่วย
                    patientArray[i] = (int) ((percentArray[i] / 100.0) * populationArray[i]);
                    healthyArray[i] = (int)populationArray[i]-patientArray[i];
                    statusShow[i]=checkStatus; //เก็บว่าจะให้แสดงสีไหน
                }
                else if (dustLevel[i] >= 151 && dustLevel[i] <= 250) {
                    checkStatus = "4";
                    int minPercentage = 30;//เปอร์เซ็นต์ต่ำสุด
                    int maxPercentage = 50;//เปอร์เซ็นต์สูงสุด
    
                    // สร้าง Random object เพื่อสุ่มค่าเปอร์เซ็นต์
                    Random random = new Random();
                    percentArray[i] = random.nextInt(maxPercentage - minPercentage + 1) + minPercentage;
    
                    // คำนวณจำนวนคนที่ป่วย
                    patientArray[i] = (int) ((percentArray[i] / 100.0) * populationArray[i]);
                    healthyArray[i] = populationArray[i]-patientArray[i];
                    statusShow[i]=checkStatus; //เก็บว่าจะให้แสดงสีไหน
                }
            }
            
       }
    }
    private void populationฺButtonAction(){
        for (int i = 0; i < populationฺBox.length; i++) {
            final int index = i;
            populationฺBox[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (artIsEnabled) {
                        artIsEnabled=false;
                        planeEvent(index);
                    }
                    else
                    {
                        try {
                            showInfomation(index);
                        } catch (NullPointerException n) {
                            // ดักจับตอนคลิปเช็คตอนไม่มีข้อมูล
                        }
                    }
                    
                }
            });
        }
    }
    private void setAreaIndex(int i){
        this.areaIndex = i;
    }
    private int getAreaIndex(){
        return this.areaIndex;
    }
    private void showInfomation(int index){
        setAreaIndex(index);
        areaText.setText(""+(index+1));
        try {
            dustText.setText(""+(int)dustLevel[index]);
            populationText.setText(""+(int)populationArray[index]);
            healthyText.setText(""+(int)healthyArray[index]);
            patientText.setText(""+(int)patientArray[index]);
            percentText.setText(""+(int)percentArray[index]+"%");
            if (dustLevel[index]>=0&&dustLevel[index]<=250) {
                statusLayout.show(statusCard, statusShow[index]);
            }
            else
            {
                statusLayout.show(statusCard, "0");
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
            dustText.setText("null");
            populationText.setText("null");
            healthyText.setText("null");
            patientText.setText("null");
            percentText.setText("null");
            statusLayout.show(statusCard, "0");
        }
        
        repaint();
    } 
    public void rainEvent(){
        canRainny = false;
        artIsEnabled = false;
        for (int i = 0; i < populationฺBox.length; i++) {
            populationฺBox[i].setCursor(checkInfoCursor);
        }
        //เมื่อเวลาผ่านไป 2.5 วิ จะปิด Gif
        eventAnimation.setIcon(rainyIcon);
        repaint();
        rainAnimTimer = new Timer(5500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stopEvent) {
                    eventAnimation.setIcon(staticIcon);
                    for (int i = 0; i < dustLevel.length; i++) {
                        if (dustLevel[i]>=0&&dustLevel[i]<=250) {
                            dustLevel[i]=dustLevel[i]-50;
                            if (dustLevel[i]<0) {
                                dustLevel[i]=0;
                            }
                        }
                    }
                    updateGame();
                    showInfomation(getAreaIndex());
                    canRainny = true;
                }
            }
        });
        rainAnimTimer.setRepeats(false); // ตั้งค่าให้ทำงานครั้งเดียว
        rainAnimTimer.start();
    }
    private void planeEvent(int index){
        final int GRID_WIDTH = 20;  // จำนวนคอลัมน์
        final int GRID_HEIGHT = 10; // จำนวนแถว
        final Color SELECTED_COLOR = Color.CYAN;
        final Color NEIGHBOR_COLOR = Color.GREEN;
        eventAnimation.setIcon(artificialrainIconlIcon);
        int x = index % GRID_WIDTH;
        int y = index / GRID_WIDTH;

        populationฺBox[index].setBackground(SELECTED_COLOR);//ตัวปุ่มที่กด
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < GRID_WIDTH && ny >= 0 && ny < GRID_HEIGHT) {
                    int neighborIndex = ny * GRID_WIDTH + nx;
                    populationฺBox[neighborIndex].setBackground(NEIGHBOR_COLOR);//ตัวปุ่มรอบๆ 
                }
            }
        }

        for (int i = 0; i < populationฺBox.length; i++) {
            populationฺBox[i].setCursor(checkInfoCursor);
        }

        repaint();
        canPlaneEvent = false;
        planeAnimTimer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stopEvent) {
                    eventAnimation.setIcon(staticIcon);
                    try {     
                        populationฺBox[index].setBackground(myDarkGrey);
                        if (dustLevel[index] >= 0 && dustLevel[index] <=250) {
                            dustLevel[index] = dustLevel[index]-(dustLevel[index]*50/100); //ปุ่มที่กดลด 50%
                        }
                    } catch (ArrayIndexOutOfBoundsException x) {
                        populationฺBox[index].setBackground(myDarkGrey);
                    }
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            if (dx == 0 && dy == 0) continue;

                            int nx = x + dx;
                            int ny = y + dy;

                            if (nx >= 0 && nx < GRID_WIDTH && ny >= 0 && ny < GRID_HEIGHT) {
                                int neighborIndex = ny * GRID_WIDTH + nx;
                                
                                try {
                                    populationฺBox[neighborIndex].setBackground(myDarkGrey);
                                    if (dustLevel[neighborIndex] >= 0 && dustLevel[neighborIndex] <=250) {
                                        dustLevel[neighborIndex] = dustLevel[neighborIndex]-(dustLevel[neighborIndex]*30/100);//ปุ่มรอบๆลด 30%
                                    }
                                } catch (ArrayIndexOutOfBoundsException x) {
                                    populationฺBox[neighborIndex].setBackground(myDarkGrey);
                                }
                            }
                        }
                    }
                    tablePanel.revalidate();
                    tablePanel.repaint();
                    updateGame();
                    showInfomation(getAreaIndex());
                    canPlaneEvent = true;
                }
            }
        });
        planeAnimTimer.setRepeats(false);// ตั้งค่าให้ทำงานครั้งเดียว
        planeAnimTimer.start();
    }
    public void updateGame(){
        paintGame(populationArray);
        for (int i = 0; i < dustLevel.length; i++) {
            ImageIcon status = status0;
            try {
                if (statusShow[i].equals("0")) {
                    status = status0;
                }
                else if (statusShow[i].equals("1")) {
                    status = status1;
                }
                else if (statusShow[i].equals("2")) {
                    status = status2;
                }
                else if (statusShow[i].equals("3")) {
                    status = status3;
                }
                else if (statusShow[i].equals("4")) {
                    status = status4;
                }
            } catch (NullPointerException e) {
                status = status0;
            }
            populationฺBox[i].setIcon(status);
        }
    }
}