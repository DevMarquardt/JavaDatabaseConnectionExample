import java.util.*;

public interface ICrud<T, ID> extends AutoCloseable{
    void inserir(T object);

    T buscarUm(ID id);

    Set<T> buscarTodos();

    void editar(T obj);

    void deletar(ID id);
}