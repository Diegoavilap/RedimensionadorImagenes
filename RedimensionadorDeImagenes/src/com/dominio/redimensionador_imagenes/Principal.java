package com.dominio.redimensionador_imagenes;

import java.io.IOException;

import com.dominio.redimensionador_imagenes.redimensionador.Configuraci�n;
import com.dominio.redimensionador_imagenes.redimensionador.Redimensionador;

public class Principal {

	public static void main(String [] args){
		
		Redimensionador redimensionadorImagenes;
		redimensionadorImagenes = Redimensionador.con(new Configuraci�n());	
		
		try {
			redimensionadorImagenes.redimensionar();
		} catch (IOException e) {
			// TODO: manejar la exception
		}		
	}

}
