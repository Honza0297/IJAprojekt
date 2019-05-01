package project.game;


import javafx.collections.ObservableList;
import project.ImpossibleMoveException;
import project.common.Command;
import project.common.Field;
import project.common.Figure;
import project.common.Game;
import project.game.commands.MoveCommand;
import project.game.commands.MoveInvoker;
import project.game.figures.*;

import java.io.IOException;
import java.util.List;

public class ChessGame implements Game {

    private Board chessBoard;
    MoveInvoker invoker;
    private Parser parser;
    private InnerGameNotation gameNotation;
    private int moveIndex; //cislo aktualniho tahu

    public ChessGame(Board board) throws IOException
    {
        chessBoard = board;
        SetBoard(chessBoard);

        invoker = new MoveInvoker();
        parser = new Parser(new NotationReaderWriter("C:\\Users\\janbe\\Sources\\IntelliJIdea\\IJAproj\\src\\notace.txt"), chessBoard); //todo jmeno souboru
        gameNotation = parser.ParseGameToInner(); //fixme
        if(gameNotation == null)
        {
            System.out.println("Nepovedlo se nacist notaci!"); //fixme
            throw new IOException();
        }
        moveIndex = 0;
    }

    private void SetBoard(Board board) {
        int max = board.getSize();
        /*
        board.getField(1, 1).put(new Rook(true));
        board.getField(max, 1).put(new Rook(true));
        board.getField(1, max).put(new Rook(false));
        board.getField(max, max).put(new Rook(false));
        for(int c = 1; c < max+1;c++)
        {
            board.getField(c, 2).put(new Pawn(true));
            board.getField(c, max-1).put(new Pawn(false));
        }*/
        for(int row = 1; row <= max; row++)
        {
            for(int col = 1; col <= max; col++)
            {
                switch(row){
                    case 1:
                        switch(col)
                        {
                            case 1:
                            case 8:
                                board.getField(col, row).put(new Rook(true));
                                break;
                            case 2:
                            case 7:
                                board.getField(col, row).put(new Knight(true));
                                break;
                            case 3:
                            case 6:
                                board.getField(col, row).put(new Bishop(true));
                                break;
                            case 5:
                                board.getField(col, row).put(new Queen(true));
                                break;
                            case 4:
                                board.getField(col, row).put(new King(true));
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        board.getField(col, row).put(new Pawn(true));
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        break;
                    case 7:
                        board.getField(col, row).put(new Pawn(false));
                        break;
                    case 8:
                        switch(col)
                        {
                            case 1:
                            case 8:
                                board.getField(col, row).put(new Rook(false));
                                break;
                            case 2:
                            case 7:
                                board.getField(col, row).put(new Knight(false));
                                break;
                            case 3:
                            case 6:
                                board.getField(col, row).put(new Bishop(false));
                                break;
                            case 5:
                                board.getField(col, row).put(new Queen(false));
                                break;
                            case 4:
                                board.getField(col, row).put(new King(false));
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //todo denny uzivatelske tahy - hlidat, aby se stridaly barvy
    private Command move(Figure figure, Field field) //todo denny vyresit tahy z prazdneho policka!
        {
        Command cmd = new MoveCommand(figure, figure.getPositionField(), field, field.get());
        return invoker.execute(cmd);
    }

    @Override
    public void undo() { invoker.undo(false);
    }

    /**
     * Does move back in existing notation
     * @return
     */
    public Command backMove()
    {
        moveIndex--;
        return invoker.undo(false);
    }

    /**
     * Undo user's move - deletes it from notation
     * @return
     */
    public Command undoMove()
    {
        moveIndex--;
        gameNotation.DeleteMovesFromIndexToEnd(moveIndex);
        parser.FromInnerGameNotation(gameNotation);
        return invoker.undo(true);
    }

    public boolean canUndo()
    {
        System.err.printf("moveIndex: %d getusize %d\n", moveIndex, gameNotation.GetSize());
        return moveIndex == gameNotation.GetSize();
    }

    @Override
    public Parser getParser() {
        return this.parser;
    }

    @Override
    public InnerGameNotation getGameNotation() {
        return this.gameNotation;
    }

    public Command redoMove()
    {
        Command cmd = invoker.redo();
        if(cmd == null)
            return null;
        gameNotation.AddMove(new InnerMoveNotation(cmd.getFrom(), cmd.getTo(), 'n'));
        parser.FromInnerGameNotation(gameNotation);
        moveIndex++;
        return cmd;

    }

    /**
     * Increases moveIndex, gets the moveGUI from gameNotation, finds the right figure to do the moveGUI and does the moveGUI
     * @return Command with the moveGUI or null if next moveGUI is not possible (there are no more notated moves)
     * @throws ImpossibleMoveException in case of no found figure to do the moveGUI from gameNotation
     */
    @Override
    public Command nextMove() throws ImpossibleMoveException
    {
        if(moveIndex >= gameNotation.GetSize())
            return null;

        InnerMoveNotation moveNotation = gameNotation.GetMove(moveIndex++);

        if (moveNotation.fieldFrom != null)
        {
            Command cmd = move(moveNotation.fieldFrom.get(), moveNotation.fieldTo); //todo pokud zbyde cas, zkontrolovat jestli to sedi s typem figurky
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
                    {
                        moveNotation.fieldFrom = cmd.getFrom();
                        return cmd;
                    }
                }
            }
            throw new ImpossibleMoveException(moveIndex);
        }
    }

    @Override
    public int getActualMoveIndex()
    {
        return moveIndex;
    }

    @Override
    public Command doUsersMove(InnerMoveNotation moveNotation) throws ImpossibleMoveException
    {
        gameNotation.DeleteMovesFromIndexToEnd(moveIndex);
        invoker.deleteRedoStack();
        gameNotation.AddMove(moveNotation);
        parser.FromInnerGameNotation(gameNotation);
        return nextMove();
    }

    public ObservableList<String> getNotation()
    {
        return parser.readerWriter.GetNotation();
    }
}
