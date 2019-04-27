package homework2.common;

public interface Figure {


    boolean isWhite = false;


    String getState();

    Field getPositionField();

    boolean move(Field moveTo);
    boolean canIMoveTo(Field moveTo);
    void setPosition(Field boardField);
    void setInGame(boolean inGame);
    boolean isWhite();

    //For Undo
    void forceMove(Field moveTo);
    void forceSetInGame(boolean isIt);



}
