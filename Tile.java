package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Tile extends ItemJuego{
	private int coorXDI; 
	private int coorYDI;
static int tipoTile;
    






	public Tile(int tipoTile,int x, int y, int velocidad, String nombreImagen, int alto,int ancho) {
		super(x, y, velocidad, nombreImagen);
		this.alto=alto;
		this.ancho=ancho;
	
		switch (tipoTile) {
		case 1:
		this.coorXDI=0;
		this.coorYDI=256;
		break;
		case 2:
		this.coorXDI=0;
		this.coorYDI=320;
		break;
		case 3:
			this.coorXDI=64;
			this.coorYDI=256;
			break;
		case 4:
			this.coorXDI=64;
			this.coorYDI=320;
			break;
		case 5:
			this.coorXDI=128;
			this.coorYDI=256;
			break;
		case 6 :
			this.coorXDI=128;
			this.coorYDI=320;
			break;
		case 7:
			this.coorXDI=192;
			this.coorYDI=256;
			break;
		case 8:
			this.coorXDI=192;
			this.coorYDI=320;
			break;
		case 9:
			this.coorXDI=256;
			this.coorYDI=256;
			break;
		case 10:
			this.coorXDI=256;
			this.coorYDI=320;
			break;
		case 11:
			this.coorXDI=320;
			this.coorYDI=256;
			break;
		case 12:
			this.coorXDI=320;
			this.coorYDI=320;
			break;
		case 13:
			this.coorXDI=0;
			this.coorYDI=512;
			break;
		case 14:
			this.coorXDI=0;
			this.coorYDI=576;
			break;
		case 15:
			this.coorXDI=64;
			this.coorYDI=512;
			break;
		case 16:
			this.coorXDI=64;
			this.coorYDI=576;
			break;
		case 17:
			this.coorXDI=128;
			this.coorYDI=0;
			break;
		case 18:
			this.coorXDI=196;
			this.coorYDI=0;
			break;
		case 19:
			this.coorXDI=320;
			this.coorYDI=0;
			break;
		case 20:
			this.coorXDI=320;
			this.coorYDI=64;
			break;
		case 21:
			this.coorXDI=320;
			this.coorYDI=128;
			break;
		case 22:
			this.coorXDI=150;
			this.coorYDI=150;
			break;
		case 23:
			this.coorXDI=384;
			this.coorYDI=0;
			break;
		case 24:
			this.coorXDI=384;
			this.coorYDI=64;
			break;
		case 25:
			this.coorXDI=448;
			this.coorYDI=0;
			break;
		case 26:
			this.coorXDI=448;
			this.coorYDI=64;
			break;
		case 27:
			this.coorXDI=512;
			this.coorYDI=00;
			break;
		case 28:
			this.coorXDI=640;
			this.coorYDI=0;
			break;
		case 29:
			this.coorXDI=640;
			this.coorYDI=64;
			break;
		case 30:
			this.coorXDI=704;
			this.coorYDI=64;
			break;
		case 31:
			this.coorXDI=704;
			this.coorYDI=128;
			break;
		case 32:
			this.coorXDI=196;
			this.coorYDI=196;
			break;
		case 33:
			this.coorXDI=256;
			this.coorYDI=196;
			break;
		case 34:
			this.coorXDI=640;
			this.coorYDI=256;
			break;
		case 35:
			this.coorXDI=640;
			this.coorYDI=320;
			break;
		case 36:
			this.coorXDI=704;
			this.coorYDI=256;
			break;
		case 37:
			this.coorXDI=704;
			this.coorYDI=320;
			break;
		
		
		}
	}
	

	


	public int getCoorXDI() {
		return coorXDI;
	}


	public void setCoorXDI(int coorXDI) {
		this.coorXDI = coorXDI;
	}


	public int getCoorYDI() {
		return coorYDI;
	}


	public void setCoorYDI(int coorYDI) {
		this.coorYDI = coorYDI;
	}


	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), coorXDI, coorYDI,ancho,alto,x,y,ancho,alto);
			
	}
	
	
	
	

	@Override
	public  void mover(){
		if (Juego.arriba) {
	y+=velocidad;
	
		}
	
		   
	}

	public Rectangle obtenerRectangulo(JugadorAnimado jugador, GraphicsContext graficos) {
		if (tipoTile == 19 || tipoTile==20) {
			return new Rectangle(x, y, 64,64);}
			else {
				return new Rectangle (0,0,0,0);
			
		}}

	public boolean verificarColision(JugadorAnimado jugador, GraphicsContext graficos) {
		if(obtenerRectangulo(jugador, graficos).getBoundsInLocal().intersects(jugador.obtenerRectangulo().getBoundsInLocal())&& tipoTile==19) {
			this.x= 0;
			this.y= 0;
			System.out.println("si");
			return true;
			
		} 
		
			return false;
	}

	
	

}
