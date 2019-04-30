package project.common;

public interface Invoker {
   public Command execute(Command cmd);
   public void undo();
}
