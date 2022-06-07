package ui;

import java.awt.Rectangle;

import load.ImgMeneger;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class MyButton {
    private int x, y, width, height, position;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;
    private ImgMeneger imgMeneger;
    private BufferedImage img = null;

    public MyButton(int x, int y, int width, int height, int position){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.position = position;

        imgMeneger = new ImgMeneger();

        initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g, int player){
        if (player == 1 && !(mousePressed)){
            img = imgMeneger.CIRCLE;
        }
        else if (player == 2 && !(mousePressed)){
            img = imgMeneger.CROSS;
        }

        if (mouseOver || mousePressed){
            g.drawImage(img, x, y, width, height, null);          
        }
    }

    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }
    public Rectangle getBounds(){
        return bounds;
    }

    public boolean isMouseOver(){
        return mouseOver;
    }
    public boolean isMousePressed(){
        return mousePressed;
    }
    public int getPosition(){
        return position;
    }

}
