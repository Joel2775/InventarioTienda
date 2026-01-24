package ec.com.inventario.Tienda.service;

import ec.com.inventario.Tienda.model.entity.Categorias;
import ec.com.inventario.Tienda.repository.ICategoriasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    private final ICategoriasRepository categoriasRepository;

    public CategoriaService(ICategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    @Override
    public List<Categorias> getCategoria(){
        List<Categorias>listarCategorias =categoriasRepository.findAll();
        return listarCategorias;
    }

    @Override
    public Categorias findCategoria(Long id){
        return categoriasRepository.findById(id).orElse(null);
    }

    @Override
    public void crearCategoria(Categorias categorias){
        categoriasRepository.save(categorias);
    }


    @Override
    public Categorias actualizarCategoria(Long id, Categorias datosNuevos){
        Categorias categoriaExistente = categoriasRepository.findById(id).orElse(null);

        if(categoriaExistente == null){
            return null;
        }

        if(datosNuevos.getCategoriaId() != null){
            categoriaExistente.setCategoriaId((datosNuevos.getCategoriaId()));
        }

        if(datosNuevos.getCategoriaNombre() != null){
            categoriaExistente.setCategoriaNombre(datosNuevos.getCategoriaNombre());
        }

        return categoriasRepository.save(categoriaExistente);
    }

    @Override
    public void eliminarCategoria(Long id){
        categoriasRepository.deleteById(id);
    }
}


