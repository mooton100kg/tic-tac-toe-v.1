package main;

import javax.swing.JPanel;

import input.MyMouseListener;
import java.awt.Graphics;
import java.awt.Dimension;

public class GameScreen extends JPanel{
    private Dimension size;
    private Game game;
    

    private MyMouseListener myMouseListener;

    public GameScreen(Game game){
        setPanalSize();
        this.game = game;
    }

    private void setPanalSize(){
        size = new Dimension(300,350);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getPlaying().render(g);
    }

    public void initInput(){
        myMouseListener = new MyMouseListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);

        requestFocus();
    }
}
