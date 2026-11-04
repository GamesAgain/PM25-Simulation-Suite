package main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import font.SetFont;

public class MyButtonPanel extends JPanel{
    private JButton button = new JButton();
    private Color myDarkGrey = new Color(57, 68, 76);
    private Color myLightGrey = new Color(93, 108, 114);
    
    public MyButtonPanel(){
        createrComponent();
        setBackground(myDarkGrey);
        button.setBackground(myLightGrey);
    }
    public MyButtonPanel(String name,boolean setFont){
        createrComponent(name,setFont);
        setBackground(myDarkGrey);
        button.setBackground(myLightGrey);
    }
    public MyButtonPanel(String name,int x,int y,int width,int height,boolean setFont){
        createrComponents(name, x, y, width, height,setFont);
        setBackground(myDarkGrey);
        button.setBackground(myLightGrey);
    }

    public MyButtonPanel(String name,int x,int y,int width,int height,Color color,Color fontColor,boolean setFont){
        int r=color.getRed()-20;
        int g=color.getGreen()-20;
        int b=color.getBlue()-20;
        if (r<0) {
            r=0;
        }
        if (g<0) {
            g=0;
        }
        if (b<0) {
            b=0;
        }
        Color firstColor = new Color(color.getRed(), color.getGreen(), color.getBlue());
        Color secondColor = new Color(r, g, b);
        createrComponents(name, x, y, width, height,setFont);
        setBackground(secondColor);
        button.setBackground(firstColor);
        button.setForeground(fontColor);
    }

    protected void createrComponents(String name,int x,int y,int width,int height,boolean setFont){
        if (setFont) {
            button.setFont(new SetFont().getFont());
        } 
        setBounds(x, y, width+5, height+5);
        setLayout(null);
        button.setText(name);
        button.setBounds(0, 0, width, height);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        add(button);
    }

    protected void createrComponent(String name,boolean setFont){
        if (setFont) {
            button.setFont(new SetFont().getFont());
        }
        button.setText(name);
        button.setBounds(0,0,100, 50);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        setSize(button.getWidth()+5, button.getHeight()+5);
        setLayout(null);
        add(button);
    }
    protected void createrComponent(boolean setFont){
        if (setFont) {
            button.setFont(new SetFont().getFont());
        }
        button.setBounds(0,0,100, 50);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        setSize(button.getWidth()+5, button.getHeight()+5);
        setLayout(null);
        add(button);
    }
    protected void createrComponent(){
        button.setBounds(0,0,100, 50);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new SetFont().getFont());
        setSize(button.getWidth()+5, button.getHeight()+5);
        setLayout(null);
        add(button);
    }
    public JButton getButton(){
        return this.button;
    }
    public JButton getButton(String name){
        this.button.setText(name);
        return this.button;
    }
    public JButton setFontSize(float size){
        button.setFont(new SetFont().getFont(size));
        return this.button;
    }
    public JButton setFont(String fontPath){
        button.setFont(new SetFont().getFont(fontPath));
        return this.button;
    }
    public JButton setFont(float size,String fontPath){
        button.setFont(new SetFont().getFont(size,fontPath));
        return this.button;
    }
}