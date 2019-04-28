package project.game;

public interface IReaderWriter
{
    String GetLine(int line);
    boolean Save(String gameNotation, String filename);
}
