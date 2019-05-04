package project.common;

import javafx.collections.ObservableList;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import project.ImpossibleMoveException;
import project.game.InnerGameNotation;
import project.game.InnerMoveNotation;
import project.game.Parser;

public interface Game {

    //Command moveGUI(Figure figure, Field field);
    void undo();
    ObservableList<String> getNotation();

    Command nextMove() throws ImpossibleMoveException;

    int getActualMoveIndex();

    Command doUsersMove(InnerMoveNotation moveNotation) throws ImpossibleMoveException;
    public Command backMove();
    public Command undoMove();
    public Command redoMove();
    public boolean canUndo();
    public Parser getParser();
    public InnerGameNotation getGameNotation();
}
