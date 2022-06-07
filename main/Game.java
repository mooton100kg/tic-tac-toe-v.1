package main;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable{
    
    private GameScreen gameScreen;
    private Playing playing;
    private Thread gameThread;

    private double FPS_SET = 60.0;
    private double UPS_SET = 60.0;

    public Game(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initClasses();

        add(gameScreen);
        pack();

        setVisible(true);
    }

    public void initClasses(){
        gameScreen = new GameScreen(this);
        playing = new Playing(this);
    }

    public void start(){
        gameThread = new Thread(this){};
        gameThread.start();
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.gameScreen.initInput();
        game.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        long lastTimeCheck = System.currentTimeMillis();

        int frame = 0;
        int update = 0;

        long now;

        while (true){
            now = System.nanoTime();

            //render
            if (now - lastFrame >= timePerFrame){
                repaint();
                lastFrame = now;
                frame++;
            }

            //update
            if (now - lastUpdate >= timePerUpdate){
                lastUpdate = now;
                update++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frame + " | UPS: " + update);
                frame = 0;
                update = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    public Playing getPlaying(){
        return playing;
    }
}