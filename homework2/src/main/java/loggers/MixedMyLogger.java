package loggers;

import mainpackage.Application;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MixedMyLogger extends MyLogger {

    final private String filename;
    final private String tag;
    final private static  Logger logger = Logger.getLogger(Application.class.getName());

    public MixedMyLogger(String filename, String tag) {
        this.filename = filename;
        this.tag = tag;
    }

    @Override
    public void write(String str) {
        logger.info(idLoggerStr++ + " " + str);

        try {
            logger.setUseParentHandlers(false);
            FileHandler fileHandler = new FileHandler(Paths.get(filename).toAbsolutePath().toString(), true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.info("<" + tag + ">" + idLoggerStr++ + " " + str + "</" +  tag + ">");
            fileHandler.close();
        } catch (IOException exc){
            exc.printStackTrace();
        }
        logger.setUseParentHandlers(true);

    }
}
