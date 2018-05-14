package com.dominio.redimensionador_imagenes.imagen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import javax.imageio.ImageIO;

import com.dominio.redimensionador_imagenes.utilidades.Configuraci�nUtils;

public class Imagen {
	
	private final Path ruta;
	private final File archivo;
	private final Dimensi�n dimensiones;  

	static final String DIRECTORIO_DESTINO_POR_DEFECTO = String.format("destino/");
	
	public Imagen(final Path ruta) {
		this.ruta = Objects.requireNonNull(ruta);
		this.archivo = ruta.toFile();
		esImagen();
		this.dimensiones = new Dimensi�n(ancho(), alto());
	}

	public Path ruta() {
		return this.ruta;
	}
	
	public String nombre() {
		return this.ruta.getFileName().toString();
	}
	
	public Dimensi�n dimensiones() {
		return this.dimensiones;
	}
	
	public int alto(){
		return this.leerBufferedImageOriginal().getHeight();
	}
	
	public int ancho(){
		return this.leerBufferedImageOriginal().getWidth();
	}
	
	public String formato(){
		return this.nombre().substring(this.nombre().lastIndexOf('.') + 1);
	}

	public Imagen redimensionar(final Path rutaDestino, final Dimensi�n nuevaDimensi�n) throws IOException{
		if(nuevaDimensi�n == null) throw new IllegalArgumentException("la nueva dimesi�n no puede ser null");
				
		BufferedImage bufferedImageOriginal = this.leerBufferedImageOriginal();
		BufferedImage bufferedImagenRedimensionada = crearBufferedImageRedimensionada(bufferedImageOriginal, nuevaDimensi�n);
		generarGraphics2DBufferedImageRedimensionada(bufferedImageOriginal, bufferedImagenRedimensionada, nuevaDimensi�n);
		
		return escribir(rutaDestino, bufferedImagenRedimensionada);
	}

	public Imagen redimensionar(final Dimensi�n nuevaDimensi�n) throws IOException{
		return redimensionar(Paths.get(Configuraci�nUtils.RUTA_CARPETA_DESTINO_DEFECTO + nombre()), nuevaDimensi�n);
	}
	
	//..
	
	private boolean esImagen(){
		switch (this.formato()){
			case "jpg": case "png": case "gif":
				return true;
			default:
				throw new AssertionError("El archivo no tiene el formato de imagen " + formato() );		
		}	
	}
	
	private void generarGraphics2DBufferedImageRedimensionada(BufferedImage bufferedImageOriginal, BufferedImage bufferedImagenRedimensionada, Dimensi�n nuevaDimensi�n) {
		Graphics2D gr�fico2D = bufferedImagenRedimensionada.createGraphics();
		gr�fico2D.drawImage(bufferedImageOriginal, 0, 0, nuevaDimensi�n.ancho(), nuevaDimensi�n.alto(), null);
		gr�fico2D.dispose();				
	}
	
	private Imagen escribir(final Path rutaDestino, BufferedImage bufferedImage) throws IOException{
		ImageIO.write(bufferedImage, this.formato(), rutaDestino.resolve(this.nombre()).toFile());//IOException		
		return new Imagen(rutaDestino.resolve(this.nombre())); 		
	}
	
	private BufferedImage leerBufferedImageOriginal(){
		try {
			return ImageIO.read(this.archivo);
		} catch (IOException e) {
			return new BufferedImage(0, 0, 0);	//TODO: manejar bien la exception, paraece q esta como escondida
		}
	}
	
	private BufferedImage crearBufferedImageRedimensionada(final BufferedImage bufferImagenOriginal, final Dimensi�n nuevaDimensi�n){
		return new BufferedImage(nuevaDimensi�n.ancho(), nuevaDimensi�n.alto(), bufferImagenOriginal.getType());
	}

	//..
	@Override
	public String toString() {
		return "Imagen [ruta=" + ruta + ", archivo=" + archivo + ", dimensiones=" + dimensiones + "]";
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int resultado = 1;
		resultado = primo * resultado + ((archivo == null) ? 0 : archivo.hashCode());
		resultado = primo * resultado + ((dimensiones == null) ? 0 : dimensiones.hashCode());
		resultado = primo * resultado + ((ruta == null) ? 0 : ruta.hashCode());
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
		Imagen other = (Imagen) obj;
		if (archivo == null) {
			if (other.archivo != null)
				return false;
		} else if (!archivo.equals(other.archivo))
			return false;
		if (dimensiones == null) {
			if (other.dimensiones != null)
				return false;
		} else if (!dimensiones.equals(other.dimensiones))
			return false;
		if (ruta == null) {
			if (other.ruta != null)
				return false;
		} else if (!ruta.equals(other.ruta))
			return false;
		return true;
	}
	
}
