package ec.com.inventario.Tienda.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoUpdateDTO {

    private String numeroSerie;
    private String nombre;
    private String descripcion;

    @Positive
    private Double precio;

    @Min(0)
    private Integer stock;

    private Long categoriaId;

}

