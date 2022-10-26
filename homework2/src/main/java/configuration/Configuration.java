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

        if ("-c".equals(args[0])) {

            bind(MyLogger.class).to(ConsoleMyLogger.class);

        } else {
            String tag = (args.length < 2) ? "a" : args[1];

            if ("-f".equals(args[0])) {
                bind(MyLogger.class).toInstance(new FileMyLogger(filename, tag));
            } else if ("-m".equals(args[0])) {
                bind(MyLogger.class).toInstance(new MixedMyLogger(filename, tag));
            } else {
                bind(MyLogger.class).to(ConsoleMyLogger.class);
            }
        }
    }
}
