package ru.otus.homework.test;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

public class TestClass {

    @Before
    public void beforeMethod(){
        System.out.println("Method Before");
    }

    @After
    public void afterMethod(){
        System.out.println("Method After");
    }

    @Test
    public void test1(){
        System.out.println("Test1");
    }

    @Test
    public void test2ReturnException(){
        System.out.println("Test2");
        throw new RuntimeException("Error Test2");
    }

    @Test
    public void test3(){
        System.out.println("Test3");
    }
}
