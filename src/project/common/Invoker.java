package project.common;

public interface Invoker {
   public Command execute(Command cmd);
   public Command undo(boolean redoEnabled);
   public Command redo();

   void deleteRedoStack();
}
