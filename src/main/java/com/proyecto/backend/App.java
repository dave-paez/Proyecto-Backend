package com.proyecto.backend;

import com.proyecto.backend.model.*;
import com.proyecto.backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.Optional;

@Component
public class App implements CommandLineRunner {

    @Autowired private ParticipantesRepository participantesRepository;
    @Autowired private ProyectosRepository proyectosRepository;
    @Autowired private RecursosRepository recursosRepository;
    @Autowired private PatrociniosRepository patrociniosRepository;
    @Autowired private MantenimientoRepository mantenimientoRepository;
    @Autowired private VerificacionRepository verificacionRepository;

    @Override
    public void run(String... args) throws Exception {

        String Id_delregistro, nombre_delregistro, telefono_delregistro,
               correo_delregistro, contraseña_delregistro;
        String Id_mantenimientoderecursos, nombre_mantenimientoderecursos,
               categoria_mantenimientoderecursos, estado_mantenimientoderecursos,
               ubicacion_mantenimientoderecursos, fechadeingreso_mantenimientoderecursos;
        String Id_delosparticipantes, nombre_delosparticipantes,
               ubicacion_participante, correo_delosparticipantes, rol_delosparticipante;
        String Id_delpatrocinador, nombre_delpatrocinador, correo_delpatrocinador,
               contacto_delpatrocinador, tipo_depatrocinador, aporte_delpatrocinador;
        String Id_delproyecto, nombre_delproyecto, tipo_delproyecto, descripcion_delproyecto,
               fechaInicio_delproyecto, fechaFin_delproyecto, estado_delproyecto, correo_delproyecto;
        String Id_delrecurso, nombre_delrecurso, categoria_delrecurso,
               estado_delrecurso, ubicacion_delrecurso;

        int opciones = 0, opcion1 = 0, opcion2 = 0, opcionrecursos = 0,
            opcionproyectos = 0, opcionParticipantes = 0,
            opcionpatrocinio = 0, opcionmantenimiento = 0;

        // ── MENU PRINCIPAL ──────────────────────────────────────────────────────
        do {
            opciones = Integer.parseInt(JOptionPane.showInputDialog(
                    "BIENVENIDO AL SOFTWARE INSTITUCIONAL ACADEMICO \n"
                    + "1. iniciar sesion \n"
                    + "2. registrarse \n"
                    + "3. salir"));

            switch (opciones) {

                // ── INICIO DE SESION ────────────────────────────────────────────
                case 1:
                    do {
                        opcion1 = Integer.parseInt(JOptionPane.showInputDialog(
                                "BIENVENIDO INICIO DE SESION \n"
                                + "Elija una de las siguientes opciones: \n"
                                + "1. iniciar sesion \n"
                                + "2. salir..."));

                        switch (opcion1) {
                            case 1:
                                Id_delregistro     = JOptionPane.showInputDialog("Ingrese su ID:");
                                contraseña_delregistro = JOptionPane.showInputDialog("Ingrese su contraseña:");

                                Optional<Verificacion> usuarioOpt = verificacionRepository.findById(Id_delregistro);
                                boolean encontrado = usuarioOpt.isPresent()
                                        && usuarioOpt.get().getContraseña_personaladiministrativo()
                                                     .equals(contraseña_delregistro);

                                if (encontrado) {
                                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");

                                    // ── MENU GENERAL ────────────────────────────
                                    do {
                                        opcion2 = Integer.parseInt(JOptionPane.showInputDialog(
                                                "MENU PRINCIPAL \n"
                                                + "1. Proyectos \n"
                                                + "2. Participantes \n"
                                                + "3. Recursos \n"
                                                + "4. Patrocinio \n"
                                                + "5. Mantenimiento de recursos \n"
                                                + "6. Cerrar sesión"));

                                        switch (opcion2) {

                                            // ── PROYECTOS ───────────────────────
                                            case 1:
                                                do {
                                                    opcionproyectos = Integer.parseInt(JOptionPane.showInputDialog(
                                                            "PROYECTOS \n"
                                                            + "1. Registrar proyecto \n"
                                                            + "2. Ver todos los proyectos \n"
                                                            + "3. Actualizar proyecto \n"
                                                            + "4. Eliminar proyecto \n"
                                                            + "5. Salir"));

                                                    switch (opcionproyectos) {
                                                        case 1:
                                                            Id_delproyecto = JOptionPane.showInputDialog("Ingrese el ID del proyecto:");
                                                            if (proyectosRepository.existsById(Id_delproyecto)) {
                                                                JOptionPane.showMessageDialog(null, "ese ID ya existe");
                                                            } else {
                                                                nombre_delproyecto    = JOptionPane.showInputDialog("Ingrese el nombre del proyecto");
                                                                tipo_delproyecto      = JOptionPane.showInputDialog("Ingrese el tipo del proyecto");
                                                                descripcion_delproyecto = JOptionPane.showInputDialog("Ingrese la descripcion del proyecto");
                                                                estado_delproyecto    = JOptionPane.showInputDialog("Ingrese el estado del proyecto");
                                                                fechaInicio_delproyecto = JOptionPane.showInputDialog("Ingrese la fecha de inicio");
                                                                fechaFin_delproyecto  = JOptionPane.showInputDialog("Ingrese la fecha de fin");
                                                                correo_delproyecto    = JOptionPane.showInputDialog("Ingrese el correo del proyecto");
                                                                proyectosRepository.save(new Proyectos(
                                                                        Id_delproyecto, nombre_delproyecto, correo_delproyecto,
                                                                        tipo_delproyecto, descripcion_delproyecto,
                                                                        fechaInicio_delproyecto, fechaFin_delproyecto, estado_delproyecto));
                                                                JOptionPane.showMessageDialog(null, "proyecto registrado");
                                                            }
                                                            break;
                                                        case 2:
                                                            List<Proyectos> todosProyectos = proyectosRepository.findAll();
                                                            if (todosProyectos.isEmpty()) {
                                                                JOptionPane.showMessageDialog(null, "No hay proyectos registrados");
                                                            } else {
                                                                String hoja = "";
                                                                for (Proyectos p : todosProyectos) {
                                                                    hoja += "------ \n"
                                                                            + "ID: "          + p.getProyectoId()          + "\n"
                                                                            + "nombre: "      + p.getNombre_proyecto()      + "\n"
                                                                            + "tipo: "        + p.getTipo_proyecto()        + "\n"
                                                                            + "descripcion: " + p.getDescripcion_proyecto() + "\n"
                                                                            + "estado: "      + p.getEstado_proyecto()      + "\n"
                                                                            + "fecha inicio: " + p.getFechaInicio_proyecto() + "\n"
                                                                            + "fecha fin: "   + p.getFechaFin_proyecto()    + "\n";
                                                                }
                                                                JOptionPane.showMessageDialog(null, "LISTA DE PROYECTOS \n" + hoja);
                                                            }
                                                            break;
                                                        case 3:
                                                            Id_delproyecto = JOptionPane.showInputDialog("Ingrese el ID del proyecto a actualizar");
                                                            Optional<Proyectos> proyOpt = proyectosRepository.findById(Id_delproyecto);
                                                            if (proyOpt.isPresent()) {
                                                                Proyectos actualizarrr = proyOpt.get();
                                                                actualizarrr.setNombre_proyecto(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
                                                                actualizarrr.setTipo_proyecto(JOptionPane.showInputDialog("Ingrese el nuevo tipo"));
                                                                actualizarrr.setDescripcion_proyecto(JOptionPane.showInputDialog("Ingrese la nueva descripcion"));
                                                                actualizarrr.setEstado_proyecto(JOptionPane.showInputDialog("Ingrese el nuevo estado"));
                                                                actualizarrr.setFechaInicio_proyecto(JOptionPane.showInputDialog("Ingrese la nueva fecha de inicio"));
                                                                actualizarrr.setFechaFin_proyecto(JOptionPane.showInputDialog("Ingrese la nueva fecha de fin"));
                                                                proyectosRepository.save(actualizarrr);
                                                                JOptionPane.showMessageDialog(null, "proyecto actualizado");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Este ID no existe");
                                                            }
                                                            break;
                                                        case 4:
                                                            Id_delproyecto = JOptionPane.showInputDialog("ID del proyecto a eliminar:");
                                                            if (proyectosRepository.existsById(Id_delproyecto)) {
                                                                proyectosRepository.deleteById(Id_delproyecto);
                                                                JOptionPane.showMessageDialog(null, "Proyecto eliminado");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "ID no encontrado");
                                                            }
                                                            break;
                                                        case 5:
                                                            JOptionPane.showMessageDialog(null, "Devuelta al menu principal");
                                                            break;
                                                        default:
                                                            JOptionPane.showMessageDialog(null, "ERROR, solo numeros del 1 al 5");
                                                            break;
                                                    }
                                                } while (opcionproyectos != 5);
                                                break;

                                            // ── PARTICIPANTES ───────────────────
                                            case 2:
                                                do {
                                                    opcionParticipantes = Integer.parseInt(JOptionPane.showInputDialog(
                                                            "PARTICIPANTES \n"
                                                            + "1. Registrar participante \n"
                                                            + "2. lista de todos los participantes \n"
                                                            + "3. Buscar participante por ID \n"
                                                            + "4. Actualizar participante \n"
                                                            + "5. Eliminar participante \n"
                                                            + "6. volver al menu principal"));

                                                    switch (opcionParticipantes) {
                                                        case 1:
                                                            Id_delosparticipantes = JOptionPane.showInputDialog("Ingrese el ID del participante:");
                                                            if (participantesRepository.existsById(Id_delosparticipantes)) {
                                                                JOptionPane.showMessageDialog(null, "ese ID ya existe");
                                                            } else {
                                                                nombre_delosparticipantes  = JOptionPane.showInputDialog("Ingrese el nombre del participante:");
                                                                ubicacion_participante     = JOptionPane.showInputDialog("Ingrese la ubicacion del participante:");
                                                                correo_delosparticipantes  = JOptionPane.showInputDialog("Ingrese el correo del participante:");
                                                                rol_delosparticipante      = JOptionPane.showInputDialog("Ingrese el rol del participante:");
                                                                participantesRepository.save(new Participantes(
                                                                        Id_delosparticipantes, nombre_delosparticipantes,
                                                                        ubicacion_participante, correo_delosparticipantes,
                                                                        rol_delosparticipante));
                                                                JOptionPane.showMessageDialog(null, "Participante registrado exitosamente");
                                                            }
                                                            break;
                                                        case 2:
                                                            List<Participantes> todosParticipantes = participantesRepository.findAll();
                                                            if (todosParticipantes.isEmpty()) {
                                                                JOptionPane.showMessageDialog(null, "No hay participantes registrados");
                                                            } else {
                                                                String hojadepapeldos = "";
                                                                for (Participantes p : todosParticipantes) {
                                                                    hojadepapeldos += "------ \n"
                                                                            + "ID: "        + p.getParticipanteId()         + "\n"
                                                                            + "nombre: "    + p.getNombre_participante()    + "\n"
                                                                            + "ubicacion: " + p.getUbicacion_participante() + "\n"
                                                                            + "correo: "    + p.getCorreo_participante()    + "\n"
                                                                            + "rol: "       + p.getRol_participante()       + "\n";
                                                                }
                                                                JOptionPane.showMessageDialog(null, "LISTA DE PARTICIPANTES \n" + hojadepapeldos);
                                                            }
                                                            break;
                                                        case 3:
                                                            Id_delosparticipantes = JOptionPane.showInputDialog("Ingrese el ID del participante que desea buscar");
                                                            Optional<Participantes> partiOpt = participantesRepository.findById(Id_delosparticipantes);
                                                            if (partiOpt.isPresent()) {
                                                                Participantes buscarparti = partiOpt.get();
                                                                String hojadepapeltres = "PARTICIPANTE \n"
                                                                        + "ID: "        + buscarparti.getParticipanteId()         + "\n"
                                                                        + "nombre: "    + buscarparti.getNombre_participante()    + "\n"
                                                                        + "ubicacion: " + buscarparti.getUbicacion_participante() + "\n"
                                                                        + "correo: "    + buscarparti.getCorreo_participante()    + "\n"
                                                                        + "rol: "       + buscarparti.getRol_participante();
                                                                JOptionPane.showMessageDialog(null, hojadepapeltres);
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Este ID no existe");
                                                            }
                                                            break;
                                                        case 4:
                                                            Id_delosparticipantes = JOptionPane.showInputDialog("Ingrese el ID del participante que desea actualizar");
                                                            Optional<Participantes> partiActOpt = participantesRepository.findById(Id_delosparticipantes);
                                                            if (partiActOpt.isPresent()) {
                                                                Participantes actualizarparticipante = partiActOpt.get();
                                                                actualizarparticipante.setNombre_participante(JOptionPane.showInputDialog("Ingrese el nuevo nombre del participante"));
                                                                actualizarparticipante.setUbicacion_participante(JOptionPane.showInputDialog("Ingrese la nueva ubicacion del participante"));
                                                                actualizarparticipante.setCorreo_participante(JOptionPane.showInputDialog("Ingrese el nuevo correo del participante"));
                                                                actualizarparticipante.setRol_participante(JOptionPane.showInputDialog("Ingrese el nuevo rol del participante"));
                                                                participantesRepository.save(actualizarparticipante);
                                                                JOptionPane.showMessageDialog(null, "Actualizacion hecha");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Este ID no existe pri");
                                                            }
                                                            break;
                                                        case 5:
                                                            Id_delosparticipantes = JOptionPane.showInputDialog("Ingrese el ID del participante que desea eliminar:");
                                                            if (participantesRepository.existsById(Id_delosparticipantes)) {
                                                                participantesRepository.deleteById(Id_delosparticipantes);
                                                                JOptionPane.showMessageDialog(null, "Participante eliminado exitosamente");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "ID no encontrado");
                                                            }
                                                            break;
                                                        case 6:
                                                            JOptionPane.showMessageDialog(null, "Devuelta al menu principal");
                                                            break;
                                                    }
                                                } while (opcionParticipantes != 6);
                                                break;

                                            // ── RECURSOS ────────────────────────
                                            case 3:
                                                do {
                                                    opcionrecursos = Integer.parseInt(JOptionPane.showInputDialog(
                                                            "RECURSOS \n"
                                                            + "1. Ver todos los recursos \n"
                                                            + "2. Registrar algun recurso  \n"
                                                            + "3. Actualizar recurso \n"
                                                            + "4. Eliminar recurso \n"
                                                            + "5. volver al menu principal"));

                                                    switch (opcionrecursos) {
                                                        case 1:
                                                            List<Recursos> todosRecursos = recursosRepository.findAll();
                                                            if (todosRecursos.isEmpty()) {
                                                                JOptionPane.showMessageDialog(null, "No hay recursos registrados");
                                                            } else {
                                                                String hojadepapel = "";
                                                                for (Recursos r : todosRecursos) {
                                                                    hojadepapel += "------\n"
                                                                            + "ID: "         + r.getRecursoId()           + "\n"
                                                                            + "nombre: "     + r.getNombre_delrecurso()    + "\n"
                                                                            + "categoria: "  + r.getCategoria_delrecurso() + "\n"
                                                                            + "estado: "     + r.getEstado_delrecurso()    + "\n"
                                                                            + "ubicacion: "  + r.getUbicacion_delrecurso() + "\n";
                                                                }
                                                                JOptionPane.showMessageDialog(null, "LISTA DE RECURSOS \n" + hojadepapel);
                                                            }
                                                            break;
                                                        case 2:
                                                            Id_delrecurso = JOptionPane.showInputDialog("Ingrese el ID del recurso:");
                                                            if (recursosRepository.existsById(Id_delrecurso)) {
                                                                JOptionPane.showMessageDialog(null, "ese ID ya existe");
                                                            } else {
                                                                nombre_delrecurso    = JOptionPane.showInputDialog("Ingrese el nombre del recurso:");
                                                                categoria_delrecurso = JOptionPane.showInputDialog("Ingrese la categoria del recurso:");
                                                                estado_delrecurso    = JOptionPane.showInputDialog("Ingrese el estado del recurso:");
                                                                ubicacion_delrecurso = JOptionPane.showInputDialog("Ingrese la ubicacion del recurso:");
                                                                recursosRepository.save(new Recursos(
                                                                        Id_delrecurso, nombre_delrecurso,
                                                                        categoria_delrecurso, estado_delrecurso, ubicacion_delrecurso));
                                                                JOptionPane.showMessageDialog(null, "Recurso registrado exitosamente");
                                                            }
                                                            break;
                                                        case 3:
                                                            Id_delrecurso = JOptionPane.showInputDialog("Ingrese el ID del recurso que desea actualizar:");
                                                            Optional<Recursos> recOpt = recursosRepository.findById(Id_delrecurso);
                                                            if (recOpt.isPresent()) {
                                                                Recursos actualizar = recOpt.get();
                                                                actualizar.setNombre_delrecurso(JOptionPane.showInputDialog("Ingrese el nuevo nombre del recurso:"));
                                                                actualizar.setCategoria_delrecurso(JOptionPane.showInputDialog("Ingrese la nueva categoria del recurso:"));
                                                                actualizar.setEstado_delrecurso(JOptionPane.showInputDialog("Ingrese el nuevo estado del recurso:"));
                                                                actualizar.setUbicacion_delrecurso(JOptionPane.showInputDialog("Ingrese la nueva ubicacion del recurso:"));
                                                                recursosRepository.save(actualizar);
                                                                JOptionPane.showMessageDialog(null, "Recurso actualizado exitosamente");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "ID no encontrado");
                                                            }
                                                            break;
                                                        case 4:
                                                            Id_delrecurso = JOptionPane.showInputDialog("Ingrese el ID del recurso que desea eliminar:");
                                                            if (recursosRepository.existsById(Id_delrecurso)) {
                                                                recursosRepository.deleteById(Id_delrecurso);
                                                                JOptionPane.showMessageDialog(null, "Recurso eliminado exitosamente");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "ID no encontrado");
                                                            }
                                                            break;
                                                        case 5:
                                                            JOptionPane.showMessageDialog(null, "Devuelta al menu principal");
                                                            break;
                                                        default:
                                                            JOptionPane.showMessageDialog(null, "ERROR, solo pueden numero del 1 al 5");
                                                            break;
                                                    }
                                                } while (opcionrecursos != 5);
                                                break;

                                            // ── PATROCINIO ──────────────────────
                                            case 4:
                                                do {
                                                    opcionpatrocinio = Integer.parseInt(JOptionPane.showInputDialog(
                                                            "PATROCINIO\n"
                                                            + "1. Ver todos los patrocinadores\n"
                                                            + "2. Crear nuevo patrocinador\n"
                                                            + "3. Actualizar patrocinador\n"
                                                            + "4. Eliminar patrocinador\n"
                                                            + "5. Ver información de un patrocinador\n"
                                                            + "6. Ver rol de un patrocinador\n"
                                                            + "7. Salir"));

                                                    switch (opcionpatrocinio) {
                                                        case 1:
                                                            List<Patrocinios> todosPatrocinadores = patrociniosRepository.findAll();
                                                            if (todosPatrocinadores.isEmpty()) {
                                                                JOptionPane.showMessageDialog(null, "No hay patrocinadores registrados");
                                                            } else {
                                                                String listaPatrocinadores = "";
                                                                for (Patrocinios pat : todosPatrocinadores) {
                                                                    listaPatrocinadores += "ID: " + pat.getId()
                                                                            + " nombre: "  + pat.getNombre()
                                                                            + " tipo: "    + pat.getTipo_patrocinador()
                                                                            + " aporte: "  + pat.getAporte_patrocinador()
                                                                            + "--\n";
                                                                }
                                                                JOptionPane.showMessageDialog(null, "LISTA DE PATROCINADORES\n" + listaPatrocinadores);
                                                            }
                                                            break;
                                                        case 2:
                                                            Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el id");
                                                            if (patrociniosRepository.existsById(Id_delpatrocinador)) {
                                                                JOptionPane.showMessageDialog(null, "ese id ya existe");
                                                            } else {
                                                                nombre_delpatrocinador    = JOptionPane.showInputDialog("Nombre:");
                                                                correo_delpatrocinador    = JOptionPane.showInputDialog("Correo:");
                                                                contacto_delpatrocinador  = JOptionPane.showInputDialog("Contacto:");
                                                                tipo_depatrocinador       = JOptionPane.showInputDialog("Tipo:");
                                                                aporte_delpatrocinador    = JOptionPane.showInputDialog("Aporte:");
                                                                patrociniosRepository.save(new Patrocinios(
                                                                        Id_delpatrocinador, nombre_delpatrocinador, correo_delpatrocinador,
                                                                        contacto_delpatrocinador, tipo_depatrocinador, aporte_delpatrocinador));
                                                                JOptionPane.showMessageDialog(null, "patrocinador hecho");
                                                            }
                                                            break;
                                                        case 3:
                                                            Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el ID a actualizar:");
                                                            Optional<Patrocinios> patrOpt = patrociniosRepository.findById(Id_delpatrocinador);
                                                            if (patrOpt.isPresent()) {
                                                                Patrocinios pat = patrOpt.get();
                                                                pat.setNombre(JOptionPane.showInputDialog("Nuevo nombre:"));
                                                                pat.setCorreo(JOptionPane.showInputDialog("Nuevo correo:"));
                                                                pat.setContacto_patrocinador(JOptionPane.showInputDialog("Nuevo contacto:"));
                                                                pat.setTipo_patrocinador(JOptionPane.showInputDialog("Nuevo tipo:"));
                                                                pat.setAporte_patrocinador(JOptionPane.showInputDialog("Nuevo aporte:"));
                                                                patrociniosRepository.save(pat);
                                                                JOptionPane.showMessageDialog(null, "actualizado");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "id no encontrado");
                                                            }
                                                            break;
                                                        case 4:
                                                            Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el id");
                                                            if (patrociniosRepository.existsById(Id_delpatrocinador)) {
                                                                patrociniosRepository.deleteById(Id_delpatrocinador);
                                                                JOptionPane.showMessageDialog(null, "eliminado");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "id no encontrado");
                                                            }
                                                            break;
                                                        case 5:
                                                            Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el ID a buscar:");
                                                            Optional<Patrocinios> patrInfoOpt = patrociniosRepository.findById(Id_delpatrocinador);
                                                            if (patrInfoOpt.isPresent()) {
                                                                patrInfoOpt.get().mostrarinfo();
                                                                JOptionPane.showMessageDialog(null, "Ver consola para la información completa");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "id no encontrado");
                                                            }
                                                            break;
                                                        case 6:
                                                            Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el id:");
                                                            Optional<Patrocinios> patrRolOpt = patrociniosRepository.findById(Id_delpatrocinador);
                                                            if (patrRolOpt.isPresent()) {
                                                                JOptionPane.showMessageDialog(null, patrRolOpt.get().rol());
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "id no encontrado");
                                                            }
                                                            break;
                                                        case 7:
                                                            JOptionPane.showMessageDialog(null, "Saliendo");
                                                            break;
                                                        default:
                                                            JOptionPane.showMessageDialog(null, "No. solo números del 1 al 7");
                                                    }
                                                } while (opcionpatrocinio != 7);
                                                break;

                                            // ── MANTENIMIENTO ───────────────────
                                            case 5:
                                                do {
                                                    opcionmantenimiento = Integer.parseInt(JOptionPane.showInputDialog(
                                                            "MANTENIMIENTO \n"
                                                            + "1. Ver todos los objetos en mantenimiento \n"
                                                            + "2. Crear nuevo objeto en mantenimiento \n"
                                                            + "3. Actualizar objeto \n"
                                                            + "4. Eliminar objeto \n"
                                                            + "5. Salir"));

                                                    switch (opcionmantenimiento) {
                                                        case 1:
                                                            List<Mantenimientoderecursos> todosMantenimientos = mantenimientoRepository.findAll();
                                                            if (todosMantenimientos.isEmpty()) {
                                                                JOptionPane.showMessageDialog(null, "no hay objetos en mantenimiento registrados");
                                                            } else {
                                                                String hojita = "";
                                                                for (Mantenimientoderecursos m : todosMantenimientos) {
                                                                    hojita += "------ \n"
                                                                            + "ID: "          + m.getManteni_recursoId()              + "\n"
                                                                            + "nombre: "      + m.getNombre_mantenimientorecursos()    + "\n"
                                                                            + "descripcion: " + m.getCategoria_mantenimientorecursos() + "\n"
                                                                            + "estado: "      + m.getEstado_mantenimientorecursos()    + "\n"
                                                                            + "categoria: "   + m.getUbicacion_mantenimientorecursos() + "\n";
                                                                }
                                                                JOptionPane.showMessageDialog(null, "LISTA DE MANTENIMIENTOS \n" + hojita);
                                                            }
                                                            break;
                                                        case 2:
                                                            Id_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese el ID del mantenimiento:");
                                                            if (mantenimientoRepository.existsById(Id_mantenimientoderecursos)) {
                                                                JOptionPane.showMessageDialog(null, "ese ID ya existe");
                                                            } else {
                                                                nombre_mantenimientoderecursos    = JOptionPane.showInputDialog("Ingrese el nombre del objeto");
                                                                categoria_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese la descripcion");
                                                                estado_mantenimientoderecursos    = JOptionPane.showInputDialog("Ingrese el estado");
                                                                ubicacion_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese la categoria");
                                                                fechadeingreso_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese la fecha de ingreso");
                                                                mantenimientoRepository.save(new Mantenimientoderecursos(
                                                                        Id_mantenimientoderecursos,
                                                                        nombre_mantenimientoderecursos,
                                                                        categoria_mantenimientoderecursos,
                                                                        estado_mantenimientoderecursos,
                                                                        ubicacion_mantenimientoderecursos,
                                                                        fechadeingreso_mantenimientoderecursos));
                                                                JOptionPane.showMessageDialog(null, "Mantenimiento registrado");
                                                            }
                                                            break;
                                                        case 3:
                                                            Id_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese el ID del objeto a actualizar");
                                                            Optional<Mantenimientoderecursos> mantOpt = mantenimientoRepository.findById(Id_mantenimientoderecursos);
                                                            if (mantOpt.isPresent()) {
                                                                Mantenimientoderecursos cambiar = mantOpt.get();
                                                                cambiar.setNombre_mantenimientorecursos(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
                                                                cambiar.setCategoria_mantenimientorecursos(JOptionPane.showInputDialog("Ingrese la nueva descripcion"));
                                                                cambiar.setEstado_mantenimientorecursos(JOptionPane.showInputDialog("Ingrese el nuevo estado"));
                                                                cambiar.setUbicacion_mantenimientorecursos(JOptionPane.showInputDialog("Ingrese la nueva categoria"));
                                                                cambiar.setFechadeingreso_mantenimientorecursos(JOptionPane.showInputDialog("Ingrese la nueva fecha de ingreso"));
                                                                mantenimientoRepository.save(cambiar);
                                                                JOptionPane.showMessageDialog(null, "objeto actualizado");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Este ID no existe");
                                                            }
                                                            break;
                                                        case 4:
                                                            Id_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese el ID del objeto a eliminar ");
                                                            if (mantenimientoRepository.existsById(Id_mantenimientoderecursos)) {
                                                                mantenimientoRepository.deleteById(Id_mantenimientoderecursos);
                                                                JOptionPane.showMessageDialog(null, "objeto eliminado");
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "ID no encontrado");
                                                            }
                                                            break;
                                                        case 5:
                                                            JOptionPane.showMessageDialog(null, "Devuelta al menu principal");
                                                            break;
                                                        default:
                                                            JOptionPane.showMessageDialog(null, "ERROR, solo numeros del 1 al 5");
                                                            break;
                                                    }
                                                } while (opcionmantenimiento != 5);
                                                break;

                                            case 6:
                                                JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                                                break;
                                            default:
                                                JOptionPane.showMessageDialog(null, "ERROR, solo pueden numero del 1 al 6");
                                                break;
                                        }
                                    } while (opcion2 != 6);

                                } else {
                                    JOptionPane.showMessageDialog(null, "ID o contraseña incorrectos");
                                }
                                break;

                            case 2:
                                JOptionPane.showMessageDialog(null, "Devuelta al menu principal");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "ERROR, solo pueden numero del 1 al 2");
                                break;
                        }
                    } while (opcion1 != 2);
                    break;

                // ── REGISTRO DE USUARIO ─────────────────────────────────────────
                case 2:
                    nombre_delregistro     = JOptionPane.showInputDialog("Ingrese su nombre:");
                    telefono_delregistro   = JOptionPane.showInputDialog("Ingrese su telefono:");
                    correo_delregistro     = JOptionPane.showInputDialog("Ingrese su correo:");
                    Id_delregistro         = JOptionPane.showInputDialog("Ingrese su ID:");
                    contraseña_delregistro = JOptionPane.showInputDialog("Ingrese su contraseña:");
                    verificacionRepository.save(new Verificacion(
                            Id_delregistro, contraseña_delregistro,
                            telefono_delregistro, correo_delregistro, nombre_delregistro));
                    JOptionPane.showMessageDialog(null, "registrado exitosamente");
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el software");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR, solo pueden numero del 1 al 3");
                    break;
            }
        } while (opciones != 3);
    }
}
