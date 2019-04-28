package project.game;

import project.common.Field;

public class InnerMoveNotation
{
    Field fieldFrom;
    Field fieldTo;
    char movingFigureType;
    Boolean isWhite;
    Boolean sach;
    Boolean mat;

    public InnerMoveNotation(Field from, Field to, char movingFigureType) //todo sach,mat
    {
        fieldFrom = from;
        fieldTo = to;
        this.movingFigureType = movingFigureType;
        isWhite = null;
        sach = null;
        mat = null;
    }
}
