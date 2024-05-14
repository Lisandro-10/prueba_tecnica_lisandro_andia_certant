package com.LisandroAndia.pruebaTecnicaCertant.controllers;

import com.LisandroAndia.pruebaTecnicaCertant.models.Especialidad;
import com.LisandroAndia.pruebaTecnicaCertant.services.EspecialidadService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadesController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping({"", "/"})
    public String buscarEspecialidad(Model model) {
        List<Especialidad> especialidades = especialidadService.listAll();
        model.addAttribute("especialidades", especialidades);
        return "especialidades";
    }
}
