package ru.otus.jdbc.mapper;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData{
    private EntityClassMetaData<T> entityClassMetaDataClient;
    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaDataClient){
        this.entityClassMetaDataClient =  entityClassMetaDataClient;
    }

    @Override
    public String getSelectAllSql() {
        return null;
    }

    @Override
    public String getSelectByIdSql() {
        return null;
    }

    @Override
    public String getInsertSql() {




        return "insert into client(name) values (?)";
    }

    @Override
    public String getUpdateSql() {
        return null;
    }
}
