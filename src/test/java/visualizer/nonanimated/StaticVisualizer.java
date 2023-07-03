package visualizer.nonanimated;

import engine.PipeLine;
import engine.RenderFlags;
import engine.material.Texture;
import engine.mesh.Mesh;
import engine.mesh.MeshFactory;
import engine.mesh.Model;
import engine.transforms.Rotation;
import engine.transforms.Transform;
import engine.transforms.Translation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StaticVisualizer extends Application {

    // Settings of the application

    private String appName = "Visualizer";

    private int width = 800;

    private int height = 600;

    // Scene layout

    private WritableImage img;

    // 3D Render class

    private PipeLine pipeLine;

    private Mesh mesh;

    private Texture texture;

    private Model model;

    private Rotation r;

    private Translation t;

    private void setListeners(Pane pane) {
        final float inc = 0.1f;
        pane.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case NUMPAD4:
                    r.getDelta().addToY(-inc);
                    break;
                case NUMPAD6:
                    r.getDelta().addToY(inc);
                    break;
                case NUMPAD8:
                    r.getDelta().addToX(-inc);
                    break;
                case NUMPAD2:
                    r.getDelta().addToX(inc);
                    break;
                case NUMPAD7:
                    r.getDelta().addToZ(-inc);
                    break;
                case NUMPAD3:
                    r.getDelta().addToZ(inc);
                    break;
                case DOWN:
                    t.getDelta().addToZ(-inc);
                    break;
                case UP:
                    t.getDelta().addToZ(inc);
                    break;
                case DIGIT1:
                    pipeLine.setRenderFlag(RenderFlags.RENDER_WIRE);
                    break;
                case DIGIT2:
                    pipeLine.setRenderFlag(RenderFlags.RENDER_FLAT);
                    break;
                case DIGIT3:
                    pipeLine.setRenderFlag(RenderFlags.RENDER_SMOOTH_FLAT);
                    break;
                case DIGIT4:
                    pipeLine.setRenderFlag(RenderFlags.RENDER_TEXTURED);
                    break;
                case DIGIT5:
                    pipeLine.setRenderFlag(RenderFlags.RENDER_FULL_TEXTURED);
                    break;
                case DIGIT6:
                    pipeLine.setRenderFlag(RenderFlags.RENDER_TEXTURED_SHADOW);
                    break;
                case DIGIT7:
                    pipeLine.setRenderFlag(RenderFlags.RENDER_FULL_TEXTURED_SHADOW);
                    break;
            }
            // renderModel(model);
            renderMesh(mesh, texture);
            pipeLine.clearDepthBuffer();
            pipeLine.getRenderer3D().writeImage(img);
        });
    }

    private void renderModel(Model model) {
        Transform t = new Transform();
        this.t.update();
        this.r.update();

        t.combine(this.r).combine(this.t);

        pipeLine.setTransform(t);

        pipeLine.getRenderer3D().clear(0xff000000);

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
    }

    private void renderMesh(Mesh mesh, Texture texture) {
        Transform t = new Transform();
        Translation offset = new Translation(-0.5f);

        offset.update();
        this.t.update();
        this.r.update();

        t.combine(offset).combine(this.r).combine(this.t);

        pipeLine.setTransform(t);

        pipeLine.getRenderer3D().clear(0xff000000);

        int color = 0xff00aaff;
        switch (pipeLine.getRenderFlag()) {
            case RENDER_WIRE:
                case RENDER_FLAT:
                    case RENDER_SMOOTH_FLAT:
                pipeLine.renderMesh(mesh, color);
                break;
            case RENDER_TEXTURED:
                case RENDER_FULL_TEXTURED:
                    case RENDER_TEXTURED_SHADOW:
                        case RENDER_FULL_TEXTURED_SHADOW:
                pipeLine.renderMesh(mesh, texture);
                break;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Setting the 3D render
        pipeLine = new PipeLine(width, height);
        mesh = MeshFactory.getUnitCube();
        texture = new Texture("/img/mario_bros_coin_block.png");
        model = new Model("/models/gorra mario/gorra_mario.obj");

        r = new Rotation();
        t = new Translation(0.0f, 0.0f, 5.0f);

        // Setting the image view
        img = new WritableImage(width, height);

        ImageView imageView = new ImageView(img);
        imageView.setFocusTraversable(true);

        StackPane pane = new StackPane(imageView);
        setListeners(pane);

        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());

        // Rendering the 3D mesh
        renderMesh(mesh, texture);
        // renderModel(model);
        pipeLine.clearDepthBuffer();
        pipeLine.getRenderer3D().writeImage(img);

        // Set the scene
        Scene scene = new Scene(pane, width, height);
        stage.setScene(scene);
        stage.setTitle(appName);

        // Show the scene
        stage.show();
    }

}
