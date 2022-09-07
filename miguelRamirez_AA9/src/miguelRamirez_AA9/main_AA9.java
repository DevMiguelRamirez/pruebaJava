package miguelRamirez_AA9;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*AA9
RELEVAMIENTO:
Nos contratan de una empresa de videojuegos para poder ayudarlos a desarrollar una APP que almacene 
la información de los titulos mas importantes que tienen hoy en día
Para esto, debemos desarrollar un programa que informe a los empleados los titulos de videojuegos 
y sus respectivas ventas con el monto total que viene acumulando
dichas ventas. 

ESTRUCTURA DEL PROGRAMA:
- Contar con una interfaz que calcule el monto total de los videojuegos y su recaudación
- TXT de salida que informe titulos, cantidades de unidades vendidas y monto total.
- Contar con una clase llamada videojuego que tenga atributos pertinentes a los videojuegos.

ENTREGABLES: 
- El proyecto debe estar publicado en Github
- Enviar el link del repo
- Enviar el codigo
- Enviar el TXT de salida.  


VIDEOJUEGOS:			CANTIDAD DE REGISTROS		PRECIO UNITARIO
DARK SOULS 3 				10000					5
THE LAST OF US 				50000					10
FIFA 2022					60000					15
MARIO BROSS					45000					1
DOOM 2					100000				1
HORIZON					50000					5
*/

public class main_AA9 {

	public static void main(String[] args) {

		Videojuego ds3 = new Videojuego("DARK SOULS 3", 10000, 5.0);
		Videojuego tlou = new Videojuego("THE LAST OF US", 50000, 10.0);
		Videojuego fifa = new Videojuego("FIFA 2022", 60000, 15.0);
		Videojuego mb = new Videojuego("MARIO BROSS", 45000, 1.0);
		Videojuego doom2 = new Videojuego("DOOM 2", 100000, 1.0);
		Videojuego horizon = new Videojuego("HORIZON: ZERO DAWN", 50000, 5.0);

		ArrayList<String> nuevoArray = new ArrayList<String>();
		nuevoArray.add(ds3.toString());
		nuevoArray.add(tlou.toString());
		nuevoArray.add(fifa.toString());
		nuevoArray.add(mb.toString());
		nuevoArray.add(doom2.toString());
		nuevoArray.add(horizon.toString());

		try {
			String nombreTXT = "ventas_videojuegos.txt";
			File fichero = new File(nombreTXT);

			if (!fichero.exists()) {
				Path file = Paths.get(nombreTXT);
				Files.write(file, nuevoArray, StandardCharsets.UTF_8);
				System.out.println("Fichero \"" + nombreTXT + "\" generado con exito.");
			}

			else {
				System.out.println("El archivo ya existe.");
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
