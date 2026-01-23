package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import java.util.List;

public interface ICategoriaService {

    // OBTENER TODAS LAS CATEGORÍAS
    public List<ProductoDTO> getCategoria();

    // OBTENER UNA CATEGORÍA POR ID
    public ProductoDTO findCategoria(Long id);

    // CREAR UNA NUEVA CATEGORÍA
    public void crearCategoria(ProductoDTO categoriaDTO);

    // ACTUALIZAR UNA CATEGORÍA EXISTENTE
    public ProductoDTO actualizarCategoria(Long id, ProductoDTO datosNuevos);

    // ELIMINAR UNA CATEGORÍA POR ID
    public void eliminarCategoria(Long id);
}
