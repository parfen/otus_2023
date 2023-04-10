package ru.otus.homework;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createClass(TestLoggingInterface myClass) {
        InvocationHandler handler = new DemoInvocationHandler(myClass);
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;

        private final List<Method> methodsWithAnnotationLog = new ArrayList<>();
        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            methodsWithAnnotationLog.addAll(Arrays.stream(myClass.getClass().getMethods())
                    .filter(m-> checkAnnotationLog(m.getAnnotation(Log.class))).toList());
        }

        private boolean checkAnnotationLog(Annotation annotation){
            return annotation!=null;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(checkMethodsWithAnnotationLog(method)){
                System.out.println("executed method:" + method.getName() + ", param: " + Arrays.asList(args));
            }
            return method.invoke(myClass, args);
        }

        private boolean checkMethodsWithAnnotationLog(Method method) {
            List<Method> methods = methodsWithAnnotationLog.stream()
                    .filter(m->m.getName().equals(method.getName())).toList();
            return methods.stream().anyMatch(m-> Arrays.equals(m.getParameterTypes(), method.getParameterTypes()));
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
