package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.repository.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> listarTodos() {
        List<ProductoDTO> listaProductoDTO = productoRepository.findAll();
        return listaProductoDTO;
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void crear(ProductoDTO producto) {
        productoRepository.save(producto);

    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO nuevoProducto) {
        ProductoDTO productoExistente = productoRepository.findById(id).orElse(null);

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
        return productoRepository.save(productoExistente);


    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
