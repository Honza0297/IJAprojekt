package project.game.figures;

import project.common.Field;
import project.common.Figure;

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
        return "P["+color+"]"+this.whereAmI.getCol()+":"+this.whereAmI.getRow();
    }



    @Override
    public boolean move(Field moveTo) {
        if(!checkIfCanMoveBasic(this.whereAmI, moveTo))
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
        if((isWhite() && direction == Field.Direction.U)||
                (!isWhite() && direction == Field.Direction.D))
        {
            if(!moveTo.isEmpty())
            {
                return false;
                //NOTE pro sbirani pesakem odkomentovat
                /*if(this.isItWhite != moveTo.get().isWhite)
                {
                    moveTo.kill();
                }
                else
                {
                    return false;
                }*/
            }
            this.whereAmI.remove(this);
            moveTo.put(this);
            return true;
        }
        else
        {
            return false;
        }

    }
}
