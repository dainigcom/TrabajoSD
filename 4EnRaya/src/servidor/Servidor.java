package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import juego.Partida;

public class Servidor {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(8080,2);
			Socket s1 = null;
			Socket s2 = null;
			System.out.println("Esperando jugadores....");
			while (true) {
				try{
					if(s1==null) {
						s1=server.accept();
					}
					if(s2==null) {
						s2=server.accept();
						System.out.println("dos aceptados");
						Partida p= new Partida(s1,s2);
						p.jugar();
						System.out.println("El ganador es el jugador "+p.getGanador()+"!!!!!");
					}
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
