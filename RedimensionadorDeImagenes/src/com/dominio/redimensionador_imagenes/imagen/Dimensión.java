package com.dominio.redimensionador_imagenes.imagen;

public class Dimensi�n{
 
	private final int ancho;
	private final int alto;
	
	public Dimensi�n(final int ancho, final int alto) {
		if(alto < 0 || ancho  < 0) throw new IllegalArgumentException("Las dimensiones no son validas, debe ser > que cero");
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public int ancho(){
		return this.ancho;
	}
	
	public int alto(){
		return this.alto;
	}

	@Override
	public String toString() {
		return "Dimensi�n2D [alto=" + alto + ", ancho=" + ancho + "]";
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + alto;
		resultado = primo * resultado + ancho;
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dimensi�n other = (Dimensi�n) obj;
		if (alto != other.alto)
			return false;
		if (ancho != other.ancho)
			return false;
		return true;
	}
	
}
