package loggers;

import mainpackage.Application;

import java.util.logging.Logger;

public class ConsoleMyLogger implements MyLogger {

    final private Logger logger = Logger.getLogger(Application.class.getName());

//    @Inject
//    public ConsoleMyLogger(Logger logger) {
//        this.logger = logger;
//    }

    @Override
    public int write(int id_str, String str) {
        logger.info(id_str++ + " " + str);
        return id_str;
    }
}
