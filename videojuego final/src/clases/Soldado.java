package clases;

import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Soldado extends ItemJuego {
	private String animacionActual;
	private int vidas;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private int direccion=1;
	private int vidaActual;
	private int vidasMaxima=500;
	private int ataque=0;
	private HashMap<String,animacion>animaciones;

	
	public Soldado(int x, int y, int velocidad, String nombreImagen, int vidas, String animacionActual,int vidaActual) {
			super(x, y, velocidad, nombreImagen);
			this.vidas = vidas;
			this.vidasMaxima=vidasMaxima;
			this.vidaActual=vidaActual;
			this.animacionActual = animacionActual;
			animaciones=new HashMap<String,animacion>();
			inicializarAnimacionesEnemigo();
			this.ancho=(int)Juego.imagenes.get("malo").getWidth();
			this.alto=(int)Juego.imagenes.get("malo").getHeight();
		}
	public void inicializarAnimacionesEnemigo() {
		 Rectangle coordenadasAbajo[]={
//		    		new Rectangle(68,71,164,1024),
//		    		new Rectangle(68,71,162,950),
//		    		new Rectangle(68,71,96,1028),
//		    		new Rectangle(68,71,945,90),
					new Rectangle(1024,164,68,71),
		    		new Rectangle(950,162,68,71),
		    		new Rectangle(1028,96,68,71),
		    		new Rectangle(90,945,68,71),
		    	
		    };
		    animacion animacionAbajo=new animacion(0.09,coordenadasAbajo);
		    animaciones.put("abajo", animacionAbajo);
		
	}
	
	public void calcularFrame(double t) {
		Rectangle coordenadas=animaciones.get(animacionActual).calcularFrameActual(t);
	    this.xImagen=(int)coordenadas.getX();
	    this.yImagen=(int)coordenadas.getY();
	    this.altoImagen=(int)coordenadas.getWidth();
	    this.anchoImagen=(int)coordenadas.getHeight();
	}; 
	@Override
	public void pintar(GraphicsContext graficos) {
		if(vidaActual<=0)
			return;
		graficos.drawImage(Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,direccion*altoImagen,x,y,3*anchoImagen,altoImagen);
		
	}
	@Override
	public void mover() {	
	}
	
	public String getAnimacionActual() {
		return animacionActual;
	}
	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	public int getxImagen() {
		return xImagen;
	}
	public void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}
	public int getyImagen() {
		return yImagen;
	}
	public void setyImagen(int yImagen) {
		this.yImagen = yImagen;
	}
	public int getAnchoImagen() {
		return anchoImagen;
	}
	public void setAnchoImagen(int anchoImagen) {
		this.anchoImagen = anchoImagen;
	}
	public int getAltoImagen() {
		return altoImagen;
	}
	public void setAltoImagen(int altoImagen) {
		this.altoImagen = altoImagen;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public HashMap<String, animacion> getAnimaciones() {
		return animaciones;
	}
	public void setAnimaciones(HashMap<String, animacion> animaciones) {
		this.animaciones = animaciones;
	}
	
	public Rectangle obtenerRec() {
		if(this.vidaActual>0)
		return new Rectangle(x,y,this.anchoImagen+20,this.altoImagen);
		else return new Rectangle(0,0,0,0);
	}
public boolean verificarcolision(Disparo tile,JugadorAnimado jugador) {
	if(obtenerRec().intersects( tile.obtenerRec(jugador).getBoundsInLocal()))
	{if(ataque==1)
		this.vidaActual-=15;
	else this.vidaActual-=5;
	

		return true;}
	return false;
}
public void verificarcolision1(JugadorAnimado jugador) {
	if(obtenerRec().intersects(jugador.obtenerRectangulo().getBoundsInLocal()))
		jugador.setVidaActual(jugador.getVidaActual()-1);
		if(jugador.getVidaActual()<=0) {
			jugador.setVidas(jugador.getVidas()-1);
		jugador.setX(0);
		jugador.setY(640);
		jugador.setVidaActual(100);
		}
	 
}

public void textovidas(GraphicsContext graficos) {
	graficos.fillRect(this.x, this.y-5, this.anchoImagen*vidaActual/this.vidasMaxima*3,2);
	graficos.setFill(Color.GOLD);
	
	
}
public int getVidaActual() {
	return vidaActual;
}
public void setVidaActual(int vidaActual) {
	this.vidaActual = vidaActual;
}
public int getVidasMaxima() {
	return vidasMaxima;
}
public void setVidasMaxima(int vidasMaxima) {
	this.vidasMaxima = vidasMaxima;
}
public void mover1(JugadorAnimado uno) {
	
		if(x>uno.getX())
			x-=1;
		else x+=1;
	
		if(y>uno.getY())
			y-=1;
		else y+=1;
}
public int getAtaque() {
	return ataque;
}
public void setAtaque(int ataque) {
	this.ataque = ataque;
}

}
