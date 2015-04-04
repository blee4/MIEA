package mea.riotApi;

/**
 * CLass for retrieving API developer-specific resources.
 * TODO This should be git ignored.
 */
public class DevResource
{
    private static final String FILE_PATH = "C:\\Users\\Soprano\\Desktop\\";
    private static final String DEV_KEY_FILENAME = "RiotDeveloperApiKey.txt";

    /**
     * @return Gets the full path to the developer API key.
     */
    public static String getDevKey()
    {
        return FILE_PATH + DEV_KEY_FILENAME;
    }
}
