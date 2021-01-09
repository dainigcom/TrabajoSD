package juego;
import java.io.Serializable;

public class Tablero implements Serializable{

	private int[][] tablero;
	
	//constructor que inicia la matriz del tablero
	public Tablero() {
		this.tablero = new int[6][7];
		for (int i=0; i<6; i++){
			 for (int j=0; j<7; j++){
				 this.tablero[i][j]=0;
			 }
		}

	}
	
	//muestra por pantalla el tablero
	public void mostrarTablero() {
		for (int i=0; i<6; i++){
			System.out.print("|");
			for (int j=0; j<7; j++){
				System.out.print(""+this.tablero[i][j]+"");
				System.out.print("|");
			}
			System.out.println();
		}
	}
	
	//comprueba si la partida ha terminado ya sea por victoria de algun jugador o po haber completado todo el tablero
	public boolean terminado() {
		boolean lleno=true;
		int i=0;
		if(win()) {
			return true;
		}
		else {
			while(i<7 && lleno) {
				if(this.tablero[0][i]==0) {
					lleno=false;
				}
				i++;
			}
			return lleno;
		}
	}
	
	//comprueba si alguien ha ganado
	public boolean win() {
		boolean victoria=false;
		try {
			//se crean los objetos para comprobar, que son clases que extienden thread para poder ejecutarse concurrentemente y ahorrar tiempo en la medida de lo posible
			ComprobarVerticales cv= new ComprobarVerticales(this.tablero);
			ComprobarHorizontales ch= new ComprobarHorizontales(this.tablero);
			ComprobarDiagonalesDcha cdd= new ComprobarDiagonalesDcha(this.tablero);
			ComprobarDiagonalesIzq cdi= new ComprobarDiagonalesIzq(this.tablero);
			
			//se inician
			cv.start();
			ch.start();
			cdd.start();
			cdi.start();

			//esperan a terminar todos
			cv.join();
			ch.join();
			cdd.join();
			cdi.join();
			
			//si ha habido 4 en raya en cualquier direccion devuelve true
			victoria= cv.getWin()||ch.getWin()||cdd.getWin()||cdi.getWin();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return victoria;
	}
	
	//mete una ficha del jugador correspondiente en la columna indicada, cae hasta la fila mas baja que no tenga ficha
	public boolean meterFicha(int columna,int numJug) {
		
		if(this.tablero[0][columna]!=0) {
			return false;
		}
		else {
			int e=0;
			while(this.tablero[e][columna]==0 && e<5) {
				e++;
			}
			if (e==5 && this.tablero[e][columna]==0) {
				e++;
			}
			this.tablero[e-1][columna]=numJug;
			return true;
		}
		
	}
}
