package homework2.game.figures;


import homework2.common.Field;
import homework2.common.Figure;

public class Pawn extends FigureBase implements Figure {

    public Pawn(boolean isWhite)
    {
        this.isItWhite = isWhite;
        this.inGame = false;
        this.whereAmI = null;
    }

    public String getState()
    {
        String color;
        if(isWhite())
        {
            color = "W";
        }
        else
        {
            color = "B";
        }
        return "Pawn["+color+"]"+this.whereAmI.getCol()+":"+this.whereAmI.getRow();
    }

    public boolean move(Field moveTo) {
        if(!canIMoveTo(moveTo))
        {
            return false;
        }
        return false;
    }

    //Check
    public boolean canIMoveTo(Field moveTo) {
        //common
        if(!canIMoveBasic(this.whereAmI, moveTo))
        {
            return false;
        }
        //Check if going only by one row
        if((isWhite() && (moveTo.getRow() != this.whereAmI.getRow()+1)) ||
                (!isWhite() && (moveTo.getRow() != this.whereAmI.getRow()-1)))
        {
            return false;
        }
        Field.Direction direction = this.whereAmI.getDirection(moveTo);
        return (isWhite() && direction == Field.Direction.U) ||
                (!isWhite() && direction == Field.Direction.D);

    }
}
