package ru.otus.jdbc.mapper;

import ru.otus.annotations.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;


public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T>{

    private final Class<?> clazz;
    private final List<Field> fields;
    private final String name;

    public EntityClassMetaDataImpl(T obj){
        clazz = obj.getClass();
        fields = List.of(clazz.getDeclaredFields());
        name = clazz.getSimpleName().toLowerCase();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override

    public Constructor<T> getConstructor() {
        try {
            return (Constructor<T>) clazz.getConstructor();
        }catch (NoSuchMethodException e){
            return null;
        }
    }

    @Override
    public Field getIdField() {
        return getFieldsWithAnnotation(Id.class).size() > 0 ? getFieldsWithAnnotation(Id.class).get(0) : null;
    }

    @Override
    public List<Field> getAllFields() {
        return fields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fields.stream().filter(field -> !field.isAnnotationPresent(Id.class)).toList();
    }

    private List<Field> getFieldsWithAnnotation(Class<? extends Annotation> annotation){
        return fields.stream().filter(field -> field.isAnnotationPresent(annotation)).toList();
    }
}
