package loggers;

public abstract class MyLogger {
    protected int idLoggerStr = 0;

    public abstract void write(String str);
}
