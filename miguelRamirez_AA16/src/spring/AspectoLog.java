package spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class AspectoLog {

	
	
	@Before("execution(* logTXT())")
	public void logLeerTXT() {
		System.out.println("A continuacion procedera a leer el fichero TXT.");

	}

	@After("execution(* logTXT2())")
	public void logLeerTXT2() {
		System.out.println("Fichero leido con exito.");
	}

	@Before("execution(* logVerificar())")
	public void logVerificar() {
		System.out.println("Se procede a verificar el formato del archivo.");

	}

	@After("execution(* logVerificar2())")
	public void logVerificar2() {
		System.out.println("Archivo verificado.");
	}

	@Before("execution(* logGenerarTXT())")
	public void logGenerarTXT() {
		System.out.println("Se procede a generar el TXT.");

	}

	@After("execution(* logGenerarTXT2())")
	public void logGenerarTXT2() {
		System.out.println("TXT generado con exito.");
	}

}
