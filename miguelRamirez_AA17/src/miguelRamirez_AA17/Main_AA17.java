package miguelRamirez_AA17;

import java.util.Scanner;

/*Generar un programa que funcione como un juego de adivinanzas, donde el usuario
tenga que adivinar un numero entre 1 y 1.000.000
Donde si adivina le damos un mensaje de "Adivinaste, campeon" y sino adivino que siga iterando hasta adivinar
teniendo el usuario hasta 5 intentos para adivinar, si excede este numero se le indica que perdio el juego. 
ESTRUCTURA DEL PROGRAMA:
Clase de usuario, clase principal, interfaz que implemente alguna variable y/o metodo
que el programa utilice utilizando lambda.

ENTREGABLES:
- Codigo del proyecto
- Captura de pantalla
- Subirlo al repositorio de Github
*/

public class Main_AA17 {

	public static void main(String[] args) {

		Scanner scannerStr = new Scanner(System.in);
		System.out.print("Ingrese su nombre: ");
		String nombre = scannerStr.nextLine();
		Jugador jugador = new Jugador(nombre);
		Juego prueba = (jugador1) -> {
			System.out.println("BIENVENIDO " + jugador.getNombreJugador().toUpperCase());
			System.out.println("Debes de intenta adivinar un numero entre 1 y 1000000");
			Scanner scannerInt = new Scanner(System.in);
			int numSecreto = (int) (Math.random() * 1000000 + 1);
			int intentos = 0;
			int numSeleccionado;
			boolean acertado = false;

			while (intentos < 5) {
				System.out.println("");
				if (intentos == 4) {
					System.out.println(numSecreto + " para que sera este numero?");
				}
				System.out.print("Introduce el numero: ");
				numSeleccionado = scannerInt.nextInt();

				if (numSeleccionado == numSecreto) {
					System.out.println("Numero correcto!");
					acertado = true;
					intentos = 5;
				} else {
					if (numSeleccionado < numSecreto && intentos < 4) {
						System.out.println("Prueba con un numero mas grande");
					}
					if (numSeleccionado > numSecreto && intentos < 4) {
						System.out.println("Prueba con un numero mas pequeño");
					}

					System.out.println("Intentos restantes: " + (4 - intentos));
				}
				intentos++;

			}
			if (acertado == false) {
				System.out.println("F, has perdido.");
			}
			scannerInt.close();

			return 0;
		};

		prueba.jugar(jugador);

	}

}
