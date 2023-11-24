package com.adriana.tienda.utilidades;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class GeneradorCodigo {
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int TRACKING_NUMBER_LENGTH = 25;

	private static SecureRandom random = new SecureRandom();

	public static String generarNumeroSeguimiento() {
		StringBuilder sb = new StringBuilder(TRACKING_NUMBER_LENGTH);
		for (int i = 0; i < TRACKING_NUMBER_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(randomIndex));
		}
		return sb.toString();
	}

//	public static void main(String[] args) {
//		Set<String> numerosSeguimientoGenerados = new HashSet<>();
//		int cantidadNumerosGenerados = 10000; // Cantidad de números que deseas generar
//
//		for (int i = 0; i < cantidadNumerosGenerados; i++) {
//			String numeroSeguimiento = generarNumeroSeguimiento();
//
//			if (numerosSeguimientoGenerados.contains(numeroSeguimiento)) {
//				System.out.println("Se generó un número de seguimiento duplicado: " + numeroSeguimiento);
//			} else {
//				numerosSeguimientoGenerados.add(numeroSeguimiento);
//			}
//		}
//
//		System.out.println("Se generaron " + numerosSeguimientoGenerados.size() + " números de seguimiento únicos.");
//	}
}
