package homework2.game.figures;


import homework2.common.Field;
import homework2.common.Figure;

public class CheckerDisk extends FigureBase implements Figure
{
    /**
     * Class for disk for CheckerGame. Can go only forward-diagonal by one field without killing enemies.
     *//*
    private boolean isItWhite;
    private boolean inGame;
    private Field whereAmI;*/

    /**
     * Constructor.
     * @param isWhite Decides if disk is white or not
     */
    public CheckerDisk(boolean isWhite)
    {
        this.isItWhite = isWhite;
        this.inGame = false;
        this.whereAmI = null;
    }

    /**
     * Gets state for tests
     * @return
     */
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


    /**
     * Moves disk from one field to another in diagonal direction, if possible. There cannot be any other
     * disks in the way, including goal field.
     * @param moveTo Field to which we want to move.
     * @return true/false value, if disk was really moved.
     */
    public boolean move(Field moveTo)
    {

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

        //check direction
        Field.Direction direction = this.whereAmI.getDirection(moveTo);
        if( (this.isWhite() &&
                (direction == Field.Direction.LU || direction == Field.Direction.RU))
                ||
                (!this.isWhite() &&
                        (direction == Field.Direction.LD || direction == Field.Direction.RD )))
        {
            //I can move :3
            if(!moveTo.isEmpty())
            {
                return false;
                //NOTE tady by se implementovalo brani kamenu.
                // Nyni to nejde a hodim err. Proto to je taky tady -> na policko muzu a brani mi jedine to, ze nevyhazuju (nebo jeste mozna barva, neresim).
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

    //NOTE: Deprecated in checker for now.
    public boolean canIMoveTo(Field moveTo) {
        return false;
    }


}
