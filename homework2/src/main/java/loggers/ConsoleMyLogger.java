package loggers;

import mainpackage.Application;

import java.util.logging.Logger;

public class ConsoleMyLogger extends MyLogger {

    final static private Logger logger = Logger.getLogger(Application.class.getName());

    @Override
    public void write(String str) {
        logger.info(  idLoggerStr++ + " " + str);

    }
}
