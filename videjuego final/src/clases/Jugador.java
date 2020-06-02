package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Jugador extends ItemJuego{

private int vidas;



public Jugador(int x, int y, int velocidad, String nombreImagen, int vidas) {
	super(x, y, velocidad, nombreImagen);
	this.vidas = vidas;
}






public int getVidas() {
	return vidas;
}


public void setVidas(int vidas) {
	this.vidas = vidas;
}




@Override 
public void pintar(GraphicsContext graficos) {
	graficos.drawImage(Juego.imagenes.get(nombreImagen),x,y);
}
@Override 
public void mover() {
	if (x > 840)
		x=0;
	if(x<0)
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



}

