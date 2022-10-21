package configuration;

import com.google.inject.AbstractModule;
import loggers.*;

public class Configuration extends AbstractModule {

    private final String[] args;
    private final static String filename = "logs/output.log";

    public Configuration(String[] args) {
        this.args = args;
    }

    @Override
    protected void configure() {

        if (args.length == 0) {
            System.out.println("Параметры не введены - применяется стандартная настройка: логгирование в консоль");
            bind(MyLogger.class).to(ConsoleMyLogger.class);
            return;
        }

        if (args[0].equals("-c")) {

            bind(MyLogger.class).to(ConsoleMyLogger.class);

        } else {
            String tag = (args.length < 2) ? "a" : args[1];

            if (args[0].equals("-f")) {
                bind(MyLogger.class).toInstance(new FileMyLogger(filename, tag));
            } else if (args[0].equals("-m")) {
                bind(MyLogger.class).toInstance(new MixedMyLogger(filename, tag));
            } else {
                bind(MyLogger.class).to(ConsoleMyLogger.class);
            }
        }
    }
}
