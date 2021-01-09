package juego;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Sala extends Thread{

	private Socket s;
	private List<Partida> salas= new ArrayList<>();
	
	public Sala(Socket s,List<Partida> salas2) {
		this.s=s;
		this.salas=salas2;
	}
	
	public void run() {
		if(salas.isEmpty()) {
			salas.add(new Partida(s));
		}
		else {
			boolean metido=false;
			int i=0;
			while(i<salas.size() && !metido) {
				if(salas.get(i).getJugador2()==null) {
					salas.get(i).añadirJugador(s);
					metido=true;
					salas.get(i).jugar();
				}
				i++;
			}
			if(!metido) {
				Partida p=new Partida(s);
				salas.add(p);
			}
		}
	}
}
