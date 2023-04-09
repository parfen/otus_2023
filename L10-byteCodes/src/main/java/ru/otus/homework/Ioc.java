package ru.otus.homework;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(checkMethodsWithAnnotationLog(method)){
                System.out.println("executed method:" + method.getName() + ", param: " + Arrays.asList(args));
            }
            return method.invoke(myClass, args);
        }

        private boolean checkMethodsWithAnnotationLog(Method method) {
            List<Method> methods = Arrays.stream(myClass.getClass().getMethods()).filter(m->m.getName().equals(method.getName())).toList();
            for(var m:methods) {
                var annotation = m.getDeclaredAnnotation(Log.class);
                if (annotation == null) {
                    continue;
                }
                var paramTypes = m.getParameterTypes();
                return Arrays.equals(paramTypes, method.getParameterTypes());
            }
            return false;
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}
