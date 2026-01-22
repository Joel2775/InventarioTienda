package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
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
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(producto -> {return pasarAProductoDTO(producto);}).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null); // aqui deberia manejar el caso de producto no encontrado con throw o similar

        return pasarAProductoDTO(producto);
    }

    @Override
    public void crear(ProductoDTO productoDTO) {
        Categorias categorias = categoriasRepository
                .findById(productoDTO.getCategoriaId())
                .orElse(null); // aqui deberia manejar el caso de categoria no encontrada con throw o similar

        Producto producto = pasarAProductoEntidad(productoDTO, categorias);

        productoRepository.save(producto);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO nuevoProducto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null); // aqui deberia manejar el caso de producto no encontrado con throw o similar

        if(productoExistente == null){
            return null;
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
                .orElse(null); // aqui deberia manejar el caso de categoria no encontrada con throw o similar
            productoExistente.setCategoria(categoria);
        }

        // 3. Guardar y retornar
        Producto actualizado = productoRepository.save(productoExistente);
        return  pasarAProductoDTO(actualizado);

    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }


    public ProductoDTO pasarAProductoDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
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

    public Producto pasarAProductoEntidad(ProductoDTO dto, Categorias categoria) {
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
