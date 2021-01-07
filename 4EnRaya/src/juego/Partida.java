package juego;

import java.io.*;
import java.net.*;

public class Partida {

	private Tablero t;
	private Socket j1;
	private Socket j2;
	private int ganador;
	
	public Partida(Socket s1, Socket s2) {
		this.t=new Tablero();
		this.j1=s1;
		this.j2=s2;
		this.ganador=0;
	}
	
	public boolean terminado() {
		return t.terminado();
	}
	
	public int getGanador() {
		return this.ganador;
	}
	
	public void jugar() {
		System.out.println("entra");
		try (
			ObjectInputStream in1= new ObjectInputStream(j1.getInputStream());
			ObjectOutputStream out1= new ObjectOutputStream(j1.getOutputStream());
			ObjectInputStream in2= new ObjectInputStream(j2.getInputStream());
			ObjectOutputStream out2= new ObjectOutputStream(j2.getOutputStream());)
				
		{
			boolean fin=false;
			
			System.out.println("antes de bucle");
			
			while(!fin) {
				
				t.setTurno(1);
				out1.writeObject(t);
				t=(Tablero) in1.readObject();
				
				
				fin=terminado();
				
				if(!fin){
					t.setTurno(2);
					
					out2.writeObject(t);
					t=(Tablero) in2.readObject();
					
					fin=terminado();
				}		
			}
			this.ganador=t.getTurno();
			
			
		}catch(IOException | ClassNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
			
		}
	}
	
	
}
