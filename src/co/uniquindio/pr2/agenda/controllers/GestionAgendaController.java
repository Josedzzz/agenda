package co.uniquindio.pr2.agenda.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import co.uniquindio.pr2.agenda.application.Aplicacion;
import co.uniquindio.pr2.agenda.model.Agenda;
import co.uniquindio.pr2.agenda.model.Categoria;
import co.uniquindio.pr2.agenda.model.Contacto;
import co.uniquindio.pr2.agenda.model.Grupo;
import co.uniquindio.pr2.agenda.model.Reunion;
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
    private Button btnListadoReunionesContacto;

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

    @FXML
    private Button btnNuevaReunion;

    @FXML
    private TextField txtHoraReunion;

    @FXML
    private Button btnAgregarReunion;

    @FXML
    private TableColumn<Reunion, String> columnFechaReunion;

    @FXML
    private TableColumn<Reunion, String> columnHoraReunion;

    @FXML
    private TableColumn<Reunion, String> columnDescripcionReunion;

    @FXML
    private TextField txtDescripcionReunion;

    @FXML
    private TableView<Reunion> tableViewReuniones;

    @FXML
    private TextField txtFechaReunion;

    @FXML
    private Button btnActualizarReunion;

    @FXML
    private Button btnContactosReunion;

    @FXML
    private Button btnAniadirContactoReunion;

    @FXML
    private Button btnEliminarContactoReunion;

    @FXML
    private Button btnEliminarReunion;


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

	//Creo el listado de reuniones que se van a ver
	ObservableList<Reunion> listadoReuniones = FXCollections.observableArrayList();

	//Creo la reunion que el usuario puede seleccionar
	private Reunion reunionSeleccion;


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

		//Datos en la tableWiew de reuniones
		this.columnDescripcionReunion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.columnFechaReunion.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		this.columnHoraReunion.setCellValueFactory(new PropertyValueFactory<>("hora"));
		//Para poder seleccionar las reuniones en una tabla
		tableViewReuniones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				reunionSeleccion = newSelection;
				mostrarInfromacionReunion();
			}
		});

		//Datos del comboBox de grupos
		this.comboBoxCategoriaGrupo.getItems().addAll(Categoria.values());
	}

	/**
	 * Set de la apliacion con los respectivos datos en los tableviews
	 * @param aplicacion
	 */
	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
		this.agenda = aplicacion.getAgenda();
		//Lista que se va a mostrar en contactos
		tableViewContactos.getItems().clear();
		tableViewContactos.setItems(getContactos());
		//Lista que se va a mostrar en grupos
		tableViewGrupos.getItems().clear();
		tableViewGrupos.setItems(getGrupos());
		//Lista que se va a mostrar en reuniones:
		tableViewReuniones.getItems().clear();
		tableViewReuniones.setItems(getReuniones());
	}

	/**
	 * Para actualizar la tabla de contactos
	 * @return
	 */
	private ObservableList<Contacto> getContactos() {
		listadoContactos.addAll(agenda.getListaContactos());
		return listadoContactos;
	}

	/**
	 * Para actualizar la tabla de grupos
	 * @return
	 */
	private ObservableList<Grupo> getGrupos() {
		listadoGrupos.addAll(agenda.getListaGrupos());
		return listadoGrupos;
	}

	/**
	 * Para actualizar la tabla de reuniones
	 * @return
	 */
	private ObservableList<Reunion> getReuniones() {
		listadoReuniones.addAll(agenda.getListaReuniones());
		return listadoReuniones;
	}

	/**
	 * Muestra en los texfield la informacion del contacto seleccionado
	 */
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

	/**
	 * Le setea en los texfields info sobre lo que el usuario debe poner
	 * @param event
	 */
    @FXML
    void nuevoContacto(ActionEvent event) {
		txtNombreContacto.setText("Ingrese el nombre del contacto");
		txtAliasContacto.setText("Ingrese el alias del contacto");
		txtDireccionContacto.setText("Ingrese la direcci�n del contacto");
		txtTelefonoContacto.setText("Ingrese el tel�fono del contacto");
		txtEmailContacto.setText("Ingrese el email del contacto");
		//Deshabilito los textFields necesarios
		txtNombreContacto.setDisable(false);
		txtTelefonoContacto.setDisable(false);
    }

    /**
     * Si se selcciona un contacto se toman los datos de los textfields para actualizarlo
     * @param event
     */
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
    			mostrarMensaje("Notificaci�n contacto", "Contacto actualizado", "El contacto " + nombre +
    					" ha sido actualizado", AlertType.INFORMATION);
    		}
    	} else {
    		mostrarMensaje("Contacto selecci�n", "Contacto seleccionado", "No se ha seleccionado ning�n contacto",
    				AlertType.WARNING);
    	}
    }

    /**
     * Me verifica si los datos de un contacto son validos
     * @param nombre
     * @param alias
     * @param direccion
     * @param telefono
     * @param email
     * @return
     */
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
    		notificacion += "El tel�fono es invalido\n";
    	}
    	if(email == null || email.equals("")) {
    		notificacion += "El email es invalido\n";
    	}
    	//Si no hay notificacion los datos son validos
    	if(notificacion.equals("")) {
    		return true;
    	}
    	//notifica al usuario que la info es invalida
    	mostrarMensaje("Notificaci�n contacto", "Informaci�n del contacto invalida", notificacion, AlertType.WARNING);
    	return false;


	}

    /**
     * A�ade un contacto teniendo en cuenta los textFields
     * @param event
     */
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

	/**
	 * Crea el contacto y actualiza la table view si se pudo crear
	 * @param nombre
	 * @param alias
	 * @param direccion
	 * @param telefono
	 * @param email
	 */
    private void crearContacto(String nombre, String alias, String direccion, String telefono, String email) {
    	boolean fueCreado = aplicacion.crearContacto(nombre, alias, direccion, telefono, email);
    	if(fueCreado) {
    		//A�ado el contacto a el listadoDocumentos(tableView)
    		tableViewContactos.getItems().clear();
    		tableViewContactos.setItems(getContactos());
    		mostrarMensaje("Notificaci�n contacto", "Contacto registrado", "El contacto " + nombre +
    				" ha sido registrado", AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Notificaci�n contacto", "Contacto no registrado", "El contacto " + nombre +
    				" no ha sido registrado", AlertType.WARNING);
    	}
	}

    /**
     * Elimina el contacto seleccionado
     * @param event
     */
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
			mostrarMensaje("Contacto Selecci�n", "Contacto Selecci�n", "No se ha seleccionado ning�n contacto",
					AlertType.WARNING);
		}
    }

	/**
	 * Da la lista de grupos del contacto selccionado
	 * @param event
	 */
    @FXML
    void darListadoGruposContacto(ActionEvent event) {
    	if(contactoSeleccion != null) {
    		String gruposListados = aplicacion.darListadoGruposContacto(contactoSeleccion);
    		mostrarMensaje("Notificaci�n contactos", "Notificaci�n contactos", "Los grupos a los que pertenece "
    				+ "este contacto son: " + gruposListados, AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Contacto selecci�n", "Contacto selecci�n", "No se ha seleccionado ning�n contacto",
    				AlertType.WARNING);
    	}
    }

    /**
     * Da la lista de reuniones del contacto seleccionado
     * @param event
     */
    @FXML
    void darListadoReunionesContacto(ActionEvent event) {
    	if(contactoSeleccion != null) {
    		String reunionesListadas = aplicacion.darListadoReunionesContacto(contactoSeleccion);
    		mostrarMensaje("Notificaci�n contactos", "Notificaci�n contactos", "Las reuniones a las que pertence "
    				+ "este contactos son: " + reunionesListadas, AlertType.INFORMATION);
    	} else {
       		mostrarMensaje("Contacto selecci�n", "Contacto selecci�n", "No se ha seleccionado ning�n contacto",
    				AlertType.WARNING);
    	}
    }

    /**
     * Da el telefono de un contacto
     * @param event
     */
    @FXML
    void buscarContacto(ActionEvent event) {
    	String nombreContacto = txtBuscarContacto.getText();
    	String telefonoContacto = "";
    	if(nombreContacto == null || nombreContacto.equals("")) {
    	   	//notifica al usuario que la info es invalida
        	mostrarMensaje("Notificaci�n contacto", "Informaci�n del contacto invalida",
        			"Ingrese el nombre del contacto", AlertType.WARNING);
    	} else {
    		telefonoContacto = aplicacion.darTelefonoContacto(nombreContacto);
    		if(!telefonoContacto.equals("")) {
    			mostrarMensaje("Notificaci�n contacto", "El tel�fono del contacto es: ",
    					telefonoContacto, AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificaci�n contacto", "Informaci�n del contacto invalida",
            			"No se ha encontrado ning�n contacto con ese nombre", AlertType.WARNING);
    		}
    	}
    }

    /**
     * Cuantos espacios de contactos hay disponibles
     * @param event
     */
    @FXML
    void espaciosEnContactos(ActionEvent event) {
    	int espaciosDisponibles = aplicacion.espaciosDisponiblesContactos();
    	mostrarMensaje("Notificaci�n agenda", "Espacios de contactos", "Hay " + espaciosDisponibles +
    			" para contactos", AlertType.INFORMATION);
    }

    /**
     * lista todos los contactos de la agenda
     * @param event
     */
    @FXML
    void listarContactos(ActionEvent event) {
    	String contactosListados = aplicacion.listarContactos();
    	mostrarMensaje("Notificaci�n agenda", "Contactos en agenda: ", contactosListados, AlertType.INFORMATION);
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


    /**
     * Muestra los contactos de un grupo
     * @param event
     */
    @FXML
    void mostrarContactosGrupo(ActionEvent event) {
    	if(grupoSeleccion != null) {
    		String contactosGrupo = aplicacion.mostrarContactosGrupo(grupoSeleccion);
    		mostrarMensaje("Notificaci�n grupos", "Contactos en el grupo " + grupoSeleccion.getNombre() + ": ",
    				contactosGrupo, AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Grupo selecci�n", "Grupo selecci�n", "No se ha selccionado ning�n grupo", AlertType.WARNING);
    	}
    }

    /**
     * Me setea los txt de el grupo seleccionado
     */
	private void mostrarInformacionGrupo() {
		if(grupoSeleccion != null) {
			txtNombreGrupo.setText(grupoSeleccion.getNombre());
			comboBoxCategoriaGrupo.setValue(grupoSeleccion.getCategoria());
			//Deshabilito este textField
			txtNombreGrupo.setDisable(true);
		}
	}

	/**
	 * Setea los textfields de grupo para que el usuario sepa que meter
	 * @param event
	 */
    @FXML
    void nuevoGrupo(ActionEvent event) {
    	txtNombreGrupo.setText("Ingrese el nombre del grupo");
    	comboBoxCategoriaGrupo.setValue(Categoria.AMIGOS);
    	//Habilito este textfield
    	txtNombreGrupo.setDisable(false);
    }

    /**
     * Actualiza la categoria de un grupo
     * @param event
     */
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
    		mostrarMensaje("Notificai�n grupo", "Grupo actualizado", "El grupo " + nombreGrupo + " ha sido actualizado",
    				AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("Grupo selcci�n", "Grupo selecci�n", "No ha seleccionado ning�n grupo", AlertType.WARNING);
    	}
    }

    /**
     * Agrega un grupo siempre y cuando la info de este sea valida
     * @param event
     */
    @FXML
    void agregarGrupo(ActionEvent event) {
    	String nombreGrupo = txtNombreGrupo.getText();
    	Categoria categoriaGrupo = comboBoxCategoriaGrupo.getValue();
    	if(validarDatosGrupo(nombreGrupo, categoriaGrupo)) {
    		crearGrupo(nombreGrupo, categoriaGrupo);
    	}
    }

    /**
     * Valida la informaci�n de un grupo
     * @param nombreGrupo
     * @param categoriaGrupo
     * @return
     */
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
		mostrarMensaje("Notificaci�n grupo", "Notificaci�n grupo", notificacion, AlertType.WARNING);
		return false;
	}

	/**
	 * Crea el grupo despu�s de verificar que su info es valida
	 * @param nombreGrupo
	 * @param categoriaGrupo
	 */
	private void crearGrupo(String nombreGrupo, Categoria categoriaGrupo) {
		boolean fueCreado = aplicacion.crearGrupo(nombreGrupo, categoriaGrupo);
		if(fueCreado) {
			tableViewGrupos.getItems().clear(); //Limpio la lista
			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
			mostrarMensaje("Notificaci�n grupo", "Grupo registrado", "El grupo " + nombreGrupo +
					" ha sido registrado", AlertType.INFORMATION);
		} else {
			mostrarMensaje("Notificaci�n grupo", "Grupo no registrado", "El grupo " + nombreGrupo +
					" no fue registrado, ya que este nombre esta repetido", AlertType.WARNING);
		}
	}

	/**
	 * Elimina el grupo seleccionado
	 * @param event
	 */
	@FXML
    void eliminarGrupo(ActionEvent event) {
		if(grupoSeleccion != null) {
			if(aplicacion.eliminarGrupo(grupoSeleccion)) {
				mostrarMensaje("Grupo eliminado", "Grupo eliminado", "Se ha eliminado correctamente el grupo " +
						grupoSeleccion.getNombre(), AlertType.INFORMATION);
				listadoGrupos.remove(grupoSeleccion);
			}
		} else {
			mostrarMensaje("Grupo selecci�n", "Grupo selecci�n", "No se ha seleccionado ningun grupo", AlertType.WARNING);
		}
    }

	/**
	 * A�ade un contacto seleccionado a un grupo seleccionado
	 * @param event
	 */
    @FXML
    void aniadirContactoGrupo(ActionEvent event) {
    	if(contactoSeleccion != null && grupoSeleccion != null) {
    		if(aplicacion.aniadirContactoGrupo(contactoSeleccion, grupoSeleccion)) {
    			tableViewContactos.getItems().clear();
    			tableViewContactos.setItems(getContactos());
    			tableViewGrupos.getItems().clear(); //Limpio la lista
    			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
    			mostrarMensaje("Notificaci�n grupo", "Notificaci�n grupo", "Se ha a�adido el contacto " +
    					contactoSeleccion.getNombre() + " a el grupo " + grupoSeleccion.getNombre(), AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificaci�n grupo", "Notificaci�n grupo", "No se pudo a�adir el contacto."
    					+ " Esto se debe a que no hay espacios disponibles o el contacto ya existe en este grupo", AlertType.WARNING);
    		}
    	} else {
    		mostrarMensaje("Notificaci�n grupo", "Notificaci�n grupo", "Por favor verifique que se haya seleccionado"
    				+ " el contacto y el grupo deseado", AlertType.WARNING);
    	}
    }

    /**
     * Elimina un contacto seleccionado de un grupo seleccionado
     * @param event
     */
    @FXML
    void eliminarContactoGrupo(ActionEvent event) {
    	if(contactoSeleccion != null && grupoSeleccion != null) {
    		if(aplicacion.eliminarContactoGrupo(contactoSeleccion, grupoSeleccion)) {
    			tableViewContactos.getItems().clear();
    			tableViewContactos.setItems(getContactos());
    			tableViewGrupos.getItems().clear(); //Limpio la lista
    			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
    			mostrarMensaje("Notificaci�n grupo", "Notificaci�n grupo", "Se pudo eliminar el contacto " +
    					contactoSeleccion.getNombre() + " del grupo " + grupoSeleccion.getNombre(), AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificaci�n grupo", "Notificaci�n grupo", "No se pudo eliminar el contacto " +
    					" verifique que ese contacto si perteneciera al grupo indicado", AlertType.WARNING);
    		}
    	} else {
    		mostrarMensaje("Notificaci�n grupo", "Notificaci�n grupo", "Por favor verifique que se haya seleccionado"
    				+ " el contacto y el grupo deseado", AlertType.WARNING);
    	}
    }

