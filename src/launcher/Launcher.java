package launcher;

import logica.Backtraking;


public class Launcher {

	public static void main(String[] args) {
		Backtraking back = new Backtraking();
		long tInicio, tFin;
		
		for(int i=1 ; i< 18 ; i++){
			tInicio = System.currentTimeMillis();
			back.backtraking("src/ficheros/caso"+i+".txt",false);
			tFin = System.currentTimeMillis();
			System.out.println("\nTiempo empleado: " + (tFin-tInicio)+" milisegundos -//- "+(tFin-tInicio)/1000+" segundos");
			System.out.println("\n################################################\n");
		}
	}

}
