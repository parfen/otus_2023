package ru.otus.homework;

public class Demo{

    public void action() {
        new TestLogging().calculation(6);
    }


    public static void main(String args[]){
        TestLoggingInterface myClass = ru.otus.homework.Ioc.createClass();
        myClass.calculation(6);
    }
}
