package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.service.IProductoService;
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
    public ResponseEntity<List<ProductoDTO>> getAll(){
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<String> save(@RequestBody ProductoDTO producto){
        productoService.crear(producto);
        return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
    }
    // Actualizar solo un campo
    @PatchMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO producto){
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
