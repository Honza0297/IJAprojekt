package homework2.game.commands;


import homework2.common.Command;
import homework2.common.Field;
import homework2.common.Figure;

public class MoveCommand implements Command {
    private Figure moving;
    private Figure wasKilled;
    private Field from;
    private Field to;

    public MoveCommand(Figure moving, Field from, Field to, Figure wasKilled){
        this.moving = moving;
        this.wasKilled = wasKilled;
        this.to = to;
        this.from = from;
    }
    public boolean execute() {
        return moving.move(to);
    }

    @Override
    public void undo() {
        moving.forceMove(from);
        if(wasKilled != null)
        {
            wasKilled.forceSetInGame(true);
            wasKilled.forceMove(to);
        }

    }
}
