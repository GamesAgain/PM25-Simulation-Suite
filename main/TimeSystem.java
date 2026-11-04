package main;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeSystem {
    private static int day = 0;
    private static int hour = 0;
    private JLabel dayText;
    private JLabel hourText;
    private static final int REAL_SECOND_PER_VIRTUAL_HOUR = 500; // 0.5 วินาทีในมิลลิวินาที
    public static volatile boolean canRainny = true;
    Timer timer;
    public TimeSystem(GamePanel gamePanel){
        this.dayText = gamePanel.dayText;
        this.hourText = gamePanel.hourText;

        if (timer == null) {
            timer = new Timer(REAL_SECOND_PER_VIRTUAL_HOUR, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hour++;
                    if (hour == 24) {
                        hour = 0;
                        day++;
                    }
                    if (Math.random() <= 0.04&&canRainny&&gamePanel.canPlaneEvent&&!gamePanel.artIsEnabled) {//กำหนดโอกาสที่ฝนจะตก
                        gamePanel.rainEvent();
                        canRainny = false;
                        new Timer(5600, new ActionListener() {//ดีเลย์ฝนเมื่อตกไปแล้ว อีก 5 วิถึงจะสามารถมีโอกาสตกอีก
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                canRainny = true;
                            }
                        }).start();
                    }
                    dayText.setText(""+day);
                    hourText.setText(""+hour);
                }
            });
        }
    }
    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }
    public void stopRepeat(){
        timer.setRepeats(false);
    }
    public void restart(){
        stop();
        day = 0;
        hour = 0;
        dayText.setText("0");
        hourText.setText("0");
    }
}
