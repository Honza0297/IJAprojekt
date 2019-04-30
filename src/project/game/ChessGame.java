package project.game;


import project.ImpossibleMoveException;
import project.common.Command;
import project.common.Field;
import project.common.Figure;
import project.common.Game;
import project.game.commands.MoveCommand;
import project.game.commands.MoveInvoker;
import project.game.figures.Pawn;
import project.game.figures.Rook;

import java.util.List;

public class ChessGame implements Game {

    private Board chessBoard;
    MoveInvoker invoker;
    private Parser parser;
    private InnerGameNotation gameNotation;
    private int moveIndex; //cislo aktualniho tahu

    public ChessGame(Board board)
    {
        chessBoard = board;
        SetBoard(chessBoard);

        invoker = new MoveInvoker();
        parser = new Parser(new TestingReaderWriter(), chessBoard); //todo zmenit TestingReaderWriter
        gameNotation = parser.ParseGameToInner(); //fixme
        if(gameNotation == null)
            System.out.println("Nepovedlo se nacist notaci!"); //fixme
        moveIndex = 0;
    }

    private void SetBoard(Board board) {
        int max = board.getSize();
        board.getField(1, 1).put(new Rook(true));
        board.getField(max, 1).put(new Rook(true));
        board.getField(1, max).put(new Rook(false));
        board.getField(max, max).put(new Rook(false));
        for(int c = 1; c < max+1;c++)
        {
            board.getField(c, 2).put(new Pawn(true));
            board.getField(c, max-1).put(new Pawn(false));
        }
    }

    @Override
    public Command move(Figure figure, Field field) {
        Command cmd = new MoveCommand(figure, figure.getPositionField(), field, field.get());
        return invoker.execute(cmd);
    }

    @Override
    public void undo() { invoker.undo();
    }

    /**
     * Increases moveIndex, gets the move from gameNotation, finds the right figure to do the move and does the move
     * @return Command with the move or null if next move is not possible (there are no more notated moves)
     * @throws ImpossibleMoveException in case of no found figure to do the move from gameNotation
     */
    @Override
    public Command nextMove() throws ImpossibleMoveException
    {
        if(++moveIndex >= gameNotation.GetSize())
            return null;

        InnerMoveNotation moveNotation = gameNotation.GetMove(++moveIndex);

        if (moveNotation.fieldFrom != null)
        {
            Command cmd = move(moveNotation.fieldFrom.get(), moveNotation.fieldTo);
            if(cmd == null)
                throw new ImpossibleMoveException(moveIndex);
            else
                return cmd;
        }
        else
        {
            List<Figure> figures = chessBoard.GetAllFigures(moveNotation.isWhite);
            for (Figure figure : figures)
            {
                if (moveNotation.movingFigureType == figure.getType())
                {
                    Command cmd = move(figure, moveNotation.fieldTo);
                    if (cmd != null) //pokud se tah povedl, vratim ho
                        return cmd;
                }
            }
            throw new ImpossibleMoveException(moveIndex);
        }
    }

}
