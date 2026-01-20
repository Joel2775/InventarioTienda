package ec.com.inventario.Tienda.repository;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

//se le pone el extendes JpaRepository<Entidad, Tipo de dato del ID> para poder automatixar las operaciones basicas de CRUD
public interface IProductoRepository extends JpaRepository<ProductoDTO, Long> {
}

