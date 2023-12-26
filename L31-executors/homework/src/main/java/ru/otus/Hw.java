package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Hw {
    public static final Logger logger = LoggerFactory.getLogger(Hw.class);
    private String last = "Поток2";
    private boolean isUp = true;

    public static void main(String[] args) {
        new Hw().run();
    }

    private void run() {

        var thread1 = new Thread(() -> action("Поток1"));
        thread1.start();

        var thread2 = new Thread(() -> action("Поток2"));
        thread2.start();
    }


    private synchronized void action(String message) {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (last.equals(message)) {
                    this.wait();
                }
                last = message;
                count = getNumber(count);
                logger.info("{} : {}", message, count);

                sleep();
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getNumber(int n) {
        if (n == 10) {
            isUp = false;
        } else if (n == 1) {
            isUp = true;
        }
        return isUp ? ++n : --n;
    }

    private static void sleep() {
        try {
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
