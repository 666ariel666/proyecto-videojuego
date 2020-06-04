package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Recolectables extends ItemJuego{
private int valor;
private String nombreImagen2;

	public int getValor() {
	return valor;
}

public void setValor(int valor) {
	this.valor = valor;
}

	public Recolectables(int x, int y, int velocidad, String nombreImagen, int valor,String nombreImagen2) {
	super(x, y, velocidad, nombreImagen);
	this.nombreImagen2=nombreImagen2;
	this.valor = valor;
	this.ancho=(int)Juego.imagenes.get("kratos").getWidth();
	this.alto=(int)Juego.imagenes.get("kratos").getHeight();
}

	public void pintar1(GraphicsContext graficos,JugadorAnimado tile) {
		if(tile.isCapturado())
			return;
		graficos.drawImage(Juego.imagenes.get("kratos"),this.x,this.y);	
	}
	public void pintar2(GraphicsContext graficos,JugadorAnimado tile) {
		if(tile.isCapturado1() )
			return;
		graficos.drawImage(Juego.imagenes.get("arma"), 300, 640);
	}

	@Override
	public void mover() {
		if (Juego.arriba) {
			y+=velocidad;
			
				}
		
		// TODO Auto-generated method stub
		
	}
	public Rectangle obtenerRectangulo() {
		return new Rectangle(x,y,alto+15,ancho+15);
		
	}
	public Rectangle arma() {
		return new Rectangle(300,640,30,30);
	}

public boolean verificarColision18(JugadorAnimado tile) {
if(!tile.isCapturado()&&obtenerRectangulo().intersects( tile.obtenerRectangulo().getBoundsInLocal()) ) {
	this.valor=1;
	tile.setVidas(tile.getVidas()+valor);
	tile.setCapturado(true);
	return true;} 
else return false;
	
}
public boolean verificarColisionArma(JugadorAnimado tile) {
if(!tile.isCapturado1()&&arma().intersects( tile.obtenerRectangulo().getBoundsInLocal())  ) {
	tile.setCapturado1(true);
	
	return true;} 
else return false;


	
}

@Override
public void pintar(GraphicsContext graficos) {
	// TODO Auto-generated method stub
	
} 

}
