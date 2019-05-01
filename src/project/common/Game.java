package project.common;

import javafx.collections.ObservableList;
import project.ImpossibleMoveException;
import project.game.InnerMoveNotation;

public interface Game {

    //Command moveGUI(Figure figure, Field field);
    void undo();
    ObservableList<String> getNotation();

    Command nextMove() throws ImpossibleMoveException;

    int getActualMoveIndex();

    Command doUsersMove(InnerMoveNotation moveNotation) throws ImpossibleMoveException;
}
