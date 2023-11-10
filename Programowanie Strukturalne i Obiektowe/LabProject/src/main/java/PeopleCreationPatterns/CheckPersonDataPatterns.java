package PeopleCreationPatterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPersonDataPatterns
{

    //Sprawdzanie czy slowo sklada sie z samych liter
    public static boolean letterPattern(String word)
    {
        Pattern compiledPattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = compiledPattern.matcher(word);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    //Sprawdzanie czy slowo sklada sie z samych cyfr
    public static boolean numberPattern(String word)
    {
        Pattern compiledPattern = Pattern.compile("[0-9]+");
        Matcher matcher = compiledPattern.matcher(word);

        if(matcher.matches())
            return true;
        else
            return false;
    }
}
