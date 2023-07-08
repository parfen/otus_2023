package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData{
    private EntityClassMetaData<T> entityClassMetaData;

    private static final String SELECT = "select %s from %s";
    private static final String SELECT_WITH_WHERE = SELECT + " where %s";
    private static final String INSERT = "insert into %s (%s) values (%s)";
    private static final String UPDATE = "update %s set %s where s%";
    private final List<String> nameFields;
    private final List<String> nameFieldsWithoutId;
    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaData){
        this.entityClassMetaData =  entityClassMetaData;
        nameFields = entityClassMetaData.getAllFields().stream().map(e->e.getName()).collect(Collectors.toList());
        nameFieldsWithoutId = entityClassMetaData.getFieldsWithoutId().stream().map(e->e.getName()).collect(Collectors.toList());
    }

    @Override
    public String getSelectAllSql() {
        return String.format(SELECT, String.join(", ", nameFields),  entityClassMetaData.getName());
    }

    @Override
    public String getSelectByIdSql() {
        Field idField = entityClassMetaData.getIdField();
        return String.format(SELECT_WITH_WHERE, String.join(", ", nameFields),  entityClassMetaData.getName(), idField.getName() + " = ?");
    }

    @Override
    public String getInsertSql() {
        return String.format(INSERT, entityClassMetaData.getName(), String.join(", ",nameFieldsWithoutId),  String.join(",", Collections.nCopies(nameFieldsWithoutId.size(), "?")));
    }

    @Override
    public String getUpdateSql() {
        Field idField = entityClassMetaData.getIdField();
        return String.format(UPDATE,entityClassMetaData.getName(), String.join(" =?, ",nameFieldsWithoutId), idField.getName() + " = ?");
    }
}
