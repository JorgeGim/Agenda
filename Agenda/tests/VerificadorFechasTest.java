import static org.junit.Assert.*;

import org.junit.Test;

import verificador.VerificadorDeDatos;

public class VerificadorFechasTest {

	@Test
	public void happyPathTest() {
		
		assertTrue(VerificadorDeDatos.verificarFecha("12/04/2002"));
	}
	
	@Test
	public void mesErroneoTest() {
		assertFalse(VerificadorDeDatos.verificarFecha("01/13/2001"));
	}
	
	@Test
	public void añoErroneoTest() {
		assertFalse(VerificadorDeDatos.verificarFecha("01/02/96"));
	}
	
	@Test
	public void todoErroneoTest() {
		assertFalse(VerificadorDeDatos.verificarFecha("1/2/96"));
	}
	
	@Test
	public void diaErroneoTest() {
		assertFalse(VerificadorDeDatos.verificarFecha("32/02/2001"));
	}
}
