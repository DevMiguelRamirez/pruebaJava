package spring;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*IMPLEMENTANDO AOP, INTERFACES, CLASES, HERENCIA, GITHUB ---------------
PROBLEMATICA: Nos contrataron de Spotify para desarrollar un programa que nos brinde información general del uso de la aplicación
A día de hoy tienen 4 archivos planos donde tienen los registros de los ultimos años y les gustaría unificarlos en uno solo. Los archivos son los siguientes:
TOP 10 CANCIONES DE 2021
TOP 10 CANCIONES DE 2020
TOP 10 ARTISTAS DE 2021
TOP 10 ARTISTAS DE 2020
Luego de haber informado esto, nos piden que informemos, segun el valor de cada canción su recaudación global. teniendo en cuenta que por cada vez que se escucha una canción se le da 2 euros a cada artista
El formato del archivo de salida esperado es el siguiente: 
TOP 10 CANCIONES DE 2020 + Recaudación de cada una
TOP 10 CANCIONES DE 2021 + Recaudación de cada una
TOP 10 ARTISTAS DE 2020
TOP 10 ARTISTAS DE 2021

AOP: 
- Implementar AOP ANTES de ejecutar el metodo generaArchivo() informando que se esta por generar un archivo nuevo. 
- Implementar AOP ANTES de cargar los TXTS en el programa validando que sean formato .txt 
- Implementar AOP DESPUES de generar la salida informando que se genero la salida del programa.

INTERFACES: 
- Implementar un metodo que calcule la recaudación

CLASES:
Declarar la clase Persona, Artista y Usuario informando atributos de cada una de las mismas. 

ENTREGABLE:
- Codigo del proyecto
- Archivo final de salida
- Subir el proyecto al repo de Github. 
*/

public class Main_AA16 {

	public static void main(String[] args) throws IOException {

		String artistas2020 = "Artistas 2020.txt";
		String canciones2020 = "Canciones 2020.txt";
		String artistas2021 = "Artistas 2021.txt";
		String canciones2021 = "Canciones 2021.txt";
		ArrayList<String> arrayFinal = new ArrayList<>();

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurarSpring.class)) {
			Servicio servicio = ctx.getBean(Servicio.class);

			servicio.logVerificar();
			if (servicio.verificar(canciones2020) == true && servicio.verificar(canciones2021) == true
					&& servicio.verificar(artistas2020) == true && servicio.verificar(artistas2021) == true) {
				servicio.logVerificar2();

				arrayFinal.add("\n\n====== 2020 ======\n\n");
				arrayFinal.add("\nMejores canciones\n");

				servicio.logTXT();
				String temp = servicio.leerTXT(canciones2020);
				servicio.logTXT2();
				temp = temp.replace("	", ";");
				temp = temp.replace("-", ";");
				temp = temp.replace("\n", ";");
				String[] datos = temp.split(";");

				for (int x = 0; x < datos.length; x++) {
					Artista nuevo = new Artista();
					nuevo.setCancion(datos[x]);
					nuevo.setNombreArtista(datos[x + 1]);
					nuevo.setNombrePersona(datos[x + 1]);
					nuevo.setVisitas(Double.parseDouble(datos[x + 2]));
					nuevo.calcRecaudacion();
					x = x + 2;
					arrayFinal.add(nuevo.toString());
				}

				arrayFinal.add("\nMejores artsitas\n");

				temp = "";
				servicio.logTXT();
				temp = servicio.leerTXT(artistas2020);
				servicio.logTXT2();
				String[] datos2 = temp.split("\n");
				for (int x = 0; x < datos2.length; x++) {
					Artista nuevo = new Artista();
					nuevo.setNombreArtista(datos2[x]);
					arrayFinal.add(nuevo.getNombreArtista());
				}

				arrayFinal.add("\n\n====== 2021 ======\n\n");
				arrayFinal.add("\nMejores canciones\n");

				temp = "";
				servicio.logTXT();
				temp = servicio.leerTXT(canciones2021);
				servicio.logTXT2();
				temp = temp.replace("	", ";");
				temp = temp.replace("-", ";");
				temp = temp.replace("\n", ";");
				String[] datos3 = temp.split(";");

				for (int x = 0; x < datos3.length; x++) {
					Artista nuevo = new Artista();
					nuevo.setCancion(datos3[x]);
					nuevo.setNombreArtista(datos3[x + 1]);
					nuevo.setNombrePersona(datos3[x + 1]);
					nuevo.setVisitas(Double.parseDouble(datos3[x + 2]));
					nuevo.calcRecaudacion();
					x = x + 2;
					arrayFinal.add(nuevo.toString());
				}

				arrayFinal.add("\nMejores artsitas\n");
				temp = "";
				servicio.logTXT();
				temp = servicio.leerTXT(artistas2021);
				servicio.logTXT2();
				String[] datos4 = temp.split("\n");
				for (int x = 0; x < datos4.length; x++) {
					Artista nuevo = new Artista();
					nuevo.setNombreArtista(datos4[x]);
					arrayFinal.add(nuevo.getNombreArtista());
				}

				servicio.logGenerarTXT();
				servicio.generarTXT(arrayFinal);
				servicio.logGenerarTXT2();

			} else {
				System.out.println("Alguno de los ficheros no tiene el formato correcto");
			}

		} catch (BeansException e) {
			e.printStackTrace();
		}

	}

}
