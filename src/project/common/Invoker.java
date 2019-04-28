package project.common;

public interface Invoker {
   public boolean execute(Command cmd);
   public void undo();
}
