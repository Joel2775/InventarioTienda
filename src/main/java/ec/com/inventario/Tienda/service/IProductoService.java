package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoCreateDTO;
import ec.com.inventario.Tienda.model.dto.ProductoResponseDTO;
import ec.com.inventario.Tienda.model.dto.ProductoUpdateDTO;

import java.util.List;

public interface IProductoService {

    // OBTENER TODOS LOS PRODUCTOS
    List<ProductoResponseDTO> listarTodos();

    // OBTENER UN PRODUCTO POR ID
    ProductoResponseDTO obtenerPorId(Long id);

    // CREAR UN NUEVO PRODUCTO
    ProductoResponseDTO crear(ProductoCreateDTO productoCreateDTO);

    // ACTUALIZAR UN PRODUCTO EXISTENTE
    ProductoResponseDTO actualizar(Long id, ProductoUpdateDTO productoUpdateDTO);

    // ELIMINAR UN PRODUCTO POR ID
    void eliminar(Long id);
}
