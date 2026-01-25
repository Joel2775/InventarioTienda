package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.CategoriaCreateDTO;
import ec.com.inventario.Tienda.model.dto.CategoriaResponseDTO;
import ec.com.inventario.Tienda.model.dto.CategoriaUpdateDTO;
import ec.com.inventario.Tienda.model.entity.Categorias;

import java.util.List;

public interface ICategoriaService {

    // OBTENER TODAS LAS CATEGORÍAS
    public List<CategoriaResponseDTO> getCategoria();

    // OBTENER UNA CATEGORÍA POR ID
    public CategoriaResponseDTO findCategoria(Long id);

    // CREAR UNA NUEVA CATEGORÍA
    public CategoriaResponseDTO crearCategoria(CategoriaCreateDTO categoriaCreateDTO);

    // ACTUALIZAR UNA CATEGORÍA EXISTENTE
    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaUpdateDTO categoriaUpdateDTO);

    // ELIMINAR UNA CATEGORÍA POR IDY
    // public void eliminarCategoria(Long id);
}
