package homework2.game.figures;

import homework2.common.Field;
import homework2.common.Figure;

public class Knight extends FigureBase implements Figure
{
    public Knight(boolean isWhite)
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
        return "Knight["+color+"]"+this.whereAmI.getCol()+":"+this.whereAmI.getRow();
    }

    //
    public boolean move(Field moveTo) {
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

    //checked the direction. Knight can jump over all other figures (including enemies) => no need to control
    public boolean canIMoveTo(Field moveTo)
    {
        if(!canIMoveBasic(whereAmI, moveTo))
        {
            return false;
        }
       int differenceRow = java.lang.Math.abs(this.whereAmI.getRow() - moveTo.getRow());
       int differenceCol = java.lang.Math.abs(this.whereAmI.getCol() - moveTo.getCol());
       return (differenceCol == 2 && differenceRow == 1) || (differenceCol == 1 && differenceRow == 2);
    }
}
