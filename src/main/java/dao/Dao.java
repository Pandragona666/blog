package dao;
import io.vavr.collection.List;

public interface Dao<T, U> {

    List<T> getAll();

    T get(long id);

    void save(U obj); //ponieważ zapisujey tylko raz, artykuł który nie ma jeszcze id, wiec używamy "U"

    void delete(long id);

    void update(long id);

}
