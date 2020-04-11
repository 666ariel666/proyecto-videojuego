package clases;

import javafx.scene.canvas.GraphicsContext;

public abstract class ItemJuego {
	protected int x;
	protected int y;
	protected int velocidad;
	protected String nombreImagen;
	protected int ancho,alto;

	public ItemJuego(int x, int y, int velocidad, String nombreImagen) {
		super();
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.nombreImagen = nombreImagen;
	}
	public abstract void pintar(GraphicsContext graficos);
    public abstract void mover();
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public  void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
}
