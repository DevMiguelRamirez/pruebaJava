package miguelRamirez_AA14;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import org.json.JSONObject;

/*(!) AA14 (!)
ESTRUCTURA DEL PROGRAMA:
- Proyecto Maven (Lo van a necesitar para el JSON)
- Herencia entre clases
- Implementación de una interfaz
- Conectividad con Github
- JSON / API
- Implementar un jenkinsfile

PROBLEMATICA:
Nos contrataron desde la empresa Charlie y la fabrica de chocolate situados en Sevilla para ayudarlos a mejorar su sistema de generación de chocolates. Para esto, el cliente nos dio algunas
especificaciones. Al ser una fabrica de chocolates, tienen en consideración las condiciones climaticas, ya que si la temperatura actual es mayor a 40° no se fabrican
chocolates ese día dado que existe un riesgo de que el mismo se derrita. Si las condiciones climaticas son favorables la producción se hace habitualmente.
Para informar al cliente de como esta yendo el proceso, nos pidio que se lo informemos a traves de un archivo plano. Informandole por producto las cantidades generadas
diariamente. 

ESPECIFICACIONES TECNICAS:
- El programa debe conectar con Github y dejar el codigo en la rama de Desarrollo
- Obtener la información del clima a traves de la API del sitio https://www.el-tiempo.net/
- La interfaz debe implementar el metodo produccionActiva() que es la que indica si ese día se produce chocolate o no.
- Clase Chocolate que herede de la clase Golosina con sus respectivos atributos.
- El jenkinsfile debe mostrar la información por consola de los chocolates generados. 

ENTREGABLES:
- Codigo del proyecto
- Captura de consola de jenkins
- Captura de consola de Java. La consola de Java debe informar si se puede producir chocolate o no, y en el caso de poderse, informar que fue lo que se produjo
el día de hoy. 

SET DE DATOS:
Nombre					CANTIDAD PRODUCIDA
- Chocolate Blanco			1000
- Choclate Negro				1500
- Chocolate con almendras		1200
- Chocolate con castañas de caju	1300
- Chocolate en rama			100
- Chocolate con 70% de cacao		1500*/

public class main_AA14 implements Produccion {

	public static void main(String[] args) {

		StringBuilder builder = new StringBuilder();

		String hoy = LocalDateTime.now().getDayOfMonth() + "_" + LocalDateTime.now().getMonthValue() + "_"
				+ LocalDateTime.now().getYear();

		String nombreTXT = "Produccion_" + hoy + ".txt";

		try {

			URL url = new URL("https://www.el-tiempo.net/api/json/v2/provincias/41");
			HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
			conexion.connect();

			int respuesta = conexion.getResponseCode();

			if (respuesta != 200) {
				throw new RuntimeException("HttpResponse" + respuesta);
			} else {

				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					builder.append(sc.nextLine());
				}
				sc.close();

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		JSONObject jsonObj = new JSONObject(builder.toString());
		JSONObject jsonTemperaturas = jsonObj.getJSONArray("ciudades").getJSONObject(0).getJSONObject("temperatures");
		int tempMax = jsonTemperaturas.getInt("max");

		ArrayList<String> arrayChoco = new ArrayList<>();

		if (produccionActiva(tempMax) == false) {
			arrayChoco.add("Hoy " + hoy + "no se ha producido chocolate devido a que la temperatura a sido de "
					+ tempMax + " grados.");
		} else {
			Chocolate blanco = new Chocolate("Chocolate blanco", 1000,
					new Ingredientes("Manteca de cacao, leche, azúcar, grasa de leche y lecitina"));

			arrayChoco.add(blanco.toString());

			Chocolate negro = new Chocolate("Chocolate negro", 1500, new Ingredientes(
					"Pasta de cacao, azúcar, cacao desgrasado en polvo, manteca de cacao, emulgente: lecitina de soja."));

			arrayChoco.add(negro.toString());

			Chocolate almendras = new Chocolate("Chocolate con almendras", 1200, new Ingredientes(
					"Pasta de cacao, azúcar, cacao desgrasado en polvo, manteca de cacao, y almendras"));

			arrayChoco.add(almendras.toString());

			Chocolate castana = new Chocolate("Chocolate con castañas de caju", 1300, new Ingredientes(
					"Pasta de cacao, azúcar, cacao desgrasado en polvo, manteca de cacao, y castañas"));

			arrayChoco.add(castana.toString());

			Chocolate rama = new Chocolate("Chocolate en rama	", 100,
					new Ingredientes("Manteca de cacao, leche, azúcar, grasa de leche y lecitina"));

			arrayChoco.add(rama.toString());

			Chocolate cacao70 = new Chocolate("Chocolate con 70% de cacao", 1500, new Ingredientes(
					"Pasta de cacao, azúcar, cacao desgrasado en polvo, manteca de cacao, emulgente: lecitina de soja."));

			arrayChoco.add(cacao70.toString());

		}

		System.out.println("Temperatura: " + tempMax);
		System.out.println("Hoy se ha producido la siguiente cantidad de chocolate: ");
		for (int x = 0; x < arrayChoco.size(); x++) {
			System.out.println(arrayChoco.get(x).toString());
		}

		try {

			File fichero = new File(nombreTXT);

			if (!fichero.exists()) {
				Path file = Paths.get(nombreTXT);
				Files.write(file, arrayChoco, StandardCharsets.UTF_8);
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

		StringBuilder chocobuilder = new StringBuilder();
		chocobuilder.append("import java.time.LocalDate\r\n");
		chocobuilder.append("pipeline{\r\n");
		chocobuilder.append("agent any \r\n");
		chocobuilder.append("stages{ \r\n");
		chocobuilder.append("stage('AA13'){ \r\n");
		chocobuilder.append("steps{ \r\n");
		chocobuilder.append("script{ \r\n");

		chocobuilder.append("println \"Chocolates generados el " + hoy + "\"  \r\n");

		if (produccionActiva(tempMax) == false) {
			chocobuilder.append("println \"\"No se ha producido chocolate devido a que la temperatura a sido de "
					+ tempMax + " grados\"\r\n");
		} else {
			for (int x = 0; x < arrayChoco.size(); x++) {
				chocobuilder.append("println \"" + arrayChoco.get(x).toString() + "\"\r\n");
			}
		}

		chocobuilder.append("} \r\n");
		chocobuilder.append("} \r\n");
		chocobuilder.append("} \r\n");
		chocobuilder.append("} \r\n");
		chocobuilder.append("} \r\n");

		ArrayList<String> array = new ArrayList<>();

		try {
			array.add(chocobuilder.toString());
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

	private static boolean produccionActiva(int temperatura) {
		boolean produccion = false;

		if (temperatura >= 40) {
			produccion = false;
		} else {
			produccion = true;
		}

		return produccion;
	}

}
