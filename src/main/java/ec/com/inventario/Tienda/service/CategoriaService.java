package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.model.entity.Categorias;
import ec.com.inventario.Tienda.model.entity.Producto;
import ec.com.inventario.Tienda.repository.ICategoriasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {

    private final ICategoriasRepository categoriasRepository;

    public CategoriaService(ICategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    @Override
    public List<ProductoDTO> listarTodas() {
        return categoriasRepository.findAll()
                .stream()
                .map(this::pasarACategoriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        Categorias categoria = categoriasRepository.findById(id).orElse(null);

        return pasarACategoriaDTO(categoria);
    }

    @Override
    public void crear(ProductoDTO categoriaDTO) {
        Categorias categoria = pasarACategoriaEntidad(categoriaDTO);
        categoriasRepository.save(categoria);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO nuevaCategoria) {
        Categorias categoriaExistente = categoriasRepository.findById(id).orElse(null);

        if(categoriaExistente == null){
            return null;
        }

        // 2. Actualizar los campos que vienen con valor
        if(nuevaCategoria.getCategoriaNombre() != null){
            categoriaExistente.setCategoriaNombre(nuevaCategoria.getCategoriaNombre());
        }

        // 3. Guardar y retornar
        Categorias actualizada = categoriasRepository.save(categoriaExistente);
        return pasarACategoriaDTO(actualizada);
    }

    @Override
    public void eliminar(Long id) {
        categoriasRepository.deleteById(id);
    }

    public ProductoDTO pasarACategoriaDTO(Categorias categoria) {
        if (categoria == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setCategoriaId(categoria.getCategoriaId());
        dto.setCategoriaNombre(categoria.getCategoriaNombre());

        return dto;
    }

    public Categorias pasarACategoriaEntidad(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        Categorias categoria = new Categorias();
        categoria.setCategoriaNombre(dto.getCategoriaNombre());

        return categoria;
    }
}