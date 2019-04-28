package project.common;

public interface Command {
    public boolean execute();
    public void undo();
}
