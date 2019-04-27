package project.game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser
{
    IReaderWriter readerWriter;
    Board board;
    String patternShort1 = "^";
    String patternShort2 = "\\. ([S,K,V,S,J])?([a-h])([1-8]) ([S,K,V,S,J])?([a-h])([1-8])$";

    public Parser(IReaderWriter readerWriter, Board board)
    {
        this.readerWriter = readerWriter;
        this.board = board;
    }
    public InnerGameNotation ParseGameToInner()
    {
        InnerGameNotation gameNotation = new InnerGameNotation();
        String line;
        for(int i = 0; (line = readerWriter.GetLine(i)) != null; i++)
        {
            Pattern r = Pattern.compile(patternShort1 + Integer.toString(i) + patternShort2);
            Matcher m = r.matcher(line);
            if(m.find())
            {

            }
            String pes = line;
        }
        return gameNotation;
    }
}
