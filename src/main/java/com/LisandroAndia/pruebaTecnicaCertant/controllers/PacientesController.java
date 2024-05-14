package com.LisandroAndia.pruebaTecnicaCertant.controllers;

import com.LisandroAndia.pruebaTecnicaCertant.models.Paciente;
import com.LisandroAndia.pruebaTecnicaCertant.models.PacienteDto;
import com.LisandroAndia.pruebaTecnicaCertant.services.PacienteService;
import com.LisandroAndia.pruebaTecnicaCertant.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacientesController {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;

    @GetMapping({"", "/"})
    public String buscarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listAll());
        return "pacientes/pacientes";
    }

    @GetMapping({"/registrarPaciente"})
    public String mostrarFormPaciente(Model model){
        model.addAttribute("PacienteDto", new PacienteDto());
        return "pacientes/registrarPacientes";
    }

    @GetMapping({"/historialClinico"})
    public String mostrarHistorialClinico(Model model, @RequestParam int id){
        model.addAttribute("turnos", turnoService.findByIdPaciente(id));
        return "pacientes/historialClinico";
    }

    @PostMapping({"", "/"})
    public String registrarPaciente(@ModelAttribute("PacienteDto") PacienteDto pacienteDto){
        Paciente nuevoPaciente = new Paciente();

        nuevoPaciente.setNombre(pacienteDto.getNombre());
        nuevoPaciente.setApellido(pacienteDto.getApellido());
        nuevoPaciente.setFechaNacimiento(pacienteDto.getFechaNacimiento());
        nuevoPaciente.setEmail(pacienteDto.getEmail());
        nuevoPaciente.setObraSocial(pacienteDto.getObraSocial());

        pacienteService.save(nuevoPaciente);
        return "redirect:pacientes";
    }

}
