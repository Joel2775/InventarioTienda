package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.model.dto.ProductoCreateDTO;
import ec.com.inventario.Tienda.model.dto.ProductoResponseDTO;
import ec.com.inventario.Tienda.model.dto.ProductoUpdateDTO;
import ec.com.inventario.Tienda.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class ProductoController {
    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoResponseDTO>> getAll(){
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoResponseDTO> save( @Valid @RequestBody ProductoCreateDTO producto) {
        ProductoResponseDTO creado = productoService.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Actualizar solo un campo
    @PatchMapping("/productos/{id}")
    public ResponseEntity<ProductoResponseDTO> update(@PathVariable Long id,@Valid @RequestBody ProductoUpdateDTO producto){
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
