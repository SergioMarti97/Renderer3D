package visualizer.animated;

import engine.PipeLine;
import engine.RenderFlags;
import engine.material.Texture;
import engine.mesh.Mesh;
import engine.mesh.MeshFactory;
import engine.mesh.Model;
import engine.transforms.Rotation;
import engine.transforms.Transform;
import engine.transforms.Translation;
import game.AbstractGame;
import javafx.scene.input.KeyCode;

public class Visualizer implements AbstractGame<TestGameVisualizer> {

    private PipeLine pipeLine;

    private Mesh mesh;

    private Texture texture;

    private Model model;

    private Rotation r;

    private Translation t;

    @Override
    public void init(TestGameVisualizer gc) {
        pipeLine = new PipeLine(gc.getRenderer().getP(), gc.getWidth(), gc.getHeight());
        mesh = MeshFactory.getUnitCube();
        texture = new Texture("/img/mario_bros_coin_block.png");
        model = new Model("/models/grass block/minecraft_dirt.obj");
        r = new Rotation();
        t = new Translation(0.0f, 0.0f, 5.0f);
        t.update();
    }

    @Override
    public void update(TestGameVisualizer gc, float dt) {
        Transform t = new Transform();
        Translation offset = new Translation(-0.5f);
        offset.update();

        r.getDelta().addToX(dt * 1.25f);
        r.getDelta().addToZ(dt * 0.75f);
        r.getDelta().addToY(dt * -0.5f);
        r.update();

        t.combine(offset).combine(r).combine(this.t);

        pipeLine.setTransform(t);

        if (gc.getInput().isKeyDown(KeyCode.DIGIT1)) {
            pipeLine.setRenderFlag(RenderFlags.RENDER_WIRE);
        }
        if (gc.getInput().isKeyDown(KeyCode.DIGIT2)) {
            pipeLine.setRenderFlag(RenderFlags.RENDER_FLAT);
        }
        if (gc.getInput().isKeyDown(KeyCode.DIGIT3)) {
            pipeLine.setRenderFlag(RenderFlags.RENDER_SMOOTH_FLAT);
        }
        if (gc.getInput().isKeyDown(KeyCode.DIGIT4)) {
            pipeLine.setRenderFlag(RenderFlags.RENDER_TEXTURED);
        }
        if (gc.getInput().isKeyDown(KeyCode.DIGIT5)) {
            pipeLine.setRenderFlag(RenderFlags.RENDER_FULL_TEXTURED);
        }
        if (gc.getInput().isKeyDown(KeyCode.DIGIT6)) {
            pipeLine.setRenderFlag(RenderFlags.RENDER_TEXTURED_SHADOW);
        }
        if (gc.getInput().isKeyDown(KeyCode.DIGIT7)) {
            pipeLine.setRenderFlag(RenderFlags.RENDER_FULL_TEXTURED_SHADOW);
        }
    }

    @Override
    public void render(TestGameVisualizer gc) {
        gc.getRenderer().clear(0xff000000);
        int color = 0xff00aaff;
        switch (pipeLine.getRenderFlag()) {
            case RENDER_WIRE:
            case RENDER_FLAT:
            case RENDER_SMOOTH_FLAT:
                pipeLine.renderModel(model, color);
                break;
            case RENDER_TEXTURED:
            case RENDER_FULL_TEXTURED:
            case RENDER_TEXTURED_SHADOW:
            case RENDER_FULL_TEXTURED_SHADOW:
                pipeLine.renderModel(model);
                break;
        }
        pipeLine.clearDepthBuffer();
    }
    
}
