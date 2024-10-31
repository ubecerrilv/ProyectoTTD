package com.uaemex.proyectottd.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

//CLASE CONTROLADORA
@Controller
public class Controlador {
    private static final Logger logger = Logger.getLogger(Controlador.class.getName());

    @GetMapping("/")
    public String index() {
        return "index";
    }
}