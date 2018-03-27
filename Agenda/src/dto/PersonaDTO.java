package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonaDTO implements Comparable<PersonaDTO>
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String calle;
	private String altura;
	private String piso;
	private String depto;
	private int localidad;
	private String email;
	private String fechaCumpleaños;
	private int tipoContacto;
	private SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
	private char caracter='@';
	private int añoNacimiento;
	
	public PersonaDTO(int idPersona, String nombre, String telefono)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public PersonaDTO(int idPersona, String nombre, String telefono,String calle,String altura,String piso, String depto, int localidad,String Email,String fechaDeCumpleaños,int tipoDeContacto)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle=calle;
		this.altura=altura;
		this.piso=piso;
		this.depto=depto;
		this.localidad=localidad;
		this.email=Email;
		this.fechaCumpleaños=fechaDeCumpleaños;
		this.tipoContacto=tipoDeContacto;
		if(!fechaDeCumpleaños.isEmpty())
		this.añoNacimiento=Integer.parseInt(fechaDeCumpleaños.substring(fechaDeCumpleaños.length()-4, fechaDeCumpleaños.length()));
	}
	
	public int getAñoNacimiento() {
		return añoNacimiento;
	}

	public void setAñoNacimiento(int añoNacimiento) {
		this.añoNacimiento = añoNacimiento;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return this.piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return this.depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public int getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(int localidad) {
		this.localidad = localidad;
	}

	public String getEmail() {
		return this.email;
	}			

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaCumpleaños() {
		return this.fechaCumpleaños;
	}

	public void setFechaCumpleaños(String fechaCumpleaños) {
		this.fechaCumpleaños = fechaCumpleaños;
	}

	public int getTipoContacto() {
		return this.tipoContacto;
	}

	public void setTipoContacto(int tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	@Override
	public int compareTo(PersonaDTO p) {

		if(this.añoNacimiento<p.getAñoNacimiento())
			return -1;
		if(this.añoNacimiento>p.getAñoNacimiento())
			return 1;
		//Agarra los mails y se queda con lo posterior al @
		String extension1 = this.email.substring(this.email.indexOf(caracter)+1,this.email.length());
		String extension2 = p.getEmail().substring(p.getEmail().indexOf(caracter)+1, p.getEmail().length());
		// retorna la extension de los mail en orden alfabetico
		return extension1.compareTo(extension2);
	}

}
