package es.gimbernat;
import java.util.Scanner;

public class Utils {
	
	/**
	 * Pregunta un número entero haciendo la pregunta que se indica
	 * @param label Pregunta que se muestra al usuario
	 * @return Número entero introducido por el usuario
	 */
	public static int askInteger(String label)
	{
		Scanner s = new Scanner(System.in);
		System.out.println(label);
		return Integer.parseInt(s.nextLine());
	}
	
	/**
	 * Pregunta un texto haciendo la pregunta que se indica
	 * @param label Pregunta que se muestra al usuario
	 * @return Texto introducido por el usuario
	 */
	public static String askText(String label)
	{
		Scanner s = new Scanner(System.in);
		System.out.println(label);
		return s.nextLine();
	}
	
	/**
	 * Hace la pregunta de sí o no al usuario utilizando el texto indicado
	 * @param label Pregunta que se muestra al usuario
	 * @return True si el usuario escribe S o s. False si escribe N o n
	 */
	public static boolean askFlag(String label)
	{
		Scanner s = new Scanner(System.in);
		String opcion;
		do
		{
			System.out.println(label);
			System.out.println("S / N");
			opcion = s.nextLine();
		}while(!opcion.contains("S") && !opcion.contains("N") && !opcion.contains("s") && !opcion.contains("n"));
		if (opcion.contains("S") || opcion.contains("s")) return true;
		else return false;
	}
	
	/**
	 * Pregunta al usuario entre las diferentes alternativas dadas por un enum
	 * @param <T> Enum
	 * @param label Pregunta que se hace al usuario
	 * @param enumClass Enum sobre el que se pregunta
	 * @return Opción del enum escogida por el usuario
	 */
	public static <T extends Enum<T>> T askEnum(String label, Class<T> enumClass) {
	    T[] enumValues = enumClass.getEnumConstants();
	    int opc;
	    do {
	        for (int i = 0; i < enumValues.length; i++) {
	            System.out.println((i + 1) + ". " + enumValues[i]);
	        }
	        opc = Utils.askInteger(label);
	    } while (opc < 1 || opc > enumValues.length);
	    return enumValues[opc - 1];
	}

	/**
	 * Muestra al usuario las opciones de un menú y le pide escoger una opción
	 * @param title Título del menú
	 * @param options Opciones del menú
	 * @return La opción escogida. En caso de escoger la opción de Salir o Volver se retorna un 0.
	 */
	public static int menu(String title, String[] options)
	{
		System.out.println(title);
		return menu(options);
	}
	
	/**
	 * Muestra al usuario las opciones de un menú y le pide escoger una opción
	 * @param options Opciones del menú
	 * @return La opción escogida. En caso de escoger la opción de Salir o Volver se retorna un 0.
	 */
	public static int menu(String[] options)
	{
		int opc = -1;
		do
		{
			for (int i = 0; i < options.length; i++) {
				System.out.println((i+1)+". "+options[i]);
			}
			opc = askInteger("Choose: ");
		}while(opc<1 || opc>options.length);
		if (opc==options.length) 
			opc = 0;
		return opc;
	}
	
	/**
	 * Si flag es true, se muestra el msgOK por consola. Si es false, se muestra msgKO
	 * @param flag 
	 * @param msgOK
	 * @param msgKO
	 */
	public static void mostrarResultadoOperacion(boolean flag, String msgOK, String msgKO)
	{
		if (flag)
			System.out.println(msgOK);
		else
			System.out.println(msgKO);
	}
}
