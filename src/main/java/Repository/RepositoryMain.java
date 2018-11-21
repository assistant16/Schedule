package Repository;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import java.util.List;


public class RepositoryMain<C,T,F> implements Repository<C, T, F> {
    SQLMapper<C, T, F> mapper;
    DataSource dataSource;

    public RepositoryMain(SQLMapper<C, T, F> mapper, DataSource dataSource){
        this.mapper = mapper;
        this.dataSource = dataSource;
    }

    @Override
    public List<T> findAll(F item){
        List<T> items = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()){
            items.addAll(mapper.getData(connection,item));
        } catch (Throwable ex) {
           throw new RuntimeException(ex);
        }
        return items;
    }

    @Override
    public T findById(C id) {
        try (Connection connection = dataSource.getConnection()) {
           return mapper.findOne(connection, id);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void create(T item) {
        try(Connection connection = dataSource.getConnection()){
            mapper.createData(connection,item);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public T update(T item) {
        return null;
    }

}




