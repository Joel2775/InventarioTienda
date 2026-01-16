package ec.com.inventario.Tienda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size(min = 2, max = 50)
        @Column(nullable = false, length = 50)
        private String nombre;

        @NotBlank
        @Column(nullable = false, columnDefinition = "TEXT")
        private String descripcion;

        @NotNull
        @Min(0)
        private Double precio;

        @NotNull
        @Min(0)
        private Integer stock;

        @NotBlank
        private String categoria;
    }

