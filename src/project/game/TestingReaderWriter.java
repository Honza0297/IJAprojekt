package project.game;

public class TestingReaderWriter implements IReaderWriter
{
    private String vstup[] = {"1. a3 d6\n",
            "2. d3 Jc6\n",
            "3. e2e4 e7e5\n",
            "4. Sf1c4 Dd8f6\n"}; //todo sach, mat, posledni tah jen jedne figurky

    public String GetLine(int line)
    {
        if(line >= vstup.length)
            return null;
        return vstup[line];
    }

    @Override
    public boolean Save(String gameNotation, String filename)
    {
        System.out.println(gameNotation);
        return true;
    }
}
