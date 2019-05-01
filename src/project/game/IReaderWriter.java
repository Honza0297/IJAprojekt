package project.game;

import javafx.collections.ObservableList;

public interface IReaderWriter
{
    String GetLine(int line);
    boolean Save(String gameNotation, String filename);
    ObservableList<String> GetNotation();

}
