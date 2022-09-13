package spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service

public class Servicio {

	public String leerTXT(String file) throws IOException {
		logTXT();
		String completo = "";
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {
				completo = completo + linea + "\n";
				linea = br.readLine();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		logTXT2();
		return completo;
	}

	public boolean verificar(String file) {
		logVerificar();
		if (file.contains(".txt")) {
			logVerificar2();
			return true;
		} else {
			logVerificar2();
			return false;
		}
	}

	public void generarTXT(ArrayList<String> array) {
		try {
			logGenerarTXT();
			String nombre = "Artistas y canciones 20 - 21.txt";

			File fichero = new File(nombre);

			if (!fichero.exists()) {
				Path file = Paths.get(nombre);
				Files.write(file, array, StandardCharsets.UTF_8);
			}

			else {
				System.out.println("El fichero ya existe.");
			}
			logGenerarTXT2();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void logTXT() {

	}

	public void logVerificar() {

	}

	public void logGenerarTXT() {

	}

	public void logTXT2() {

	}

	public void logVerificar2() {

	}

	public void logGenerarTXT2() {

	}

}