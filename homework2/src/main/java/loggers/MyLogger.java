package loggers;

public abstract class MyLogger {
    protected int id_str = 0;

    public abstract void write(String str);
}
