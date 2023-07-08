package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сохраняет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;

    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), rs->{
            try {
                if (rs.next()) {
                    return createInstanceEntity(rs);
                }
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
            return null;
        });
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(), List.of(), rs ->{
            try {
                List<T> result = new ArrayList<T>();
                while (rs.next()) {
                    result.add(createInstanceEntity(rs));
                }
                return result;
            } catch (SQLException e) {
                throw new DataTemplateException(e);
            }
        }).get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public long insert(Connection connection, T entity) {
        return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), getParamsEntityWithoutId(entity));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Connection connection, T entity) {
        List<Object> params = getParamsEntityWithoutId(entity);
        try {
            params.add(entityClassMetaData.getIdField().get(entity));
        } catch (IllegalAccessException e) {
            throw new DataTemplateException(e);
        }
        dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), params);
    }
    @SuppressWarnings("unchecked")
    private List<Object> getParamsEntityWithoutId(T entity)  {
        List<Object> params = new ArrayList<Object>();
        try {
            for (Field field : entityClassMetaData.getFieldsWithoutId()) {
                field.setAccessible(true);
                params.add(field.get(entity));
            }
        } catch (IllegalAccessException e){
            throw new DataTemplateException(e);
        }
        return params;
    }
    @SuppressWarnings("unchecked")
    private T createInstanceEntity(ResultSet rs){
        try{
            var constructor = entityClassMetaData.getConstructor();
            T instanceEntity = constructor.newInstance();
            for(Field field : entityClassMetaData.getAllFields()){
                field.setAccessible(true);
                field.set(instanceEntity,rs.getObject(field.getName()));
            }
            return instanceEntity;
        }catch (Exception e){
            return null;
        }
    }

}
