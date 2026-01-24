package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.model.entity.Categorias;
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
    public List<Categorias> getAll(){
        return categoriaService.getCategoria();
    }

    @GetMapping("/categorias/{id}")
    public Categorias getById(@PathVariable Long id){
        return categoriaService.findCategoria(id);
    }

    @PostMapping("/categorias")
    public String save(@RequestBody Categorias categorias){
        categoriaService.crearCategoria(categorias);
        return"La categoria fue creada correctamente";
    }

    @PatchMapping("/{id}")
    public Categorias update(@PathVariable Long id, @RequestBody Categorias categorias){
        return categoriaService.actualizarCategoria(id, categorias);
    }

    @DeleteMapping("/categorias/{id}")
    public String eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return "Categoria Eliminada correctamente";
    }
}
