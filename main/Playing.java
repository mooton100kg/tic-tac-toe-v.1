package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import ui.MyButton;

public class Playing {
    private ArrayList<Integer> player1po = new ArrayList<>();
    private ArrayList<Integer> player2po = new ArrayList<>();
    private ArrayList<MyButton> button = new ArrayList<>();
    private int player = 1;
    private String winner = "none";
    private boolean gameover = false;

    public Playing(Game game){
        initButton();
    }
    private void initButton() {
        button.add(new MyButton(0, 50, 100, 100,0));
        button.add(new MyButton(100, 50, 100, 100,1));
        button.add(new MyButton(200, 50, 100, 100,2));
        button.add(new MyButton(0, 150, 100, 100,3));
        button.add(new MyButton(100, 150, 100, 100,4));
        button.add(new MyButton(200, 150, 100, 100,5));
        button.add(new MyButton(0, 250, 100, 100,6));
        button.add(new MyButton(100, 250, 100, 100, 7));
        button.add(new MyButton(200, 250, 100, 100,8));
    }

    public void render(Graphics g){
        if (!(gameover)){
            g.setColor(Color.black);
            //line
            g.drawLine(100, 70, 100, 330);
            g.drawLine(200, 70, 200, 330);
            g.drawLine(30, 150, 270, 150);
            g.drawLine(30, 250, 270, 250);
            g.setFont(new Font("hooge 05_55", Font.PLAIN, 25)); 
            g.drawString("Player: " + player, 10,45);
            g.setFont(new Font("hooge 05_55", Font.PLAIN, 15)); 
            g.drawString("Previuse winner: " + winner, 10,20);
            

            //button
            for (MyButton b: button){
                b.draw(g, player);
            }
            checkWinner();
        }
        else if (gameover){
            g.setColor(Color.black);
            g.setFont(new Font("hooge 05_55", Font.PLAIN, 30)); 
            int w = g.getFontMetrics().stringWidth("Game over");
            g.drawString("Game over", 150 - w/2, 100);
            
            w = g.getFontMetrics().stringWidth(winner);
            g.drawString(winner, 150 - w/2, 200);
            
            g.setFont(new Font("hooge 05_55", Font.PLAIN, 15)); 
            w = g.getFontMetrics().stringWidth("click anywhere to restart");
            g.drawString("click anywhere to restart", 150 - w/2, 250);
            
        }
    }
    private void checkWinner() {
        boolean player1win = (player1po.contains(0) && player1po.contains(1) && player1po.contains(2)) ||
                            (player1po.contains(3) && player1po.contains(4) && player1po.contains(5)) ||
                            (player1po.contains(6) && player1po.contains(7) && player1po.contains(8)) ||
                            (player1po.contains(0) && player1po.contains(3) && player1po.contains(6)) ||
                            (player1po.contains(1) && player1po.contains(4) && player1po.contains(7)) ||
                            (player1po.contains(2) && player1po.contains(5) && player1po.contains(8)) ||
                            (player1po.contains(0) && player1po.contains(4) && player1po.contains(8)) ||
                            (player1po.contains(2) && player1po.contains(4) && player1po.contains(6));
        boolean player2win = (player2po.contains(0) && player2po.contains(1) && player2po.contains(2)) ||
                            (player2po.contains(3) && player2po.contains(4) && player2po.contains(5)) ||
                            (player2po.contains(6) && player2po.contains(7) && player2po.contains(8)) ||
                            (player2po.contains(0) && player2po.contains(3) && player2po.contains(6)) ||
                            (player2po.contains(1) && player2po.contains(4) && player2po.contains(7)) ||
                            (player2po.contains(2) && player2po.contains(5) && player2po.contains(8)) ||
                            (player2po.contains(0) && player2po.contains(4) && player2po.contains(8)) ||
                            (player2po.contains(2) && player2po.contains(4) && player2po.contains(6));
        boolean tie = (player1po.size() + player2po.size() == 8) && !(player1win || player2win); 
        if (player1win){
            gameover = true;
            winner = "player 1 win";
        }
        else if (player2win){
            gameover = true;
            winner = "player 2 win";
        }
        else if (tie){
            gameover = true;
            winner = "tie: no winner";
        }
    }
    public void mouseClicked(int x, int y){
        if (!(gameover)){
            for (MyButton b: button){
                if (b.getBounds().contains(x, y) && !(b.isMousePressed())){
                    b.setMousePressed(true);
                    addPlayerPo(b);
                    changeRound();
                    return;
                }
            
            }
        }
        else if (gameover){
            resetGame();
        }

    }

    private void resetGame() {
        button.clear();
        player1po.clear();
        player2po.clear();
        initButton();
        this.gameover = false;
    }

    private void addPlayerPo(MyButton b) {
        if (player == 1){
            player1po.add(b.getPosition());
        }
        else if (player == 2){
            player2po.add(b.getPosition());
        }
    }
    public void changeRound(){
        if (player == 1){
            player = 2;
        }
        else if (player == 2){
            player = 1;
        }
    }
    public void mouseMoved(int x, int y) {
        resetButton();
        for (MyButton b: button){
            if (b.getBounds().contains(x, y) && !(b.isMousePressed())){
                b.setMouseOver(true);
                return;
            }
        }
    }
    private void resetButton() {
        for (MyButton b: button){
            b.setMouseOver(false);
        }
    }
}
