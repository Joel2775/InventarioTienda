package ec.com.inventario.Tienda.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long productoId;
    private String numeroSerie;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    private Long categoriaId;
    private String categoriaNombre;


}

