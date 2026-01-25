package ec.com.inventario.Tienda.repository;

import ec.com.inventario.Tienda.model.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriasRepository extends JpaRepository<Categorias, Long> {

    boolean existsByCategoriaNombre(String categoriaNombre);

}

