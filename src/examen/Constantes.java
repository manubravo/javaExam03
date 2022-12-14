package examen;

public interface Constantes {
	
	int NOVELA = 1;
	int RELATOS = 2;
	int POESIA = 3;
	
	String DRIVER ="com.mysql.jdbc.Driver";
	
	String URL = "jdbc:mysql://localhost:3306/";
	
	String USER = "root";
	String PASSWORD = "";
	
	String LIBRO_TABLA = "CREATE TABLE `LIBROS` ( `IDENTIFICADOR` INT NOT NULL , `TITULO` VARCHAR(60) NOT NULL , `DESCRIPCION` VARCHAR(120) NOT NULL , `PRESTADO` BOOLEAN NOT NULL , `TIPOLIBRO` INT NOT NULL , PRIMARY KEY (`IDENTIFICADOR`)) ENGINE = InnoDB;";

	String LIBRO_INSERTAR = "INSERT INTO `LIBROS`(`IDENTIFICADOR`, `TITULO`, `DESCRIPCION`, `PRESTADO`, `TIPOLIBRO`) "
			+ "VALUES ( ? , ? , ? , ? , ? )";
	
	String LIBRO_ELIMINAR = "DELETE FROM `LIBROS` WHERE `IDENTIFICADOR`= ? ";
	
	String LIBRO_OBTENER_TODOS = "SELECT * FROM LIBROS";
	
	String LIBRO_OBTENER_NO_PRESTADOS = " SELECT * FROM LIBROS WHERE PRESTADO = 0";
	
	String LIBRO_OBTENER_SI_PRESTADOS = " SELECT * FROM LIBROS WHERE PRESTADO = 1";
	
}
