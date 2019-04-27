package homework2.common;

public interface Game {

    boolean move(Figure figure, Field field);
    void undo();
}
