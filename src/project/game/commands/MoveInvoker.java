package project.game.commands;

import project.common.Command;
import project.common.Invoker;

import java.util.Stack;

public class MoveInvoker implements Invoker
{
    private Stack<Command> undoStack;

    public MoveInvoker(){
        undoStack = new Stack<Command>();
    }


    public Command execute(Command cmd) {

        boolean succeeded = cmd.execute();
        if(succeeded)
        {
            undoStack.push(cmd);
            return cmd;
        }
        else
        {
            return null;
        }
    }

    public void undo() {
        if(!undoStack.empty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
        }
    }
}
