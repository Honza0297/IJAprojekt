package project.common;

public interface Figure {


    boolean isWhite = false;

    public String getState();

    public Field getPositionField();

    boolean move(Field moveTo);

    void forceMove(Field moveTo);

    void forceSetInGame(boolean isIt);

    void setPosition(Field boardField);

    void setInGame(boolean inGame);

}
