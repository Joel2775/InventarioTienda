package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.entity.Categorias;

import java.util.List;

public interface ICategoriaService {

    // OBTENER TODAS LAS CATEGORÍAS
    public List<Categorias> getCategoria();

    // OBTENER UNA CATEGORÍA POR ID
    public Categorias findCategoria(Long id);

    // CREAR UNA NUEVA CATEGORÍA
    public void crearCategoria(Categorias categorias);

    // ACTUALIZAR UNA CATEGORÍA EXISTENTE
    public Categorias actualizarCategoria(Long id, Categorias datosNuevos);

    // ELIMINAR UNA CATEGORÍA POR ID
    public void eliminarCategoria(Long id);
}
