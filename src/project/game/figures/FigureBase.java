package project.game.figures;

import project.common.Field;
import project.common.Figure;

public class FigureBase {
    protected boolean isItWhite;
    protected boolean inGame;
    protected Field whereAmI;

    public void forceMove(Field moveTo) {
        if(this.whereAmI != null)
        {
            this.whereAmI.remove((Figure) this);
        }
        else
        {
            forceSetInGame(true);
        }
        moveTo.put((Figure) this);
    }

    public void forceSetInGame(boolean isIt) {
        this.inGame = isIt;
    }

    /**
     * Sets if current disk is in game
     * @param isIt true/false according to real state
     */
    public void setInGame(boolean isIt)
    {
        this.inGame = isIt;
    }

    /**
     * Function returns if the current disk is white
     * @return true/false according to real state
     */
    public boolean isWhite()
    {
        return this.isItWhite;
    }

    /**
     * Function sets the position of the disk to the given field.
     * @param field Given field in which we want to place disk
     */
    public void setPosition(Field field)
    {
        this.whereAmI = field;
    }

    public Field getPositionField()
    {
        return this.whereAmI;
    }

    protected boolean checkIfCanMoveBasic(Field from, Field moveTo)
    {
        if(this.whereAmI == moveTo)
        {
            return false; //Cannot make zero-move (written in forum)
        }
        if(!this.inGame)
        {
            return false; //Have to be in game :)
        }
        return true;
    }
}
