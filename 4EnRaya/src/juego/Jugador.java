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
			//lee el numero de jugador que le corresponde y guarda tanto ese como el de su rival
			numJug=in.read();
			if(numJug==1) {
				numContrario=2;
			}
			else {
				numContrario=1;
			}
			System.out.println("Se te ha asignado el numero "+numJug);
			while(!t.terminado() || t==null) {
				//lee la columna que ha puesto el rival
				columna=in.read();
				if(t!=null) {
					//con esto evitamos fallo en el primer movimiento, que envía columna 9 ya que es necesaria la orden ahi para el resto del bucle pero en el primer movimiento el jugador 1 solo elige columna, no recibe
					if(columna>=0 && columna<7) {
						t.meterFicha(columna, numContrario);
					}
					System.out.println("El tablero esta así: ");
					t.mostrarTablero();
					//con este if vemos que si ha ganado el jugador contrario en el turno anterior, muestra mensaje de derrota y si no sigue el turno
					if(!t.terminado()) {
						System.out.println("Introduce el número de la columna en la que quieres meter la ficha (la primera es la 0)");
						
						Scanner reader = new Scanner(System.in);
						columna = reader.nextInt();
						
						t.meterFicha(columna, numJug);
						
						out.write(columna);
						//si el ha ganado con su movimiento muestra mensaje de victoria
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
