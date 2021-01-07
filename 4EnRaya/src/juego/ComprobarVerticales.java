package juego;

public class ComprobarVerticales extends Thread{
	
	private int[][] tablero;
	private boolean win;
	
	public ComprobarVerticales(int[][] tablero) {
		super();
		this.tablero=tablero;
		this.win=false;
	}
	
	public boolean getWin() {
		return this.win;
	}
	
	public void run() {
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
		if(fin==1) {
			win=true;
		}
	}
}
