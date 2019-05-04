package project.common;

import javafx.collections.ObservableList;

public interface IReaderWriter
{
    String GetLine(int line);
    boolean Save(String gameNotation, String filename);
    ObservableList<String> GetNotation();

}
