package homework2.game;


import homework2.common.Command;
import homework2.common.Field;
import homework2.common.Figure;
import homework2.common.Game;
import homework2.game.commands.MoveCommand;
import homework2.game.commands.MoveInvoker;
import homework2.game.figures.CheckerDisk;

public class CheckerGame implements Game
{
    private Board checkerBoard;
    private MoveInvoker invoker;
    public CheckerGame(Board board)
    {
        checkerBoard = board;
        SetBoard(checkerBoard);

        invoker = new MoveInvoker();
    }

    /**
     * Function sets disks to its appropriate positions = First on (1,1), second on (1,3) etc,
     * then (2,2), (2,4)...
     * @param board board to put fields
     */
    private void SetBoard(Board board)
    {
        for(int r = 1; r < 3; r++)
        {
            for(int c = r; c < board.getSize()+1; c+=2)
            {
                if(c >= board.getSize()+1)
                {
                    continue;
                }
                board.getField(c, r).put(new CheckerDisk(true));
            }
        }

    }

    public boolean move(Figure figure, Field field) {
        Command cmd = new MoveCommand(figure, figure.getPositionField(), field, field.get());
        return invoker.execute(cmd);
                /*int[] position = figure.getPositionField();
       return checkerBoard.getField(position[0], position[1]).get().move(field);*/
    }


    public void undo() {
        invoker.undo();
    }
}
