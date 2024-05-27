package application.services;

import java.util.List;

public interface IService<T, R> {

    List<T> listar();

    T guardar(R entity);

    T buscarPorPK(String pk);
    T buscarPorId(Integer id);

}
