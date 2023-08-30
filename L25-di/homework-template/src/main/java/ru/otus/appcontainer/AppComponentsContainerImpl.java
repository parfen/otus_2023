package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        // You code here...
        Method[] methods = configClass.getMethods();
        Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AppComponent.class))
                .sorted(Comparator.comparingInt(method -> method.getAnnotation(AppComponent.class).order()))
                .forEachOrdered(method -> createComponent(createConstructor(configClass), method));
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> C getAppComponent(Class<C> componentClass) {
        List<Object> components = new ArrayList<>();
        components.addAll(appComponents.stream().filter(object -> componentClass.isAssignableFrom(object.getClass())).toList());
        if(components.isEmpty() || components.size() > 1){
           throw new IllegalArgumentException("В контексте не должно быть компонентов с одинаковым именем");
        }
        return (C) components.get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <C> C getAppComponent(String componentName) {
        if(!appComponentsByName.containsKey(componentName)){
            throw new IllegalArgumentException("В контексте не должно быть компонентов с одинаковым именем");
        }
        return (C) appComponentsByName.get(componentName);
    }

    private Object createConstructor(Class<?> configClass) {
        Object appComponents = null;
        try {
            appComponents = configClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException(String.format("Для класса %S нет конструктора по умолчанию",
                    configClass.getName()));
        }
        return appComponents;
    }
    private void createComponent(Object configObject, Method method) {
        Object[] args = Arrays.stream(method.getParameters())
                .map(Parameter::getType)
                .map(this::getAppComponent)
                .filter(Objects::nonNull)
                .toArray(Object[]::new);

        Object appComponent = createObject(configObject, method, args);
        if (appComponents.contains(appComponent)){
            throw new IllegalArgumentException("В контексте не должно быть компонентов с одинаковым именем");
        }
        if (appComponentsByName.containsKey (method.getAnnotation(AppComponent.class).name())){
            throw new IllegalArgumentException("В контексте не должно быть компонентов с одинаковым именем");
        }
        appComponents.add(appComponent);
        appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), appComponent);
    }

    private Object createObject(Object configObject, Method method, Object... args) {
        try {
            return method.invoke(configObject, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(String.format("Не возможно создать экземпляр класса %s", method.getReturnType().getName()), e);
        }
    }
}
