package ec.com.inventario.Tienda.controller;

import ec.com.inventario.Tienda.service.IProductoService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
    private final IProductoService service;

    public ProductoController(IProductoService service) {
        this.service = service;
    }
}
