package persistencia.dao.interfaz;

public interface ConexionesDAO {
	public boolean nuevaConexion(String ip, String puerto, String usuario, String contraseña);

}
