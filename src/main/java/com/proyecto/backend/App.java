package com.proyecto.backend;

import com.proyecto.backend.model.*;
import com.proyecto.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class App implements CommandLineRunner {

    @Autowired private ProyectosRepository     proyectosRepo;
    @Autowired private ParticipantesRepository  participantesRepo;
    @Autowired private PatrociniosRepository    patrociniosRepo;
    @Autowired private RecursosRepository       recursosRepo;
    @Autowired private MantenimientoRepository  mantenimientoRepo;
    @Autowired private VerificacionRepository   verificacionRepo;

    private final Scanner sc = new Scanner(System.in);

    // ══════════════════════════════════════════════════════
    //  MENU PRINCIPAL
    // ══════════════════════════════════════════════════════
    @Override
    public void run(String... args) {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║   SISTEMA INSTITUCIONAL ACADEMICO    ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1. Proyectos                        ║");
            System.out.println("║  2. Participantes                    ║");
            System.out.println("║  3. Patrocinios                      ║");
            System.out.println("║  4. Recursos                         ║");
            System.out.println("║  5. Mantenimiento de Recursos        ║");
            System.out.println("║  6. Usuarios                         ║");
            System.out.println("║  0. Salir                            ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Selecciona una opcion: ");
            opcion = leerInt();

            switch (opcion) {
                case 1 -> menuProyectos();
                case 2 -> menuParticipantes();
                case 3 -> menuPatrocinios();
                case 4 -> menuRecursos();
                case 5 -> menuMantenimiento();
                case 6 -> menuUsuarios();
                case 0 -> System.out.println("\nCerrando sistema. Hasta luego.");
                default -> System.out.println("Opcion invalida, intenta de nuevo.");
            }
        } while (opcion != 0);
    }

    // ══════════════════════════════════════════════════════
    //  PROYECTOS
    // ══════════════════════════════════════════════════════
    private void menuProyectos() {
        int op;
        do {
            System.out.println("\n--- PROYECTOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear nuevo");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> {
                    List<Proyectos> lista = proyectosRepo.findAll();
                    if (lista.isEmpty()) System.out.println("No hay proyectos registrados.");
                    else lista.forEach(p -> System.out.println(p.traerDetalles() + "\n"));
                }
                case 2 -> {
                    System.out.print("ID del proyecto: ");
                    String id = sc.nextLine().trim();
                    Proyectos p = proyectosRepo.buscar(id);
                    if (p != null) System.out.println(p.traerDetalles());
                    else System.out.println("Proyecto no encontrado.");
                }
                case 3 -> crearProyecto();
                case 4 -> actualizarProyecto();
                case 5 -> {
                    System.out.print("ID del proyecto a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (proyectosRepo.buscar(id) != null) {
                        proyectosRepo.eliminar(id);
                        System.out.println("Proyecto eliminado correctamente.");
                    } else System.out.println("Proyecto no encontrado.");
                }
            }
        } while (op != 0);
    }

    private void crearProyecto() {
        System.out.print("ID           : "); String id   = sc.nextLine().trim();
        System.out.print("Nombre       : "); String nom  = sc.nextLine().trim();
        System.out.print("Correo       : "); String cor  = sc.nextLine().trim();
        System.out.print("Tipo         : "); String tipo = sc.nextLine().trim();
        System.out.print("Descripcion  : "); String desc = sc.nextLine().trim();
        System.out.print("Fecha inicio : "); String ini  = sc.nextLine().trim();
        System.out.print("Fecha fin    : "); String fin  = sc.nextLine().trim();
        System.out.print("Estado       : "); String est  = sc.nextLine().trim();
        proyectosRepo.guardar(new Proyectos(id, nom, cor, tipo, desc, ini, fin, est));
        System.out.println("Proyecto creado correctamente.");
    }

    private void actualizarProyecto() {
        System.out.print("ID del proyecto a actualizar: ");
        String id = sc.nextLine().trim();
        Proyectos p = proyectosRepo.buscar(id);
        if (p == null) { System.out.println("Proyecto no encontrado."); return; }
        System.out.print("Nuevo nombre    [" + p.getNombre() + "] (Enter para dejar igual): ");
        String v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setNombre_proyecto(v);
        System.out.print("Nuevo estado    [" + p.getEstado_proyecto() + "] (Enter para dejar igual): ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setEstado_proyecto(v);
        System.out.print("Nueva fecha fin [" + p.getFechaFin_proyecto() + "] (Enter para dejar igual): ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setFechaFin_proyecto(v);
        proyectosRepo.guardar(p);
        System.out.println("Proyecto actualizado correctamente.");
    }

    // ══════════════════════════════════════════════════════
    //  PARTICIPANTES
    // ══════════════════════════════════════════════════════
    private void menuParticipantes() {
        int op;
        do {
            System.out.println("\n--- PARTICIPANTES ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear nuevo");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> {
                    List<Participantes> lista = participantesRepo.findAll();
                    if (lista.isEmpty()) System.out.println("No hay participantes registrados.");
                    else lista.forEach(p -> { p.mostrarinfo(); System.out.println(); });
                }
                case 2 -> {
                    System.out.print("ID del participante: ");
                    String id = sc.nextLine().trim();
                    Participantes p = participantesRepo.buscar(id);
                    if (p != null) p.mostrarinfo();
                    else System.out.println("Participante no encontrado.");
                }
                case 3 -> crearParticipante();
                case 4 -> actualizarParticipante();
                case 5 -> {
                    System.out.print("ID del participante a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (participantesRepo.buscar(id) != null) {
                        participantesRepo.eliminar(id);
                        System.out.println("Participante eliminado correctamente.");
                    } else System.out.println("Participante no encontrado.");
                }
            }
        } while (op != 0);
    }

    private void crearParticipante() {
        System.out.print("ID        : "); String id   = sc.nextLine().trim();
        System.out.print("Nombre    : "); String nom  = sc.nextLine().trim();
        System.out.print("Correo    : "); String cor  = sc.nextLine().trim();
        System.out.print("Ubicacion : "); String ubic = sc.nextLine().trim();
        System.out.print("Rol       : "); String rol  = sc.nextLine().trim();
        participantesRepo.guardar(new Participantes(id, nom, ubic, cor, rol));
        System.out.println("Participante creado correctamente.");
    }

    private void actualizarParticipante() {
        System.out.print("ID del participante a actualizar: ");
        String id = sc.nextLine().trim();
        Participantes p = participantesRepo.buscar(id);
        if (p == null) { System.out.println("Participante no encontrado."); return; }
        System.out.print("Nuevo nombre    [" + p.getNombre_participante() + "]: ");
        String v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setNombre_participante(v);
        System.out.print("Nuevo rol       [" + p.getRol_participante() + "]: ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setRol_participante(v);
        System.out.print("Nueva ubicacion [" + p.getUbicacion_participante() + "]: ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setUbicacion_participante(v);
        participantesRepo.guardar(p);
        System.out.println("Participante actualizado correctamente.");
    }

    // ══════════════════════════════════════════════════════
    //  PATROCINIOS
    // ══════════════════════════════════════════════════════
    private void menuPatrocinios() {
        int op;
        do {
            System.out.println("\n--- PATROCINIOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear nuevo");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> {
                    List<Patrocinios> lista = patrociniosRepo.findAll();
                    if (lista.isEmpty()) System.out.println("No hay patrocinios registrados.");
                    else lista.forEach(p -> { p.mostrarinfo(); System.out.println(); });
                }
                case 2 -> {
                    System.out.print("ID del patrocinador: ");
                    String id = sc.nextLine().trim();
                    Patrocinios p = patrociniosRepo.buscar(id);
                    if (p != null) p.mostrarinfo();
                    else System.out.println("Patrocinio no encontrado.");
                }
                case 3 -> crearPatrocinio();
                case 4 -> actualizarPatrocinio();
                case 5 -> {
                    System.out.print("ID del patrocinio a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (patrociniosRepo.buscar(id) != null) {
                        patrociniosRepo.eliminar(id);
                        System.out.println("Patrocinio eliminado correctamente.");
                    } else System.out.println("Patrocinio no encontrado.");
                }
            }
        } while (op != 0);
    }

    private void crearPatrocinio() {
        System.out.print("ID       : "); String id     = sc.nextLine().trim();
        System.out.print("Nombre   : "); String nom    = sc.nextLine().trim();
        System.out.print("Correo   : "); String cor    = sc.nextLine().trim();
        System.out.print("Contacto : "); String cont   = sc.nextLine().trim();
        System.out.print("Tipo     : "); String tipo   = sc.nextLine().trim();
        System.out.print("Aporte   : "); String aporte = sc.nextLine().trim();
        patrociniosRepo.guardar(new Patrocinios(id, nom, cor, cont, tipo, aporte));
        System.out.println("Patrocinio creado correctamente.");
    }

    private void actualizarPatrocinio() {
        System.out.print("ID del patrocinio a actualizar: ");
        String id = sc.nextLine().trim();
        Patrocinios p = patrociniosRepo.buscar(id);
        if (p == null) { System.out.println("Patrocinio no encontrado."); return; }
        System.out.print("Nuevo aporte [" + p.getAporte_patrocinador() + "]: ");
        String v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setAporte_patrocinador(v);
        System.out.print("Nuevo tipo   [" + p.getTipo_patrocinador() + "]: ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) p.setTipo_patrocinador(v);
        patrociniosRepo.guardar(p);
        System.out.println("Patrocinio actualizado correctamente.");
    }

    // ══════════════════════════════════════════════════════
    //  RECURSOS
    // ══════════════════════════════════════════════════════
    private void menuRecursos() {
        int op;
        do {
            System.out.println("\n--- RECURSOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear nuevo");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> {
                    List<Recursos> lista = recursosRepo.findAll();
                    if (lista.isEmpty()) System.out.println("No hay recursos registrados.");
                    else lista.forEach(r -> System.out.println(r.traerDetalles() + "\n"));
                }
                case 2 -> {
                    System.out.print("ID del recurso: ");
                    String id = sc.nextLine().trim();
                    Recursos r = recursosRepo.buscar(id);
                    if (r != null) System.out.println(r.traerDetalles());
                    else System.out.println("Recurso no encontrado.");
                }
                case 3 -> crearRecurso();
                case 4 -> actualizarRecurso();
                case 5 -> {
                    System.out.print("ID del recurso a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (recursosRepo.buscar(id) != null) {
                        recursosRepo.eliminar(id);
                        System.out.println("Recurso eliminado correctamente.");
                    } else System.out.println("Recurso no encontrado.");
                }
            }
        } while (op != 0);
    }

    private void crearRecurso() {
        System.out.print("ID        : "); String id   = sc.nextLine().trim();
        System.out.print("Nombre    : "); String nom  = sc.nextLine().trim();
        System.out.print("Categoria : "); String cat  = sc.nextLine().trim();
        System.out.print("Estado    : "); String est  = sc.nextLine().trim();
        System.out.print("Ubicacion : "); String ubic = sc.nextLine().trim();
        recursosRepo.guardar(new Recursos(id, nom, cat, est, ubic));
        System.out.println("Recurso creado correctamente.");
    }

    private void actualizarRecurso() {
        System.out.print("ID del recurso a actualizar: ");
        String id = sc.nextLine().trim();
        Recursos r = recursosRepo.buscar(id);
        if (r == null) { System.out.println("Recurso no encontrado."); return; }
        System.out.print("Nuevo estado    [" + r.getEstado_delrecurso() + "]: ");
        String v = sc.nextLine().trim();
        if (!v.isEmpty()) r.setEstado_delrecurso(v);
        System.out.print("Nueva ubicacion [" + r.getUbicacion_delrecurso() + "]: ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) r.setUbicacion_delrecurso(v);
        recursosRepo.guardar(r);
        System.out.println("Recurso actualizado correctamente.");
    }

    // ══════════════════════════════════════════════════════
    //  MANTENIMIENTO DE RECURSOS
    // ══════════════════════════════════════════════════════
    private void menuMantenimiento() {
        int op;
        do {
            System.out.println("\n--- MANTENIMIENTO DE RECURSOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear nuevo");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> {
                    List<Mantenimientoderecursos> lista = mantenimientoRepo.findAll();
                    if (lista.isEmpty()) System.out.println("No hay registros de mantenimiento.");
                    else lista.forEach(m -> System.out.println(m.traerDetalles() + "\n"));
                }
                case 2 -> {
                    System.out.print("ID del mantenimiento: ");
                    String id = sc.nextLine().trim();
                    Mantenimientoderecursos m = mantenimientoRepo.buscar(id);
                    if (m != null) System.out.println(m.traerDetalles());
                    else System.out.println("Mantenimiento no encontrado.");
                }
                case 3 -> crearMantenimiento();
                case 4 -> actualizarMantenimiento();
                case 5 -> {
                    System.out.print("ID del mantenimiento a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (mantenimientoRepo.buscar(id) != null) {
                        mantenimientoRepo.eliminar(id);
                        System.out.println("Mantenimiento eliminado correctamente.");
                    } else System.out.println("Mantenimiento no encontrado.");
                }
            }
        } while (op != 0);
    }

    private void crearMantenimiento() {
        System.out.print("ID             : "); String id    = sc.nextLine().trim();
        System.out.print("Nombre tecnico : "); String nom   = sc.nextLine().trim();
        System.out.print("Descripcion    : "); String desc  = sc.nextLine().trim();
        System.out.print("Estado         : "); String est   = sc.nextLine().trim();
        System.out.print("Categoria      : "); String cat   = sc.nextLine().trim();
        System.out.print("Fecha          : "); String fecha = sc.nextLine().trim();
        mantenimientoRepo.guardar(new Mantenimientoderecursos(id, nom, desc, est, cat, fecha));
        System.out.println("Mantenimiento creado correctamente.");
    }

    private void actualizarMantenimiento() {
        System.out.print("ID del mantenimiento a actualizar: ");
        String id = sc.nextLine().trim();
        Mantenimientoderecursos m = mantenimientoRepo.buscar(id);
        if (m == null) { System.out.println("Mantenimiento no encontrado."); return; }
        System.out.print("Nuevo estado [" + m.getEstado_mantenimientorecursos() + "]: ");
        String v = sc.nextLine().trim();
        if (!v.isEmpty()) m.setEstado_mantenimientorecursos(v);
        System.out.print("Nueva fecha  [" + m.getFechadeingreso_mantenimientorecursos() + "]: ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) m.setFechadeingreso_mantenimientorecursos(v);
        mantenimientoRepo.guardar(m);
        System.out.println("Mantenimiento actualizado correctamente.");
    }

    // ══════════════════════════════════════════════════════
    //  USUARIOS
    // ══════════════════════════════════════════════════════
    private void menuUsuarios() {
        int op;
        do {
            System.out.println("\n--- USUARIOS ---");
            System.out.println("1. Listar todos");
            System.out.println("2. Buscar por ID");
            System.out.println("3. Crear nuevo");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerInt();
            switch (op) {
                case 1 -> {
                    List<Verificacion> lista = verificacionRepo.findAll();
                    if (lista.isEmpty()) System.out.println("No hay usuarios registrados.");
                    else lista.forEach(u -> { u.mostrarinfo(); System.out.println(); });
                }
                case 2 -> {
                    System.out.print("ID del usuario: ");
                    String id = sc.nextLine().trim();
                    Verificacion u = verificacionRepo.buscar(id);
                    if (u != null) u.mostrarinfo();
                    else System.out.println("Usuario no encontrado.");
                }
                case 3 -> crearUsuario();
                case 4 -> actualizarUsuario();
                case 5 -> {
                    System.out.print("ID del usuario a eliminar: ");
                    String id = sc.nextLine().trim();
                    if (verificacionRepo.buscar(id) != null) {
                        verificacionRepo.eliminar(id);
                        System.out.println("Usuario eliminado correctamente.");
                    } else System.out.println("Usuario no encontrado.");
                }
            }
        } while (op != 0);
    }

    private void crearUsuario() {
        System.out.print("ID         : "); String id   = sc.nextLine().trim();
        System.out.print("Nombre     : "); String nom  = sc.nextLine().trim();
        System.out.print("Correo     : "); String cor  = sc.nextLine().trim();
        System.out.print("Telefono   : "); String tel  = sc.nextLine().trim();
        System.out.print("Contrasena : "); String pass = sc.nextLine().trim();
        verificacionRepo.guardar(new Verificacion(id, pass, tel, cor, nom));
        System.out.println("Usuario creado correctamente.");
    }

    private void actualizarUsuario() {
        System.out.print("ID del usuario a actualizar: ");
        String id = sc.nextLine().trim();
        Verificacion u = verificacionRepo.buscar(id);
        if (u == null) { System.out.println("Usuario no encontrado."); return; }
        System.out.print("Nueva contrasena (Enter para dejar igual): ");
        String v = sc.nextLine().trim();
        if (!v.isEmpty()) u.setContraseña_personaladiministrativo(v);
        System.out.print("Nuevo telefono [" + u.getTelefono_personaladministrativo() + "]: ");
        v = sc.nextLine().trim();
        if (!v.isEmpty()) u.setTelefono_personaladministrativo(v);
        verificacionRepo.guardar(u);
        System.out.println("Usuario actualizado correctamente.");
    }

    // ══════════════════════════════════════════════════════
    //  UTILIDAD
    // ══════════════════════════════════════════════════════
    private int leerInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
