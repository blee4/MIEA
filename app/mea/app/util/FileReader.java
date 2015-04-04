package mea.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

/** Class to read data off text files */
public final class FileReader
{
    private FileReader()
    {
        //
    }

    /**
     * Reads the first line of the file.
     * 
     * @param resourcePath The path to the resource, including the resource's filename.
     * @return A String of the line read.
     */
    public static String readFile(String resourcePath)
    {
        Path file = Paths.get(resourcePath);
        InputStream inputStream;

        String line = "";
        try
        {
            inputStream = Files.newInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            if ((line = bufferedReader.readLine()) != null)
            {
                MeaLogger.log(Level.FINEST, "File contents: " + line);
                return line;
            }
        }
        catch (IOException e)
        {
            MeaLogger.log(Level.WARNING, "Cannot read file: " + resourcePath, e);
        }

        return line;
    }
}
