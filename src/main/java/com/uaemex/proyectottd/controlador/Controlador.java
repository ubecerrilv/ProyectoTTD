package com.uaemex.proyectottd.controlador;

import com.uaemex.proyectottd.modelo.Obra;
import com.uaemex.proyectottd.modelo.Trabajador;
import com.uaemex.proyectottd.repositorio.ObraRepository;
import com.uaemex.proyectottd.repositorio.TrabajadorActivoRepository;
import com.uaemex.proyectottd.repositorio.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//CLASE CONTROLADORA
@Controller
public class Controlador {
    @Autowired
    private ObraRepository obraRepository;
    @Autowired
    private TrabajadorRepository trabajadorRepository;
    @Autowired
    private TrabajadorActivoRepository trabajadorActivoRepository;

    private static final Logger logger = Logger.getLogger(Controlador.class.getName());

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("obra", new Obra());
        model.addAttribute("trabajador", new Trabajador());
        model.addAttribute("obras", obraRepository.findObrasConFechaFinAntesDeHoy());
        model.addAttribute("trabajadoresDisponibles", trabajadorRepository.findTrabajadorDisponible());
        model.addAttribute("trabajadores", trabajadorRepository.findAll());

        //OBRAS CON NUMERO DE TRABAJADORES
        List<Object[]> obrasConTrabajadores = obraRepository.obtenerObrasConNumeroTrabajadores();

        List<Obra> obrasCompletas = new ArrayList<>();

        for (Object[] row : obrasConTrabajadores) {
            Obra obra = new Obra();
            obra.setId(Integer.parseInt(row[0].toString()));
            obra.setNombre((String) row[1]);
            obra.setUbicacion((String) row[2]);
            obra.setPresupuesto((BigDecimal) row[3]);

            Timestamp timestampInicio = (Timestamp) row[4];
            Timestamp timestampFin = (Timestamp) row[5];

            obra.setFechaInicio((timestampInicio.toLocalDateTime().toLocalDate()));
            obra.setFechaFin(timestampFin.toLocalDateTime().toLocalDate());

            // Asignar el número de trabajadores (último valor del Object[])
            obra.setNumeroTrabajadores(Integer.parseInt(row[6].toString()));

            obrasCompletas.add(obra);
        }
        model.addAttribute("obrasCompletas", obrasCompletas);

        model.addAttribute("trabajadoresActivos", trabajadorActivoRepository.findAll());

        return "index";
    }

    //MÉTODOTOS PARA OBRAS
    @PostMapping("/guardarObra")
    public String guardarObra(@ModelAttribute Obra obra) {
        obraRepository.save(obra);
        return "redirect:/";
    }

    @PostMapping("/editarObra")
    public String editarObra(@RequestParam("obraId") int obraId, Model model) {
        Obra obra = obraRepository.findById(obraId).orElseThrow(() -> new RuntimeException("Obra no encontrada"));
        model.addAttribute("obraEd", obra); // Añadir la obra al modelo
        return "editarObra"; // Redirigir al formulario de edición
    }

    @PostMapping("/darDeBajaObra")
    public String darDeBajaObra(@RequestParam("obraId") int obraId) {
        Obra obra = obraRepository.findById(obraId).orElse(null);

        if (obra != null) {
            obra.setFechaFin(LocalDate.now());
            obraRepository.save(obra);
        }
        return "redirect:/";
    }


    //METODOS PARA GENERAR TRABAJADOR
    @PostMapping("/guardarTrabajador")
    public String guardarTra(@ModelAttribute Trabajador trabajador) {
        trabajador.setEstado(1);
        if(trabajador.getObra() != null && trabajador.getObra().getId() == -1){
            trabajador.setObra(null);
        }
        trabajadorRepository.save(trabajador);
        return "redirect:/";
    }

    @PostMapping("/guardarTrabajadorEd")
    public String guardarTraED(@ModelAttribute Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
        return "redirect:/";
    }

    @PostMapping("/trabajadorAObra")
    public String trabajadorAObra(@RequestParam("trabajador_id") int trabajadorId, @RequestParam("obra_id") int obraId) {
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId).get();
        Obra obra = obraRepository.findById(obraId).get();

        trabajador.setObra(obra);
        trabajador.setEstado(1);
        trabajadorRepository.save(trabajador);

        return "redirect:/";
    }

    @PostMapping ("/darDeBajaTrabajador")
    public String darDeBajaTrabajador(@RequestParam("trabajadorId") int trabajadorId) {
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId).orElse(null);
        if (trabajador != null) {
            trabajador.setEstado(0); //Inactivo
            trabajadorRepository.save(trabajador);
        }
        return "redirect:/";
    }

    @PostMapping("/editarTrabajador")
    public String editarTrabajdor(@RequestParam("trabajadorId") int trabajadorId, Model model) {
        Trabajador trabajador = trabajadorRepository.findById(trabajadorId).orElseThrow(() -> new RuntimeException("Trabajdor no encontrado"));
        model.addAttribute("trabajadorEd", trabajador); // Añadir la obra al modelo
        return "editarTrabajador"; // Redirigir al formulario de edición
    }
}