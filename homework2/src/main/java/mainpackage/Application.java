package mainpackage;

import loggers.MyLogger;

import javax.inject.Inject;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    final private MyLogger mylogger;

    @Inject
    public Application(MyLogger mylogger) {
        this.mylogger = mylogger;
    }

    public void waitForInput(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.setProperty("java.util.logging.SimpleFormatter.format",
                    "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");

            while (true) {
                String str = scanner.nextLine();
                mylogger.write(str);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Закончили упражнение");
        }
    }
}
