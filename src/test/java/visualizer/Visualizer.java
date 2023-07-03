package visualizer;

import engine.PipeLine;
import engine.mesh.Mesh;
import engine.mesh.MeshFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Visualizer extends Application {

    // Settings of the application

    private String appName = "Visualizer";

    private int width = 800;

    private int height = 600;

    // Scene layout

    private WritableImage img;

    // 3D Render class

    private PipeLine pipeLine;

    private Mesh mesh;

    @Override
    public void start(Stage stage) throws Exception {
        // Setting the 3D render
        pipeLine = new PipeLine(width, height);
        mesh = MeshFactory.getUnitCube();

        // Setting the image view
        img = new WritableImage(width, height);

        ImageView imageView = new ImageView(img);
        imageView.setFocusTraversable(true);

        StackPane pane = new StackPane(imageView);

        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());

        // Rendering the 3D mesh
        pipeLine.getRenderer3D().clear(0xff000000);
        pipeLine.renderMesh(mesh, 0xff00aaff);
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