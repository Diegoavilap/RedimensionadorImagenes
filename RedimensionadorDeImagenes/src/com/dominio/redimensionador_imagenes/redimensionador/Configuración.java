package com.dominio.redimensionador_imagenes.redimensionador;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import com.dominio.redimensionador_imagenes.imagen.Dimensi�n;

import static com.dominio.redimensionador_imagenes.utilidades.Configuraci�nUtils.*;

public class Configuraci�n {
	
	private final Path fuente;
	private final Path destino;
	private final Dimensi�n nuevaDimensi�n;
	
	public Path fuente() {
		return fuente;
	}

	public Path destino() {
		return destino;
	}

	public Dimensi�n nuevaDimensi�n() {
		return nuevaDimensi�n;
	}
	
	public Configuraci�n(final Path fuente, final Path destino, final Dimensi�n nuevaDimensi�n) {
		this.fuente = Objects.requireNonNull(fuente);
		this.destino = Objects.requireNonNull(destino);
		this.nuevaDimensi�n = Objects.requireNonNull(nuevaDimensi�n);
	}
	
	public Configuraci�n() {
		this(leerConfiguraci�n().fuente(), leerConfiguraci�n().destino(), leerConfiguraci�n().nuevaDimensi�n());	
	}
	
	public Configuraci�n(final File configuraci�n) {
		this(leerConfiguraci�n(configuraci�n).fuente(), leerConfiguraci�n(configuraci�n).destino(), leerConfiguraci�n(configuraci�n).nuevaDimensi�n());	
	}
	
	public Configuraci�n(final Path configuraci�n) {
		this(configuraci�n.toFile());
	}

}
