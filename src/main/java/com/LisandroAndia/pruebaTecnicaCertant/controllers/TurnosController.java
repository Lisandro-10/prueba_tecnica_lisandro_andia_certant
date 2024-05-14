package com.LisandroAndia.pruebaTecnicaCertant.controllers;

import com.LisandroAndia.pruebaTecnicaCertant.models.*;
import com.LisandroAndia.pruebaTecnicaCertant.repositories.TurnosRepository;
import com.LisandroAndia.pruebaTecnicaCertant.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/turnos")
public class TurnosController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private ProfesionalService profesionalService;
    @Autowired
    private HorariosAtencionService horariosAtencionService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EstadoTurnoService estadoTurnoService;
    @Autowired
    private EspecialidadService especialidadService;
    @Autowired
    private TurnosRepository turnosRepository;

    //Validar disponibilidad profesional
    public Boolean validarDisponibilidad(TurnoDto turnoDto, Profesional profesional){
        LocalDate fechaTurnoNuevo = turnoDto.getFechaTurno();
        String diaSemana = fechaTurnoNuevo.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        //Si profesional tiene un valor estamos modificando un turno, caso contrario estamos registrando uno nuevo
        if(profesional == null) profesional =  profesionalService.findOneById(turnoDto.getProfesional().getId());

        //determinamos en que franja horaria se encuentra la hora del turno solicitado
        LocalTime horario = turnoDto.getHoraInicioTurno();
        int franja = 18;
        if (horario.isBefore(LocalTime.of(13, 0))) {
            franja = 8;
        } else if (horario.isBefore(LocalTime.of(18, 0))) {
            franja = 13;
        }

        //buscamos si existe un horario de atencion y devolvemos true en caso de que exista
        HorariosAtencion horariosAtencion = horariosAtencionService.findOne(profesional, diaSemana, franja);
        return horariosAtencion != null;
    }

    public Boolean validarHorario(LocalTime horarioTurno){
        return LocalTime.now().plusHours(1).isAfter(horarioTurno);
    }

    @GetMapping({"", "/"})
    public String buscarTurnos(Model model, @Param("filtro") String filtro) {
        try {
            List<Turno> turnos = turnoService.listAll(filtro);

            model.addAttribute("turnos", turnos);
            model.addAttribute("filtro", filtro);
        }catch (Exception ex){
            System.out.println("Exception: "+ex.getMessage());
            return "redirect:/";
        }
        return "turnos/turnos";
    }

    @GetMapping({"/registrarTurno"})
    public String mostrarFormTurno(Model model){
        try {
            model.addAttribute("turnoDto", new TurnoDto());
            model.addAttribute("fechaMinima", LocalDate.now().plusDays(1));
            model.addAttribute("fechaMaxima", LocalDate.now().plusMonths(6));
            model.addAttribute("profesionales", profesionalService.listAll());
            model.addAttribute("pacientes", pacienteService.listAll());
        }catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:turnos";
        }
        return "turnos/registrarTurno";
    }

    @PostMapping({"", "/"})
    public String registrarTurno(@ModelAttribute("turnoDto") TurnoDto turnoDto, BindingResult result){
        try {
            if(!validarDisponibilidad(turnoDto, null)){
                result.addError(new FieldError("turno", "fechaYHora", "El profesional no atiende en la fecha y hora indicada."));
            }
            if (!turnoService
                    .findByFechaHora(turnoDto.getFechaTurno(), turnoDto.getHoraInicioTurno(), estadoTurnoService.findOne("Confirmado"))
                    .isEmpty()){
                result.addError(new FieldError("turno", "fechaYHora", "Turno ocupado."));
            }
            if (result.hasErrors()){
                return "redirect:turnos/registrarTurno";
            }

            //si ok transferir datos del dto al objeto real
            Turno turno = new Turno();
            turno.setFecha(turnoDto.getFechaTurno());
            turno.setHoraInicioTurno(turnoDto.getHoraInicioTurno());
            turno.setPaciente(turnoDto.getPaciente());
            turno.setNombrePaciente(turnoDto.getPaciente().getNombre()+" "+turnoDto.getPaciente().getApellido());
            turno.setNombreProfesional(turnoDto.getProfesional().getNombre()+" "+turnoDto.getProfesional().getApellido());
            turno.setProfesional((turnoDto.getProfesional()));
            turno.setEspecialidad(turnoDto.getProfesional().getEspecialidad().getNombreEspecialidad());
            turno.setEstadoTurno(estadoTurnoService.findOne("Confirmado"));

            //Persistencia del nuevo objeto
            turnoService.save(turno);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "redirect:turnos";
    }

    @GetMapping("/modificarTurno")
    public String mostrarModificarTurno(Model model, @RequestParam int id){
        try {
            TurnoDto turnoDto =  new TurnoDto();
            Turno turnoActual = turnoService.findById(id);

            turnoDto.setFechaTurno(turnoActual.getFecha());
            turnoDto.setHoraInicioTurno(turnoActual.getHoraInicioTurno());
            turnoDto.setProfesional(turnoActual.getProfesional());
            turnoDto.setPaciente(turnoActual.getPaciente());

            model.addAttribute("turnoDto", turnoDto);
            model.addAttribute("fechaMinima", LocalDate.now().plusDays(1));
            model.addAttribute("fechaMaxima", LocalDate.now().plusMonths(6));
        }catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/";
        }
        return "turnos/formModificarTurno";
    }

    @PostMapping({"/modificarTurno", "/modificarTurno/"})
    public String modificarTurno(@RequestParam int id,@ModelAttribute TurnoDto turnoDto, BindingResult result){
        try {
            Turno turno = turnoService.findById(id);

            if(validarHorario(turnoDto.getHoraInicioTurno())){
                result.addError(new FieldError("turno", "estado", "No puede modificar antes de 1 hora del turno."));
            }
            if(!validarDisponibilidad(turnoDto, turno.getProfesional())){
                result.addError(new FieldError("turno", "profesional", "El profesional no atiende en la fecha y hora indicada."));
            }
            if (!turnoService.findByFechaHora(turnoDto.getFechaTurno(), turnoDto.getHoraInicioTurno(), estadoTurnoService.findOne("Confirmado")).isEmpty()){
                result.addError(new FieldError("turno", "fechaYHora", "Turno ocupado."));
            }
            if (result.hasErrors()) {
                return "redirect:turnos/modificarTurno";
            }
            turno.setFecha(turnoDto.getFechaTurno());
            turno.setHoraInicioTurno(turnoDto.getHoraInicioTurno());

            //Persistencia del nuevo objeto
            turnoService.save(turno);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return "redirect:turnos";
        }

        return "redirect:/turnos";
    }

    @PostMapping({"/cancelarTurno", "/cancelarTurno/"})
    public String cancelarTurno(@RequestParam int id){
        try {
            Turno turno = turnoService.findById(id);

            if(validarHorario(turno.getHoraInicioTurno())){
               return "redirect:/turnos";
            }

            turno.setEstadoTurno(estadoTurnoService.findOne("Cancelado"));

            //Persistencia del nuevo objeto
            turnoService.save(turno);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return "redirect:/turnos";
        }

        return "redirect:/turnos";
    }

}
