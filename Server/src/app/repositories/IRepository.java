package app.repositories;

import java.util.List;

public interface IRepository<T> {
    List<T> getAll(T param) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
    List<T> getAll();
}
