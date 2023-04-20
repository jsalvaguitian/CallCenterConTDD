package ar.edu.unlam.pb2.callcenter;

public class Llamada {
	private Boolean fueExitosa;
	private String observaciones;

	public Llamada(Boolean fueExitosa, String observaciones) {
		this.fueExitosa = fueExitosa;
		this.observaciones = observaciones;
	}

	public Boolean getFueExitosa() {
		return fueExitosa;
	}

	public void setFueExitosa(Boolean fueExitosa) {
		this.fueExitosa = fueExitosa;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public String toString() {
		String informacionLlamada = "";
		
		if(this.fueExitosa)
			informacionLlamada = "La llamada sí fue exitosa. \n";
		else
			informacionLlamada = "La llamada no fue exitosa. \n";
		
		informacionLlamada += "Observaciones: " + this.observaciones + "\n";
		
		return informacionLlamada;
	}

}
