package juego;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Jugador {
	
	public static void main(String[] args) {
		
		Tablero t= new Tablero();
		
		
		
		try(Socket s= new Socket("localhost",8080);
				ObjectInputStream in= new ObjectInputStream(s.getInputStream());
				ObjectOutputStream out= new ObjectOutputStream(s.getOutputStream());)
		{
			System.out.println("llega");
			while(!t.terminado() || t==null) {
				t=(Tablero) in.readObject();
				System.out.println("llega objeto");
				if(t!=null) {
					System.out.println("El tablero esta así: ");
					t.mostrarTablero();
					System.out.println("Introduce el número de la columna en la que quieres meter la ficha");
					
					Scanner reader = new Scanner(System.in);
					int columna;
					columna = reader.nextInt();
					
					t.meterFicha(columna-1, t.getTurno());
				}
			}
			
			
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
