import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

class Sprite implements Component {
    
    BufferedImage sprite;
    GameObject parent;
    Scene scene;

    public Sprite(Scene scene, GameObject parent, String imagePath) {
        this.scene = scene;
        this.parent = parent;
        //attempt to load the image from a file
        try {
            this.sprite = ImageIO.read(new File(imagePath));
        } catch (Exception e){
            System.out.println("Unable to read in image file.");
        }
    }

    public void update() {
        this.scene.drawSprite(this.sprite, this.parent.position, this.parent.rotation);
    }
}