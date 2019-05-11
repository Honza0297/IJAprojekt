package project;


public class ImpossibleMoveException extends Exception
{
    private int moveIndex;

    public ImpossibleMoveException(int moveIndex)
    {
        this.moveIndex = moveIndex;
    }

    public ImpossibleMoveException()
    {
        moveIndex = -1; //jen aby tam neco bylo, kdybych to nahodou prece jen nekde pouzil
    }

    public int getMoveIndex()
    {
        return moveIndex;
    }
}
