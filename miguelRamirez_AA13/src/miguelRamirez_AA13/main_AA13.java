package miguelRamirez_AA13;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/*ACTIVIDAD INTEGRADORA DE JAVA - Github - Jenkins

Nos contrataron de una empresa que desea implementar la metodologia de Integración Continua. Para esto, nos piden que desarrollemos 
un programa en Java que implemente un jenkinsfile
Ademas de eso, quieren que contabilicemos el TOP 20 de peliculas con mayor recaudación de LA HISTORIA.
 Para esto, nos dejaron un archivo con las 10 peliculas siguientes al top 10 con mayor recaudación
en la historia. 
PELICULA A 1
PELICULA B 2
PELICULA C 3
PELICULA D 4
PELICULA E 5
PELICULA F 6
PELICULA G 7
PELICULA H 8
PELICULA I 9
PELICULA J 10
PELICULA A 11
PELICULA B 12
PELICULA C 13
PELICULA D 14
PELICULA E 15
PELICULA F 16
PELICULA G 17
PELICULA H 18
PELICULA I 19
PELICULA J 20
El archivo debe estar ordenado por recaudación (de mayor recaudación a menor recaudación) 

En GitHub:
Subir todo el proyecto de Java, una vez generados los TXTS. 

En Jenkins:
(SOBRE LA RAMA PRINCIPAL) 
El jenkinsfile debe ser tomado del repositorio en Github. El contenido del mismo debe mostrar por consola (Consola de Jenkins) el siguiente mensaje:
"Hola Mundo! EL día de hoy es elDia (variable que almacene la fecha).
 Este curso me hizo programar mas de lo que me hubiese gustado" 

¿COMO FUNCIONA ESTO? 
1) Crear un nuevo proyecto en Eclipse "AA13 - Java integrado a Jenkins y Github"
2) Desarrollar la estructura del programa. 
3) Desarrollar el jenkinsfile. 
4) Actualizar el repo desde Eclipse a Github
5) Una vez actualizado el repo, configurar en jenkins la busqueda del jenkinsfile generado por el proyecto. 

ENTREGABLES: 
- Codigo del proyecto de Java. 
- Captura de la pantalla de Java mostrando las 20 mejores peliculas
- Captura de la pantalla de Jenkins
- TXT de Salida de peliculas (Llamarlo "top20_mejores_peliculas.txt")

¿COMO GENERAR EL JENKINSFILE? 
Un jenkinsfile es un simple TXT. GENERAMOS UN TXT Y LE INSERTAMOS EL CODIGO QUE ESPERA JENKINS. */

public class main_AA13 {

	public static void main(String[] args) {

		File file = new File("Peliculas_1_10.txt");
		File file2 = new File("Peliculas_11_20.txt");

		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String temp = sc.nextLine().replace("$", "");
				temp = temp.replace(",", "");
				temp = temp.replace("	", ";");

				String[] datos = temp.split(";");
				String nombreTemp = datos[0].trim();
				String recaudacionTemp = datos[1].trim();

				Pelicula pelicula = new Pelicula(nombreTemp, recaudacionTemp);
				peliculas.add(pelicula);
				// System.out.println(pelicula.toString());
			}

			sc.close();

			Scanner sc2 = new Scanner(file2);
			while (sc2.hasNextLine()) {
				String temp = sc2.nextLine().replace("$", "");
				temp = temp.replace(",", "");
				temp = temp.replace("	", "	");

				String[] datos = temp.split("	");
				String nombreTemp = datos[0].trim();
				String recaudacionTemp = datos[1].trim();

				Pelicula pelicula = new Pelicula(nombreTemp, recaudacionTemp);
				peliculas.add(pelicula);
				// System.out.println(pelicula.toString());
			}
			sc2.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		StringBuilder builder = new StringBuilder();
		builder.append("import java.time.LocalDate\r\n");
		builder.append("pipeline{\r\n");
		builder.append("agent any \r\n");
		builder.append("stages{ \r\n");
		builder.append("stage('AA13'){ \r\n");
		builder.append("steps{ \r\n");
		builder.append("script{ \r\n");
		builder.append("def fecha = LocalDate.now() \r\n");
		builder.append("println \"El día de hoy es: \" + fecha.getDayOfWeek() \r\n");
		builder.append("println \"TOP 20 peliculas con mas recaudaciones en taquilla:\" \r\n");

		for (int x = 0; x < peliculas.size(); x++) {
			builder.append("println \"" + peliculas.get(x).toString() + "\"\r\n");
		}

		builder.append("} \r\n");
		builder.append("} \r\n");
		builder.append("} \r\n");
		builder.append("} \r\n");
		builder.append("} \r\n");

		 System.out.println(builder.toString());

		ArrayList<String> array = new ArrayList<>();

		try {
			array.add(builder.toString());
			String nombreTXT = "Peliculas_completo.txt";
			File fichero = new File(nombreTXT);

			if (!fichero.exists()) {
				Path file3 = Paths.get(nombreTXT);
				Files.write(file3, array, StandardCharsets.UTF_8);
				System.out.println("Fichero generado con exito.");
			}

			else {
				System.out.println("El archivo ya existe.");
			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
