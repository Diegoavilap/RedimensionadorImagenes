package com.dominio.redimensionador_imagenes.utilidades;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dominio.redimensionador_imagenes.imagen.Dimensi�n;
import com.dominio.redimensionador_imagenes.redimensionador.Configuraci�n;

public final class Configuraci�nUtils {
	
	private static final String RUTA_DE_ARCHIVO_CONFIGURACI�N_DEFECTO = String.format("recursos/configuracion.txt");
	private static final int M�NIMO_N�MERO_DE_FILAS_ARCHIVO = 4;
	public static final String RUTA_CARPETA_DESTINO_DEFECTO = String.format("destino/");
	
	private Configuraci�nUtils(){
		throw new AssertionError("Esta es una clase utilitaria");
	}
	
	public static Configuraci�n leerConfiguraci�n(final File configuraci�n){
		if(!existe(configuraci�n) ) throw new IllegalArgumentException("Error en la lectura del archivo, pueda que no exista o no tenga permisos de lectura");			
		List<String> datosConfiguraci�n = leerDatosConfiguraci�n(configuraci�n);
		comprobarN�meroL�neas(datosConfiguraci�n);
		
		return new Configuraci�n(fuente(datosConfiguraci�n) , destino(datosConfiguraci�n) , nuevaDimensi�n(datosConfiguraci�n));
	}

	private static void comprobarN�meroL�neas(List<String> datosConfiguraci�n) {
		if(datosConfiguraci�n.size() != M�NIMO_N�MERO_DE_FILAS_ARCHIVO) throw new IllegalArgumentException("El archivo debe tener min�mo 4 l�neas, 1. fuente, 2. destino. 3. nuevoo ancho, 4.nuevo alto");
	}
	
	public static Configuraci�n leerConfiguraci�n(){		
		return leerConfiguraci�n(new File(RUTA_DE_ARCHIVO_CONFIGURACI�N_DEFECTO));
	}
	
	private static Path fuente(final List<String> datosConfiguraci�n){//TODO: la fuente la debe indicar el usuario, en caso de no encontrarla hay q mirar como manejarlo
		return Paths.get(datosConfiguraci�n.get(0));//TODO: manejar si no existe un path valido como fuente 	
	}
	
	private static Path destino(final List<String> datosConfiguraci�n){
		return datosConfiguraci�n.get(1).isEmpty() ? Paths.get(RUTA_CARPETA_DESTINO_DEFECTO): Paths.get(datosConfiguraci�n.get(1));
	}
	
	private static Dimensi�n nuevaDimensi�n(final List<String> datosConfiguraci�n){
		return new Dimensi�n(Integer.parseInt(datosConfiguraci�n.get(2)), Integer.parseInt(datosConfiguraci�n.get(3)));
	}
	
	public static Configuraci�n leerConfiguraci�n(final Path configuraci�n){
		return leerConfiguraci�n(configuraci�n.toFile());
	}
	
	private static List<String> leerDatosConfiguraci�n(final File archivo) {
		return Configuraci�nUtils.leer(archivo);
	}
		
	private static List<String> leer(final File archivo){
		try{			
			return new ArrayList<String>(leerPorL�neas(archivo));
		} catch(IOException ex){
			return Collections.emptyList();
		}		
	}

	private static boolean existe(final File archivo){
		return archivo.exists() || archivo.isFile();
	}
	
	private static List<String> leerPorL�neas(final File archivo) throws IOException{
		return Files.readAllLines(archivo.toPath());
	}
				
}
