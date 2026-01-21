package ec.com.inventario.Tienda.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long productoId;

        @NotBlank
        @Size(min = 2, max = 50)
        @Column(nullable = false, length = 50, unique = true)
        private String numeroSerie;

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

        @ManyToOne
        @JoinColumn(name = "categoria_id", nullable = false)
        private Categorias categoria;
    }

