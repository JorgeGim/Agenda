import static org.junit.Assert.*;

import org.junit.Test;

import verificador.VerificadorDeDatos;

public class VerificadorDeDatosTest {

	@Test
	public void happyPathTest() {
		assertTrue(VerificadorDeDatos.verificarEmail("jorgegomez@live.com"));
	}
	
	@Test
	public void happyPath2() {
		assertTrue(VerificadorDeDatos.verificarEmail("jorgegomez@yahoo.com.ar"));
	}
	
	@Test
	public void dosArrobasTest() {
		assertFalse(VerificadorDeDatos.verificarEmail("jorgegomez@@live.com"));
	}
	
	@Test
	public void soloDominioTest() {
		assertFalse(VerificadorDeDatos.verificarEmail("@hotmail.com"));
	}
	
	@Test
	public void stringSoloTest() {
		assertFalse(VerificadorDeDatos.verificarEmail("jorge"));
	}
	
	@Test
	public void sinPuntoTest() {
		assertFalse(VerificadorDeDatos.verificarEmail("jorge@livecom"));
	}
	
	@Test
	public void dosPuntosTest() {
		assertFalse(VerificadorDeDatos.verificarEmail("jorgegomez@live..com"));
	}
	
	@Test
	public void puntoAlPrincipioTest() {
		assertFalse(VerificadorDeDatos.verificarEmail(".jorge@live.com"));
	}
}
