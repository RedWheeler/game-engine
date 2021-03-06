package game_engine;

import java.util.ArrayList;

public class RenderingSystem {

    public Canvas canvas;

    private Game game;

    public RenderingSystem (Game game) {
        this.game = game;
        this.canvas = new Canvas(game.windowWidth, game.windowHeight);
    }

    private void renderText () {
        ArrayList<GameObject> hasText = this.game.sceneManager.getComponents(Text.class);
        ArrayList<Text> textComponents =  new ArrayList<Text> ();
        for (GameObject gameObject : hasText) {
            textComponents.addAll(gameObject.getComponents(Text.class));
        }
        for (Text textComponent: textComponents) {
            this.canvas.drawText(textComponent.textValue, textComponent.font, textComponent.parent.position);
        }
    }

    private void renderSprites () {
        ArrayList<GameObject> hasSprite = this.game.sceneManager.getComponents(Sprite.class);
        ArrayList<Sprite> spriteComponents = new ArrayList<Sprite> ();
        for (GameObject gameObject : hasSprite) {
            spriteComponents.addAll(gameObject.getComponents(Sprite.class));
        }
        for (Sprite spriteComponent: spriteComponents) {
            this.canvas.drawSprite(
                    spriteComponent.sprite,
                    spriteComponent.parent.position,
                    spriteComponent.parent.scale,
                    spriteComponent.parent.rotation);
        }
    }

    public void render () {
        this.renderSprites();
        this.renderText();
        this.canvas.repaint();
    }
}
