package es.gimbernat;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EntityManager {
	private Connection conn;
	private PreparedStatement psFind;
	private PreparedStatement psInsert;
	private PreparedStatement psUpdate;
	private PreparedStatement psDelete;

	public boolean init() {
		try {
			Properties p = loadPropertiesFile();
			if (p == null)
				return false;

			String strConn = (String) p.get("db.string_connection");
			System.out.println(strConn);
			conn = DriverManager.getConnection(strConn);

			psFind = conn.prepareStatement("select * from atracciones where nombre like ?");
			psInsert = conn.prepareStatement("insert into atracciones values(null, ?, ?, ?)");
			psUpdate = conn
					.prepareStatement("update atracciones set nombre = ?, tipo = ?, capacidad = ? " + "where id = ?");
			psDelete = conn.prepareStatement("delete from atracciones where id = ?");
			
			return true;
		} catch (SQLException e) {
			showError(e);
			unLoad();
			return false;
		}
	}

	private void showError(SQLException e) {
		System.out.println("Mensaje de error: " + e.getMessage());
		System.out.println("SQLState: " + e.getSQLState());
		System.out.println("VendorError: " + e.getErrorCode());
	}

	private void unLoad() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Properties loadPropertiesFile() {
		Properties p = new Properties();
		try {
			InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			p.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return p;
	}

	/**
	 * Retorna aquellos jobs que coinciden con los criterios de búsqueda
	 * 
	 * @param atraccion Contiene los criterios de búsqueda
	 * @return Retorna un array con todos las atracciones encontradas. Null, no hay
	 *         hay resultados. Siempre hay 20 casillas, cuando llegamos a la primera
	 *         casilla null, ya no hay más resultados.
	 */
	public List<Atraccion> find(Atraccion atraccion) {
		List<Atraccion> resultados = new ArrayList<Atraccion>();
		try {
			psFind.setString(1, atraccion.getNombre());
			ResultSet rs = psFind.executeQuery();
			while (rs.next()) {
				Atraccion a = new Atraccion(this, rs.getInt(1), rs.getString(2), Atraccion.Tipo.valueOf(rs.getString(3)),
						rs.getInt(4));
				resultados.add(a);
			}
			return resultados;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Inserta los datos de la atracción que se pasa como parámetro
	 * 
	 * @param atraccion Atracción a insertar
	 * @return True si la operación ha ido bien. False, en caso contrario.
	 */
	public boolean persist(Atraccion atraccion) {
		try {
			psInsert.setString(1, atraccion.getNombre());
			psInsert.setString(2, atraccion.getTipo().toString());
			psInsert.setInt(3, atraccion.getCapacidad());
			psInsert.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Actualiza los datos de la atrcción que se pasa como parámetro. Para
	 * simplificar, actualizaremos todo excepto lo que corresponde con la PK
	 * 
	 * @param atraccion Atracción a actualizar
	 * @return True si la operación ha ido bien. False, en caso contrario.
	 */
	public boolean merge(Atraccion atraccion) {
		try {
			psUpdate.setString(1, atraccion.getNombre());
			psUpdate.setString(2, atraccion.getTipo().toString());
			psUpdate.setInt(3, atraccion.getCapacidad());
			psUpdate.setInt(4, atraccion.getId());
			psUpdate.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Elimina la atracción que se pasa como parámetro. Para simplificar, se elimina a
	 * partir de la PK
	 * 
	 * @param atraccion Atracción a eliminar
	 * @return True si la operación ha ido bien. False, en caso contrario.
	 */
	public boolean Remove(Atraccion atraccion) {
		try {
			psDelete.setInt(1, atraccion.getId());
			psUpdate.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
