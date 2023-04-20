package ar.edu.unlam.pb2.callcenter;

public class Empresa {

	private String nombre;
	private Contacto[] contactos;
	private final int CANTIDAD_MAXIMA_CONTACTOS;
	private Integer[] zonasDeCoberturas;
	
	
	public Empresa(String nombreEmpresa) {
		this.nombre = nombreEmpresa;
		this.CANTIDAD_MAXIMA_CONTACTOS = 100;
		this.contactos = new Contacto[this.CANTIDAD_MAXIMA_CONTACTOS];
		this.zonasDeCoberturas = new Integer[10000];
	}
	
	/*En la zona de coberturaguardar cod postal de 1000 a 9999*/

	public void inicializarZonasDeCoberturaExistentes() {
		int codigoPostalInicio=1000;
		int codigoPostalFin = 10000;
		int contZona = 0;
		for(int i=codigoPostalInicio; i<codigoPostalFin; i++) {
			this.zonasDeCoberturas[contZona]= i;
			contZona++;
		}

	}
	
	public String getNombre() {
		return this.nombre;
	}

	public boolean agregarUnContacto(Contacto unContacto) {
		for(int i=0; i<contactos.length; i++) {
			if(contactos[i]== null &&  unContacto!=null) {
				this.contactos[i] = unContacto;
				return true;
			}
		}
		return false;
	}
	
	public Integer darLaCantidadDeContactosYaRegistrados() {
		Integer contadorContactos=0;
		
		for(int i=0; i<this.contactos.length; i++) {
			if(this.contactos[i]!= null)
				contadorContactos++;
		}
		
		return contadorContactos;
	}

	/* 1. El contacto NO debe ser cliente aún.
	 * 2. El contacto desea ser llamado o al menos no informó lo contrario.
	 * 3. El código postal del contacto debe existir en las zonas de cobertura existente.
	 * */
	public Contacto buscarCandidato() {
		Contacto[] candidatos = new Contacto[this.CANTIDAD_MAXIMA_CONTACTOS];
		int contadorCandidatos =0;
		
		int indiceCandidatoElegido =0;
		
		for(int i=0;i<this.contactos.length;i++) {
			if(this.contactos[i]!=null && this.contactos[i].isEsCliente() == false
					&& this.contactos[i].isDeseaSerLlamado() && elCodigoPostalExisteEnLasZonasDeCobertura(this.contactos[i].getCodigoPostal())) {
				candidatos[contadorCandidatos] = this.contactos[i];
				contadorCandidatos++;
			}	
		}
		
		indiceCandidatoElegido = (int)(Math.random()*contadorCandidatos);
		
		return candidatos[indiceCandidatoElegido];
	}

	public boolean elCodigoPostalExisteEnLasZonasDeCobertura(Integer codigoPostal) {
		this.inicializarZonasDeCoberturaExistentes(); //¿Esta bien?
		
		for(int i=0; i<this.zonasDeCoberturas.length; i++) {
			if(this.zonasDeCoberturas[i]!=null && this.zonasDeCoberturas[i].equals(codigoPostal))
				return true;
		}
		return false;
	}

	public void realizarLallamadaAlCandidato(Contacto candidato, Boolean laLlamadaFueExitosa, String observacionesDeLaLlamada, Boolean deseaSerLlamadoCandidato) {
		if(laLlamadaFueExitosa) {
			candidato.setEsCliente(true);
			candidato.setDeseaSerLlamado(false);
		}else {
			if(!deseaSerLlamadoCandidato)
				candidato.setDeseaSerLlamado(false);
		}
		
		Llamada llamada = new Llamada(laLlamadaFueExitosa,observacionesDeLaLlamada);
		
		candidato.registrarLaLLamada(llamada);
	}
	
	
	
	

}
