package project;


public class ImpossibleMoveException extends Exception
{
    private int moveIndex;

    public ImpossibleMoveException(int moveIndex)
    {
        this.moveIndex = moveIndex;
    }

    public int getMoveIndex()
    {
        return moveIndex;
    }
}
