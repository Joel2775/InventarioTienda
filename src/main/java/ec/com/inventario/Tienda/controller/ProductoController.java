package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.model.dto.ProductoDTO;
import ec.com.inventario.Tienda.service.IProductoService;
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
    public List<ProductoDTO> getAll(){
        return productoService.listarTodos();
    }

    @GetMapping("/productos/{id}")
    public ProductoDTO getById(@PathVariable Long id){
        return productoService.obtenerPorId(id);
    }

    @PostMapping("/persons")
    public String save(@RequestBody ProductoDTO producto){
        productoService.crear(producto);
        return "El prodcuto fue creado correctamente";
    }
    // Actualizar solo un campo
    @PatchMapping("/{id}")
    public ProductoDTO update(@PathVariable Long id, @RequestBody ProductoDTO producto){
        return productoService.actualizar(id, producto);
    }

    @DeleteMapping("/persons/{id}")
    public String delete(@PathVariable Long id){
        productoService.eliminar(id);
        return "Producto eliminado Correctamente";
    }
}
