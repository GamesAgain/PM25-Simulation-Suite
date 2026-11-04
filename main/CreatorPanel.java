package main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreatorPanel extends JPanel{
    public CreatorPanel(){
        JLabel t1 = new JLabel(new ImageIcon("Creator\\FARM.gif"));
        JLabel t2 = new JLabel(new ImageIcon("Creator\\SARAYUTGAMES.png"));
        JLabel t3 = new JLabel(new ImageIcon("Creator\\SKYWAVE101.gif"));

        setLayout(null);
        t1.setBounds(350, 350, 200, 300);
        t2.setBounds(555, 345, 200, 300);
        t3.setBounds(760, 350, 200, 300);
        add(t1);
        add(t2);
        add(t3);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image backgroundImage = new ImageIcon("Creator\\CreatorBG.png").getImage();
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}