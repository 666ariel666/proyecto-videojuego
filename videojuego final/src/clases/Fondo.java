package clases;


import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Fondo extends ItemJuego {
    private String nombreImagen2;
    private int y2;

	public Fondo(int x, int y, int velocidad, String nombreImagen, String nombreImagen2) {
		super(x, y, velocidad, nombreImagen);
		this.nombreImagen2=nombreImagen2;
		this.ancho=(int)Juego.imagenes.get("fondo").getWidth();
		this.alto=(int)Juego.imagenes.get("fondo").getHeight();
		this.y2=this.alto-this.y;
		
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen), this.x,-1* this.y);
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen2),this.x,-1*this.y2+130);
		
	}

	

	@Override
	public void mover() {
		if (y <= -1*alto+130) {
			y=alto;
		
		}
		if (y2 <= -1*alto+130) {
			y2=alto;
	
		}

			
		if (Juego.arriba) {
	y-=velocidad;
	y2-=velocidad;

		}
	
	}


	

}
