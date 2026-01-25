package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.model.dto.CategoriaCreateDTO;
import ec.com.inventario.Tienda.model.dto.CategoriaResponseDTO;
import ec.com.inventario.Tienda.model.dto.CategoriaUpdateDTO;
import ec.com.inventario.Tienda.service.ICategoriaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<CategoriaResponseDTO>> getAll(){
        return ResponseEntity.ok(categoriaService.getCategoria());
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findCategoria(id));
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseDTO> save(@Valid @RequestBody CategoriaCreateDTO categorias){
        return ResponseEntity.ok(categoriaService.crearCategoria(categorias));
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriaUpdateDTO categorias){
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categorias));
    }
}
