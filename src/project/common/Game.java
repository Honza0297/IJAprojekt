package project.common;

import project.ImpossibleMoveException;

public interface Game {

    Command move(Figure figure, Field field);
    void undo();

    Command nextMove() throws ImpossibleMoveException;
}
