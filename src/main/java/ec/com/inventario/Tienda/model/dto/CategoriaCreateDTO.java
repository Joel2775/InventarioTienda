package ec.com.inventario.Tienda.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaCreateDTO {

    @NotBlank(message = "El nombre de la categoria es obligatorio")
    private String categoriaNombre;
}
