package co.uniquindio.pr2.agenda.application;


import co.uniquindio.pr2.agenda.controllers.GestionAgendaController;
import co.uniquindio.pr2.agenda.model.Agenda;
import co.uniquindio.pr2.agenda.model.Categoria;
import co.uniquindio.pr2.agenda.model.Contacto;
import co.uniquindio.pr2.agenda.model.Grupo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Aplicacion extends Application {

	private Stage primaryStage;
	private Agenda agenda;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.agenda = new Agenda("Uq", 20, 20, 20);

		mostrarVentanaPrincipal();
	}

	private void mostrarVentanaPrincipal() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("/co/uniquindio/pr2/agenda/views/GestionAgendaView.fxml"));
			AnchorPane anchorPane = (AnchorPane)loader.load();
			GestionAgendaController gestionAgendaController = loader.getController();
			gestionAgendaController.setAplicacion(this);

			Scene scene = new Scene(anchorPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

//-----------------------------------------------------------------------------------------------------------------------

	public void actualizarContacto(String nombre, String alias, String direccion, String telefono, String email) {
		agenda.actualizarContacto(nombre, alias, direccion, telefono, email);
	}

	public boolean crearContacto(String nombre, String alias, String direccion, String telefono, String email) {
		boolean fueCreado = agenda.aniadirContacto(nombre, alias, direccion, telefono, email);
		return fueCreado;
	}

	public boolean eliminarContacto(Contacto contactoSeleccion) {
		boolean eliminarContacto = agenda.eliminarContacto(contactoSeleccion);
		return eliminarContacto;
	}

	public String darListadoGruposContacto(Contacto contactoSeleccion) {
		String gruposListados = agenda.darListadoGruposContacto(contactoSeleccion);
		return gruposListados;
	}

	public String darTelefonoContacto(String nombreContacto) {
		String telefonoContacto = agenda.darTelefonoContacto(nombreContacto);
		return telefonoContacto;
	}

	public int espaciosDisponiblesContactos() {
		int espaciosDisponibles = agenda.espaciosDisponiblesContactos();
		return espaciosDisponibles;
	}

	public String listarContactos() {
		String contactosListados = agenda.listarContactos();
		return contactosListados;
	}

	public String mostrarContactosGrupo(Grupo grupoSeleccion) {
		String contactosGrupo = agenda.mostrarContactosGrupo(grupoSeleccion);
		return contactosGrupo;
	}

	public void actualizarGrupo(String nombreGrupo, Categoria categoriaGrupo) {
		agenda.actualizarGrupo(nombreGrupo, categoriaGrupo);
	}

	public boolean crearGrupo(String nombreGrupo, Categoria categoriaGrupo) {
		boolean fueCreado = agenda.aniadirGrupo(nombreGrupo, categoriaGrupo);
		return fueCreado;
	}

	public boolean eliminarGrupo(Grupo grupoSeleccion) {
		boolean eliminarGrupo = agenda.eliminarGrupo(grupoSeleccion);
		return eliminarGrupo;
	}

	public boolean aniadirContactoGrupo(Contacto contactoSeleccion, Grupo grupoSeleccion) {
		boolean fueAgregado = agenda.aniadirContactoGrupo(contactoSeleccion, grupoSeleccion);
		return fueAgregado;
	}

	public boolean eliminarContactoGrupo(Contacto contactoSeleccion, Grupo grupoSeleccion) {
		boolean fueEliminado = agenda.eliminarContactoGrupo(contactoSeleccion, grupoSeleccion);
		return fueEliminado;
	}





}
