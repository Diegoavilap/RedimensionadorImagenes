package com.dominio.redimensionador_imagenes.redimensionador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dominio.redimensionador_imagenes.imagen.Dimensi�n;
import com.dominio.redimensionador_imagenes.imagen.Imagen;

import static com.dominio.redimensionador_imagenes.utilidades.Configuraci�nUtils.*;

public class Redimensionador {
	
	private final Path fuente;
	private final Path destino;
	private final Dimensi�n nuevaDimensi�n;
	
	private Redimensionador(final Path fuente, final Path destino, final Dimensi�n nuevaDimensi�n) {
		this.fuente = Objects.requireNonNull(fuente);
		this.destino = Objects.requireNonNull(destino);
		this.nuevaDimensi�n = Objects.requireNonNull(nuevaDimensi�n);
	}
	
	private Redimensionador(final Configuraci�n configuraci�n){
		this(configuraci�n.fuente(), configuraci�n.destino(), configuraci�n.nuevaDimensi�n());
	}
	
	//fabricas estaticas
	public static Redimensionador con(final Configuraci�n configuraci�n){
		return new Redimensionador(configuraci�n);
	}
	
	public static Redimensionador con(final Path fuente, final Path destino, final Dimensi�n nuevaDimensi�n){
		return new Redimensionador(fuente, destino, nuevaDimensi�n);
	}
	
	public static Redimensionador con(final Path fuente, final Dimensi�n nuevaDimensi�n){
		return new Redimensionador(fuente, Paths.get(RUTA_CARPETA_DESTINO_DEFECTO), nuevaDimensi�n);
	}
	//
	
	public Path fuente() {
		return fuente;
	}

	public Path destino() {
		return destino;
	}

	public Dimensi�n nuevaDimensi�n() {
		return nuevaDimensi�n;
	}

	public void redimensionar() throws IOException{
		List<Path> rutas = Files.list(Paths.get("fuente")).collect(Collectors.toList());//TODO: cambiar "fuente" para q el usuario pueda escoger la ubicaci�n de fuente    
		System.out.println(rutas);
		
		for(Path ruta: rutas){
			System.out.println(ruta);		
			
			try {
				Imagen imagenOriginal = new Imagen(ruta);
				imagenOriginal.redimensionar(this.destino(), this.nuevaDimensi�n());
				
			} catch (IOException e) {
				// TODO: manejar la exception
			}
		}
	
	}

}
