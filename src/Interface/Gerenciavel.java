package src.Interface;

import java.util.List;

public interface Gerenciavel<T> {

    boolean cadastrar(T objeto);

    T buscarPorId(String id);

    List<T> listarTodos();

    boolean atualizar(String id, T objeto);

    boolean remover(String id);
}