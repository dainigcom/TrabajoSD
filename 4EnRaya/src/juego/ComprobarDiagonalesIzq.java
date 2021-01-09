package juego;

public class ComprobarDiagonalesIzq extends Thread{
	
	private int[][] tablero;
	private boolean win;
	
	public ComprobarDiagonalesIzq(int[][] tablero) {
		super();
		this.tablero=tablero;
		this.win=false;
	}
	//devuelve si hay 4 en raya
	public boolean getWin() {
		return this.win;
	}
	
	//comprueba si hay 4 en raya en cualquier diagonal desde la derecha
	public void run() {
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
		if(fin==1) {
			win=true;
		}
	}
}
