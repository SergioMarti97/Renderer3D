package visualizer.animated;

import engine.render.PixelRenderer;
import game.AbstractGame;
import game.GameClock;
import game.Input;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestGameVisualizer extends Application {

    // Settings of the application

    private String appName = "Visualizer";

    private int width = 800;

    private int height = 600;

    // Game needed classes

    private AbstractGame<TestGameVisualizer> game;

    private Input input;

    private GameClock clock;

    private PixelRenderer renderer;

    // Scene layout

    private Scene scene;

    private WritableImage img;

    private ImageView imageView;

    @Override
    public void init() throws Exception {
        super.init();
        setGame(new Visualizer());
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Setting the image view
        img = new WritableImage(width, height);

        imageView = new ImageView(img);
        imageView.setFocusTraversable(true);

        StackPane pane = new StackPane(imageView);

        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());

        // PixelRenderer
        renderer = new PixelRenderer(width, height);

        // Input
        input = new Input(imageView);

        // Game Clock
        clock = new GameClock(this::update, this::render);

        // Set the scene
        scene = new Scene(pane, width, height);
        stage.setScene(scene);
        stage.setTitle(appName);

        // Show the scene
        if (game != null) {
            clock.start();
            stage.show();
            game.init(this);
        }
    }

    private void update(float elapsedTime) {
        game.update(this, elapsedTime);
        input.update();
    }

    private void render() {
        renderer.writeImage(img);
        game.render(this);
    }

    // Getters and Setters

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AbstractGame<TestGameVisualizer> getGame() {
        return game;
    }

    public void setGame(AbstractGame<TestGameVisualizer> game) {
        this.game = game;
    }

    public Input getInput() {
        return input;
    }

    public GameClock getClock() {
        return clock;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public WritableImage getImg() {
        return img;
    }

    public PixelRenderer getRenderer() {
        return renderer;
    }
}
