package clases;

public class Puntos {
	private String nombre;
	private double puntuacion;
	public Puntos(String nombre, double puntuacion) {
		super();
		this.nombre = nombre;
		this.puntuacion = puntuacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	@Override
	public String toString() {
		return   nombre + "   " + puntuacion ;
	}
	
	
}
