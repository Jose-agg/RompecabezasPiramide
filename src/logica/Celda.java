package logica;

public class Celda {

	private int valor;
	protected enum estados{VACIO,PREDEFINIDO,ROJO,AZUL,AMARILLO};
	private estados estado;
	
	protected Celda(){
		valor = 0;
		estado = estados.VACIO;
	}
	
	protected void setValor(int x){
		valor = x;
	}
	
	protected int getValor(){
		return valor;
	}

	protected void setEstado(estados var) {
		estado = var;
	}

	protected estados getEstado() {
		return estado;
	}
	
}
