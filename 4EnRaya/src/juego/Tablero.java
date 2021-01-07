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
	
	public boolean comprobarVerticales() {
		int i=0;
		int e=0;
		int cont=0;
		int prev=0;
		int fin=0;
		while(e<7 & fin==0) {
			i=0;
			if(this.tablero[i][e]!=0) {
				cont=1;
			}
			while(i<6 & cont<4) {
				
				if(this.tablero[i][e]!=0 && cont==0) {
					cont=1;
				}else {
					if(this.tablero[i][e]==prev & prev!=0) {
						cont++;
					}else {
						cont=1;
					}
				}
				
				prev=this.tablero[i][e];
				i++;
			}
			if(cont==4) {
				fin=1;
			}
			cont=0;
			prev=0;
			e++;
		}
		
		return (fin==1);
	}
	
	public boolean comprobarHorizontales() {
		int i=0;
		int e=0;
		int cont=0;
		int prev=0;
		int fin=0;
		while(i<6 & fin==0 ) {
			e=0;
			if(this.tablero[i][e]!=0) {
				cont=1;
			}
			while(e<7 & cont<4) {
				
				if(this.tablero[i][e]!=0 && cont==0) {
					cont=1;
				}else {
					if(this.tablero[i][e]==prev & prev!=0) {
						cont++;
					}else {
						cont=1;
					}
				}
				
				prev=this.tablero[i][e];
				e++;
			}
			if(cont==4) {
				fin=1;
			}
			cont=0;
			prev=0;
			i++;
		}
		
		return (fin==1);
	}

	public boolean comprobarDiagonalesDerecha() {
		int i=3;
		int e=1;
		int ii=0, ee=0;
		int cont=0;
		int prev=0;
		int fin=0;
		while(i<6 & fin==0 & ii>=0 & ee<7) {
			ee=0;
			ii=i;
			if(this.tablero[ii][ee]!=0) {
				cont=1;
			}
			while(ii>=0 & cont<4) {
				if(this.tablero[ii][ee]!=0 && cont==0) {
					cont=1;
				}else {
					if(this.tablero[ii][ee]==prev & prev!=0) {
						cont++;
					}else {
						cont=1;
					}
				}
				prev=this.tablero[ii][ee];
				ee++;
				ii--;
			}
			if(cont==4) {
				fin=1;
			}
			cont=0;
			prev=0;
			i++;
		}
		System.out.println(i);
		System.out.println(fin);
		while(e<7 & fin==0) {
			ee=e;
			ii=5;
			if(this.tablero[ii][ee]!=0) {
				cont=1;
			}
			while(e<7 & cont<4 & ii>=0 & ee<7) {
				if(this.tablero[ii][ee]!=0 && cont==0) {
					cont=1;
				}else {
					if(this.tablero[ii][ee]==prev & prev!=0) {
						cont++;
					}else {
						cont=1;
					}
				}
				prev=this.tablero[ii][ee];
				ee++;
				ii--;
			}
			if(cont==4) {
				fin=1;
			}
			cont=0;
			prev=0;
			e++;
		}
		
		return (fin==1);
	}
	
	public boolean comprobarDiagonalesIzquierda() {
		int i=3;
		int e=5;
		int ii=0, ee=0;
		int cont=0;
		int prev=0;
		int fin=0;
		while(i>=0 & fin==0 & ii>=0 & ee>=0) {
			ee=6;
			ii=i;
			if(this.tablero[ii][ee]!=0) {
				cont=1;
			}
			while(ii>=0 & cont<4) {
				if(this.tablero[ii][ee]!=0 && cont==0) {
					cont=1;
				}else {
					if(this.tablero[ii][ee]==prev & prev!=0) {
						cont++;
					}else {
						cont=1;
					}
				}
				prev=this.tablero[ii][ee];
				ee--;
				ii--;
			}
			if(cont==4) {
				fin=1;
			}
			cont=0;
			prev=0;
			i++;
		}
		System.out.println(i);
		System.out.println(fin);
		while(e<7 & fin==0) {
			ee=e;
			ii=5;
			if(this.tablero[ii][ee]!=0) {
				cont=1;
			}
			while(e<7 & cont<4 & ii>=0 & ee>=0) {
				if(this.tablero[ii][ee]!=0 && cont==0) {
					cont=1;
				}else {
					if(this.tablero[ii][ee]==prev & prev!=0) {
						cont++;
					}else {
						cont=1;
					}
				}
				prev=this.tablero[ii][ee];
				ee--;
				ii--;
			}
			if(cont==4) {
				fin=1;
			}
			cont=0;
			prev=0;
			e++;
		}
		
		return (fin==1);
	}
}
