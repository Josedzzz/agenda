package co.uniquindio.pr2.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.pr2.agenda.application.Aplicacion;
import co.uniquindio.pr2.agenda.model.Agenda;
import co.uniquindio.pr2.agenda.model.Contacto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionAgendaController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtTelefonoContacto;

    @FXML
    private TextField txtAliasContacto;

    @FXML
    private TextField txtDireccionContacto;

    @FXML
    private TableColumn<Contacto, String> columnAliasContacto;

    @FXML
    private TableColumn<Contacto, String> columnEmailContacto;

    @FXML
    private Button btnEliminarContacto;

    @FXML
    private TextField txtEmailContacto;

    @FXML
    private Button btnActualizarContacto;

    @FXML
    private Button btnAgregarContacto;

    @FXML
    private TableColumn<Contacto, String> columnTelefonoContacto;

    @FXML
    private TableColumn<Contacto, String> columnDireccionContacto;

    @FXML
    private TableColumn<Contacto, String> columnNombreContacto;

    @FXML
    private Button btnListarContactos;

    @FXML
    private Button btnBuscaarContacto;

    @FXML
    private TextField txtNombreContacto;

    @FXML
    private TextField txtBuscarContacto;

    @FXML
    private Button btnNuevoContacto;

    @FXML
    private TableView<Contacto> tableViewContactos;

    @FXML
    private Button btnEspaciosEnContactos;

    //Creo la aplicacion
	private Aplicacion aplicacion;

	//Creo la agenda
	private Agenda agenda;

	//Creo el listado de contactos que se van a ver
	ObservableList<Contacto> listadoContactos = FXCollections.observableArrayList();

	//Creo el contacto que el usuario puede seleccionar
	private Contacto contactoSeleccion;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Datos en la table view de contactos
		this.columnNombreContacto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnAliasContacto.setCellValueFactory(new PropertyValueFactory<>("alias"));
		this.columnDireccionContacto.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		this.columnTelefonoContacto.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		this.columnEmailContacto.setCellValueFactory(new PropertyValueFactory<>("email"));
		//Para poder seleccionar los documentos de una tabla
		tableViewContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				contactoSeleccion = newSelection;
				mostrarInformacionContacto();
			}
		});
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		this.agenda = aplicacion.getAgenda();
		//Lista que se va a mostrar en contactos
		tableViewContactos.getItems().clear();
		tableViewContactos.setItems(getDocumentos());
	}

	private ObservableList<Contacto> getDocumentos() {
		listadoContactos.addAll(agenda.getListaContactos());
		return listadoContactos;
	}

	private void mostrarInformacionContacto() {
		if(contactoSeleccion != null) {
			txtNombreContacto.setText(contactoSeleccion.getNombre());
			txtAliasContacto.setText(contactoSeleccion.getAlias());
			txtDireccionContacto.setText(contactoSeleccion.getDireccion());
			txtTelefonoContacto.setText(contactoSeleccion.getTelefono());
			txtEmailContacto.setText(contactoSeleccion.getEmail());
			//Deshabilito los textFields necesarios
			txtNombreContacto.setDisable(true);
			txtTelefonoContacto.setDisable(true);
		}
	}

    @FXML
    void nuevoContacto(ActionEvent event) {
		txtNombreContacto.setText("Ingrese el nombre del contacto");
		txtAliasContacto.setText("Ingrese el alias del contacto");
		txtDireccionContacto.setText("Ingrese la dirección del contacto");
		txtTelefonoContacto.setText("Ingrese el teléfono del contacto");
		txtEmailContacto.setText("Ingrese el email del contacto");
		//Deshabilito los textFields necesarios
		txtNombreContacto.setDisable(false);
		txtTelefonoContacto.setDisable(false);
    }

    @FXML
    void actualizarContacto(ActionEvent event) {
    	String nombre = txtNombreContacto.getText();
    	String alias = txtAliasContacto.getText();
    	String direccion = txtDireccionContacto.getText();
    	String telefono = txtTelefonoContacto.getText();
    	String email = txtEmailContacto.getText();
    	if(contactoSeleccion != null) {
    		if(datosValidosContacto(nombre, alias, direccion, telefono, email)) {
    			aplicacion.actualizarContacto(nombre, alias, direccion, telefono, email);
    			//Actualizo los datos de la interfaz
    			contactoSeleccion.setAlias(alias);
    			contactoSeleccion.setDireccion(direccion);
    			contactoSeleccion.setEmail(email);
    			//Actualizo los datos de la tabla
    			tableViewContactos.getItems().clear(); //Limpio la lista
    			tableViewContactos.setItems(getDocumentos()); //Agrego los datos a la lista
    			mostrarMensaje("Notificación contacto", "Contacto actualizado", "El contacto " + nombre +
    					" ha sido actualizado", AlertType.INFORMATION);
    		}
    	} else {
    		mostrarMensaje("Contacto selección", "Contacto seleccionado", "No se ha seleccionado ningún contacto",
    				AlertType.WARNING);
    	}
    }

    private boolean datosValidosContacto(String nombre, String alias, String direccion, String telefono, String email) {
    	String notificacion = "";
    	if(nombre == null || nombre.equals("")) {
    		notificacion += "El nombre es invalido\n";
    	}
    	if(alias == null || alias.equals("")) {
    		notificacion += "El alias es invalido\n";
    	}
    	if(direccion == null || direccion.equals("")) {
    		notificacion += "La direccion es invalida\n";
    	}
    	if(telefono == null || telefono.equals("")) {
    		notificacion += "El teléfono es invalido\n";
    	}
    	if(email == null || email.equals("")) {
    		notificacion += "El email es invalido\n";
    	}
    	//Si no hay notificacion los datos son validos
    	if(notificacion.equals("")) {
    		return true;
    	}
    	//notifica al usuario que la info es invalida
    	mostrarMensaje("Notificación contacto", "Información del contacto invalida", notificacion, AlertType.WARNING);
    	return false;


	}

	@FXML
    void agregarContacto(ActionEvent event) {
    	String nombre = txtNombreContacto.getText();
    	String alias = txtAliasContacto.getText();
    	String direccion = txtDireccionContacto.getText();
    	String telefono = txtTelefonoContacto.getText();
    	String email = txtEmailContacto.getText();

    	if(datosValidosContacto(nombre, alias, direccion, telefono, email)) {
    		crearContacto(nombre, alias, direccion, telefono, email);
    	}
    }

    private void crearContacto(String nombre, String alias, String direccion, String telefono, String email) {
    	boolean fueCreado = aplicacion.crearContacto(nombre, alias, direccion, telefono, email);
    	if(fueCreado) {
    		//Añado el contacto a el listadoDocumentos(tableView)
    		tableViewContactos.getItems().clear();
    		tableViewContactos.setItems(getDocumentos());
    		mostrarMensaje("Notificación contacto", "Contacto registrado", "El contacto " + nombre +
    				" ha sido registrado", AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Notificación contacto", "Contacto no registrado", "El contacto " + nombre +
    				" no ha sido registrado", AlertType.WARNING);
    	}
	}

	@FXML
    void eliminarContacto(ActionEvent event) {
		if(contactoSeleccion != null) {
			if(aplicacion.eliminarContacto(contactoSeleccion)) {
				//Elimina el contacto del listadoContactos
				listadoContactos.remove(contactoSeleccion);
				mostrarMensaje("Contacto eliminado", "Contacto eliminado", "Se ha eliminado correctamente",
						AlertType.INFORMATION);
			} else {
				mostrarMensaje("Contacto eliminado", "Fallo en eliminar contacto", "No ha eliminado correctamente",
						AlertType.INFORMATION);
			}
		} else {
			mostrarMensaje("Contacto Seleccion", "Contacto Seleccion", "No se ha seleccionado ningún contacto",
					AlertType.WARNING);
		}
    }

    @FXML
    void buscarContacto(ActionEvent event) {
    	String nombreContacto = txtBuscarContacto.getText();
    	String telefonoContacto = "";
    	if(nombreContacto == null || nombreContacto.equals("")) {
    	   	//notifica al usuario que la info es invalida
        	mostrarMensaje("Notificación contacto", "Información del contacto invalida",
        			"Ingrese el nombre del contacto", AlertType.WARNING);
    	} else {
    		telefonoContacto = aplicacion.darTelefonoContacto(nombreContacto);
    		if(!telefonoContacto.equals("")) {
    			mostrarMensaje("Notificación contacto", "El teléfono del contacto es: ",
    					telefonoContacto, AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificación contacto", "Información del contacto invalida",
            			"No se ha encontrado ningún contacto con ese nombre", AlertType.WARNING);
    		}
    	}
    }

    @FXML
    void espaciosEnContactos(ActionEvent event) {
    	int espaciosDisponibles = aplicacion.espaciosDisponiblesContactos();
    	mostrarMensaje("Notificación agenda", "Espacios de contactos", "Hay " + espaciosDisponibles +
    			" para contactos", AlertType.INFORMATION);
    }

    @FXML
    void listarContactos(ActionEvent event) {
    	String contactosListados = aplicacion.listarContactos();
    	mostrarMensaje("Notificación agenda", "Contactos en agenda: ", contactosListados, AlertType.INFORMATION);
    }


    /**
     * Muestra un mensaje dependiendo con el tipo de alerta seleccionado
     * @param title
     * @param header
     * @param content
     * @param alertType
     */
    public void mostrarMensaje(String title, String header, String content, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
    }



}
