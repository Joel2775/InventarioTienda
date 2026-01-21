package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.model.entity.Categorias;
import ec.com.inventario.Tienda.model.entity.Producto;
import ec.com.inventario.Tienda.repository.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(producto -> {
                    ProductoDTO dto = new ProductoDTO();
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
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
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

    @Override
    public void crear(ProductoDTO productoDTO) {
        Producto producto = new Producto();

        producto.setNumeroSerie(productoDTO.getNumeroSerie());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        //producto.setCategoria(productoDTO.getCategoriaId());

        productoRepository.save(producto);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO nuevoProducto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);

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

        // 3. Guardar y retornar
        Producto actualizado = productoRepository.save(productoExistente);
        actualizado.setNumeroSerie(nuevoProducto.getNumeroSerie());
        actualizado.setNombre(nuevoProducto.getNombre());
        actualizado.setDescripcion(nuevoProducto.getDescripcion());
        actualizado.setPrecio(nuevoProducto.getPrecio());
        actualizado.setStock(nuevoProducto.getStock());
        return nuevoProducto;


    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
