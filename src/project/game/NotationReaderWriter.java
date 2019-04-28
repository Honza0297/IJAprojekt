package project.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NotationReaderWriter implements IReaderWriter
{
    private List<String> file;
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
        catch (ArrayIndexOutOfBoundsException e)
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
            file = Files.readAllLines(Paths.get(filenameForReading));
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }
}
