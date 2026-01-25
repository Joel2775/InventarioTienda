package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.exception.ListaVaciaException;
import ec.com.inventario.Tienda.exception.RecursoDuplicadoException;
import ec.com.inventario.Tienda.exception.RecursoNoEcontradoException;
import ec.com.inventario.Tienda.model.dto.ProductoCreateDTO;
import ec.com.inventario.Tienda.model.dto.ProductoResponseDTO;
import ec.com.inventario.Tienda.model.dto.ProductoUpdateDTO;
import ec.com.inventario.Tienda.model.entity.Categorias;
import ec.com.inventario.Tienda.model.entity.Producto;
import ec.com.inventario.Tienda.repository.ICategoriasRepository;
import ec.com.inventario.Tienda.repository.IProductoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;
    private final ICategoriasRepository categoriasRepository;

    public ProductoService(IProductoRepository productoRepository, ICategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoResponseDTO> listarTodos() {

        List<Producto> productos = productoRepository.findAll();

        if (productos.isEmpty()) {
            throw new ListaVaciaException("No existen productos registrados");
        }

        return productos.stream()
                .map(producto -> pasarAProductoDTO(producto))
                .collect(Collectors.toList());
    }


    public ProductoResponseDTO obtenerPorId(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEcontradoException("Producto no encontrado con id: " + id));

        return pasarAProductoDTO(producto);
    }

    @Override
    public ProductoResponseDTO crear(ProductoCreateDTO productoCreateDTO) {

        if (productoRepository.existsByNumeroSerie(productoCreateDTO.getNumeroSerie())) {
            throw new RecursoDuplicadoException("Ya existe un producto con el número de serie: " + productoCreateDTO.getNumeroSerie());
        }

        Categorias categorias = categoriasRepository.findById(productoCreateDTO.getCategoriaId())
                .orElseThrow(() -> new RecursoNoEcontradoException( "Categoria no encontrada con id: " + productoCreateDTO.getCategoriaId()));

        Producto producto = pasarAProductoEntidad(productoCreateDTO, categorias);
        Producto guardado = productoRepository.save(producto);

        return pasarAProductoDTO(guardado);
    }


    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoUpdateDTO nuevoProducto) {

        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEcontradoException("Producto no encontrado con id: " + id));

        if (productoRepository.existsByNumeroSerie(nuevoProducto.getNumeroSerie())) {
            throw new RecursoDuplicadoException("Ya existe un producto con el número de serie: " + nuevoProducto.getNumeroSerie());
        }

        // 2. Actualizar los campos que vienen con valor
        if(nuevoProducto.getNumeroSerie() != null){
            productoExistente.setNumeroSerie(nuevoProducto.getNumeroSerie());
        }

        if(nuevoProducto.getNombre() != null){
            productoExistente.setNombre(nuevoProducto.getNombre());
        }

        if(nuevoProducto.getDescripcion() != null){
            productoExistente.setDescripcion(nuevoProducto.getDescripcion());
        }

        if(nuevoProducto.getPrecio() != null){
            productoExistente.setPrecio(nuevoProducto.getPrecio());
        }

        if(nuevoProducto.getStock() != null){
            productoExistente.setStock(nuevoProducto.getStock());
        }

        if (nuevoProducto.getCategoriaId() != null) {
            Categorias categoria = categoriasRepository.findById(nuevoProducto.getCategoriaId())
                    .orElseThrow(() -> new RecursoNoEcontradoException("Categoria no encontrada con id: " + nuevoProducto.getCategoriaId()));
            productoExistente.setCategoria(categoria);
        }

        // 3. Guardar y retornar
        Producto actualizado = productoRepository.save(productoExistente);
        return  pasarAProductoDTO(actualizado);

    }

    @Override
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEcontradoException("Producto no encontrado con id: " + id));

        productoRepository.delete(producto);
    }



    public ProductoResponseDTO pasarAProductoDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setProductoId(producto.getProductoId());
        dto.setNumeroSerie(producto.getNumeroSerie());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());

        if (producto.getCategoria() != null) {
            dto.setCategoriaId(producto.getCategoria().getCategoriaId());
            dto.setCategoriaNombre(producto.getCategoria().getCategoriaNombre());
        }

        return dto;
    }

    public Producto pasarAProductoEntidad(ProductoCreateDTO dto, Categorias categoria) {
        if (dto == null) {
            return null;
        }

        Producto producto = new Producto();
        producto.setNumeroSerie(dto.getNumeroSerie());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);

        return producto;
    }
}
