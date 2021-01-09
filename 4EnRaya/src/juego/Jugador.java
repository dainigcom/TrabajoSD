package juego;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Jugador {
	
	
	
	public static void main(String[] args) {
		
		Tablero t= new Tablero();
		int columna;
		int numJug;
		int numContrario;
		
		
		
		try(Socket s= new Socket("localhost",8080);
				DataInputStream in= new DataInputStream(s.getInputStream());
				DataOutputStream out= new DataOutputStream(s.getOutputStream());)
		{
			numJug=in.read();
			if(numJug==1) {
				numContrario=2;
			}
			else {
				numContrario=1;
			}
			System.out.println("llega jug"+numJug);
			while(!t.terminado() || t==null) {
				columna=in.read();
				System.out.println("llega columna");
				if(t!=null) {
					if(columna>=0 && columna<7) {
						t.meterFicha(columna, numContrario);
					}
					System.out.println("El tablero esta así: ");
					t.mostrarTablero();
					if(!t.terminado()) {
						System.out.println("Introduce el número de la columna en la que quieres meter la ficha (la primera es la 0)");
						
						Scanner reader = new Scanner(System.in);
						columna = reader.nextInt();
						
						t.meterFicha(columna, numJug);
						
						out.write(columna);
						if(t.terminado()) {
							System.out.println("HAS GANADO!!");
						}
					}else {
						System.out.println("HAS PERDIDO, GANADOR JUGADOR "+numContrario);
					}
					
				}
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
