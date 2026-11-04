package main;

public class Game extends Thread{
    private GameWindow gameWindow;
    public Game(){
        gameWindow = new GameWindow();
        gameWindow.setVisible(true);
    }
    @Override
    public void run() {
        while (true) {
            this.start();
        }
        
    }
}
