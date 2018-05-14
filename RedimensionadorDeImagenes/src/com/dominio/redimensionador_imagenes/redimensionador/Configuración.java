package com.dominio.redimensionador_imagenes.redimensionador;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import com.dominio.redimensionador_imagenes.imagen.Dimensión;

import static com.dominio.redimensionador_imagenes.utilidades.ConfiguraciónUtils.*;

public class Configuración {
	
	private final Path fuente;
	private final Path destino;
	private final Dimensión nuevaDimensión;
	
	public Path fuente() {
		return fuente;
	}

	public Path destino() {
		return destino;
	}

	public Dimensión nuevaDimensión() {
		return nuevaDimensión;
	}
	
	public Configuración(final Path fuente, final Path destino, final Dimensión nuevaDimensión) {
		this.fuente = Objects.requireNonNull(fuente);
		this.destino = Objects.requireNonNull(destino);
		this.nuevaDimensión = Objects.requireNonNull(nuevaDimensión);
	}
	
	public Configuración() {
		this(leerConfiguración().fuente(), leerConfiguración().destino(), leerConfiguración().nuevaDimensión());	
	}
	
	public Configuración(final File configuración) {
		this(leerConfiguración(configuración).fuente(), leerConfiguración(configuración).destino(), leerConfiguración(configuración).nuevaDimensión());	
	}
	
	public Configuración(final Path configuración) {
		this(configuración.toFile());
	}

}
