package homework2;


import homework2.common.Game;
import homework2.game.Board;
import homework2.game.ChessGame;

public abstract class GameFactory {
    public GameFactory()
    {

    }

    public static Game createChessGame(Board board)
    {
        return new ChessGame(board);
    }


}
