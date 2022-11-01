package examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	
	private Connection conexion;
	
	public Conexion() {
		
	}
	
	public Conexion(String nombreBD, String user, String password) {
		
		try {
			
			Class.forName(Constantes.DRIVER);
			this.conexion = DriverManager.getConnection(Constantes.URL, user, password);
			
			System.out.println("Has establecido una conexión en MySQL.");
					
			crearBD(nombreBD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void crearBD(String nombreBD) {
		
		String create = "CREATE DATABASE " + nombreBD + ";";
		String use = "USE " + nombreBD + ";";
		
		eliminarBD(nombreBD); // necesario para evitar conflictos de creación
		
		try (PreparedStatement psCreate = this.conexion.prepareStatement(create);
				PreparedStatement psUse = this.conexion.prepareStatement(use)){
			
			psCreate.executeUpdate();
			psUse.executeUpdate();
			
			crearEntidad();
			
			System.out.println("Has creado una base de datos " + nombreBD);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void crearEntidad() {
		
		System.out.println("Creando las entidades necesarias..");
		
		try (PreparedStatement psCrea = this.conexion.prepareStatement(Constantes.LIBRO_TABLA)){
			
			psCrea.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void eliminarBD(String nombreBD) {
		
		String drop = "DROP DATABASE IF EXISTS "+ nombreBD +";";
		
		try (PreparedStatement psDrop = this.conexion.prepareStatement(drop)){
			
			psDrop.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection conectar() {
		
		if (this.conexion == null) {
			new Conexion();
		}
		return this.conexion;
	}
	
	public void desconectar() throws SQLException {
		
		if (this.conexion != null) {
			
			this.conexion.close();
		}
	}
}