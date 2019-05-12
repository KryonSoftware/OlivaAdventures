package olivaAdventures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Puntuaciones {

	/* --- VARIABLES --- */
	private String nombre;											// Nombre escogido en la ultima partida.
	private int puntuacion;											// Puntuacion realizada en la ultima partida.
	private String name;											// Nombre que ya estaba en el top.
	private String score;											// Puntuacion que ya estaba en el top.


	private String path = System.getProperty("os.name").equals("Linux")? "/tmp/puntuaciones.txt" : "C:\\Windows\\Temp";

	private String pathEsc=getPathEsc();

	private String getPathEsc() {
		
		String puth="";
		
		switch(System.getProperty("os.name")) {
		case "Linux":
			puth="/tmp/puntuaciones.txt";
			break;
		default:
			puth="C:\\Windows\\Temp";	
		}
		
		return puth;
	}
	private String linea;											// Variable donde se guarda la lectura de la linea del archivo de las puntuaciones: *path*.
	private String nuevaPuntuacion;									// Puntuaciones que toquen guardarse en el top.
	public int p = 5;												// Puntuaciones totales que se escribiran (esta variable solo se modificara desde aqui).
	private int c;													// Contador de las lineas leidas/escritas.
	private String [] topProv = new String [p];						// Top provisional: aqui se guardaran las puntuaciones despues de comparar la nueva puntuacion.
	private String [] topDef = new String [p];						// Top definitivo: aqui si guardaran las puntuaciones despues de ordenar definitivamente el *topProv*.
	private String valorPorDefecto = "XXX000000";

	private boolean nuevaPuntuacionEscrita = false;					// Permite que NO se escriba 2 veces  la nueva puntuacion.
	private boolean soloUnUso = false;								// Permite activar un *if* concreto solo una vez.
	private boolean puntuacionNoEs_0 = true;						// Indica si es necesario retocar o no el *.txt*.
	private boolean activaSout = true;								// Aqui indicaremos si queremos activar los *System.out.print()* de ayuda (esta variable solo se modificara desde aqui).

	private enum comprobar {

		linea,
		nombre,
		puntuacion;
	}


	/* --- CONSTRUCTOR --- */
	public Puntuaciones(String nombre, int puntuacion) {

		this.nombre = nombre.toUpperCase();
		this.puntuacion = puntuacion;

		comprobacion(comprobar.nombre);
		comprobacion(comprobar.puntuacion);

		actualizarTablaPuntuaciones();

	}

	/* --- METODOS PRINCIPALES --- */
	private void actualizarTablaPuntuaciones() {

		/* Leemos las puntaciones del top y las comparamos con la nueva puntuacion. 
		Si la nueva puntuacion supera alguna de las del top, esta se introduce en la
		posicion correspondiente y desplaza las puntuaciones viejas posteriores.
		Guardamos el nuevo top de puntuaciones en un array (topProv) y luego lo ordenamos
		en el top definitivo (topDef). */
		lectura();

		/* Escribimos las puntuaciones del array *topDef* si la puntuacion realizada NO es 0.
		En ese caso no seria necesario tocar el archivo puesto que no superaria a ninguna. */
		if (puntuacionNoEs_0 == true) {
			escritura();
		}
		else if (activaSout == true) System.out.println(">> ARCHIVO SIN EDITAR << La puntuacion realizada es 0, no hay cambio en la tabla de puntuaciones.");
	}

	private void lectura() {

		try {

			BufferedReader br;
			
			try {
				
				br = new BufferedReader(new FileReader(path));
				linea = br.readLine();
				
			}catch(Exception e) {
				
				br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Menu/puntuaciones.txt"),"UTF-8"));
				linea = br.readLine();
			}
			
			comprobacion(comprobar.linea);

			for (c=0 ; c<p ; c++) {								// Usamos un bucle *for* porque la tabla de puntuaciones siempre constara de las lineas que decidamos.
				compararNuevaPuntuacion();
				linea = br.readLine();
				comprobacion(comprobar.linea);
			}
			br.close();
		}

		catch (FileNotFoundException e) {						// En caso de no encontrar el fichero con las puntuaciones:
			escrituraPorDefecto();
		}

		catch(IOException e) {
			System.out.println("No se puede abrir el fichero");
		}
	}

	private void compararNuevaPuntuacion() {

		name = cogerCadena(linea, 0, 3);														// Guardamos los 3 primeros caracteres que corresponden al nombre en *name*.
		score = cogerCadena(linea, 3, 9);														// Guardamos los 6 ultimos caracteres que corresponden a la puntuacion en *score*.
		nuevaPuntuacion = escogerNuevaPuntuacion();												// Decidimos (linea por linea) que puntuacion se escribira a continuacion (la de la ultima partida o la ya existente en la actual linea).
		topProv[c] = nuevaPuntuacion;															// Guardamos la puntuacion escogida en el array *topProv* por orden.

		if ((nuevaPuntuacionEscrita == true) && (soloUnUso == false) && (c != (p-1))) {			// La parte (c != (p-1)) no permite que se ejecute este if si ya estan todas puntuaciones requeridas escritas (por ejemplo: para que no haya 11 lineas especificando 10.).
			c++;																				// Como escribimos 2 lineas si entramos en este *if*, acortamos en 1 el bucle.
			topProv[c] = linea;																	// Escribimos la puntuaci�n que baja de ranking debajo de la *nuevaPuntuacion*.
			soloUnUso = true;																	// Bloqueamos este *if* para no volver a entrar en el.
		}
	}

	private void escritura() {

		try {

			if (nuevaPuntuacionEscrita == true) {			// Comprobamos si es necesario (otra vez) reescribir el *.txt*. En este caso, se retocara si hay cambio en el orden.

				BufferedWriter bw = new BufferedWriter(new FileWriter(pathEsc));

				ordenarTopProv();
				for (int i=0 ; i<p ; i++) {					// Escribimos las puntuaciones en orden.
					if (i != (p-1)) {						// Escribe todas las puntuaciones (menos la ultima) con un salto de linea.
						bw.write(topDef[i]);
						bw.newLine();
					}
					else bw.write(topDef[i]);				// Escribe la ultima sin salto de linea.
				}

				bw.close();
				if (activaSout == true) System.out.println(">> TABLA DE PUNTUACIONES MODIFICADA << La puntuacion de la ultima partida ha entrado en el top.");
				//bw.flush();
			}
			else if (activaSout == true) System.out.println(">> ARCHIVO SIN EDITAR << La puntuacion realizada no supera el ultimo puesto del top, aprende a jugar.");
		}

		catch(IOException e) {
			System.out.println("Fichero no encontrado.");
		}
	}


	/* --- METODOS SECUNDARIOS --- */
	public String cogerCadena(String cadena, int posicion_inicial, int posicion_final) {

		String nombreTabla = "";									// Este metodo devuelve un conjunto de caracteres concretos a partir de un String.

		for (int i=posicion_inicial ; i<posicion_final ; i++) {
			nombreTabla = (nombreTabla + cadena.charAt(i));
		}
		return nombreTabla;
	}

	private String escogerNuevaPuntuacion() {

		String mejorPuntuacion;																// Este metodo compara (mediante integers) las puntuaciones.

		if (puntuacion > Integer.parseInt(score) && (nuevaPuntuacionEscrita == false)) {	// Si la puntuacion realizada es mayor que la almacenada:
			nuevaPuntuacion = anyadeCeros(puntuacion);										// - Le anyadimos los ceros correspondientes a la puntuacion para que quede una cadena de 6 cifras.
			mejorPuntuacion = nombre + nuevaPuntuacion;										// - Marcamos esta puntuacion como la que devuelve el metodo (nombre + puntuacion).
			nuevaPuntuacionEscrita = true;
		}
		else mejorPuntuacion = name + score;												// Si la puntuacion realizada NO es mayor que la leida, reescribe la que ya habia.

		return mejorPuntuacion;

	}

	private String anyadeCeros(int puntuacion) {

		String puntuacionConCeros = "";									// Este metodo deja la puntuacion que se tenga que escribir siempre con 6 cifras

		if ((puntuacion <= 999999) && (puntuacion >= 100000)) {			// 999.999 - 100.000
			puntuacionConCeros = "" + puntuacion;
		}
		else if ((puntuacion <= 99999) && (puntuacion >= 10000)) {		// 99.999 - 10.000
			puntuacionConCeros = "0" + puntuacion;
		}
		else if ((puntuacion <= 9999) && (puntuacion >= 1000)) {		// 9.999 - 1.000
			puntuacionConCeros = "00" + puntuacion;
		}
		else if ((puntuacion <= 999) && (puntuacion >= 100)) {			// 999 - 100
			puntuacionConCeros = "000" + puntuacion;
		}
		else if ((puntuacion <= 99) && (puntuacion >= 10)) {			// 99 - 10
			puntuacionConCeros = "0000" + puntuacion;
		}
		else if ((puntuacion <= 9) && (puntuacion >= 1)) {				// 9 - 1
			puntuacionConCeros = "00000" + puntuacion;
		}

		return puntuacionConCeros;
	}

	private void ordenarTopProv() {

		int contador = 0;								// Este metodo reordena las puntuaciones en caso de que alguna puntuacion no tenga un formato valido (las iguala a *XXX000000* y las envia al final).

		for (int i=0 ; i<p ; i++) {						// Leemos todo el *topProv* y lo reescribimos en *topDef* ya ordenado definitivamente.
			linea = topProv[i];
			if (!linea.equals(valorPorDefecto)) {
				topDef[i-contador] = linea;
			}
			else contador++;
		}
		for (int i=0 ; i<p ; i++) {						// Llenamos las ultimas lineas del *topDef* con los valores por defecto removidos anteriormente.
			if (topDef[i] == null) {
				topDef[i] = valorPorDefecto;
			}
		}
	}

	private void comprobacion(comprobar comprobar) {

		if (comprobar.equals(comprobar.linea)) {
			if (linea == null) {										// Comprobamos si la linea esta vacia, en este caso introduce el valor por defecto anyadiendo un salto de linea al principio.
				linea = "\n" + valorPorDefecto;
			}
			if ((!linea.matches("[A-Z]{3}[0-9]{6}"))) {					// Comprobamos que la linea que se lea contenga un nombre y puntuacion en el formato valido.
				linea = valorPorDefecto;								// Si no los contiene, la linea toma el valor por defecto de: *XXX000000*.
			}
		}

		else if (comprobar.equals(comprobar.nombre)) {
			if (!nombre.matches("[A-Z]{3}")) {	
				if (activaSout == true) System.out.println(">> NOMBRE RESTABLECIDO << El nombre introducido no esta dentro de los parametros aceptados, se establecera por defecto *DAM*.");
				nombre = "DAM";								
			}
		}

		else if (comprobar.equals(comprobar.puntuacion)) {
			if ((!(puntuacion > 0)) || (!(puntuacion <= 999999))) {
				if (activaSout == true) System.out.println(">> ARCHIVO SIN EDITAR << La puntuacion introducida no esta dentro de los parametros aceptados, se igualara a 0.");
				puntuacion = 0;
				puntuacionNoEs_0 = false;
			}
		}

	}

	private void escrituraPorDefecto() {

		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(path));

			for (int i=0 ; i<p ; i++) {						// Escribimos las puntuaciones por defecto que sean necesarias.
				if (i != (p-1)) {							// Escribe todas las puntuaciones (menos la ultima) con un salto de linea.
					bw.write(valorPorDefecto);
					bw.newLine();
				}
				else bw.write(valorPorDefecto);					// Escribe la ultima sin salto de linea.
			}
			bw.close();
			if (activaSout == true) System.out.println(">> TABLA DE PUNTUACIONES CREADA << No se ha encontrado el fichero en el path especificado, se ha creado uno nuevo con valores por defecto.");
			lectura();										// - Vuelve a realizar la lectura, esta vez encontrara un fichero con puntuaciones y realizara el camino normal.
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}

// String.toString(IntegerAstring);
// Integer.parseInt(StringAinteger);
// http://jmoral.es/blog/IO-java