package com.sistema.pizzeria.elpirata.api.utils;

import java.security.SecureRandom;

public class SecretKeyGenerator {

	
	public static String generateKey(int lenght) {
		
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789<>";
		SecureRandom random = new SecureRandom();
		StringBuilder key = new StringBuilder();
		for (int i = 0; i < lenght; i++) {
			int index = random.nextInt(lenght);
			key.append(characters.charAt(index));
		}
		return key.toString();
	}
	
	public static void main(String []args) {
		
		System.out.println("Generated key: " + generateKey(64));
	}
}
