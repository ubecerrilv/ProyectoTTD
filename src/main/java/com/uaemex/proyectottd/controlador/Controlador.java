package com.uaemex.proyectottd.controlador;

import com.uaemex.proyectottd.modelo.Obra;
import com.uaemex.proyectottd.modelo.Trabajador;
import com.uaemex.proyectottd.repositorio.ObraRepository;
import com.uaemex.proyectottd.repositorio.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

//CLASE CONTROLADORA
@Controller
public class Controlador {
    @Autowired
    private ObraRepository obraRepository;
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    private static final Logger logger = Logger.getLogger(Controlador.class.getName());

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("obra", new Obra());
        model.addAttribute("trabajador", new Trabajador());
        model.addAttribute("obras", obraRepository.findObrasConFechaFinAntesDeHoy());
        model.addAttribute("trabajadoresDisponibles", trabajadorRepository.findTrabajadorDisponible());
        model.addAttribute("trabajadores", trabajadorRepository.findAll());
        return "index";
    }

    //MÉTODOTOS PARA OBRAS
    @PostMapping("/guardarObra")
    public String guardarObra(@ModelAttribute Obra obra) {
        obraRepository.save(obra);
        return "redirect:/";
    }


    //METODOS PARA GENERAR TRABAJADOR
    @PostMapping("/guardarTrabajador")
    public String guardarTra(@ModelAttribute Trabajador trabajador) {
        trabajador.setEstado(1);
        trabajadorRepository.save(trabajador);
        return "redirect:/";
    }

    @PostMapping("/trabajadorAObra")
    public String trabajadorAObra(@ModelAttribute int trabajador_id, @ModelAttribute int obra_id) {
        System.out.println(trabajador_id);
        System.out.println(obra_id);
        Trabajador trabajador = trabajadorRepository.findById(trabajador_id).orElse(null);
        Obra obra = obraRepository.findById(obra_id).orElse(null);
        trabajador.setObra(obra);

        trabajadorRepository.save(trabajador);
        return "redirect:/";
    }
}