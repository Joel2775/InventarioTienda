package ec.com.inventario.Tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @Autowired
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
