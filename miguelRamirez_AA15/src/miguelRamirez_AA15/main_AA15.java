package miguelRamirez_AA15;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/*Este programa debe tener implementado lo siguiente:
- Composición
- Interfaces
- Herencia
- Manejo de Ficheros
- Implementación de un archivo JSON a traves de una API
- Mensaje por consola de Jenkins
- Implementación del proyecto en la rama de Desarrollo con merge en la rama principial (main o master segun aplique)
https://public.opendatasoft.com/explore/dataset/provincias-espanolas/table/?sort=-provincia


PROBLEMATICA: 
Nos contrataron desde el gobierno de España para diseñarles un programa que les informe las Provincias y sus respectivas capitales, el programa debe tener la capacidad de generar un archivo TXT
 de salida informando por cada provincia
su capital. El txt de salida debe tener la fecha de cuando corrio el proceso dentro del archivo. 

ESTRUCTURA DEL PROGRAMA: 
- Contar con al menos 3 clases, una de Provincia, una de Capital de provincia y otra a definir por el programador.
- Contar con un metodo implementado en una interfaz que conste de generar el archivo de salida.
- Utilizar la composición para implementar la información del usuario que lo implemente. Ejemplo: ID_USUARIO: 1 Nombre del usuario: Vargas,Gustavo Fecha Login 12/09/2022
(!) La información del usuario debe ir en el TXT implementada 
- Mostrar la información por consola de Jenkins. Generando un jenkinsfile que implemente la salida


ENTREGABLES:
- Codigo del proyecto
- Captura de consola de Jenkins 
- TXT de salida */

public class main_AA15 implements exportar {

	public static void main(String[] args) {

		StringBuilder builder = new StringBuilder();

		try {

			URL url = new URL(
					"https://public.opendatasoft.com/api/records/1.0/search/?dataset=provincias-espanolas&q=&rows=0&sort=-provincia&facet=ccaa&facet=provincia");
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.connect();

			int respuesta = conexion.getResponseCode();

			if (respuesta != 200) {
				throw new RuntimeException("HttpResponse" + respuesta);
			} else {

				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					builder.append(sc.nextLine() + "\n");
				}
				sc.close();

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		JSONObject jsonObj = new JSONObject(builder.toString());
		JSONArray arrayJson = (JSONArray) jsonObj.get("facet_groups");
		JSONObject jsonCiudades = (JSONObject) arrayJson.get(1);
		JSONArray arrayJson2 = (JSONArray) jsonCiudades.get("facets");

		ArrayList<Capital> capitales = new ArrayList<>();

		for (int x = 0; x < arrayJson2.length(); x++) {
			Capital nuevaCapital = new Capital(arrayJson2.getJSONObject(x).get("path").toString(),
					arrayJson2.getJSONObject(x).get("name").toString());
			capitales.add(nuevaCapital);
		}

		Usuario nuevoUsuario = new Usuario("Miguel", 1334);

		exportarTXT(nuevoUsuario, capitales);

		// System.out.println(capitales);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		// jenkins///////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////

		StringBuilder pronvinciaBuilder = new StringBuilder();
		pronvinciaBuilder.append("import java.time.LocalDate\r\n");
		pronvinciaBuilder.append("pipeline{\r\n");
		pronvinciaBuilder.append("agent any \r\n");
		pronvinciaBuilder.append("stages{ \r\n");
		pronvinciaBuilder.append("stage('AA13'){ \r\n");
		pronvinciaBuilder.append("steps{ \r\n");
		pronvinciaBuilder.append("script{ \r\n");

		pronvinciaBuilder
				.append("println \"" + nuevoUsuario.getNombreUsuario() + " ID " + nuevoUsuario.getID() + "\"\r\n");
		pronvinciaBuilder.append("println \"Fecha: " + LocalDateTime.now() + "\"\r\n");
		pronvinciaBuilder.append("println \"INFORMACION SOBRE CAPITALES\"\r\n");
		pronvinciaBuilder.append("println \"\"\r\n");

		for (int x = 0; x < capitales.size(); x++) {
			pronvinciaBuilder.append("println \"" + capitales.get(x).toString() + "\"\r\n");
		}

		pronvinciaBuilder.append("} \r\n");
		pronvinciaBuilder.append("} \r\n");
		pronvinciaBuilder.append("} \r\n");
		pronvinciaBuilder.append("} \r\n");
		pronvinciaBuilder.append("} \r\n");

		ArrayList<String> array = new ArrayList<>();

		try {
			array.add(pronvinciaBuilder.toString());
			String jenkins = "Jenkins.txt";
			File fichero = new File(jenkins);

			if (!fichero.exists()) {
				Path file3 = Paths.get(jenkins);
				Files.write(file3, array, StandardCharsets.UTF_8);
				System.out.println("");
				System.out.println("Fichero Jenkins generado con exito.");
			}

			else {
				System.out.println("");
				System.out.println("El fichero Jenkins ya existe.");
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void exportarTXT(Usuario usuario, ArrayList<Capital> capitales) {

		String total = "";

		try {

			String nombreTXT = "capitales.txt";

			File fichero = new File(nombreTXT);

			ArrayList<String> contenido = new ArrayList<>();
			contenido.add("Usuario: " + usuario.getNombreUsuario() + " ID " + usuario.getID() + "\n");
			contenido.add("Fecha: " + LocalDateTime.now() + "\n");
			contenido.add("INFORMACION SOBRE CAPITALES" + "\n");

			for (int x = 0; x < capitales.size(); x++) {
				contenido.add(capitales.get(x).toString());
			}

			total = contenido.toString();

			if (!fichero.exists()) {
				Path file = Paths.get(nombreTXT);
				Files.write(file, contenido, StandardCharsets.UTF_8);
				System.out.println("");
				System.out.println("Fichero " + nombreTXT + " generado con exito.");
			}

			else {
				System.out.println("");
				System.out.println("El fichero " + nombreTXT + " ya existe.");
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
