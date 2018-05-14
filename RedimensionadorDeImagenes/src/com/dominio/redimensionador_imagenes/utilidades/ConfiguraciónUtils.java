package com.dominio.redimensionador_imagenes.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dominio.redimensionador_imagenes.imagen.Dimensión;
import com.dominio.redimensionador_imagenes.redimensionador.Configuración;

public final class ConfiguraciónUtils {
	
	private static final String RUTA_DE_ARCHIVO_CONFIGURACIÓN_DEFECTO = String.format("recursos/configuracion.txt");
	private static final int MÍNIMO_NÚMERO_DE_FILAS_ARCHIVO = 4;
	public static final String RUTA_CARPETA_DESTINO_DEFECTO = String.format("destino/");
	
	private ConfiguraciónUtils(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static Configuración leerConfiguración(final File configuración){
		if(!existe(configuración) ) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga permisos de lectura");			
		List<String> datosConfiguración = leerDatosConfiguración(configuración);
		comprobarNúmeroLíneas(datosConfiguración);
		
		return new Configuración(fuente(datosConfiguración) , destino(datosConfiguración) , nuevaDimensión(datosConfiguración));
	}

	private static void comprobarNúmeroLíneas(List<String> datosConfiguración) {
		if(datosConfiguración.size() != MÍNIMO_NÚMERO_DE_FILAS_ARCHIVO) throw new IllegalArgumentException("El archivo debe tener minímo 4 líneas, 1. fuente, 2. destino. 3. nuevoo ancho, 4.nuevo alto");
	}
	
	public static Configuración leerConfiguración(){		
		return leerConfiguración(new File(RUTA_DE_ARCHIVO_CONFIGURACIÓN_DEFECTO));
	}
	
	private static Path fuente(final List<String> datosConfiguración){//TODO: la fuente la debe indicar el usuario, en caso de no encontrarla hay q mirar como manejarlo
		return Paths.get(datosConfiguración.get(0));//TODO: manejar si no existe un path valido como fuente 	
	}
	
	private static Path destino(final List<String> datosConfiguración){
		return datosConfiguración.get(1).isEmpty() ? Paths.get(RUTA_CARPETA_DESTINO_DEFECTO): Paths.get(datosConfiguración.get(1));
	}
	
	private static Dimensión nuevaDimensión(final List<String> datosConfiguración){
		return new Dimensión(Integer.parseInt(datosConfiguración.get(2)), Integer.parseInt(datosConfiguración.get(3)));
	}
	
	public static Configuración leerConfiguración(final Path configuración){
		return leerConfiguración(configuración.toFile());
	}
	
	private static List<String> leerDatosConfiguración(final File archivo) {
		return ConfiguraciónUtils.leer(archivo);
	}
		
	private static List<String> leer(final File archivo){
		try{			
			return new ArrayList<String>(leerPorLíneas(archivo));
		} catch(IOException ex){
			return Collections.emptyList();
		}		
	}

	private static boolean existe(final File archivo){
		return archivo.exists() || archivo.isFile();
	}
	
	private static List<String> leerPorLíneas(final File archivo) throws IOException{
		return Files.readAllLines(archivo.toPath());
	}
				
}
