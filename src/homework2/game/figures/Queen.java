package homework2.game.figures;


import homework2.common.Field;
import homework2.common.Figure;

public class Queen extends FigureBase implements Figure {
    public Queen(boolean isWhite)
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
        return "Queen["+color+"]"+this.whereAmI.getCol()+":"+this.whereAmI.getRow();
    }

    //
    public boolean move(Field moveTo)
    {
        if(!canIMoveTo(moveTo))
        {
            return false;
        }
        if(moveTo.containsEnemy(this.isWhite()))
        {
            moveTo.kill();
        }
        if(moveTo.isEmpty())
        {
            this.whereAmI.remove(this);
            moveTo.put(this);
            return true;
        }
        return false;
    }

    //checks only if the way is clear (excluding moveTo)
    public boolean canIMoveTo(Field moveTo)
    {
        if(!canIMoveBasic(whereAmI, moveTo))
        {
            return false;
        }
        Field.Direction direction = this.whereAmI.getDirection(moveTo);
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
