package ru.otus.homework;

import ru.otus.homework.annotations.After;
import ru.otus.homework.annotations.Before;
import ru.otus.homework.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

public class RunTest {
    private static Logger logger = Logger.getLogger(RunTest.class.toString());
    public static void main(String[] args) throws Exception {
        if(args.length==0){
            throw new RuntimeException("No arguments");
        }
        //ru.otus.homework.test.TestClass
        String nameClass = args[0];
        Class<?> clazz = Class.forName(nameClass);

        logger.info("SimpleNameClass = "+clazz.getSimpleName());
        Map<String,Class<?>> methodsClassWithAnnotation = searchMethodTests(clazz);
        Map<String, Object> resultRunTests = executeTests(clazz,methodsClassWithAnnotation);
        printResult(resultRunTests);
    }

    private static void printResult(Map<String, Object> resultRunTests) {
        logger.info("Size tests "+ resultRunTests.size());
        List<String> errorTest = new ArrayList<>();
        List<String> goodTest = new ArrayList<>();

        resultRunTests.entrySet().stream().forEach(entry-> {
            if(entry.getValue() instanceof Exception){
                errorTest.add(entry.getKey());
            }else{
                goodTest.add(entry.getKey());
            }
        });
        logger.info("Size error tests "+errorTest.size());
        logger.info("Error tests "+ errorTest);

        logger.info("Size good tests "+goodTest.size());
        logger.info("Good tests "+ goodTest);
    }

    private static Map<String, Object> executeTests(Class<?> clazz, Map<String, Class<?>> methodsClassWithAnnotation) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<String, Object> resultRunTests = new HashMap<>();
        String beforeMethodName = searchSpecialMethod(methodsClassWithAnnotation,Before.class);
        String afterMethodName = searchSpecialMethod(methodsClassWithAnnotation,After.class);
        for (Map.Entry<String, Class<?>> methods : methodsClassWithAnnotation.entrySet()) {
            if (methods.getValue().equals(Test.class)) {
                Object object = clazz.getConstructor().newInstance();
                Class<? extends Object> mainClass = object.getClass();

                Method methodBefore = mainClass.getMethod(beforeMethodName);
                Method methodTest = mainClass.getMethod(methods.getKey());
                Method methodAfter = mainClass.getMethod(afterMethodName);
                try {
                    methodBefore.invoke(object);
                    methodTest.invoke(object);
                    resultRunTests.put(methods.getKey(), object);
                } catch (Exception e) {
                    resultRunTests.put(methods.getKey(), e);
                }finally {
                    methodAfter.invoke(object);
                }
            }
        }
        return resultRunTests;
    }

    private static String searchSpecialMethod(Map<String, Class<?>> methodsClassWithAnnotation, Class<?> clazz) {
        return methodsClassWithAnnotation
                .entrySet()
                .stream()
                .filter(entry -> clazz.equals(entry.getValue()))
                .map(Map.Entry::getKey).findFirst().get();
    }

    private static Map<String,Class<?>> searchMethodTests(Class<?> clazz) {
        Map<String,Class<?>> methodsClassWithAnnotation = new HashMap<>();
        for (Method method : clazz.getDeclaredMethods()) {
            Annotation[] annotations = method.getAnnotations();
            for(Annotation annotation: annotations) {
                if (annotation instanceof Before ||
                        annotation instanceof After ||
                        annotation instanceof Test) {
                    methodsClassWithAnnotation.put(method.getName(),annotation.annotationType());
                }
            }
        }
        return methodsClassWithAnnotation;
    }
}
