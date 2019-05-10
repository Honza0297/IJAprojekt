package project;


import project.common.Game;
import project.game.Board;
import project.game.ChessGame;

import java.io.IOException;

public abstract class GameFactory {
    public GameFactory()
    {

    }

    public static Game createChessGame(Board board, String filename) throws IOException
    {
        return new ChessGame(board, filename);
    }


    public static Game createChessGame(Board board) throws IOException
    {
        return new ChessGame(board);
    }
}
