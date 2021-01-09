package juego;

import java.io.*;
import java.net.*;

public class Partida {

	private int columna;
	private Socket j1;
	private Socket j2;
	private int ganador;
	private int turno=1;
	private Tablero t;
	
	public Partida(Socket s) {
		this.columna=9;
		this.j1=s;
		this.j2=null;
		this.ganador=0;
		this.t=new Tablero();
	}
	
	public void añadirJugador(Socket s) {
		this.j2=s;		
	}
	
	public boolean terminado() {
		return this.t.terminado();
	}
	
	public Socket getJugador2() {
		return j2;
	}
	
	public int getGanador() {
		return this.ganador;
	}
	
	public void jugar() {
		System.out.println("entra");
		try (
			DataInputStream in1= new DataInputStream(j1.getInputStream());
			DataOutputStream out1= new DataOutputStream(j1.getOutputStream());
			DataInputStream in2= new DataInputStream(j2.getInputStream());
			DataOutputStream out2= new DataOutputStream(j2.getOutputStream());)
				
		{
			boolean fin=false;
			
			System.out.println("antes de bucle");
			
			out1.write(1);
			out2.write(2);
			out1.flush();
			out2.flush();
			
			out1.write(columna);
			while(!fin) {
				
				this.turno=1;
				System.out.println("Pone Jug 1");
				columna=in1.read();
				
				this.t.meterFicha(columna, this.turno);

				fin=terminado();
				
				if(!fin){
					this.turno=2;
					
					out2.write(columna);
					System.out.println("Pone Jug 2");
					columna=in2.read();
					this.t.meterFicha(columna, this.turno);
					
					fin=terminado();
					out1.write(columna);
				}		
			}
			this.ganador=this.turno;
			if(this.turno==1) {
				out2.write(columna);
			}
			System.out.println("Fin del juego!!! Ganador: Jugador "+this.ganador);
			
			
		}catch(IOException e) {
			System.out.println("error");
			e.printStackTrace();
			
		}
	}
	
	
}
