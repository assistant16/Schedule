package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SQLMapper<C,T,F> {
    public C getKey(T item);

    public C setKey(T item,int size);

    public List<T> getData(Connection connection,F item) throws SQLException;

    public void createData(Connection connection,T item) throws SQLException;

    public T findOne(Connection connection,C id) throws Exception;
}
