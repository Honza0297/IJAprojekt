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

    //TODO
    public boolean move(Field moveTo) {
        return false;
    }

    //TODO
    public boolean canIMoveTo(Field moveTo) {
        return false;
    }
}
