package examen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccesoBaseDatos {
	/*
	 * CRUD insertar modificar // no lo pide en el examen eliminar obtenertodos
	 * obtenerUno
	 */

	private Connection conexion;
	private Biblioteca activaBiblioteca;

	public AccesoBaseDatos() {
		this.activaBiblioteca = null;
	}

	public AccesoBaseDatos(Connection conexion) {

		this.conexion = conexion;
	}

	public void insertar(Libro a) {

		try (PreparedStatement ps = this.conexion.prepareStatement(Constantes.LIBRO_INSERTAR)) {

//			5 valores
			ps.setInt(1, a.getOrden());
			ps.setString(2, a.getTitulo());
			ps.setString(3, a.getDescripcion());
			ps.setBoolean(4, a.isPrestado());
			ps.setInt(5, a.getTipoLibro());

			ps.executeUpdate();

			System.out.println("Has insertado un libro en la BD");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Biblioteca activaBiblioteca() {

		if (activaBiblioteca == null) {

			this.activaBiblioteca = new Biblioteca();
		}
		return this.activaBiblioteca;
	}

	public void inyectarFicheroSerEnBD(String nombreFichero) {

		List<Libro> inyecta = this.activaBiblioteca.leerLibros(nombreFichero);
		
		System.out.println("Inyectando fichero de objetos en la base de datos..");
		
		for (Libro itera : inyecta) {

			this.insertar(itera);
		}
		System.out.println("Has inyectado un fichero de objetos en la BD.");
	}

	public void eliminar(Integer id) {

		try (PreparedStatement ps = this.conexion.prepareStatement(Constantes.LIBRO_ELIMINAR)) {

			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("Has eliminado un libro de bd.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Libro> obtenerNoPrestados() {

		List<Libro> listaNoPrestados = new ArrayList<Libro>();
		Libro aux = null;

		try (PreparedStatement ps = this.conexion.prepareStatement(Constantes.LIBRO_OBTENER_NO_PRESTADOS)) {

			try (ResultSet cursor = ps.executeQuery()) {

				while (cursor.next()) {

					aux = new Libro(cursor.getString(2), cursor.getString(3), cursor.getInt(5));

					listaNoPrestados.add(aux);
				}
			} catch (ValidaLibro e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaNoPrestados;
	}

	public Integer totalSiPrestados() {
		
		Integer numeroLibrosPrestados = null;
		
		List<Libro> listaNoPrestados = new ArrayList<Libro>();
		Libro aux = null;

		try (PreparedStatement ps = this.conexion.prepareStatement(Constantes.LIBRO_OBTENER_SI_PRESTADOS)) {

			try (ResultSet cursor = ps.executeQuery()) {

				while (cursor.next()) {

					aux = new Libro(cursor.getString(2), cursor.getString(3), cursor.getInt(5));

					listaNoPrestados.add(aux);
				}
			} catch (ValidaLibro e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		numeroLibrosPrestados = listaNoPrestados.size();
		
		return numeroLibrosPrestados;
	}

}
