package examen;

public class ValidaLibro extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidaLibro() {
		super("Construcción de libro no válida");
	}

	public ValidaLibro(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValidaLibro(Throwable e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public ValidaLibro(String message, Throwable e) {
		super(message, e);
		// TODO Auto-generated constructor stub
	}

}
