package Exceptions;

import java.util.IllegalFormatException;

public class StringFormatException extends IllegalArgumentException
{
    public StringFormatException(String message)
    {
        super(message);
    }

}
