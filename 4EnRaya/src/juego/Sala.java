package juego;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Sala extends Thread{

	private Socket s;
	private List<Partida> salas= new ArrayList<>();
	
	//constructor que inicia los atributos
	public Sala(Socket s,List<Partida> salas2) {
		this.s=s;
		this.salas=salas2;
	}
	
	//el método run permite ir creando partidas cuando no hay disponibles, llenarla cuando solo tiene un jugador y empezar una partida cuando esta completa
	public void run() {
		//si no hay ninguna crea una primera partida con el jugador que llega
		if(salas.isEmpty()) {
			salas.add(new Partida(s));
		}
		else {
			boolean metido=false;
			int i=0;
			//recorre la lista hasta que encuentra una partida con hueco para el j2, lo añade y comienza la partida
			while(i<salas.size() && !metido) {
				if(salas.get(i).getJugador2()==null) {
					salas.get(i).añadirJugador(s);
					metido=true;
					System.out.println("Empieza la partida...");
					salas.get(i).jugar();
				}
				i++;
			}
			if(!metido) {
				//si no habia huecos crea nueva
				Partida p=new Partida(s);
				salas.add(p);
			}
		}
	}
}
