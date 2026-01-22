package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import java.util.List;

public interface ICategoriaService {

    // OBTENER TODAS LAS CATEGORÍAS
    List<ProductoDTO> listarTodas();

    // OBTENER UNA CATEGORÍA POR ID
    ProductoDTO obtenerPorId(Long id);

    // CREAR UNA NUEVA CATEGORÍA
    void crear(ProductoDTO categoriaDTO);

    // ACTUALIZAR UNA CATEGORÍA EXISTENTE
    ProductoDTO actualizar(Long id, ProductoDTO categoriaDTO);

    // ELIMINAR UNA CATEGORÍA POR ID
    void eliminar(Long id);
}