package project.game;

import java.util.ArrayList;
import java.util.List;

public class InnerGameNotation
{
    private List<InnerMoveNotation> gameNotation;

    public InnerGameNotation()
    {
        gameNotation = new ArrayList<>();
    }

    /**
     * Adds move to gameNotation
     * @param move
     */
    public void AddMove(InnerMoveNotation move)
    {
        if(gameNotation.size() % 2 == 0) //prave tahne bily
            move.isWhite = true;
        else
            move.isWhite = false;

        gameNotation.add(move);
    }

    /**
     * Returns move with the index
     * @param indexOfMove index of move
     * @return
     */
    public InnerMoveNotation GetMove(int indexOfMove)
    {
        return gameNotation.get(indexOfMove);
    }

    public int GetSize()
    {
        return gameNotation.size();
    }
}
/*
        fieldFrom.add(from);
        fieldTo.add(to);
        this.movingFigure.add(movingFigure);Field from, Field to, Figure movingFigure) //todo sachmat
        {
            fieldFrom.add(from);
            fieldTo.add(to);
            this.movingFigure.add(movingFigure);Field from, Field to, Figure movingFigure) //todo sachmat
            {
                fieldFrom.add(from);
                fieldTo.add(to);
                this.movingFigure.add(movingFigure);))
    {
        fieldFrom = new ArrayList<>();
        fieldTo = new ArrayList<>();
        movingFigure =  new ArrayList<>();
        sach = new ArrayList<>();
        mat = new ArrayList<>();
    }

    public void AddMove(Field from, Field to, Figure movingFigure) //todo sachmat
    {
        fieldFrom.add(from);
        fieldTo.add(to);
        this.movingFigure.add(movingFigure);
    }
}
        }*/