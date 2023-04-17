package ru.otus.homework;

public class TestLogging  implements TestLoggingInterface {
    @Log
    @Override
    public void calculation(int param1) {
    }

    @Log
    @Override
    public void calculation(int param1, String param2) {
    }
}
