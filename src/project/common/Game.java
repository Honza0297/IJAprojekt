package project.common;

import javafx.collections.ObservableList;
import project.ImpossibleMoveException;

public interface Game {

    //Command moveGUI(Figure figure, Field field);
    void undo();
    ObservableList<String> getNotation();

    Command nextMove() throws ImpossibleMoveException;

    int getActualMoveIndex();
}
