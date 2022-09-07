package miguelRamirez_AA10;

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

/*GENERAR UN PROYECTO MAVEN QUE MUESTRE POR CONSOLA LA INFORMACION DEL CLIMA ACTUAL,
 *  ADEMAS DE MOSTRAR UN MENSAJE DE BIENVENIDA AL USUARIO, UNA VEZ INSERTADO
SU NOMBRE.
INTEGRAR EL NUEVO PROYECTO EN GITHUB BAJO EL NOMBRE DE EJERCICIO AA10
ENTREGABLE:
Codigo del proyecto
link del repositorio
*/

public class main_AA10 {

	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		Scanner scannerStr = new Scanner(System.in);
		ArrayList<String> array = new ArrayList<>();
		String temp, nombre;

		System.out.print("Indique su nombre: ");
		nombre = scannerStr.nextLine();
		System.out.println("");

		System.out.println("==========================================");
		System.out.println(" BIENVENIDO " + nombre.toUpperCase());
		System.out.println("==========================================");

		try {

			URL url = new URL("https://www.el-tiempo.net/api/json/v2/provincias/18");
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

		temp = builder.toString().replace(",", ",\n");
		temp = temp.replace("\"", "");
		temp = temp.replace("{", "");
		temp = temp.replace("}", "\n");
		System.out.println(temp);
	}

}
