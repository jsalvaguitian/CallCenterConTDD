package ar.edu.unlam.pb2.callcenter;

import java.util.Arrays;

public class Contacto {
	private String nombreYApellido;
	private String numeroCelular;
	private String mail;
	private String direccion;
	private Integer codigoPostal;
	private String localidad;
	private Provincia provincia;
	
	private boolean esCliente;
	private boolean deseaSerLlamado;
	
	private Llamada[] llamadas;
	private final Integer CANT_MAX_LLAMADAS  = 100;

//**************************************
	public Contacto() {
		this.esCliente = false;
		this.deseaSerLlamado = true;
		this.llamadas = new Llamada[this.CANT_MAX_LLAMADAS];
	}
	
//**************************************
	public Contacto(String nombreYApellido, String celular, String mail, String direccion, Integer codigoPostal,
			String localidad, Provincia provincia) {
		
		this.nombreYApellido = nombreYApellido;
		this.numeroCelular = celular;
		this.mail = mail;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.localidad = localidad;
		this.provincia = provincia;
		
		this.esCliente = false;
		this.deseaSerLlamado = true;	
		
		this.llamadas = new Llamada[this.CANT_MAX_LLAMADAS];
	}
	
//**************************************
	//Un constructor que tira EXCEPCION
	public Contacto(String nombreYApellido, String celular, String mail, String direccion)throws DatosIncorrectos {
		
		this.nombreYApellido = nombreYApellido;
		this.numeroCelular = celular;
		this.mail = mail;
		if(!this.esMailValido(mail))
			throw new DatosIncorrectos();
		this.direccion = direccion;
		
		this.esCliente = false;
		this.deseaSerLlamado = true;	
		this.llamadas = new Llamada[this.CANT_MAX_LLAMADAS];
		
	}
//**************************************	
	public static String crearSuNumeroCelular(String codigoPais, String codigoArea, String nroCelular) {
		String celular = "";
		Integer codPais = Integer.parseInt(codigoPais);	
		Integer codArea = Integer.parseInt(codigoArea);
		Integer numCelular = Integer.parseInt(nroCelular);
		
		celular = "+"+codPais+codArea+numCelular;
		
		return celular;
	}

	
	public String getNumeroCelular() {
		
		return this.numeroCelular;
	}


	public void setMail(String mail) {
		if(this.esMailValido(mail))
			this.mail = mail;	
	}

	public String getMail() {
		return this.mail;
	}


	public boolean esMailValido(String mail) {
		boolean tienePunto = false;
		int contadorDeArroba = 0;
		
		if(mail.contains(".")) 
			tienePunto = true;
		
		for(int i=0; i< mail.length(); i++){
			if(mail.charAt(i) == '@')
				contadorDeArroba++;
		}
		
		if(tienePunto && contadorDeArroba == 1)
			return true;

		return false;
	}


	public boolean isEsCliente() {
		return esCliente;
	}


	public boolean isDeseaSerLlamado() {
		return deseaSerLlamado;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	@Override
	public String toString() {
		return
				"Nombre y apellido: "+this.nombreYApellido +
				"\n Celular: "+ this.numeroCelular +
				"\n Mail: "+ this.mail +
				"\n Direccion: " + this.direccion +
				"\n Codigo Postal: " +this.codigoPostal+
				"\n Localidad: "+ this.localidad +
				"\n Provincia: " + this.provincia +
				"\n Llamadas: " + Arrays.toString(llamadas);
	}

	public String getDireccion() {
		return this.direccion;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void registrarLaLLamada(Llamada llamada) {
		for(int i=0; i<this.llamadas.length; i++) {
			if(this.llamadas[i]==null && llamada!=null) {
				this.llamadas[i]=llamada;
				break;
			}	
		}
		
	}

	public Integer darLaCantidadTotalDeLlamadas() {
		Integer contadorDeLlamadas =0;
		
		for(int i=0; i<this.llamadas.length; i++) {
			if(this.llamadas[i]!=null)
				contadorDeLlamadas++;
		}
		return contadorDeLlamadas;	
	}
	
	
	public Llamada obtenerUnaLlamadaDeterminada(int indiceLlamada) {	
		return this.llamadas[indiceLlamada];
	}
	
	public void setEsCliente(boolean esCliente) {
		this.esCliente = esCliente;
	}

	public void setDeseaSerLlamado(boolean deseaSerLlamado) {
		this.deseaSerLlamado = deseaSerLlamado;
	}

	public Llamada[] getLlamadas() {
		return this.llamadas;
		
	}
}
