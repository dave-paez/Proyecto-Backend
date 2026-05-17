
//Importamos los modelos
import models.Persona;
import models.Mantenimientoderecursos;
import models.Participantes;
import models.Patrocinios;
import models.Recursos;
import models.Verificacion;
import models.Proyectos;

// importamos el array
import java.util.ArrayList;
//salida de datos, consola o ventana emergente, en este caso usaremos una ventana emergente.
import javax.swing.JOptionPane;

public class App {
	public static void main(String[] args) throws Exception {
		// Creamos un ArrayList para cada modelo
		// esto es como un invenatario para cada modelo, donde guardaremos los objetos
		// creados de cada clase
		ArrayList<Participantes> participantesList = new ArrayList<>();
		ArrayList<Proyectos> proyectosList = new ArrayList<>();
		ArrayList<Recursos> recursosList = new ArrayList<>();
		ArrayList<Patrocinios> patrocinioList = new ArrayList<>();
		ArrayList<Mantenimientoderecursos> mantenimientoList = new ArrayList<>();
		ArrayList<Verificacion> registroList = new ArrayList<>();

		// CREACION DE VARIABLES PARA PROYECTOS
		/*
		 * creamos variables temporales para cada atributo de cada modelo.
		 * cuando hagamos el " new Xmodelo" le pasaremos los datos temporales a cada
		 * cosntructor de cada clase
		 * y despues agregaremos el objeto creado a su inventario propio, osea a los
		 * ArrayList.
		 */

		// registro

		String Id_delregistro = " ";
		String nombre_delregistro;
		String telefono_delregistro = " ";
		String correo_delregistro;
		String contraseña_delregistro = " ";

		// mantenimiento de recursos
		String Id_mantenimientoderecursos = " ";
		String nombre_mantenimientoderecursos;
		String categoria_mantenimientoderecursos;
		String estado_mantenimientoderecursos;
		String ubicacion_mantenimientoderecursos;
		String fechadeingreso_mantenimientoderecursos = " ";

		// parcipantes
		String Id_delosparticipantes = " ";
		String nombre_delosparticipantes;
		String ubicacion_participante = " ";
		String correo_delosparticipantes;
		String rol_delosparticipante;

		// patrocinio
		String Id_delpatrocinador = " ";
		String nombre_delpatrocinador;
		String correo_delpatrocinador;
		String contacto_delpatrocinador = " ";
		String tipo_depatrocinador;
		String aporte_delpatrocinador = " ";

		// proyectos
		String Id_delproyecto = " ";
		String nombre_delproyecto;
		String tipo_delproyecto;
		String descripcion_delproyecto;
		String fechaInicio_delproyecto = " ";
		String fechaFin_delproyecto = " ";
		String estado_delproyecto;
			String correo_delproyecto;

		// recursos
		String Id_delrecurso = " ";
		String nombre_delrecurso;
		String categoria_delrecurso;
		String estado_delrecurso;
		String ubicacion_delrecurso;

		// MENUS
		/*
		 * usamos opciones para el menu principal, y opcion1, opcion2, opcion3, opcion4,
		 * opcion5, opcion6 para los submenus de cada modelo.
		 */
		int opciones = 0, opcion1 = 0, opcion2 = 0, opcionrecursos = 0, opcionproyectos = 0,
				opcionParticipantes = 0,
				opcionpatrocinio = 0, opcionmantenimiento = 0;

		// MENU PRINCIPAL
		do {
			opciones = Integer.parseInt(JOptionPane.showInputDialog("BIENVENIDO AL SOFTWARE INSTITUCIONAL ACADEMICO \n"
					+ "1. iniciar sesion \n"
					+ "2. registrarse \n"
					+ "3. salir"));
			switch (opciones) {
				case 1:
					// SUBMENU DE INICIO DE SESION, para que el usuario quiera o no iniciar sesion
					do {
						opcion1 = Integer.parseInt(JOptionPane.showInputDialog("BIENVENIDO INICIO DE SESION \n"
								+ "Elija una de las siguientes opciones: \n"
								+ "1. iniciar sesion \n"
								+ "2. salir..."));
						switch (opcion1) {
							case 1:
								boolean encontrado = false;
								// pedimos datos
								Id_delregistro = JOptionPane
										.showInputDialog("Ingrese su ID:");
								contraseña_delregistro = JOptionPane.showInputDialog(
										"Ingrese su contraseña:");
								// for each para comparar los datos ingresados con los guardados en el array
								for (Verificacion comprobar : registroList) {
									// comparamos el ID y la contraseña
									if (comprobar.getPersonaladiministrativoId().equals(Id_delregistro)
											&& comprobar.getContraseña_personaladiministrativo().equals(contraseña_delregistro)) {
										encontrado = true;
										break;
									}
								} // si se encontro el ID y la contraseña, deja pasar al usuario
								if (encontrado) {
									JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
									// MENU DE TODAS LAS CLASES
									do {
										opcion2 = Integer.parseInt(JOptionPane.showInputDialog("MENU PRINCIPAL \n"
												+ "1. Proyectos \n"
												+ "2. Participantes \n"
												+ "3. Recursos \n"
												+ "4. Patrocinio \n"
												+ "5. Mantenimiento de recursos \n"
												+ "6. Cerrar sesión"));
										switch (opcion2) {
											case 1:
												// MENU DE PROYECTOS
												// JHON STIVEN
												do {
													opcionproyectos = Integer.parseInt(JOptionPane.showInputDialog("PROYECTOS \n"
															+ "1. Registrar proyecto \n"
															+ "2. Ver todos los proyectos \n"
															+ "3. Actualizar proyecto \n"
															+ "4. Eliminar proyecto \n"
															+ "5. Salir"));
													switch (opcionproyectos) {
														case 1:
															// registrar proyecto
															Id_delproyecto = JOptionPane.showInputDialog("Ingrese el ID del proyecto:");
															boolean IDproyectoexiste = false;
															for (Proyectos comprobar : proyectosList) {
																if (comprobar.getProyectoId().equals(Id_delproyecto)) {
																	IDproyectoexiste = true;
																	break;
																}
															}
															if (IDproyectoexiste) {
																JOptionPane.showMessageDialog(null, "ese ID ya existe");
															} else {
																nombre_delproyecto = JOptionPane.showInputDialog("Ingrese el nombre del proyecto");
																tipo_delproyecto = JOptionPane.showInputDialog("Ingrese el tipo del proyecto");
																descripcion_delproyecto = JOptionPane
																		.showInputDialog("Ingrese la descripcion del proyecto");
																estado_delproyecto = JOptionPane.showInputDialog("Ingrese el estado del proyecto");
																fechaInicio_delproyecto = JOptionPane.showInputDialog("Ingrese la fecha de inicio");
																fechaFin_delproyecto = JOptionPane.showInputDialog("Ingrese la fecha de fin");
																correo_delproyecto = JOptionPane.showInputDialog("Ingrese el correo del proyecto");
																proyectosList.add(new Proyectos(Id_delproyecto, nombre_delproyecto, correo_delproyecto, tipo_delproyecto, descripcion_delproyecto,
																		fechaInicio_delproyecto, fechaFin_delproyecto, estado_delproyecto));
																JOptionPane.showMessageDialog(null, "proyecto registrado");
															}
															break;
														case 2:
															// ver todos los proyectos
															if (proyectosList.isEmpty()) {
																JOptionPane.showMessageDialog(null, "No hay proyectos registrados");
															} else {
																String hoja = "";
																for (Proyectos mostrarproyectoss : proyectosList) {
																	hoja += "------ \n"
																			+ "ID: " + mostrarproyectoss.getProyectoId() + "\n"
																			+ "nombre: " + mostrarproyectoss.getNombre_proyecto() + "\n"
																			+ "tipo: " + mostrarproyectoss.getTipo_proyecto() + "\n"
																			+ "descripcion: " + mostrarproyectoss.getDescripcion_proyecto() + "\n"
																			+ "estado: " + mostrarproyectoss.getEstado_proyecto() + "\n"
																			+ "fecha inicio: " + mostrarproyectoss.getFechaInicio_proyecto() + "\n"
																			+ "fecha fin: " + mostrarproyectoss.getFechaFin_proyecto() + "\n";
																}
																JOptionPane.showMessageDialog(null, "LISTA DE PROYECTOS \n" + hoja);
															}
															break;
														case 3:
															// actualizar proyecto
															Id_delproyecto = JOptionPane.showInputDialog("Ingrese el ID del proyecto a actualizar");
															boolean proyectoactualizado = false;
															for (Proyectos actualizarrr : proyectosList) {
																if (actualizarrr.getProyectoId().equals(Id_delproyecto)) {
																	proyectoactualizado = true;
																	nombre_delproyecto = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
																	tipo_delproyecto = JOptionPane.showInputDialog("Ingrese el nuevo tipo");
																	descripcion_delproyecto = JOptionPane.showInputDialog("Ingrese la nueva descripcion");
																	estado_delproyecto = JOptionPane.showInputDialog("Ingrese el nuevo estado");
																	fechaInicio_delproyecto = JOptionPane
																			.showInputDialog("Ingrese la nueva fecha de inicio");
																	fechaFin_delproyecto = JOptionPane.showInputDialog("Ingrese la nueva fecha de fin");
																	// actualizamos
																	actualizarrr.setNombre_proyecto(nombre_delproyecto);
																	actualizarrr.setTipo_proyecto(tipo_delproyecto);
																	actualizarrr.setDescripcion_proyecto(descripcion_delproyecto);
																	actualizarrr.setEstado_proyecto(estado_delproyecto);
																	actualizarrr.setFechaInicio_proyecto(fechaInicio_delproyecto);
																	actualizarrr.setFechaFin_proyecto(fechaFin_delproyecto);
																	JOptionPane.showMessageDialog(null, "proyecto actualizado");
																	break;
																}
															}
															if (!proyectoactualizado) {
																JOptionPane.showMessageDialog(null, "Este ID no existe");
															}
															break;
														case 4:
															// eliminar proyecto
															Id_delproyecto = JOptionPane.showInputDialog("ID del proyecto a eliminar:");
															boolean proyectoEliminado = false;
															for (int i = 0; i < proyectosList.size(); i++) {
																if (proyectosList.get(i).getProyectoId().equals(Id_delproyecto)) {
																	proyectosList.remove(i);
																	proyectoEliminado = true;
																	JOptionPane.showMessageDialog(null, "Proyecto eliminado");
																	break;
																}
															}
															if (!proyectoEliminado) {
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
											// -----------------------------------------------------------------------------------------------
											case 2:
												// MENU DE PARTICPANTES
												// JOHAN GONZALEZ
												do {
													opcionParticipantes = Integer.parseInt(JOptionPane.showInputDialog("PARTICIPANTES \n"
															+ "1. Registrar participante \n"
															+ "2. lista de todos los participantes \n"
															+ "3. Buscar participante por ID \n"
															+ "4. Actualizar participante \n"
															+ "5. Eliminar participante \n"
															+ "6. volver al menu principal"));
													switch (opcionParticipantes) {
														case 1:
															Id_delosparticipantes = JOptionPane.showInputDialog("Ingrese el ID del participante:");
															boolean existeparticipante = false;
															for (Participantes comprobarIDparticipante : participantesList) {
																if (comprobarIDparticipante.getParticipanteId().equals(Id_delosparticipantes)) {
																	existeparticipante = true;
																	break;
																}
															}
															if (existeparticipante) {
																JOptionPane.showMessageDialog(
																		null,
																		"ese ID ya existe");
															} else {
																nombre_delosparticipantes = JOptionPane
																		.showInputDialog("Ingrese el nombre del participante:");
																ubicacion_participante = JOptionPane
																		.showInputDialog("Ingrese la ubicacion del participante:");
																correo_delosparticipantes = JOptionPane
																		.showInputDialog("Ingrese el correo del participante:");
																rol_delosparticipante = JOptionPane.showInputDialog("Ingrese el rol del participante:");
																participantesList
																		.add(new Participantes(Id_delosparticipantes, nombre_delosparticipantes,
																				ubicacion_participante, correo_delosparticipantes, rol_delosparticipante));
																JOptionPane.showMessageDialog(null, "Participante registrado exitosamente");
															}
															break;
														case 2:
															if (participantesList.isEmpty()) {
																JOptionPane.showMessageDialog(null, "No hay participantes registrados");
															} else {
																String hojadepapeldos = "";
																for (Participantes mostrarlistaparticipante : participantesList) {
																	hojadepapeldos += "------ \n"
																			+ "ID: " + mostrarlistaparticipante.getParticipanteId() + "\n"
																			+ "nombre: " + mostrarlistaparticipante.getNombre_participante() + "\n"
																			+ "ubicacion: " + mostrarlistaparticipante.getUbicacion_participante() + "\n"
																			+ "correo: " + mostrarlistaparticipante.getCorreo_participante() + "\n"
																			+ "rol: " + mostrarlistaparticipante.getRol_participante();
																}
																JOptionPane.showMessageDialog(null, "LISTA DE PARTICIPANTES \n" + hojadepapeldos);
															}
															break;
														case 3:
															Id_delosparticipantes = JOptionPane
																	.showInputDialog("Ingrese el ID del participante que desea buscar");
															boolean participanteencontrado = false;
															for (Participantes buscarparti : participantesList) {
																if (buscarparti.getParticipanteId().equals(Id_delosparticipantes)) {
																	String hojadepapeltres;
																	hojadepapeltres = "PARTICIPANTE \n"
																			+ "ID: " + buscarparti.getParticipanteId() + "\n"
																			+ "nombre: " + buscarparti.getNombre_participante() + "\n"
																			+ "ubicacion: " + buscarparti.getUbicacion_participante() + "\n"
																			+ "correo: " + buscarparti.getCorreo_participante() + "\n"
																			+ "rol: " + buscarparti.getRol_participante();
																	// Mostramos participante
																	JOptionPane.showMessageDialog(null, hojadepapeltres);
																	participanteencontrado = true;
																	break;
																}
															}
															if (!participanteencontrado) {
																JOptionPane.showMessageDialog(null, "Este ID no existe");
															}
															break;
														case 4:
															Id_delosparticipantes = JOptionPane
																	.showInputDialog("Ingrese el ID del participante que desea actualizar");
															boolean seencontro = false;
															for (Participantes actualizarparticipante : participantesList) {
																if (actualizarparticipante.getParticipanteId().equals(Id_delosparticipantes)) {
																	seencontro = true;
																	nombre_delosparticipantes = JOptionPane
																			.showInputDialog("Ingrese el nuevo nombre del participante");
																	ubicacion_participante = JOptionPane
																			.showInputDialog("Ingrese la nueva ubicacion del participante");
																	correo_delosparticipantes = JOptionPane
																			.showInputDialog("Ingrese el nuevo correo del participante");
																	rol_delosparticipante = JOptionPane
																			.showInputDialog("Ingrese el nuevo rol del participante");
																	actualizarparticipante.setNombre_participante(nombre_delosparticipantes);
																	actualizarparticipante.setUbicacion_participante(ubicacion_participante);
																	actualizarparticipante.setCorreo_participante(correo_delosparticipantes);
																	actualizarparticipante.setRol_participante(rol_delosparticipante);
																	JOptionPane.showMessageDialog(null, "Actualizacion hecha");
																	break;
																}
															}
															if (!seencontro) {
																JOptionPane.showMessageDialog(null, "Este ID no existe pri");
															}
															break;
														case 5:
															Id_delosparticipantes = JOptionPane
																	.showInputDialog("Ingrese el ID del participante que desea eliminar:");
															boolean sepuedeeliminar = false;
															for (int i = 0; i < participantesList.size(); i++) {
																if (participantesList.get(i).getParticipanteId().equals(Id_delosparticipantes)) {
																	participantesList.remove(i);
																	sepuedeeliminar = true;
																	JOptionPane.showMessageDialog(null, "Participante eliminado exitosamente");
																	break;
																}
															}
															if (!sepuedeeliminar) {
																JOptionPane.showMessageDialog(null, "ID no encontrado");
															}
															break;
														case 6:
															JOptionPane.showMessageDialog(null, "Devuelta al menu principal");
															break;
													}
												} while (opcionParticipantes != 6);
												break;
											// -------------------------------------------------------------------------------------------------
											case 3:
												// MENU DE RECURSOS
												// DAVE PAEZ
												do {
													opcionrecursos = Integer.parseInt(JOptionPane.showInputDialog("RECURSOS \n"
															+ "1. Ver todos los recursos \n"
															+ "2. Registrar algun recurso  \n"
															+ "3. Actualizar recurso \n"
															+ "4. Eliminar recurso \n"
															+ "5. volver al menu principal"));
													switch (opcionrecursos) {
														case 1:
															if (recursosList.isEmpty()) {
																JOptionPane.showMessageDialog(null, "No hay recursos registrados");
															} else {
																String hojadepapel = " ";
																for (Recursos mostrarlista : recursosList) {
																	hojadepapel += "------"
																			+ "ID: " + mostrarlista.getRecursoId() + "\n"
																			+ "nombre: " + mostrarlista.getNombre_delrecurso() + "\n"
																			+ "categoria: " + mostrarlista.getCategoria_delrecurso() + "\n"
																			+ "estado: " + mostrarlista.getEstado_delrecurso() + "\n"
																			+ "ubicacion:" + mostrarlista.getUbicacion_delrecurso();
																}
																JOptionPane.showMessageDialog(null, "LISTA DE RECURSOS \n" + hojadepapel);
															}
															break;
														case 2:
															Id_delrecurso = JOptionPane.showInputDialog("Ingrese el ID del recurso:");
															boolean IDyaexiste = false;
															for (Recursos comprobarIDrecurso : recursosList) {
																if (comprobarIDrecurso.getRecursoId().equals(Id_delrecurso)) {
																	IDyaexiste = true;
																	break;
																}
															}
															if (IDyaexiste) {
																JOptionPane.showMessageDialog(null, "ese ID ya existe");
															} else {
																nombre_delrecurso = JOptionPane.showInputDialog("Ingrese el nombre del recurso:");
																categoria_delrecurso = JOptionPane.showInputDialog("Ingrese la categoria del recurso:");
																estado_delrecurso = JOptionPane.showInputDialog("Ingrese el estado del recurso:");
																ubicacion_delrecurso = JOptionPane.showInputDialog("Ingrese la ubicacion del recurso:");
																recursosList.add(new Recursos(Id_delrecurso, nombre_delrecurso, categoria_delrecurso,
																		estado_delrecurso, ubicacion_delrecurso));
																JOptionPane.showMessageDialog(null, "Recurso registrado exitosamente");
															}
															break;
														case 3:
															Id_delrecurso = JOptionPane
																	.showInputDialog("Ingrese el ID del recurso que desea actualizar:");
															boolean IDencontrado = false;
															for (Recursos actualizar : recursosList) {
																if (actualizar.getRecursoId().equals(Id_delrecurso)) {
																	IDencontrado = true;
																	nombre_delrecurso = JOptionPane
																			.showInputDialog("Ingrese el nuevo nombre del recurso:");
																	categoria_delrecurso = JOptionPane
																			.showInputDialog("Ingrese la nueva categoria del recurso:");
																	estado_delrecurso = JOptionPane
																			.showInputDialog("Ingrese el nuevo estado del recurso:");
																	ubicacion_delrecurso = JOptionPane
																			.showInputDialog("Ingrese la nueva ubicacion del recurso:");
																	actualizar.setNombre_delrecurso(nombre_delrecurso);
																	actualizar.setCategoria_delrecurso(categoria_delrecurso);
																	actualizar.setEstado_delrecurso(estado_delrecurso);
																	actualizar.setUbicacion_delrecurso(ubicacion_delrecurso);
																	JOptionPane.showMessageDialog(null, "Recurso actualizado exitosamente");
																	break;
																}
															}
															if (!IDencontrado) {
																JOptionPane.showMessageDialog(null, "ID no encontrado");
															}
															break;
														case 4:
															Id_delrecurso = JOptionPane
																	.showInputDialog("Ingrese el ID del recurso que desea eliminar:");
															boolean iddelrecursoencontrado = false;
															for (int i = 0; i < recursosList.size(); i++) {
																if (recursosList.get(i).getRecursoId().equals(Id_delrecurso)) {
																	recursosList.remove(i);
																	iddelrecursoencontrado = true;
																	JOptionPane.showMessageDialog(null, "Recurso eliminado exitosamente");
																	break;
																}
															}
															if (!iddelrecursoencontrado) {
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
											// --------------------------------------------------------------------------------------------------------
											case 4:
												// MENU DE PATROCINIO
												// JUAN DUARTE

												do {
													opcionpatrocinio = Integer.parseInt(JOptionPane.showInputDialog(
															"PATROCINIO\n" +
																	"1. Ver todos los patrocinadores\n" +
																	"2. Crear nuevo patrocinador\n" +
																	"3. Actualizar patrocinador\n" +
																	"4. Eliminar patrocinador\n" +
																	"5. Ver información de un patrocinador\n" +
																	"6. Ver rol de un patrocinador\n" +
																	"7. Salir"));
													switch (opcionpatrocinio) {
														case 1:
															if (patrocinioList.isEmpty()) {
																JOptionPane.showMessageDialog(null, "No hay patrocinadores registrados");
															} else {
																String listaPatrocinadores = "";
																for (Patrocinios pat : patrocinioList) {
																	listaPatrocinadores += "ID: " + pat.getId() +
																			" nombre: " + pat.getNombre() +
																			" tipo: " + pat.getTipo_patrocinador() +
																			" aporte: " + pat.getAporte_patrocinador()
																			+ "--\n";
																}
																JOptionPane.showMessageDialog(null, "LISTA DE PATROCINADORES\n" + listaPatrocinadores);
															}
															break;
														case 2:// crear patro
															Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el id");
															boolean idExiste = false;
															for (Patrocinios pat : patrocinioList) {
																if (pat.getId().equals(Id_delpatrocinador)) {
																	idExiste = true;
																	break;
																}
															}
															if (idExiste) {
																JOptionPane.showMessageDialog(null, "ese id ya existe");
															} else {
																nombre_delpatrocinador = JOptionPane.showInputDialog("Nombre:");
																correo_delpatrocinador = JOptionPane.showInputDialog("Correo:");
																contacto_delpatrocinador = JOptionPane.showInputDialog("Contacto:");
																tipo_depatrocinador = JOptionPane.showInputDialog("Tipo:");
																aporte_delpatrocinador = JOptionPane.showInputDialog("Aporte:");
																Patrocinios nuevoPatrocinador = new Patrocinios(Id_delpatrocinador, nombre_delpatrocinador,
																		correo_delpatrocinador,
																		contacto_delpatrocinador, tipo_depatrocinador, aporte_delpatrocinador);
																patrocinioList.add(nuevoPatrocinador);
																JOptionPane.showMessageDialog(null, "patrocinador hecho");
															}
															break;
														case 3: // Actualizar
															Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el ID a actualizar:");
															boolean actualizado = false;
															for (Patrocinios pat : patrocinioList) {
																if (pat.getId().equals(Id_delpatrocinador)) {
																	actualizado = true;
																	nombre_delpatrocinador = JOptionPane.showInputDialog("Nuevo nombre:");
																	correo_delpatrocinador = JOptionPane.showInputDialog("Nuevo correo:");
																	contacto_delpatrocinador = JOptionPane.showInputDialog("Nuevo contacto:");
																	tipo_depatrocinador = JOptionPane.showInputDialog("Nuevo tipo:");
																	aporte_delpatrocinador = JOptionPane.showInputDialog("Nuevo aporte:");
																	pat.setNombre(nombre_delpatrocinador);
																	pat.setCorreo(correo_delpatrocinador);
																	pat.setContacto_patrocinador(contacto_delpatrocinador);
																	pat.setTipo_patrocinador(tipo_depatrocinador);
																	pat.setAporte_patrocinador(aporte_delpatrocinador);
																	JOptionPane.showMessageDialog(null, "actualizado");
																	break;
																}
															}
															if (!actualizado) {
																JOptionPane.showMessageDialog(null, "id no encontrado");
															}
															break;
														case 4: // Eliminar
															Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el id");
															boolean eliminado = false;
															for (int i = 0; i < patrocinioList.size(); i++) {
																if (patrocinioList.get(i).getId().equals(Id_delpatrocinador)) {
																	patrocinioList.remove(i);
																	eliminado = true;
																	JOptionPane.showMessageDialog(null, "eliminado");
																	break;
																}
															}
															if (!eliminado) {
																JOptionPane.showMessageDialog(null, "id no encontrado");
															}
															break;
														case 5: // Ver info
															Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el ID a buscar:");
															boolean patroooencontradoo = false;
															for (Persona nombrefor : patrocinioList) {
																if (nombrefor.getId().equals(Id_delpatrocinador)) {
																	patroooencontradoo = true;
																	nombrefor.mostrarinfo();
																	JOptionPane.showMessageDialog(null, "Ver consola para la información completa");
																	break;
																}
															}
															if (!patroooencontradoo) {
																JOptionPane.showMessageDialog(null, "id no encontrado");
															}
															break;
														case 6: // Ver rol
															Id_delpatrocinador = JOptionPane.showInputDialog("Ingrese el id:");
															boolean rolEncontradopatro = false;
															for (Persona fornombree : patrocinioList) {
																if (fornombree.getId().equals(Id_delpatrocinador)) {
																	rolEncontradopatro = true;
																	JOptionPane.showMessageDialog(null, fornombree.rol());
																	break;
																}
															}
															if (!rolEncontradopatro) {
																JOptionPane.showMessageDialog(null, "id no encontrado");
															}
															break;
														case 7:
															JOptionPane.showMessageDialog(null, "Saliendo");
															break;
														default:
															JOptionPane.showMessageDialog(null, "No.solo números del 1 al 7");
													}
												} while (opcionpatrocinio != 7);
												break;
											// -----------------------------------------------------------------------------------------------------------
											case 5:
												// MENU MANTENIMIENTO
												// SAMUEL GONZALEZ
												do {
													opcionmantenimiento = Integer.parseInt(JOptionPane.showInputDialog("MANTENIMIENTO \n"
															+ "1. Ver todos los objetos en mantenimiento \n"
															+ "2. Crear nuevo objeto en mantenimiento \n"
															+ "3. Actualizar objeto \n"
															+ "4. Eliminar objeto \n"
															+ "5. Salir"));
													switch (opcionmantenimiento) {
														case 1:
															// mostrar objetos
															if (mantenimientoList.isEmpty()) {
																JOptionPane.showMessageDialog(null, "no hay objetos en mantenimiento registrados");
															} else {
																String hojita = "";
																for (Mantenimientoderecursos mostrar : mantenimientoList) {
																	hojita += "------ \n"
																			+ "ID: " + mostrar.getManteni_recursoId() + "\n"
																			+ "nombre: " + mostrar.getNombre_mantenimientorecursos() + "\n"
																			+ "descripcion: " + mostrar.getCategoria_mantenimientorecursos() + "\n"
																			+ "estado: " + mostrar.getEstado_mantenimientorecursos() + "\n"
																			+ "categoria: " + mostrar.getUbicacion_mantenimientorecursos() + "\n";
																}
																JOptionPane.showMessageDialog(null, "LISTA DE MANTENIMIENTOS \n" + hojita);
															}
															break;
														case 2:
															// meter un objeto en mantenimiento
															Id_mantenimientoderecursos = JOptionPane
																	.showInputDialog("Ingrese el ID del mantenimiento:");
															boolean Idmanteniexiste = false;
															for (Mantenimientoderecursos comprobar : mantenimientoList) {
																if (comprobar.getManteni_recursoId().equals(Id_mantenimientoderecursos)) {
																	Idmanteniexiste = true;
																	break;
																}
															}
															if (Idmanteniexiste) {
																JOptionPane.showMessageDialog(null, "ese ID ya existe");
															} else {
																nombre_mantenimientoderecursos = JOptionPane
																		.showInputDialog("Ingrese el nombre del objeto");
																categoria_mantenimientoderecursos = JOptionPane
																		.showInputDialog("Ingrese la descripcion");
																estado_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese el estado");
																ubicacion_mantenimientoderecursos = JOptionPane.showInputDialog("Ingrese la categoria");
																fechadeingreso_mantenimientoderecursos = JOptionPane
																		.showInputDialog("Ingrese la fecha de ingreso");
																mantenimientoList.add(new Mantenimientoderecursos(Id_mantenimientoderecursos,
																		nombre_mantenimientoderecursos, categoria_mantenimientoderecursos,
																		estado_mantenimientoderecursos, ubicacion_mantenimientoderecursos,
																		fechadeingreso_mantenimientoderecursos));
																JOptionPane.showMessageDialog(null, "Mantenimiento registrado");
															}
															break;
														case 3:
															// actualizar objeto en mantenimiento
															Id_mantenimientoderecursos = JOptionPane
																	.showInputDialog("Ingrese el ID del objeto a actualizar");
															boolean manteniactualizado = false;
															for (Mantenimientoderecursos cambiar : mantenimientoList) {
																if (cambiar.getManteni_recursoId().equals(Id_mantenimientoderecursos)) {
																	manteniactualizado = true;

																	nombre_mantenimientoderecursos = JOptionPane
																			.showInputDialog("Ingrese el nuevo nombre");
																	categoria_mantenimientoderecursos = JOptionPane
																			.showInputDialog("Ingrese la nueva descripcion");
																	estado_mantenimientoderecursos = JOptionPane
																			.showInputDialog("Ingrese el nuevo estado");
																	ubicacion_mantenimientoderecursos = JOptionPane
																			.showInputDialog("Ingrese la nueva categoria");
																	fechadeingreso_mantenimientoderecursos = JOptionPane
																			.showInputDialog("Ingrese la nueva fecha de ingreso");

																	cambiar.setNombre_mantenimientorecursos(nombre_mantenimientoderecursos);
																	cambiar.setCategoria_mantenimientorecursos(categoria_mantenimientoderecursos);
																	cambiar.setEstado_mantenimientorecursos(estado_mantenimientoderecursos);
																	cambiar.setUbicacion_mantenimientorecursos(ubicacion_mantenimientoderecursos);
																	cambiar
																			.setFechadeingreso_mantenimientorecursos(fechadeingreso_mantenimientoderecursos);
																	JOptionPane.showMessageDialog(null, "objeto actualizado");
																	break;
																}
															}
															if (!manteniactualizado) {
																JOptionPane.showMessageDialog(null, "Este ID no existe");
															}
															break;
														case 4:
															Id_mantenimientoderecursos = JOptionPane
																	.showInputDialog("Ingrese el ID del objeto a eliminar ");
															boolean mantenimientoeliminado = false;
															for (int i = 0; i < mantenimientoList.size(); i++) {
																if (mantenimientoList.get(i).getManteni_recursoId()
																		.equals(Id_mantenimientoderecursos)) {
																	mantenimientoList.remove(i);
																	mantenimientoeliminado = true;
																	JOptionPane.showMessageDialog(null, "objeto eliminado");
																	break;
																}
															}
															if (!mantenimientoeliminado) {
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
											// ----------------------------------------------------------------------------------------
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
								// si no quiere iniciar sesion, lo devuelve al menu principal
								JOptionPane.showMessageDialog(null,
										"Devuelta al menu principal");
								break;
							// se ingresa un numero diferente a 1 o 2, error
							default:
								JOptionPane.showMessageDialog(null,
										"ERROR, solo pueden numero del 1 al 2");
								break;
						}
					} while (opcion1 != 2);
					break;
				// resgistro de usuario,
				case 2:
					// pedimos datos
					nombre_delregistro = JOptionPane.showInputDialog("Ingrese su nombre:");
					telefono_delregistro = JOptionPane.showInputDialog("Ingrese su telefono:");
					correo_delregistro = JOptionPane.showInputDialog("Ingrese su correo:");
					Id_delregistro = JOptionPane.showInputDialog("Ingrese su ID:");
					contraseña_delregistro = JOptionPane.showInputDialog("Ingrese su contraseña:");
					// los pasamos al constructor de la clase registro, y lo agregamos a su
					// inventario, osea al ArrayList de registro
					registroList.add(new Verificacion(Id_delregistro, contraseña_delregistro, telefono_delregistro,
							correo_delregistro, nombre_delregistro));
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