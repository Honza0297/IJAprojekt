package homework2.game.figures;

import homework2.common.Field;
import homework2.common.Figure;

public class King extends FigureBase implements Figure {

    public King(boolean isWhite)
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
        return "King["+color+"]"+this.whereAmI.getCol()+":"+this.whereAmI.getRow();
    }

    //TODO
    public boolean move(Field moveTo) {
        if(!canIMoveTo(moveTo))
        {
            return false;
        }
        return false;
    }

    //TODO
    public boolean canIMoveTo(Field moveTo) {
        if(!canIMoveBasic(whereAmI, moveTo))
        {
            return false;
        }

        boolean contains = false;
        for(int i = 0; i < 8; i++)
        {
            if(whereAmI.surrounding[i].getRow() == moveTo.getRow()) //moveTo is off-by-one
            {
                if(!whereAmI.surrounding[i].isEmpty() && (whereAmI.surrounding[i].get().isWhite() != this.isWhite()))
                contains = true;
                break;
            }
        }
        return contains;
    }
}
