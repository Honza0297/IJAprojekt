package project.game;

import project.common.Field;

import java.util.ArrayList;
import java.util.List;

public class InnerGameNotation
{
    private List<InnerMoveNotation> gameNotation;

    public InnerGameNotation()
    {
        gameNotation = new ArrayList<>();
    }

    public void AddMove(InnerMoveNotation move)
    {
        gameNotation.add(move);
    }
    public InnerMoveNotation GetMove(int indexOfMove)
    {
        return gameNotation.get(indexOfMove);
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