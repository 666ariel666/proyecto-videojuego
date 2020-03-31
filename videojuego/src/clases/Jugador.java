package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Jugador {
private int x;
private int y;
private int vidas;
private int velocidad=10;
private String nombreImagen;
public Jugador(int x, int y, int vidas, String nombreImagen) {
	super();
	this.x = x;
	this.y = y;
	this.vidas = vidas;
	this.nombreImagen = nombreImagen;

}


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


public int getVidas() {
	return vidas;
}


public void setVidas(int vidas) {
	this.vidas = vidas;
}


public String getNombreImagen() {
	return nombreImagen;
}


public void setNombreImagen(String nombreImagen) {
	this.nombreImagen = nombreImagen;
}


public void pintar(GraphicsContext graficos) {
	graficos.drawImage(Juego.imagenes.get(nombreImagen),x,y);
};
public void mover() {
	if (x > 840)
		x=0;
	if(Juego.derecha)
	x+=velocidad;
	if(Juego.izquierda)
	x-=velocidad;
	if(Juego.arriba)
		y-=velocidad;
	if(Juego.abajo)
		y+=velocidad;
}


public int getVelocidad() {
	return velocidad;
}


public void setVelocidad(int velocidad) {
	this.velocidad = velocidad;
}
}
