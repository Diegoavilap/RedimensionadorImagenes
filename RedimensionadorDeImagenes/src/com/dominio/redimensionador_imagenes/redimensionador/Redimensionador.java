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

import com.dominio.redimensionador_imagenes.imagen.Dimensión;
import com.dominio.redimensionador_imagenes.imagen.Imagen;

import static com.dominio.redimensionador_imagenes.utilidades.ConfiguraciónUtils.*;

public class Redimensionador {
	
	private final Path fuente;
	private final Path destino;
	private final Dimensión nuevaDimensión;
	
	private Redimensionador(final Path fuente, final Path destino, final Dimensión nuevaDimensión) {
		this.fuente = Objects.requireNonNull(fuente);
		this.destino = Objects.requireNonNull(destino);
		this.nuevaDimensión = Objects.requireNonNull(nuevaDimensión);
	}
	
	private Redimensionador(final Configuración configuración){
		this(configuración.fuente(), configuración.destino(), configuración.nuevaDimensión());
	}
	
	//fabricas estaticas
	public static Redimensionador con(final Configuración configuración){
		return new Redimensionador(configuración);
	}
	
	public static Redimensionador con(final Path fuente, final Path destino, final Dimensión nuevaDimensión){
		return new Redimensionador(fuente, destino, nuevaDimensión);
	}
	
	public static Redimensionador con(final Path fuente, final Dimensión nuevaDimensión){
		return new Redimensionador(fuente, Paths.get(RUTA_CARPETA_DESTINO_DEFECTO), nuevaDimensión);
	}
	//
	
	public Path fuente() {
		return fuente;
	}

	public Path destino() {
		return destino;
	}

	public Dimensión nuevaDimensión() {
		return nuevaDimensión;
	}

	public void redimensionar() throws IOException{
		List<Path> rutas = Files.list(Paths.get("fuente")).collect(Collectors.toList());//TODO: cambiar "fuente" para q el usuario pueda escoger la ubicación de fuente    
		System.out.println(rutas);
		
		for(Path ruta: rutas){
			System.out.println(ruta);		
			
			try {
				Imagen imagenOriginal = new Imagen(ruta);
				imagenOriginal.redimensionar(this.destino(), this.nuevaDimensión());
				
			} catch (IOException e) {
				// TODO: manejar la exception
			}
		}
	
	}

}
