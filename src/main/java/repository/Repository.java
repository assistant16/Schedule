package repository;

import java.util.List;

public interface Repository<C, T, F> {

    public List<T> findAll(F item);

    public T findById(C id);

    public void create(T item);

    public void delete(String id);

    public T update(T item);

}
