package juego;

public class ComprobarHorizontales extends Thread{
	
	private int[][] tablero;
	private boolean win;
	
	public ComprobarHorizontales(int[][] tablero) {
		super();
		this.tablero=tablero;
		this.win=false;
	}
	
	//devuelve si hay 4 en raya
	public boolean getWin() {
		return this.win;
	}
	
	//comprueba si hay 4 en raya en cualquier horizontal
	public void run() {
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
		if(fin==1) {
			win=true;
		}
	}
}
