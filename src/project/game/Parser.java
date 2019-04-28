package project.game;

import project.common.Field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser
{
    IReaderWriter readerWriter;
    Board board;
    String pattern1 = "^";
    String patternShort = "\\. ([S,K,V,D,J])?([a-h])([1-8]) ([S,K,V,D,J])?([a-h])([1-8])$";
    String patternLong = "\\. ([S,K,V,D,J])?([a-h])([1-8])([a-h])([1-8]) ([S,K,V,D,J])?([a-h])([1-8])([a-h])([1-8])$";

    public Parser(IReaderWriter readerWriter, Board board)
    {
        this.readerWriter = readerWriter;
        this.board = board;
    }

    /**
     * Converts official notation of game to InnerGameNotation
     * @return InnerGameNotation on success, null otherwise
     */
    public InnerGameNotation ParseGameToInner()
    {
        InnerGameNotation gameNotation = new InnerGameNotation();
        String line;
        Pattern r;
        Matcher m;
        for(int i = 0; (line = readerWriter.GetLine(i)) != null; i++)
        {
            r = Pattern.compile(pattern1 + Integer.toString(i+1) + patternShort);
            m = r.matcher(line);
            if(m.find())
            {
                Field to = board.getField(m.group(2).charAt(0), Integer.parseInt(m.group(3)));
                Field to2 = board.getField(m.group(5).charAt(0), Integer.parseInt(m.group(6)));
                InnerMoveNotation moveNotation = new InnerMoveNotation(null, to, getFigureType(m.group(1)));
                InnerMoveNotation moveNotation2 = new InnerMoveNotation(null, to2, getFigureType(m.group(4)));
                gameNotation.AddMove(moveNotation);
                gameNotation.AddMove(moveNotation2);
            }
            else
            {
                r = Pattern.compile(pattern1 + Integer.toString(i+1) + patternLong);
                m = r.matcher(line);
                if(m.find())
                {
                    Field from = board.getField(m.group(2).charAt(0), Integer.parseInt(m.group(3)));
                    Field from2 = board.getField(m.group(7).charAt(0), Integer.parseInt(m.group(8)));
                    Field to = board.getField(m.group(4).charAt(0), Integer.parseInt(m.group(5)));
                    Field to2 = board.getField(m.group(9).charAt(0), Integer.parseInt(m.group(10)));

                    InnerMoveNotation moveNotation = new InnerMoveNotation(from, to, getFigureType(m.group(1)));
                    InnerMoveNotation moveNotation2 = new InnerMoveNotation(from2, to2, getFigureType(m.group(6)));
                    gameNotation.AddMove(moveNotation);
                    gameNotation.AddMove(moveNotation2);
                } //TODO ukonceni tahem bileho
                else //notace neodpovida pravidlum
                    return null;
            }
        }
        return gameNotation;
    }

    /**
     * Converts InnerGameNotation to official notation and saves it to file
     * @param gameNotation
     * @param filename
     * @return true if success, false otherwise
     */
    public boolean SaveGameNotation(InnerGameNotation gameNotation, String filename)
    {
        return readerWriter.Save(FromGameNotation(gameNotation), filename);
    }

    /**
     * Converts innerGameNotation to official notation (string)
     * @param gameNotation
     * @return String with official notation of game
     */
    public String FromGameNotation(InnerGameNotation gameNotation)
    {
        String line;
        StringBuilder gameInString = new StringBuilder();
        for(int i = 0;(line = FromInnerMoveNotation(gameNotation, i)) != null;i = i + 2)
            gameInString.append(line);

        return gameInString.toString();
    }

    /**
     * Converts two innerMoveNotations from InnerGameNotation to string - official notation
     * @param gameNotation Must have not null Fields from and to
     * @param whiteMoveIndex Index of move of white figure
     * @return string line with long notation
     */
    public String FromInnerMoveNotation(InnerGameNotation gameNotation, int whiteMoveIndex)
    {
        if(whiteMoveIndex%2 != 0)
            return null;
        InnerMoveNotation move1;
        InnerMoveNotation move2;
        try
        {
            move1 = gameNotation.GetMove(whiteMoveIndex);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
        try
        {
            move2 = gameNotation.GetMove(whiteMoveIndex+1);
        }
        catch (IndexOutOfBoundsException e)
        {
            move2 = null;
        }

        StringBuilder retStr = new StringBuilder(Integer.toString(whiteMoveIndex/2+1) + ". ");
        retStr.append(move1.fieldFrom.getColAsChar());
        retStr.append(move1.fieldFrom.getRow());
        retStr.append(move1.fieldTo.getColAsChar());
        retStr.append(move1.fieldTo.getRow());
        retStr.append(" ");
        if(move2 != null)
        {
            retStr.append(move2.fieldFrom.getColAsChar());
            retStr.append(move2.fieldFrom.getRow());
            retStr.append(move2.fieldTo.getColAsChar());
            retStr.append(move2.fieldTo.getRow());
        }
        retStr.append(System.getProperty("line.separator"));

        return retStr.toString();
    }

    /**
     * From char in string fromGroup gets the char meaning a figure type
     * @param fromGroup
     * @return 'n' if string is null, other chars if not null
     */
    private char getFigureType(String fromGroup)
    {
        if(fromGroup != null)
            return fromGroup.charAt(0);
        else
            return 'n'; //todo ten typ nejak upravit
    }
}
