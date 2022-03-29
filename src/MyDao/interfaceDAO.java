package MyDao;

import java.util.List;

public interface interfaceDAO<T> {
    T get (int id);
    List getAll();
    void add(T t);
    void remove(T t);
    void update(T t);
}
