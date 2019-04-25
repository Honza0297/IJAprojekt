package homework2.game.figures;


import homework2.common.Field;
import homework2.common.Figure;

public class Rook extends FigureBase implements Figure {


    public Rook(boolean isWhite)
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
        return "Rook["+color+"]"+this.whereAmI.getCol()+":"+this.whereAmI.getRow();
    }


    @Override
    public boolean move(Field moveTo) {
        if(!canIMoveTo(moveTo))
        {
            return false;
        }

        //can move and possibly kill
        if(!moveTo.isEmpty())
        {
            if(this.isItWhite != moveTo.get().isWhite)
            {
                moveTo.kill();
            }
            else
            {
                return false;
            }
        }
        this.whereAmI.remove(this);
        moveTo.put(this);
        return true;
    }

    //Check
    public boolean canIMoveTo(Field moveTo) {
        if(!canIMoveBasic(this.whereAmI, moveTo))
        {
            return false;
        }

        Field.Direction direction = this.whereAmI.getDirection(moveTo);
        switch(direction) //if bad direction, return false. Strange, but seems OK and quite nice
        {
            case L:
            case R:
            case U:
            case D:
                break;
            default:
                return false; //vez nemuze do strany
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
