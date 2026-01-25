package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.exception.ListaVaciaException;
import ec.com.inventario.Tienda.exception.RecursoDuplicadoException;
import ec.com.inventario.Tienda.exception.RecursoNoEcontradoException;
import ec.com.inventario.Tienda.model.dto.CategoriaCreateDTO;
import ec.com.inventario.Tienda.model.dto.CategoriaResponseDTO;
import ec.com.inventario.Tienda.model.dto.CategoriaUpdateDTO;
import ec.com.inventario.Tienda.model.entity.Categorias;
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
    public List<CategoriaResponseDTO> getCategoria(){
        List<Categorias>listarCategorias =categoriasRepository.findAll();

        if (listarCategorias.isEmpty()) {
            throw new ListaVaciaException("No existen categorias registradas");
        }

        return listarCategorias.stream()
                .map(categoria  -> pasarACategoriaDTO(categoria ))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponseDTO findCategoria(Long id) {
        Categorias categoria = categoriasRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEcontradoException("Categoria no encontrada con id: " + id));
        return pasarACategoriaDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaCreateDTO categoriaCreateDTO){
        if (categoriasRepository.existsByCategoriaNombre(categoriaCreateDTO.getCategoriaNombre())) {
            throw new RecursoDuplicadoException("La categoria con nombre " + categoriaCreateDTO.getCategoriaNombre() + " ya existe.");
        }

        Categorias categoriaExistente = pasarACategoriaEntidad(categoriaCreateDTO);
        Categorias guardado = categoriasRepository.save(categoriaExistente);

        return pasarACategoriaDTO(guardado);
    }


    @Override
    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaUpdateDTO datosNuevos){
        Categorias categoriaExistente = categoriasRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEcontradoException("Categoria no encontrada con id: " + id));

        if (!datosNuevos.getCategoriaNombre().equalsIgnoreCase(categoriaExistente.getCategoriaNombre()) &&
                categoriasRepository.existsByCategoriaNombre(datosNuevos.getCategoriaNombre())) {

            throw new RecursoDuplicadoException(
                    "La categoria con nombre " + datosNuevos.getCategoriaNombre() + " ya existe."
            );
        }

        if(datosNuevos.getCategoriaNombre() != null){
            categoriaExistente.setCategoriaNombre(datosNuevos.getCategoriaNombre());
        }

        Categorias actualizado = categoriasRepository.save(categoriaExistente);
        return pasarACategoriaDTO(actualizado);
    }

    public CategoriaResponseDTO pasarACategoriaDTO (Categorias categoria){
        if(categoria == null){
            return null;
        }
        CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO();
        categoriaDTO.setCategoriaId(categoria.getCategoriaId());
        categoriaDTO.setCategoriaNombre(categoria.getCategoriaNombre());
        return categoriaDTO;
    }

    public Categorias pasarACategoriaEntidad (CategoriaCreateDTO categoriaDTO){
        if(categoriaDTO == null){
            return null;
        }
        Categorias categoria = new Categorias();
        categoria.setCategoriaNombre(categoriaDTO.getCategoriaNombre());
        return categoria;
    }
}
