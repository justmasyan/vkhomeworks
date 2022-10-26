package mainpackage;

import com.google.inject.Guice;
import com.google.inject.Injector;
import configuration.Configuration;
import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(@NotNull String[] args) {
        final Injector injector = Guice.createInjector(new Configuration(args));
        injector.getInstance(Application.class).waitForInput();
    }
}
