package Gestión_de_datos;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Persona {
	public int Identificacion;
	public String Nombre;
	public String Apellido;
	
	public Persona(int Identificacion, String Nombre,String Apellido ){
		this.Identificacion= Identificacion;
		this.Nombre= Nombre;
		this.Apellido= Apellido;
	}
	public Persona() {
		this.Nombre=Nombre;
		this.Identificacion=Identificacion;
		this.Apellido=Apellido;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String Nombre) {
		this.Nombre= Nombre;
	}
	public int getIdentificacion() {
		return Identificacion;
	}
	public void setIdentificacion(int Identificacion) {
		this.Identificacion=Identificacion ;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String Apellido) {
		this.Apellido= Apellido;
	}
	public void Guardar(PrintWriter escribe) {
		escribe.println("\n");
		escribe.println("Identificacion :"+Identificacion);
		escribe.println("Nombre: "+Nombre);
		escribe.println("Apellido: "+Apellido+"\n");
		escribe.println("--------------------------");
		
	}
	public Persona cargar(BufferedReader almacen) {
		try {int id;
		String Nombre,Apellido;
		Nombre= almacen.readLine();
		id=Integer.parseInt(almacen.readLine());
		Apellido=almacen.readLine();
		return new Persona(id,Nombre,Apellido);
		}catch(Exception e) {
			
		}
		return null;
	}
	
	
}
