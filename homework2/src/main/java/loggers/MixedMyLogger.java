package loggers;

import mainpackage.Application;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MixedMyLogger implements MyLogger {
//    @Inject
//    private Logger logger;

    final private String filename;
    final private String tag;
    final private Logger logger = Logger.getLogger(Application.class.getName());

    public MixedMyLogger(String filename, String tag) {
        this.filename = filename;
        this.tag = tag;
    }

    @Override
    public int write(int id_str, String str) {
        logger.info(id_str++ + " " + str);

        try {
            logger.setUseParentHandlers(false);
            FileHandler fileHandler = new FileHandler(filename,true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.info("<" + tag + ">" + id_str++ + " " + str + "</" +  tag + ">");
            fileHandler.close();
        } catch (IOException exc){
            exc.printStackTrace();
        }
        logger.setUseParentHandlers(true);
        return id_str;
    }
}
