package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Backtraking {
	
	private int numLineas;
	private Celda[][] piramide;
	private boolean FIN;
	
	private int rojo[]={-1,-1};
	private int azul[]={-1,-1};
	private int amarillo[]={-1,-1};
	
	private int rojo2[]={-1,-1};
	private int azul2[]={-1,-1};
	private int amarillo2[]={-1,-1};
	
	/**
	 * Manejador de todo el Backtraking
	 * 
	 * @param nombreFichero
	 */
	public void backtraking(String nombreFichero, boolean salidaFichero){
		cargarFichero(nombreFichero);
		if(salidaFichero) pintarOriginal(nombreFichero);
		cabezaPensante(0,0);
		pintarResultado();
	}
	
	/**
	 * Metodo que se encarga de "pensar" la solucion
	 * 
	 * @param altura
	 * @param columna
	 */
	private void cabezaPensante(int altura, int columna) {
		if(altura == numLineas) FIN = true;
		else
			for(int i=1; i<=9; i++)
				if(!FIN && esPosible(i,altura,columna) ){
					asignarValor(i,altura,columna);
					if(columna == numLineas-altura-1) cabezaPensante(altura+1,0);
					else cabezaPensante(altura,columna+1);
				}
	}
	
	/**
	 * Metodo que se encarga de introducir el valor en la celda segun su estado
	 * 
	 * @param i
	 * @param altura
	 * @param columna
	 */
	private void asignarValor(int i, int altura, int columna) {
		Celda celda = piramide[altura][columna];
		switch (celda.getEstado()){
		case VACIO:
			celda.setValor(i);
			break;
		case ROJO:
			if(altura==rojo[0] && columna==rojo[1])
				celda.setValor(i);
			else{
				if(piramide[rojo[0]][rojo[1]].getValor()==i) celda.setValor(i);
				else System.out.println("Ha fallado");
			}
			break;
		case AZUL:
			if(altura==azul[0] && columna==azul[1])
				celda.setValor(i);
			else{
				if(piramide[azul[0]][azul[1]].getValor()==i) celda.setValor(i);
				else System.out.println("Ha fallado");
			}
			break;
		case AMARILLO:
			if(altura==amarillo[0] && columna==amarillo[1])
				celda.setValor(i);
			else{
				if(piramide[amarillo[0]][amarillo[1]].getValor()==i) celda.setValor(i);
				else System.out.println("Ha fallado");
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Metodo que se encarga de probar si el numero que se le pasa como parametro cumple una serie de condiciones para la posicion que se requiere
	 * 
	 * @param i
	 * @param altura
	 * @param columna
	 * @return
	 */
	private boolean esPosible(int i, int altura, int columna) {
		// Si es una celda ya dada por el fichero y el valor no es el mismo
		if(piramide[altura][columna].getEstado()==Celda.estados.PREDEFINIDO && i != piramide[altura][columna].getValor()) return false;
		
		// Comprueba que el numero es una combinacion valida
		if(altura>0){
			int hijoIzquierdo = piramide[altura-1][columna].getValor();
			int hijoDerecho = piramide[altura-1][columna+1].getValor();
			if(combinaciones(i,hijoIzquierdo,hijoDerecho)) return false;
		}
		
		// Comprueba que la suma o resta entre dos celdas este el intevalo [1,9]
		// Comprueba que el numero es valido si la hija esta predefinida y la celda previa ya ha sido rellenada
		if(columna > 0){
			Celda hermanaIzq = piramide[altura][columna-1];
			Celda hija = piramide[altura+1][columna-1];
			int suma = i + hermanaIzq.getValor();
			int resta = Math.abs(i-hermanaIzq.getValor());
			if(hermanaIzq.getValor()>=5 && hermanaIzq.getValor()==i) return false;
			if(hermanaIzq.getValor()==9 && i==9) return false;
			if(suma>9 && resta <1) return false;
			if(hija.getEstado()==Celda.estados.PREDEFINIDO)
				if(combinaciones(hija.getValor(),i,hermanaIzq.getValor())) return false;
		}

		// Comprueba que el numero es valido si las celdas hija y hermana derecha estan predefinida
		if(columna < piramide[altura].length-1){
			Celda hija = piramide[altura+1][columna];
			Celda hermanaDrch = piramide[altura][columna+1];
			if(hija.getEstado()==Celda.estados.PREDEFINIDO && hermanaDrch.getEstado()==Celda.estados.PREDEFINIDO)
				if(combinaciones(hija.getValor(),i,hermanaDrch.getValor())) return false;
		}
		
		// Comprueba que el numero es valido teniendo el cuenta el color de la celda
		Celda celda = piramide[altura][columna];
		if(celda.getEstado()!=Celda.estados.VACIO && celda.getEstado() !=Celda.estados.PREDEFINIDO){
			int x=-1,y=-1;
			switch(celda.getEstado()){
			case ROJO:
				x = rojo[0];
				y = rojo[1];
				break;
			case AZUL:
				x = azul[0];
				y = azul[1];
				break;
			case AMARILLO:
				x = amarillo[0];
				y = amarillo[1];
			default:
				break;
			}
			if(altura==x && columna==y) return true;
			else {
				if(piramide[x][y].getValor()==i) return true;
				else return false;
			}
		}
		return true;
	}
	
	/**
	 * Metodo auxiliar que comprueba que se cumplen las condiciones de suma o resta entre padres e hijos
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private boolean combinaciones(int x, int y, int z){
		return (x != y+z && x != Math.abs(y-z))?true:false;
	}

	// --------------- METODOS PARA LEER EL FICHERO --------------- \\\
	
	/**
	 * Metodo que carga la piramide del fichero
	 * 
	 * @param nombre
	 */
	private void cargarFichero(String nombre){
		BufferedReader fichero;
		String actual;
		String[] valores;
		int altura;
		boolean[] primeroColocado = new boolean[3];	//R=0, B=1, Y=2
		try {
			fichero = new BufferedReader(new FileReader(nombre));
			actual=fichero.readLine();
			numLineas=Integer.parseInt(actual);
			piramide = new Celda[numLineas][];
			
			for(int i=numLineas-1; i>=0; i--)
				piramide[i] = new Celda[numLineas-i];
			
			for(int i=0; i<numLineas; i++)
				for(int j=0; j<piramide[i].length; j++)
					piramide[i][j]= new Celda();
			
			altura = numLineas -1;
			
			while(fichero.ready()){
				actual = fichero.readLine();
				valores = actual.split(" ");
				
				for(int i=valores.length-1; i>=0; i--){
					switch (valores[i]){
					case "R":
						if(!primeroColocado[0]){
							primeroColocado[0]=true;
							rojo2[0]=altura;
							rojo2[1]=i;
						}else{
							rojo[0]=altura;
							rojo[1]=i;
						}
						piramide[altura][i].setEstado(Celda.estados.ROJO);
						break;
					case "B":
						if(!primeroColocado[1]){
							primeroColocado[1]=true;
							azul2[0]=altura;
							azul2[1]=i;
						}else{
							azul[0]=altura;
							azul[1]=i;
						}
						piramide[altura][i].setEstado(Celda.estados.AZUL);
						break;
					case "Y":
						if(!primeroColocado[2]){
							primeroColocado[2]=true;
							amarillo2[0]=altura;
							amarillo2[1]=i;
						}else{
							amarillo[0]=altura;
							amarillo[1]=i;
						}
						piramide[altura][i].setEstado(Celda.estados.AMARILLO);
						break;
					default:
						if(esNumero(valores[i])){
							piramide[altura][i].setValor(Integer.parseInt(valores[i]));
							piramide[altura][i].setEstado(Celda.estados.PREDEFINIDO);
						}
						else piramide[altura][i].setValor(0);
						break;
					}				
				}
				altura--;
			}
			fichero.close();
			FIN=false;

		} catch (FileNotFoundException fnfe) {
			System.out.println("El nombre del fichero no coincide con un archivo existente");
		} catch (IOException ioe) {
			new RuntimeException("Fallo dentro del archvio.");
		}
	}
	
	/**
	 * Metodo auxiliar que comprueba que lo que se pase por parametro sea un numero
	 * 
	 * @param s
	 * @return
	 */
	private boolean esNumero(String s){
		try{
			@SuppressWarnings("unused")
			int aux = Integer.parseInt(s);
			return true;
		}catch(NumberFormatException  ioe){
			return false;
		}
	}
	
	
	// --------------- METODOS PARA PINTAR LAS PIRAMIDES --------------- \\\
	
	/**
	 * Metodo que se encarga de pintar la piramide Original
	 * 
	 * @param nombreFichero
	 */
	private void pintarOriginal(String nombreFichero){
		String[] ruta = nombreFichero.split("/");
		System.out.println("Piramide original leida del fichero: "+ruta[2]);
		int cont = -1;
		for(int i=numLineas-1; i>=0; i--){
			for(int j=0; j<piramide[i].length; j++){
				if(j==0){
					pintarTabuladores(piramide.length-cont++);
					System.out.print("|");
				}
				switch (piramide[i][j].getEstado()){
				case ROJO:
					System.out.print(" R |");
					break;
				case AZUL:
					System.out.print(" B |");
					break;
				case AMARILLO:
					System.out.print(" Y |");
					break;
				case VACIO:
					System.out.print(" * |");
					break;
				default:
					System.out.print(" "+piramide[i][j].getValor()+" |");
					break;
				}
			}
			System.out.println();
		}
	}

	/**
	 * Metodo que pinta la piramide Resuelta
	 */
	private void pintarResultado(){
		System.out.println("\nSolucion:");
		int cont = -1;
		for(int i=numLineas-1; i>=0; i--){
			for(int j=0; j<piramide[i].length; j++){
				if(j==0){
					pintarTabuladores(piramide.length-cont++);
					System.out.print("|");
				}
				System.out.print(" "+piramide[i][j].getValor()+" |");
			}
			System.out.println();	
		}
	}
	
	/**
	 * Metodo auxiliar que pinta las tabulaciones para que aparezca en forma de piramide
	 * 
	 * @param numero
	 */
	private void pintarTabuladores(int numero){
		if(numero>=0){
			System.out.print("  ");
			numero--;
			pintarTabuladores(numero);
		}
	}
}