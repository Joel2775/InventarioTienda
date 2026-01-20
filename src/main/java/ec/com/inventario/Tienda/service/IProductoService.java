package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.model.entity.Producto;

import java.util.List;

public interface IProductoService {

    // OBTENER TODOS LOS PRODUCTOS
    List<ProductoDTO> listarTodos();

    // OBTENER UN PRODUCTO POR ID
    ProductoDTO obtenerPorId(Long id);

    // CREAR UN NUEVO PRODUCTO
    void crear(ProductoDTO productoDTO);

    // ACTUALIZAR UN PRODUCTO EXISTENTE
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);

    // ELIMINAR UN PRODUCTO POR ID
    void eliminar(Long id);
}
