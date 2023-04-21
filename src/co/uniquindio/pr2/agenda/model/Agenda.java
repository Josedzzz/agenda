package co.uniquindio.pr2.agenda.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


import co.uniquindio.pr2.agenda.exceptions.ContactoException;

public class Agenda implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Grupo[] listaGrupos;
	private Reunion[] listaReuniones;


	public Agenda(String nombre, int numeroContactos,int numeroGrupos,int numeroReuniones) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[numeroContactos];
		this.listaGrupos = new Grupo[numeroGrupos];
		this.listaReuniones = new Reunion[numeroReuniones];

		//Inicializo valores para probarlos en la tabla
		Contacto contacto1 = new Contacto("Jose", "Balin", "Cr 10", "000000999", "joseda@algo.com", 10, 10);
		Contacto contacto2 = new Contacto("Nico", "Perro", "Cr 15", "999000000", "nicoda@algo.com", 10, 10);
		listaContactos[0] = contacto1;
		listaContactos[1] = contacto2;

		//Inicalizo valores de grupos para probarlos en la tabla
		Grupo grupo1 = new Grupo("Amigos", 10, Categoria.AMIGOS);
		Grupo grupo2 = new Grupo("Work", 10, Categoria.OFICINA);
		listaGrupos[0] = grupo1;
		listaGrupos[1] = grupo2;
	}


	public Agenda() {
		super();
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Contacto[] getListaContactos() {
		return listaContactos;
	}


	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}


	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}


	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}


	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}


	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}


	@Override
	public String toString() {
		return "Agenda [nombre=" + nombre + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
//----------------------------------------------------------------------------------------------------------------------
	/**
	 *
	 * @param nombre
	 * @param telefono
	 * @return
	 */
	public Contacto obtenerContacto(String nombre, String telefono) {
		Contacto contactoEncontrado = null;
		for(Contacto contacto : listaContactos) {
			//Tengo que ponerle el que sea diferente a null porque o sino causo un nullPointerException
			if(contacto != null && contacto.getNombre().equals(nombre) && contacto.getTelefono().equals(telefono)) {
				contactoEncontrado = contacto;
			}
		}
		return contactoEncontrado;
	}

	public void actualizarContacto(String nombre, String alias, String direccion, String telefono, String email) {
		Contacto contactoEncontrado = obtenerContacto(nombre, telefono);
		contactoEncontrado.setAlias(alias);
		contactoEncontrado.setDireccion(direccion);
		contactoEncontrado.setEmail(email);
	}

	/**
	 * Añade un contacto siempre y cuanto este no exista y la agenda tenga espacio
	 * @param newContacto
	 * @throws ContactoException
	 */
	/*public void aniadirContacto(Contacto newContacto) throws ContactoException {
		Contacto contacto = buscarContacto(newContacto);
		int posDisponible = obtenerPosicionLibre();

		if(contacto != null){
			throw new ContactoException("El contacto ya existe");
		}
		if(posDisponible == -1){
			throw new ContactoException("Agenda llena");
		}

		listaContactos[posDisponible] = newContacto;
	}*/

	public boolean aniadirContacto(String nombre, String alias, String direccion, String telefono, String email) {
		Contacto contacto = obtenerContacto(nombre, telefono);
		int posDisponible = obtenerPosicionLibre();

		if(contacto == null && posDisponible != -1) {
			contacto = new Contacto(nombre, alias, direccion, telefono, email, 10, 10);
			listaContactos[posDisponible] = contacto;
			return true;
		}
		return false;
	}

	/**
	 * Me dice cual es la posición libre de la lista de contactos, si no existe me retorna -1
	 * @return
	 */
	private int obtenerPosicionLibre() {
	    for (int i = 0; i < listaContactos.length; i++) {
	        if(listaContactos[i] == null) {
	            return i;
	        }
	    }
	    return -1;
	}

	/**
	 * Dado un contacto retorna ese contacto si tiene su nombre y telefono igual, si no existe retorna null
	 * @param newContacto
	 * @return
	 */
	/*private Contacto buscarContacto(Contacto newContacto) {
		List<Contacto> asList = Arrays.asList(listaContactos);
		Optional<Contacto> findFirst = asList.stream().filter(c -> c.equals(newContacto)).findFirst();
		return findFirst.get();
	}*/

	/**
	 * Dado un contacto retorna true si existe un contacto en la lista de contactos con un nombre y telefono igual
	 * @param newContacto
	 * @return
	 */
	public boolean existeContacto(Contacto newContacto) {
		List<Contacto> asList = Arrays.asList(listaContactos);
		return asList.stream().filter(c -> c.equals(newContacto)).findFirst().isPresent();
	}

	public String darListadoGruposContacto(Contacto contactoSeleccion) {
		String gruposListados = "";
		for(Grupo grupo : contactoSeleccion.getListaGrupos()) {
			if(grupo != null) {
				gruposListados = gruposListados + grupo.getNombre() + "\n";
			}
		}
		return gruposListados;
	}

	/**
	 * Me devuelve el String de la lista de contactos
	 * @return
	 */
	public String listarContactos() {
		String contactos = "";
		for(Contacto contacto : listaContactos) {
			if(contacto != null) {
				contactos = contactos + contacto.getNombre() + " : " + contacto.getTelefono() + "\n";
			}
		}
		return contactos;
	}

	/**
	 * Retorna el telefono dado el nombre de un contacto
	 * @param nombre
	 * @return
	 */
	public String darTelefonoContacto(String nombre) {
		String telefono = "";
		for(Contacto contacto : listaContactos) {
			if(contacto != null && contacto.getNombre().equalsIgnoreCase(nombre)) {
				telefono = contacto.getTelefono();
			}
		}
		return telefono;
	}

	/**
	 * Dado un contacto como parametro
	 * @param contactoEliminar
	 */
	public boolean eliminarContacto(Contacto contactoEliminar) {
		boolean fueEliminado = false;
	    for(int i = 0; i < listaContactos.length; i++) {
	        Contacto contacto = listaContactos[i];
	        if(contacto != null && contacto.equals(contactoEliminar)) {
	            listaContactos[i] = null;
	            fueEliminado = true;
	            break;
	        }
	    }
	    return fueEliminado;
	}


	/**
	 * Me dice si la lista de contactos esta llena o no
	 * @return
	 */
	public boolean estaLlenaAgendaContactos() {
	    for(Contacto contacto : listaContactos) {
	        if(contacto == null) {
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * Retorna cuantos espacios disponibles hay en la lista de contactos
	 * @return
	 */
	public int espaciosDisponiblesContactos() {
	    int contador = 0;
	    for (Contacto contacto : listaContactos) {
	        if (contacto == null) {
	            contador++;
	        }
	    }
	    return contador;
	}

//------------------------ GRUPOS ---------------------------------

	public Grupo obtenerGrupo(String nombre) {
		Grupo grupoEncontrado = null;
		for(Grupo grupo : listaGrupos) {
			//Tengo que ponerle el que sea diferente a null porque o sino causo un nullPointerException
			if(grupo != null && grupo.getNombre().equals(nombre)) {
				grupoEncontrado = grupo;
			}
		}
		return grupoEncontrado;
	}

	public String mostrarContactosGrupo(Grupo grupo) {
		String contactosGrupo = "";
		for(Contacto contacto : grupo.getListaContactos()) {
			if(contacto != null) {
				contactosGrupo = contactosGrupo + contacto.getNombre() + " : " + contacto.getTelefono() + "\n";
			}
		}
		return contactosGrupo;
	}


	public void actualizarGrupo(String nombreGrupo, Categoria categoriaGrupo) {
		Grupo grupoEncontrado = obtenerGrupo(nombreGrupo);
		grupoEncontrado.setCategoria(categoriaGrupo);
	}


	public boolean aniadirGrupo(String nombreGrupo, Categoria categoriaGrupo) {
		Grupo grupo = obtenerGrupo(nombreGrupo);
		int posDisponibleGrupo = obtenerPosicionLibreGrupo();

		if(grupo == null && posDisponibleGrupo != -1) {
			grupo = new Grupo(nombreGrupo, 10, categoriaGrupo);
			listaGrupos[posDisponibleGrupo] = grupo;
			return true;
		}
		return false;
	}


	private int obtenerPosicionLibreGrupo() {
	    for (int i = 0; i < listaGrupos.length; i++) {
	        if(listaGrupos[i] == null) {
	            return i;
	        }
	    }
	    return -1;
	}


	public boolean eliminarGrupo(Grupo grupoEliminar) {
		boolean fueEliminado = false;
	    for(int i = 0; i < listaGrupos.length; i++) {
	        Grupo grupo = listaGrupos[i];
	        if(grupo != null && grupo.equals(grupoEliminar)) {
	            listaGrupos[i] = null;
	            fueEliminado = true;
	            break;
	        }
	    }
	    return fueEliminado;
	}


	public boolean aniadirContactoGrupo(Contacto contactoSeleccion, Grupo grupoSeleccion) {
		boolean fueAgregado = false;

		Contacto[] listaContactosAux = grupoSeleccion.getListaContactos();
		int posDisponibleContacto = obtenerPosicionLibreContactoGrupo(listaContactosAux);

		Grupo[] listaGruposAux = contactoSeleccion.getListaGrupos();
		int posDiosponibleGrupo = obtenerPosicionLibreGrupoContacto(listaGruposAux);

		if(!existeContactoGrupo(contactoSeleccion, listaContactosAux) && posDisponibleContacto != -1) {
			if(!existeGrupoContacto(grupoSeleccion, listaGruposAux) && posDiosponibleGrupo != -1) {
				listaContactosAux[posDisponibleContacto] = contactoSeleccion;
				grupoSeleccion.setListaContactos(listaContactosAux);
				listaGruposAux[posDiosponibleGrupo] = grupoSeleccion;
				contactoSeleccion.setListaGrupos(listaGruposAux);
				fueAgregado = true;
			}
		}
		return fueAgregado;
	}

	private int obtenerPosicionLibreContactoGrupo(Contacto[] listaContactosAux) {
	    for (int i = 0; i < listaContactosAux.length; i++) {
	        if(listaContactosAux[i] == null) {
	            return i;
	        }
	    }
	    return -1;
	}

	private int obtenerPosicionLibreGrupoContacto(Grupo[] listaGruposAux) {
		for (int i = 0; i < listaGruposAux.length; i++) {
			if(listaGruposAux[i] == null) {
				return i;
			}
		}
		return -1;
	}

	private boolean existeContactoGrupo(Contacto contactoSeleccion, Contacto[] listaContactosAux) {
		boolean existe = false;
		for(Contacto contacto : listaContactosAux) {
			if(contacto != null && contacto.equals(contactoSeleccion)) {
				existe = true;
				break;
			}
		}
		return existe;
	}

	private boolean existeGrupoContacto(Grupo grupoSeleccion, Grupo[] listaGruposAux) {
		boolean existe = false;
		for(Grupo grupo : listaGruposAux) {
			if(grupo != null && grupo.equals(grupoSeleccion)) {
				existe = true;
				break;
			}
		}
		return existe;
	}


	public boolean eliminarContactoGrupo(Contacto contactoSeleccion, Grupo grupoSeleccion) {
		boolean fueEliminado = false;
		//Tengo que eliminar a cada uno de su respectiva lista
		Contacto[] listaContactosAux = grupoSeleccion.getListaContactos();
		Grupo[] listaGruposAux = contactoSeleccion.getListaGrupos();

	    for(int i = 0; i < listaContactosAux.length; i++) {
	        Contacto contacto = listaContactosAux[i];
	        if(contacto != null && contacto.equals(contactoSeleccion)) {
	            listaContactosAux[i] = null;
	            grupoSeleccion.setListaContactos(listaContactosAux);
	            fueEliminado = true;
	            break;
	        }
	    }

	    for(int i = 0; i < listaGruposAux.length; i++) {
	        Grupo grupo = listaGruposAux[i];
	        if(grupo != null && grupo.equals(grupoSeleccion)) {
	            listaGruposAux[i] = null;
	            contactoSeleccion.setListaGrupos(listaGruposAux);
	            fueEliminado = true;
	            break;
	        }
	    }
		return fueEliminado;
	}





}
