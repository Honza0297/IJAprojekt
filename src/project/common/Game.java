package project.common;

public interface Game {

    Command move(Figure figure, Field field);
    void undo();

    Command nextMove();
}
