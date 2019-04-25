package homework2.game.figures;


import homework2.common.Field;
import homework2.common.Figure;

public class Bishop extends FigureBase implements Figure
{
    public Bishop(boolean isWhite)
    {
        this.isItWhite = isWhite;
        this.inGame = false;
        this.whereAmI = null;
    }

    public String getState() {
        String color;
        if(isWhite())
        {
            color = "W";
        }
        else
        {
            color = "B";
        }
        return "Bishop["+color+"]"+this.whereAmI.getCol()+":"+this.whereAmI.getRow();
    }

   //TODO
    public boolean move(Field moveTo) {
        if(!canIMoveTo(moveTo))
        {
            return false;
        }
        //check if moveTo is empty or with enemy
        if(moveTo.isEmpty() && )
    }

    //checked direction and isempty of all field except moveTo i
    public boolean canIMoveTo(Field moveTo)
    {
        if(!canIMoveBasic(whereAmI, moveTo))
        {
            return false;
        }
        //Bishop can go only diagonal
        Field.Direction direction = this.whereAmI.getDirection(moveTo);
        switch(direction)
        {
            case LU:
            case RU:
            case LD:
            case RD:
                break;
            default:
                return false;
        }
        Field neighborInDirection = this.whereAmI.nextField(direction);
        while (neighborInDirection != moveTo)
        {
            if(!neighborInDirection.isEmpty())
                return false;
            neighborInDirection = neighborInDirection.nextField(direction);
        }
        return true;
    }
}
