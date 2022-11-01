package examen;

import java.util.List;

public class TestFiltrosListas {

	public static void main(String[] args) throws ValidaLibro {
		
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
		System.out.println("-------------");
		
		b.buscaLibro(101);

		System.out.println("-------------");
		b.estaPrestado(10);
		
		System.out.println("-------------");
		List<Libro> listaPrestados = b.librosPrestados();
		for (Libro itera : listaPrestados) {
			System.out.println(itera);
		}
		
		System.out.println("-------------");
		b.creaXML("libreria");
	}
}