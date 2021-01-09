package juego;
import java.io.Serializable;

public class Tablero implements Serializable{

	private int[][] tablero;
	private int turno;
	
	public Tablero() {
		this.tablero = new int[6][7];
		for (int i=0; i<6; i++){
			 for (int j=0; j<7; j++){
				 this.tablero[i][j]=0;
			 }
		}
		this.turno=1;
	}
	
	public int getTurno() {
		return this.turno;
	}
	
	public void setTurno(int turno) {
		this.turno=turno;
	}
	
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
	
	public boolean win() {
		boolean victoria=false;
		try {
			ComprobarVerticales cv= new ComprobarVerticales(this.tablero);
			ComprobarHorizontales ch= new ComprobarHorizontales(this.tablero);
			ComprobarDiagonalesDcha cdd= new ComprobarDiagonalesDcha(this.tablero);
			ComprobarDiagonalesIzq cdi= new ComprobarDiagonalesIzq(this.tablero);
			
			
			cv.start();
			ch.start();
			cdd.start();
			cdi.start();

			cv.join();
			ch.join();
			cdd.join();
			cdi.join();
			
			victoria= cv.getWin()||ch.getWin()||cdd.getWin()||cdi.getWin();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		return victoria;
	}
	
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
