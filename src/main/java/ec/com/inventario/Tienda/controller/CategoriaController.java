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
}
