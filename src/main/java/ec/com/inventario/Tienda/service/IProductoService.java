package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.Producto;

import java.util.List;

public interface IProductoService {

    List<Producto> listarTodos();
    Producto obtenerPorId(Long id);
    Producto crear(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