//-----------------------------------------------REUNIONES-------------------------------------------------------------
   /**
    * Setea los textFields de a cuerdo a la reunion seleccionada
    */
    private void mostrarInfromacionReunion() {
	   if(reunionSeleccion != null) {
		   txtDescripcionReunion.setText(reunionSeleccion.getDescripcion());
		   txtFechaReunion.setText(reunionSeleccion.getFecha());
		   txtHoraReunion.setText(reunionSeleccion.getHora());
		   //Deshabilito la descripcion de la reunion
		   txtDescripcionReunion.setDisable(true);
	   }
   }

    /**
     * Setea los textFields con una pista de como el usuario debe ingresar los datos
     * @param event
     */
    @FXML
    void nuevaReunion(ActionEvent event) {
    	txtDescripcionReunion.setText("Ingrese una descripci�n para la reunion");
    	txtFechaReunion.setText("Ingrese la fecha en el formato: dd/mm/yyyy");
    	txtHoraReunion.setText("Ingrese la hora en el formato: HH:mm");
    	//Ahibilito los txt deshabilitados
    	txtDescripcionReunion.setDisable(false);
    }

    /**
     * Actualiza los datos de una reunion
     * @param event
     */
    @FXML
    void actualizarReunion(ActionEvent event) {
    	String descripcion = txtDescripcionReunion.getText();
    	String fecha = txtFechaReunion.getText();
    	String hora = txtHoraReunion.getText();
    	if(reunionSeleccion != null) {
    		if(datosValidosReunion(descripcion, fecha, hora)) {
    			aplicacion.actualizarReunion(reunionSeleccion, descripcion, fecha, hora);
    			//Actualizo los datos de la interfaz
    			reunionSeleccion.setDescripcion(descripcion);
    			reunionSeleccion.setFecha(fecha);
    			reunionSeleccion.setHora(hora);
    			//Actualizo los datos de la tabla
    			tableViewReuniones.getItems().clear();
    			tableViewReuniones.setItems(getReuniones());
    			mostrarMensaje("Notificaci�n reuni�n", "Reuni�n actualizada", "La reuni�n " + descripcion +
    					" ha sido actualizada", AlertType.INFORMATION);
    		}
    	} else {
    		mostrarMensaje("Reuni�n selcci�n", "Reuni�n selcci�n", "No se ha seleccionado ninguna reuni�n", AlertType.WARNING);
    	}
    }

    /**
     * Verifica que los datos ingresados esten correctos, asi como la fecha y la hora en un formato especifico
     * @param descripcion
     * @param fecha
     * @param hora
     * @return
     */
    private boolean datosValidosReunion(String descripcion, String fecha, String hora) {
    	String notificacion = "";
    	boolean fechaValida = aplicacion.validarFechaReunion(fecha);
    	boolean horaValida = aplicacion.validarHoraReunion(hora);
    	if(descripcion == null || descripcion.equals("")) {
    		notificacion += "La descripci�n de la reuni�n es invalida\n";
    	}
    	if(!fechaValida) {
    		notificacion += "La fecha de la reuni�n es invalida\n";
    	}
    	if(!horaValida) {
    		notificacion += "La hora de la reuni�n es invalida\n";
    	}
    	//Si no hay una notificaci�n es porque los datos son validos
    	if(notificacion.equals("")) {
    		return true;
    	}
    	//Notifico al ususario la info que es invalida
    	mostrarMensaje("Notificaci�n reuni�n", "Informaci�n de reuni�n invalida", notificacion, AlertType.WARNING);
    	return false;
	}

    /**
     * Agrega una nueva reunion a la agenda
     * @param event
     */
	@FXML
    void agregarReunion(ActionEvent event) {
	   	String descripcion = txtDescripcionReunion.getText();
    	String fecha = txtFechaReunion.getText();
    	String hora = txtHoraReunion.getText();

    	if(datosValidosReunion(descripcion, fecha, hora)) {
    		crearReunion(descripcion, fecha, hora);
    	}
    }

	/**
	 * Crear una reunion
	 * @param descripcion
	 * @param fecha
	 * @param hora
	 */
    private void crearReunion(String descripcion, String fecha, String hora) {
    	boolean fueCreada = aplicacion.crearReunion(descripcion, fecha, hora);
    	if(fueCreada) {
    		//A�ado el contenido al listado de reuniones
    		tableViewReuniones.getItems().clear();
    		tableViewReuniones.setItems(getReuniones());
    		mostrarMensaje("Notificai�n reuni�n", "Reuni�n registrada", "La reuni�n " + descripcion +
    				" fue creada correctamente", AlertType.INFORMATION);
    	} else {
    		mostrarMensaje("NOtificaci�n reuni�n", "Reuni�n no registrada", "La reuni�n " + descripcion +
    				" no se pudo registrar", AlertType.WARNING);
    	}
	}

    /**
     * Elimina una reunion seleccionada
     * @param event
     */
	@FXML
    void eliminarReunion(ActionEvent event) {
		if(reunionSeleccion != null) {
			if(aplicacion.eliminarReunion(reunionSeleccion)) {
				//Elimina la reuni�n del listado de reuniones
				listadoReuniones.remove(reunionSeleccion);
				mostrarMensaje("Reuni�n eliminada", "Reuni�n eliminada", "Se ha eliminado correctamente", AlertType.INFORMATION);
			} else {
				mostrarMensaje("Reuni�n no eliminada", "Fallo al eliminar reuni�n", "No se ha eliminado correctamente",
						AlertType.WARNING);
			}
		} else {
			mostrarMensaje("Reuni�n selecci�n", "Reuni�n selecci�n", "No se ha seleccionado ninguna reuni�n", AlertType.WARNING);
		}
    }

	/**
	 * Muestra los contactos de una reunion seleccionada
	 * @param event
	 */
    @FXML
    void mostratContactosReunion(ActionEvent event) {
    	if(reunionSeleccion != null) {
    		String contactosReunion = aplicacion.mostrarContactosReunion(reunionSeleccion);
    		mostrarMensaje("Notificaci�n reuni�n", "Contactos en reuni�n " + reunionSeleccion.getDescripcion() + ": ",
    				contactosReunion, AlertType.INFORMATION);
    	} else {
     		mostrarMensaje("Reuni�n selecci�n", "Reuni�n selecci�n", "No se ha seleccionado ninguna reuni�n", AlertType.WARNING);
    	}
    }

    /**
     * A�ade un contacot seleccionado a una reunion seleccionada
     * @param event
     */
    @FXML
    void aniadirContactoReunion(ActionEvent event) {
    	if(contactoSeleccion != null && reunionSeleccion != null) {
    		if(aplicacion.aniadirContactoReunion(contactoSeleccion, reunionSeleccion)) {
      			tableViewContactos.getItems().clear();
    			tableViewContactos.setItems(getContactos());
    			tableViewGrupos.getItems().clear(); //Limpio la lista
    			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
       			mostrarMensaje("Notificaci�n reuni�n", "Notificaci�n reuni�n", "Se ha a�adido el contacto " +
    					contactoSeleccion.getNombre() + " a la reuni�n " + reunionSeleccion.getDescripcion(), AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificaci�n reuni�n", "Notificaci�n reuni�n", "No se pudo a�adir el contacto."
    					+ " Esto se debe a que no hay espacios disponibles o el contacto ya existe en esta reuni�n", AlertType.WARNING);
    		}
    	} else {
    		mostrarMensaje("Notificaci�n reuni�n", "Notificaci�n reuni�n", "Por favor verifique que se haya "
    				+ "selccionado la reuni�n y el contacto deseado", AlertType.WARNING);
    	}
    }

    /**
     * Elimina un contacto seleccionado de una reunion seleccionada
     * @param event
     */
    @FXML
    void eliminarContactoReunion(ActionEvent event) {
    	if(contactoSeleccion != null && reunionSeleccion != null) {
    		if(aplicacion.eliminarContactoReunion(contactoSeleccion, reunionSeleccion)) {
       			tableViewContactos.getItems().clear();
    			tableViewContactos.setItems(getContactos());
    			tableViewGrupos.getItems().clear(); //Limpio la lista
    			tableViewGrupos.setItems(getGrupos()); //Agrego nuevos datos a la lista
    			mostrarMensaje("Notificaci�n reuni�n", "Notificaci�n reuni�n", "Se pudo eliminar el contacto " +
    					contactoSeleccion.getNombre() + " de la reuni�n " + reunionSeleccion.getDescripcion(), AlertType.INFORMATION);
    		} else {
    			mostrarMensaje("Notificaci�n reuni�n", "Notificaci�n reuni�n", "No se pudo eliminar el contacto " +
    					" verifique que ese contacto si perteneciera a la reuni�n indicada", AlertType.WARNING);
    		}
    	} else {
    		mostrarMensaje("Notificaci�n reuni�n", "Notificaci�n reuni�n", "Por favor verifique que se haya seleccionado"
    				+ " el contacto y la reuni�n deseada", AlertType.WARNING);
    	}
    }


}
