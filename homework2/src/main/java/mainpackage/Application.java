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
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            int count = 0;
            while (true) {
                String str = scanner.nextLine();
                count = mylogger.write(count,str);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Закончили упражнение");
        }
    }
}
