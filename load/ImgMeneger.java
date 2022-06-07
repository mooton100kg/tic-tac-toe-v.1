package load;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImgMeneger {
    private BufferedImage atlas;
    public BufferedImage CIRCLE, CROSS;

    public ImgMeneger(){
        getSpriteAtlas();
        getimg();
    }

    private void getSpriteAtlas(){

        InputStream is = ImgMeneger.class.getClassLoader().getResourceAsStream("res/spriteatlas.png");
        
        try {
            atlas = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getimg(){
        CROSS = atlas.getSubimage(0, 0, 50,50);
        CIRCLE = atlas.getSubimage(50, 0, 50,50);
    }



}
