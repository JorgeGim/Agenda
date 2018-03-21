package verificador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificadorDeDatos {
	
	public static boolean verificarEmail(String email) {
		
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
	
	public static boolean verificarFecha(String fecha) {
		
		String aux = "\\d{2}/\\d{2}/\\d{4}";
		
		Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
		
		
		
		Matcher matcher = pattern.matcher(fecha);
		
		return matcher.matches();
	}

}
