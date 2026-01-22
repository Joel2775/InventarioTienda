package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.service.ICategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class CategoriaController {
    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<ProductoDTO>> getAll(){
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<ProductoDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    @PostMapping("/categorias")
    public ResponseEntity<Void> save(@RequestBody ProductoDTO categoria){
        categoriaService.crear(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // Actualizar solo un campo
    @PatchMapping("/categorias/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO categoria){
        return ResponseEntity.ok(categoriaService.actualizar(id, categoria));
    }
    
}
