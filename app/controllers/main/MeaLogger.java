package controllers.main;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MeaLogger
{
    private static final Logger MEA_LOGGER = Logger.getLogger("MEA-Logger");

    public MeaLogger()
    {
        // forgot
    }

    /**
     * Typically used for logging catastrophic events.
     * @param level SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
     * @param message The description of the log entry.
     * @param e The exception message to log.
     */
    public static void log(final Level level, final String message, final Exception e)
    {
        MEA_LOGGER.log(level, message, e);
    }

    /**
     * Typically used for non-severe level logging.
     * @param level SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
     * @param message The description of the log entry.
     */
    public static void log(final Level level, final String message)
    {
        MEA_LOGGER.log(level, message);
    }
}
