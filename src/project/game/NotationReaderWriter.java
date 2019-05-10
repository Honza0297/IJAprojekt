package project.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.common.IReaderWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class NotationReaderWriter implements IReaderWriter
{
    private ObservableList<String> file;
    private String filenameForReading;

    public NotationReaderWriter(String filenameForReading)
    {
        file = null;
        this.filenameForReading = filenameForReading;
    }

    @Override
    public String GetLine(int line)
    {
        if(file == null)
            if(!ReadFile())
                return null; //unsuccessful reading
        try
        {
            return file.get(line);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    @Override
    public boolean Save(String gameNotation, String filename)
    {
        try
        {
            Files.write(Paths.get(filename), gameNotation.getBytes());
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    private boolean ReadFile()
    {
        try
        {
            System.out.println(Paths.get(filenameForReading).toString());
            file = FXCollections.observableArrayList(Files.readAllLines(Paths.get(filenameForReading)));
            return true;
        }
        catch (IOException e)
        {
            System.out.println(e);
            return false;
        }
    }

    public ObservableList<String> GetNotation()
    {
        return file;
    }
}
