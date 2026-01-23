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
    public List<ProductoDTO> getCategoria() {
        List<Categorias>listaCategorias = categoriasRepository.findAll();
        return listaCategorias.stream()
                .map(categorias -> pasarACategoriaDTO(categorias))
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO findCategoria(Long id) {
        Categorias categorias = categoriasRepository.findById(id).orElse(null);
        return pasarACategoriaDTO(categorias);
    }


    @Override
    public void crearCategoria(ProductoDTO categoriaDTO) {
        Categorias categorias = categoriasRepository.findById(categoriaDTO.getProductoId()).orElse((null));
        categoriasRepository.save(categorias);
    }


    @Override
    public ProductoDTO actualizarCategoria(Long id, ProductoDTO datosNuevos) {
        Categorias categoriaExistente = categoriasRepository.findById(id).orElse(null);

        if(categoriaExistente == null){
            return null;
        }

        // 2. Actualizar los campos que vienen con valor
        if(datosNuevos.getCategoriaNombre() != null){
            categoriaExistente.setCategoriaNombre(datosNuevos.getCategoriaNombre());
        }

        // 3. Guardar y retornar
        Categorias actualizado = categoriasRepository.save(categoriaExistente);
        return  pasarACategoriaDTO(actualizado);
    }

    @Override
    public void eliminarCategoria(Long id) {
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
