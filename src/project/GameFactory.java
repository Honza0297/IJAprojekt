package project;


import project.common.Game;
import project.game.Board;
import project.game.ChessGame;

public abstract class GameFactory {
    public GameFactory()
    {

    }

    public static Game createChessGame(Board board)
    {
        return new ChessGame(board);
    }


}
