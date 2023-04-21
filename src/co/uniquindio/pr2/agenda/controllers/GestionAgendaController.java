package co.uniquindio.pr2.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.pr2.agenda.application.Aplicacion;
import co.uniquindio.pr2.agenda.model.Agenda;
import co.uniquindio.pr2.agenda.model.Categoria;
import co.uniquindio.pr2.agenda.model.Contacto;
import co.uniquindio.pr2.agenda.model.Grupo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    @FXML
    private TextField txtNombreGrupo;

    @FXML
    private Button btnEliminarGrupo;

    @FXML
    private Button btnAgregarGrupo;

    @FXML
    private TableView<Grupo> tableViewGrupos;

    @FXML
    private TableColumn<Grupo, Categoria> columnCategoriaGrupo;

    @FXML
    private TableColumn<Grupo, String> columnNombreGrupo;

    @FXML
    private ComboBox<Categoria> comboBoxCategoriaGrupo;

    @FXML
    private Button btnContactosGrupo;

    @FXML
    private Button btnNuevoGrupo;

    @FXML
    private Button btnActualizarGrupo;

    @FXML
    private Button btnAniadirContactoGrupo;

    @FXML
    private Button btnEliminarContactoGrupo;

    @FXML
    private Button btnListadoGruposContacto;

    //Creo la aplicacion
	private Aplicacion aplicacion;

	//Creo la agenda
	private Agenda agenda;

	//Creo el listado de contactos que se van a ver
	ObservableList<Contacto> listadoContactos = FXCollections.observableArrayList();

	//Creo el contacto que el usuario puede seleccionar
	private Contacto contactoSeleccion;

	//Creo el listado de grupos que se van a ver
	ObservableList<Grupo> listadoGrupos = FXCollections.observableArrayList();

	//Creo el grupo que el usuario puede seleccionar
	private Grupo grupoSeleccion;


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

		//Datos en la table view de grupos
		this.columnNombreGrupo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCategoriaGrupo.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		//Para poder seleccionar los grupos en una tabla
		tableViewGrupos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				grupoSeleccion = newSelection;
				mostrarInformacionGrupo();
			}
		});

		//Datos del comboBox de grupos
		this.comboBoxCategoriaGrupo.getItems().addAll(Categoria.values());
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		this.agenda = aplicacion.getAgenda();
		//Lista que se va a mostrar en contactos
		tableViewContactos.getItems().clear();
		tableViewContactos.setItems(getContactos());
		//Lista que se va a mostrar en grupos
		tableViewGrupos.getItems().clear();
		tableViewGrupos.setItems(getGrupos());
	}

	private ObservableList<Contacto> getContactos() {
		listadoContactos.addAll(agenda.getListaContactos());
		return listadoContactos;
	}

	private ObservableList<Grupo> getGrupos() {
		listadoGrupos.addAll(agenda.getListaGrupos());
		return listadoGrupos;
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
    			tableViewContactos.setItems(getContactos()); //Agrego los datos a la lista
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
    		tableViewContactos.setItems(getContactos());
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
			mostrarMensaje("Contacto Selección", "Contacto Selección", "No se ha seleccionado ningún contacto",
					AlertType.WARNING);
		}
    }

    @FXML
    void darListadoGruposContacto(ActionEvent event) {
    	if(contactoSeleccion != null) {
    		String gruposListados = aplicacion.darListadoGruposContacto(contactoSeleccion);
    		mostrarMensaje("Notificación contactos", "Notificación contactos", "Los grupos a los que pertenece "
    				+ "este contacto son: " + gruposListados, AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Contacto selección", "Contacto selección", "No se ha seleccionado ningún contacto",
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

    @FXML
    void mostrarContactosGrupo(ActionEvent event) {
    	if(grupoSeleccion != null) {
    		String contactosGrupo = aplicacion.mostrarContactosGrupo(grupoSeleccion);
    		mostrarMensaje("Notificación grupos", "Contactos en el grupo " + grupoSeleccion.getNombre() + ": ",
    				contactosGrupo, AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Grupo selección", "Grupo selección", "No se ha selccionado ningún grupo", AlertType.WARNING);
    	}
    }

	private void mostrarInformacionGrupo() {
		if(grupoSeleccion != null) {
			txtNombreGrupo.setText(grupoSeleccion.getNombre());
			comboBoxCategoriaGrupo.setValue(grupoSeleccion.getCategoria());
			//Deshabilito este textField
			txtNombreGrupo.setDisable(true);
		}
	}

    @FXML
    void nuevoGrupo(ActionEvent event) {
    	txtNombreGrupo.setText("Ingrese el nombre del grupo");
    	comboBoxCategoriaGrupo.setValue(Categoria.AMIGOS);
    	//Habilito este textfield
    	txtNombreGrupo.setDisable(false);
    }

    @FXML
    void actualizarGrupo(ActionEvent event) {
    	String nombreGrupo = txtNombreGrupo.getText();
    	Categoria categoriaGrupo = comboBoxCategoriaGrupo.getValue();
    	if(grupoSeleccion != null) {
    		aplicacion.actualizarGrupo(nombreGrupo, categoriaGrupo);
    		//Actualizo los datos de la interfaz
    		grupoSeleccion.setCategoria(categoriaGrupo);
    		//Refresh a la tabla para que se vean los cambios
    		tableViewGrupos.getItems().clear();
    		tableViewGrupos.setItems(getGrupos());
    		mostrarMensaje("Notificaión grupo", "Grupo actualizado", "El grupo " + nombreGrupo + " ha sido actualizado",
    				AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Grupo selcción", "Grupo selección", "No ha seleccionado ningún grupo", AlertType.WARNING);
    	}
    }

    @FXML
    void agregarGrupo(ActionEvent event) {
    	String nombreGrupo = txtNombreGrupo.getText();
    	Categoria categoriaGrupo = comboBoxCategoriaGrupo.getValue();
    	if(validarDatosGrupo(nombreGrupo, categoriaGrupo)) {
    		crearGrupo(nombreGrupo, categoriaGrupo);
    	}
    }

	private boolean validarDatosGrupo(String nombreGrupo, Categoria categoriaGrupo) {
		String notificacion = "";
		if(nombreGrupo == null || nombreGrupo.equals("")) {
			notificacion += "El nombre del grupo es invalido\n";
		}
		if(categoriaGrupo == null) {
			notificacion += "La categoria del grupo es invalida\n";
		}

		if(notificacion.equals("")) {
			return true;
		}
		mostrarMensaje("Notificación grupo", "Notificación grupo", notificacion, AlertType.WARNING);
		return false;
	}

	private void crearGrupo(String nombreGrupo, Categoria categoriaGrupo) {
		boolean fueCreado = aplicacion.crearGrupo(nombreGrupo, categoriaGrupo);
		if(fueCreado) {
			tableViewGrupos.getItems().clear(); //Limpio la lista
			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
			mostrarMensaje("Notificación grupo", "Grupo registrado", "El grupo " + nombreGrupo +
					" ha sido registrado", AlertType.INFORMATION);
		} else {
			mostrarMensaje("Notificación grupo", "Grupo no registrado", "El grupo " + nombreGrupo +
					" no fue registrado, ya que este nombre esta repetido", AlertType.WARNING);
		}
	}

	@FXML
    void eliminarGrupo(ActionEvent event) {
		if(grupoSeleccion != null) {
			if(aplicacion.eliminarGrupo(grupoSeleccion)) {
				mostrarMensaje("Grupo eliminado", "Grupo eliminado", "Se ha eliminado correctamente el grupo " +
						grupoSeleccion.getNombre(), AlertType.INFORMATION);
				listadoGrupos.remove(grupoSeleccion);
			}
		} else {
			mostrarMensaje("Grupo selección", "Grupo selección", "No se ha seleccionado ningun grupo", AlertType.WARNING);
		}
    }

    @FXML
    void aniadirContactoGrupo(ActionEvent event) {
    	if(contactoSeleccion != null && grupoSeleccion != null) {
    		if(aplicacion.aniadirContactoGrupo(contactoSeleccion, grupoSeleccion)) {
    			tableViewContactos.getItems().clear();
    			tableViewContactos.setItems(getContactos());
    			tableViewGrupos.getItems().clear(); //Limpio la lista
    			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
    			mostrarMensaje("Notificación grupo", "Notificación grupo", "Se ha añadido el contacto " +
    					contactoSeleccion.getNombre() + " a el grupo " + grupoSeleccion.getNombre(), AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificación grupo", "Notificación grupo", "No se pudo añadir el contacto."
    					+ " Verifique que el grupo o el contacto tenga espacio, o que el contacto ya esté en el grupo", AlertType.WARNING);
    		}
    	} else {
    		mostrarMensaje("Notificación grupo", "Notificación grupo", "Por favor verifique que se haya seleccionado"
    				+ " el contacto y el grupo deseado", AlertType.WARNING);
    	}
    }

    @FXML
    void eliminarContactoGrupo(ActionEvent event) {
    	if(contactoSeleccion != null && grupoSeleccion != null) {
    		if(aplicacion.eliminarContactoGrupo(contactoSeleccion, grupoSeleccion)) {
    			tableViewContactos.getItems().clear();
    			tableViewContactos.setItems(getContactos());
    			tableViewGrupos.getItems().clear(); //Limpio la lista
    			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
    			mostrarMensaje("Notificación grupo", "Notificación grupo", "Se pudo eliminar el contacto " +
    					contactoSeleccion.getNombre() + " del grupo " + grupoSeleccion.getNombre(), AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificación grupo", "Notificación grupo", "No se pudo eliminar el contacto " +
    					" verifique que ese contacto si perteneciera al grupo indicado", AlertType.WARNING);
    		}
    	} else {
    		mostrarMensaje("Notificación grupo", "Notificación grupo", "Por favor verifique que se haya seleccionado"
    				+ " el contacto y el grupo deseado", AlertType.WARNING);
    	}
    }



}
