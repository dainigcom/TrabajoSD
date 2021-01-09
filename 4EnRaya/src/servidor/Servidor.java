package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import juego.Partida;
import juego.Sala;

//El servidor permite conectar jugadores de dos en dos, genera salas conforme van llegando jugadores y cuando se completa una sala con los dos jugadores empezara su partida
public class Servidor {
	public static void main(String[] args) {
		//esta pool de hilos permite la ejecución simultánea de varias salas
		ExecutorService pool= Executors.newCachedThreadPool();
		ServerSocket server = null;
		Socket s = null;
		List<Partida> salas=new ArrayList<>();
		try {
			server = new ServerSocket(8080);
			System.out.println("Esperando jugadores....");
			while (true) {
				try{
					s=server.accept();
					Sala sala=new Sala(s,salas);
					pool.execute(sala);
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			pool.shutdown();
		}
	}
}
