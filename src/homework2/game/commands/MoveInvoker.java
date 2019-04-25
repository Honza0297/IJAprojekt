package homework2.game.commands;

import homework2.common.Command;
import homework2.common.Invoker;

import java.util.Stack;

public class MoveInvoker implements Invoker {
    private Stack<Command> undoStack;

    public MoveInvoker(){
        undoStack = new Stack<Command>();
    }


    public boolean execute(Command cmd) {

        boolean succeeded = cmd.execute();
        if(succeeded)
        {
            undoStack.push(cmd);
        }
        return succeeded;

    }

    public void undo() {
        if(!undoStack.empty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
        }
    }
}
