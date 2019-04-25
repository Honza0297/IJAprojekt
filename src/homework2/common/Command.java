package homework2.common;

public interface Command {
    public boolean execute();
    public void undo();
}
