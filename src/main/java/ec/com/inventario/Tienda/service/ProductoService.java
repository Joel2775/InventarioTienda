package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.Producto;
import ec.com.inventario.Tienda.repository.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private final IProductoRepository repository;

    public ProductoService(IProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> listarTodos() {
        return List.of();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return null;
    }

    @Override
    public Producto crear(Producto producto) {
        return null;
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
