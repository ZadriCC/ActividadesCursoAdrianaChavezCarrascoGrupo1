package com.adriana.tienda.utilidades;

import com.adriana.tienda.excepciones.CampoNuloOVacioException;
import com.adriana.tienda.excepciones.ErrorAppException;

public class Validaciones {

	public static void validarCampoNuloOVacio(String campo, String nombreCampo) {
		if (campo == null || campo.isEmpty()) {
			throw new CampoNuloOVacioException("El campo '" + nombreCampo + "' no puede estar vacío.");
		}
	}

//	public static void compararIds(Object objeto, String nombrePropiedad, Object valorEsperado) {
//		try {
//			Field campo = objeto.getClass().getDeclaredField(nombrePropiedad);
//			System.out.println("Propiedad: " + campo.getName());
//			campo.setAccessible(true);
//			Object valorPropiedad = campo.get(objeto);
//
//			if (!valorPropiedad.equals(valorEsperado)) {
//				throw new ErrorAppException("El valor de la propiedad no coincide con el valor esperado");
//			}
//		} catch (NoSuchFieldException | IllegalAccessException e) {
//			throw new ErrorAppException("Error al acceder a la propiedad o al objeto: " + e.getMessage());
//		}
//	}

	public static void compararIDs(Object id1, Object id2) {
		if (!id1.equals(id2)) {
			throw new ErrorAppException("Los IDs no coinciden.");
		}
	}
}
