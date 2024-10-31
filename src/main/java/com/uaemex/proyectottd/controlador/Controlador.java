package com.uaemex.proyectottd.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

//CLASE CONTROLADORA
@RestController
public class Controlador {
    private static final Logger logger = Logger.getLogger(Controlador.class.getName());

    @GetMapping("/")
    public String index() {
        return "index";
    }
}