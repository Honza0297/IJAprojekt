package project.game;

import project.common.Field;
import project.common.Figure;

import java.util.ArrayList;
import java.util.List;

public class InnerMoveNotation
{
    Field fieldFrom;
    Field fieldTo;
    Figure movingFigure;
    Boolean sach;
    Boolean mat;

    public InnerMoveNotation(Field from, Field to, Figure moving) //todo sach,mat
    {
        fieldFrom = from;
        fieldTo = to;
        movingFigure = moving;
        sach = null;
        mat = null;
    }
}
