package examen;

import java.util.List;

public class TestConexion {

	public static void main(String[] args) {
		
		Conexion conexion = null;
		
		try {
			
			conexion = new Conexion("bdd_java_biblioteca_extraordinaria", Constantes.USER, Constantes.PASSWORD);
			
			conexion.conectar();
			
			Biblioteca b = new Biblioteca();
			
			b.aniadirLibro(new Libro("DFG", "blablablabla", 2));
			b.aniadirLibro(new Libro("XFG", "blablablabla", 1));
			b.aniadirLibro(new Libro("TFG", "blablablabla", 1));
			b.aniadirLibro(new Libro("ZFG", "blablablabla"));
			b.aniadirLibro(new Libro("ABG", "blablablabla"));
			b.aniadirLibro(new Libro("BFG", "blablablabla", 2));
			b.aniadirLibro(new Libro("CFG", "blablablabla", 2));
			
			Libro libroPrestado = new Libro("AAA", "bsjaglñwa"); // prueba para testear dao
			libroPrestado.setPrestado(true);
			
			Libro libroPrestado2 = new Libro("BBB", "bsjaglñwa"); // prueba para testear dao
			libroPrestado2.setPrestado(true);
			
			Libro libroPrestado3 = new Libro("CCC", "bsjaglñwa"); // prueba para testear dao
			libroPrestado3.setPrestado(true);
			
			
			b.aniadirLibro(libroPrestado);
			b.aniadirLibro(libroPrestado2);
			b.aniadirLibro(libroPrestado3);
			
			List<Libro> listaVolatil = b.obtenerListaLibros(); 
			
			for (Libro itera : listaVolatil) {
				System.out.println(itera);
			}
			
			b.guardarLibrosTxt("libros.txt");
			
			b.guardarLibros("biblioteca.ser");
			
			Libro unLibro = new Libro("Test", "A�adiendo libro");
			
			b.guardarUnLibro("biblioteca.ser", unLibro);
			
			List<Libro> listaSer = b.leerLibros("biblioteca.ser");
			
			for (Libro itera : listaSer) {
				
				System.out.println(itera);
			}
			
			AccesoBaseDatos dao = new AccesoBaseDatos(conexion.conectar());
			
//			dao.insertar(libroPrueba);
//			dao.eliminar(107);
			
			Integer totalSi = dao.totalSiPrestados(); // FUNCIONA, TENGO SOLO 3 PRESTADOs
			System.out.println(totalSi);

			List<Libro> noPrestado = dao.obtenerNoPrestados();
			for (Libro itera : noPrestado) {
				System.out.println(itera);
			}
			System.out.println();
			
			b.ordenarLibrosDescendente(); // FUNCIONA
			for (Libro itera : listaVolatil) {
				System.out.println(itera);
			};

//			dao.inyectarFicheroSerEnBD("biblioteca.ser");
			
			List<Libro> inyectaTest = b.leerLibros("biblioteca.ser");
			for (Libro itera : inyectaTest) {
				
				dao.insertar(itera);
			}
			
//			List<Libro> lecturaTesteo = dao.activaBiblioteca().leerLibros("biblioteca.ser");
//			for (Libro itera : lecturaTesteo) {
//				System.out.println(itera);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}