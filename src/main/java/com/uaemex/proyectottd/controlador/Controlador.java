package com.uaemex.proyectottd.controlador;

import com.uaemex.proyectottd.modelo.Obra;
import com.uaemex.proyectottd.repositorio.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

//CLASE CONTROLADORA
@Controller
public class Controlador {
    @Autowired
    private ObraRepository obraRepository;

    private static final Logger logger = Logger.getLogger(Controlador.class.getName());

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("obra", new Obra());
        return "index";
    }

    @PostMapping("/obras/guardar")
    public String guardarObra(@ModelAttribute Obra obra) {
        obraRepository.save(obra);
        return "redirect:/obras/nueva?success";
    }
}