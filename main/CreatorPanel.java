package main;

import javax.swing.JPanel;

public class CreatorPanel extends JPanel{
    ControlBar controlBar;
    public CreatorPanel(GameWindow gameWindow){
        setLayout(null);
        controlBar = new ControlBar(gameWindow);
        add(controlBar);
    }

}