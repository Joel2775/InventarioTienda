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
public class ProductoCreateDTO {
    @NotBlank(message = "El número de serie es obligatorio")
    private String numeroSerie;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull
    @Min(0)
    private Double precio;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotNull
    private Long categoriaId;
}
