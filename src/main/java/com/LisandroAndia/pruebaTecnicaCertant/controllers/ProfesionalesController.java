package com.LisandroAndia.pruebaTecnicaCertant.controllers;

import com.LisandroAndia.pruebaTecnicaCertant.services.HorariosAtencionService;
import com.LisandroAndia.pruebaTecnicaCertant.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profesionales")
public class ProfesionalesController {

    @Autowired
    private ProfesionalService profesionalService;

    @GetMapping({"", "/"})
    public String buscarProfesionales(Model model){
        model.addAttribute("profesionales", profesionalService.listAll());
        return "profesionales";
    }
}
