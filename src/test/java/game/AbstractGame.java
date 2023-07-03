package game;

public interface AbstractGame<T> {

    void init(T controller);

    void update(T controller, float elapsedTime);

    void render(T controller);

}
