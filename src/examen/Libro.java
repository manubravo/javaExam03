package examen;

import java.io.Serializable;

public class Libro implements Ordenable, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descripcion;
	private boolean prestado;
	
	public Integer tipoLibro;
	
	public static int contador;
	private Integer identificador; // empezará en 100 desde constructor
	
	public Libro() {
		
	}

	public Libro(String titulo, String descripcion, Integer tipoLibro) throws ValidaLibro {
		this.setTitulo(titulo);
		this.setDescripcion(descripcion);
		this.setPrestado(prestado); // Por defecto es false.
		this.setTipoLibro(tipoLibro);;
		this.identificador = 100 + contador++;
	}

	public Libro(String titulo, String descripcion) throws ValidaLibro{
		this(titulo, descripcion, Constantes.POESIA);
	}

	private void setTitulo(String titulo) throws ValidaLibro{
		
		if (titulo == null) {
			throw new ValidaLibro("Título no puede ser nulo");
		}
		this.titulo = titulo;
	}

	private void setDescripcion(String descripcion) throws ValidaLibro {
		if (descripcion == null) {
			throw new ValidaLibro("Descripción no puede ser nulo");
		}
		this.descripcion = descripcion;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	private void setTipoLibro(Integer tipoLibro) throws ValidaLibro {
		
		if (tipoLibro == null) {
			throw new ValidaLibro("El tipo no puede ser nulo");
		} else {
			switch (tipoLibro) {
			case Constantes.NOVELA:
			case Constantes.RELATOS:
			case Constantes.POESIA:
				
				this.tipoLibro = tipoLibro;
				break;

			default:
				throw new ValidaLibro("Debes elegir entre 1,2,3");
			}
		}
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public Integer getTipoLibro() {
		return tipoLibro;
	}

	public static int getContador() {
		return contador;
	}

	@Override
	public Integer getOrden() {
		
		return this.identificador;
	}
	
	@Override
	public int compareTo(Libro parametro) { // ORDEN NATURAL
		
		if (this.getOrden().compareTo(parametro.getOrden()) == 0) {
			return -1;
		
		} else if (this.getOrden().compareTo(parametro.getOrden()) == 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public String toString() {
		return getTitulo() + ", " + getDescripcion() + ", "
				+ isPrestado() + ", " + getTipoLibro() + ", " + getOrden();
	}
	
//	public boolean equals(Object o) {
//		
//		boolean iguala = false;
//		
//		if (o instanceof Libro) {
//			Libro libroIgual;
//			o = (Libro)libroIgual;
//			if (iguala) {
//				
//			}
//		}
//		return iguala;
//	}
	
}
