package clases;



import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Disparo extends ItemJuego{

	private boolean disparo=false;
	public boolean isDisparo() {
	return disparo;
}



public void setDisparo(boolean disparo) {
	this.disparo = disparo;
}


	public Disparo(int x, int y, int velocidad, String nombreImagen) {
		super(x, y, velocidad, nombreImagen);
		this.ancho=(int)Juego.imagenes.get("ataque").getWidth();
		this.alto=(int)Juego.imagenes.get("ataque").getHeight();
		// TODO Auto-generated constructor stub
	}



public boolean pintar1(GraphicsContext graficos,JugadorAnimado jugador ) {
	if(x<=700 && x>=0 && y>=0 && y<=640 ) {
	graficos.drawImage(Juego.imagenes.get("ataque"),x,y);
	return true;
}return false;
	}
	@Override
	public void mover() {
		y-=velocidad+7;
		        
	}
//public Rectangle obtenerRectangulo(){
//	return new Rectangle(x,y,ancho,alto);
//}


	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		
	}
public Rectangle obtenerRec(JugadorAnimado jugador) {
	if (jugador.isCapturado1()==true  && x<=700 && x>=0 && y>=0 && y<=640 )
	return new Rectangle(x,y,this.ancho,this.alto);
	else return new Rectangle(0,0,0,0);
}
}
