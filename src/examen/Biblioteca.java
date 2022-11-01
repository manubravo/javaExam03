package examen;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Biblioteca {

	private List<Libro> listaLibros;

	private String nombre;
	private String localidad;

	public Biblioteca() {

		this.listaLibros = new ArrayList<Libro>();
		this.nombre = null;
		this.localidad = null;
	}

	public Biblioteca(String nombre, String localidad) {
		this.listaLibros = new ArrayList<Libro>();
		this.nombre = nombre;
		this.localidad = localidad;
	}

	public List<Libro> obtenerListaLibros() {
		return listaLibros;
	}

	public void aniadirLibro(Libro unLibro) {
		this.listaLibros.add(unLibro);
	}

	public void ordenarLibrosDescendente() {
		Collections.sort(this.listaLibros, new ComparaTituloDescendentemente());
	}

	public void ordenarLibrosAscendente() {
		Collections.sort(this.listaLibros); // ORDEN NATURAL
	}

	public void buscaLibro(Integer id) {

		boolean encontrado = false;

		for (Libro itera : listaLibros) {

			if (itera.getOrden().compareTo(id) == 0) {

				encontrado = true;
				System.out.println("ID: " + id + "--> Libro encontrado: " + itera);
				break;
			}
		}
		if (!encontrado) {
			System.out.println("ID: " + id + "--> No existe ese libro.");
		}
	}

	public void estaPrestado(Integer id) {

		boolean encontrado = false;

		if (encontrado) {
			for (Libro itera : listaLibros) {

				buscaLibro(id);
				System.out.println("Estado: " + itera.isPrestado());
				break;
			}
		} else if (!encontrado) {
			System.out.println("No existe un libro con ID " + id + ".");
		}
	}

	public List<Libro> librosPrestados() {

		List<Libro> listaPrestados = new ArrayList<Libro>();

		for (Libro itera : this.listaLibros) {

			if (itera.isPrestado() == true) { // si están prestados entonces

				listaPrestados.add(itera);
			}
		}
		Collections.sort(listaPrestados, new ComparaTituloDescendentemente());
		return listaPrestados;
	}

//	public List<Integer> cuentaLibros(Integer tipoLibro) {
//
//		List<Integer> listaTipoLibro = new ArrayList<Integer>();
//
//		for (Libro itera : this.listaLibros) {
//
//			if (tipoLibro == Constantes.NOVELA) {
//				
//				if (itera.getTipoLibro().compareTo(tipoLibro) == 0) {
//					
//					this.listaLibros.add(itera);
//				}
//				
//			} else if (tipoLibro == Constantes.RELATOS) {
//
//			} else if (tipoLibro == Constantes.POESIA) {
//
//			}
//		}
//
//	}

	public void guardarLibros(String nombreFichero) {

		System.out.println("Creando fichero serializado...");

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {

			for (Libro itera : listaLibros) {

				oos.writeObject(itera);
			}
			System.out.println("¡Has creado un fichero serializado!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void guardarUnLibro(String nombreFichero, Libro unLibro) {

		// Uso MyOOS para evitar problemas de cabecera
		try (MyOOS oos = new MyOOS(new FileOutputStream(nombreFichero, true))) {

			oos.writeObject(unLibro);

		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void guardarLibrosTxt(String nombreFichero) {

		System.out.println("Creando fichero txt...");

		try (PrintWriter pw = new PrintWriter(new File(nombreFichero))) {

			for (Libro itera : listaLibros) {

				pw.print(itera + "\n");
			}
			System.out.println("¡Has creado un fichero txt!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Libro> leerLibros(String nombreFichero) {

		System.out.println("Lectura de fichero serializado:");

		List<Libro> lista = new ArrayList<Libro>();

		Libro aux = null;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {

			while (true) {

				aux = (Libro) ois.readObject();
				lista.add(aux);
			}
		} catch (EOFException e) {
//			No ponemos nada
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void creaXML(String nombreFichero) {

		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder construye = fabrica.newDocumentBuilder();
			
			DOMImplementation implementa = construye.getDOMImplementation();
			
			Document documenta = implementa.createDocument(null, nombreFichero, null);
			
			documenta.setXmlVersion("1.0");

			// NODO RAÍZ
			Element nodoRaiz = documenta.getDocumentElement();
			for (Libro itera : this.listaLibros) {
				
				Element nodoObjeto = documenta.createElement("libro");

				Element nodoPropiedad = documenta.createElement("toString");
				Text valorNodo = documenta.createTextNode(itera.toString());
				
				nodoPropiedad.appendChild(valorNodo);
				
				nodoObjeto.appendChild(nodoPropiedad);
				
				nodoRaiz.appendChild(nodoObjeto);
			}
//			GENERA XML
			Source fuenteOrigen = new DOMSource(documenta);
			
//			DONDE SE GUARDARA
			Result fuenteDestino = new StreamResult(nombreFichero + ".xml");
			
			Transformer tranforma = TransformerFactory.newInstance().newTransformer();
			
			tranforma.transform(fuenteOrigen, fuenteDestino);
			
			System.out.println("\n¡Has creado un documento XML!");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

}
