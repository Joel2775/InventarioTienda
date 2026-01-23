package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.service.ICategoriaService;
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
    public List<ProductoDTO> getAll(){
        return categoriaService.getCategoria();
    }

    @GetMapping("/categorias/{id}")
    public ProductoDTO getById(@PathVariable Long id){
        return categoriaService.findCategoria(id);
    }

    @PostMapping("/categorias")
    public String save(@RequestBody ProductoDTO categoriasDTO){
        categoriaService.crearCategoria(categoriasDTO);
        return "La categoria fue creada correctamente";
    }

    // Actualizar solo un campo
    @PatchMapping("/{id}")
    public ProductoDTO update(@PathVariable Long id, @RequestBody ProductoDTO categorias){
        return categoriaService.actualizarCategoria(id, categorias);
    }

    @DeleteMapping("/categorias/{id}")
    public String delete(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return "La categoria fue eliminada correctamente";
    }
}
