package clases;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends ItemJuego{
	private int coorXDI; 
	private int coorYDI;
private int tipoTile;
private int llaves=0;

	public int getTipoTile() {
	return tipoTile;
}


 


	public Tile(int tipoTile,int x, int y, int velocidad, String nombreImagen, int alto,int ancho) {
		super(x, y, velocidad, nombreImagen);
		this.alto=alto;
		this.ancho=ancho;
	    this.setTipoTile(tipoTile);
	
	    this.x=x;
	    this.y=y;
		switch (this.getTipoTile()) {
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
			this.coorXDI=552;
			this.coorYDI=111;
			break;
		case 23:
			this.coorXDI=454;
			this.coorYDI=130;
			break;
		case 24:
			this.coorXDI=642;
			this.coorYDI=465;
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



	public void pintar(GraphicsContext graficos) {
		
		graficos.drawImage(Juego.imagenes.get(nombreImagen), coorXDI, coorYDI,ancho,alto,x,y,64,64);
		
	}
	
	

	
	

	@Override
	public  void mover(){
		if (Juego.arriba) {
	y+=velocidad;
	
		}
		
	}
	

	public Rectangle ObtenerRectangulo() {
		if(tipoTile==18 || tipoTile==21 || tipoTile==24) {
			return new Rectangle(x-5,y,ancho-25,alto-5);
		}
		else return new Rectangle(0,0,0,0);
	}
	public Rectangle obtenerRectangulollave() {
		if(tipoTile==23)
			return new Rectangle(x,y,ancho,alto);
		else return new Rectangle(0,0,0,0);
	}
	public Rectangle obtenerRectangulocofre() {
		if(tipoTile==22)
			return new Rectangle(x,y,ancho,alto);
		else return new Rectangle(0,0,0,0);
	}
	public boolean verificarColision18(JugadorAnimado tile ) {
		if(ObtenerRectangulo().intersects( tile.obtenerRectangulo().getBoundsInLocal())&&tipoTile==18 
		 ) {
			tile.setVidaActual(tile.getVidaActual()-1);
			if(tile.getVidaActual()<=0) {
			tile.setVidas(tile.getVidas()-1);
			tile.setX(0);
			tile.setY(640);
			tile.setVidaActual(100);}
			return true;} 
		else return false;}
	
	
	public boolean verificarColision21(JugadorAnimado tile) {
		if(ObtenerRectangulo().intersects( tile.obtenerRectangulo().getBoundsInLocal())&&tipoTile==21 
		 ) {
			tile.setVidaActual(tile.getVidaActual()-1);
			if(tile.getVidaActual()<=0) {
			tile.setVidas(tile.getVidas()-1);
			tile.setX(0);
			tile.setY(640);
			tile.setVidaActual(100);}
			return true;} 
		else return false;}
	
	public boolean verificarColisionllave(JugadorAnimado tile) {
		if(!tile.isCapturado3() && obtenerRectangulollave().intersects( tile.obtenerRectangulo().getBoundsInLocal())&&tipoTile==23 
		 ) {tile.setLlaves(tile.getLlaves()+1);
		 tile.setCapturado3(true);
		 return true;
			} 
		 return false;}

	public boolean verificarColisioncofre(JugadorAnimado tile, Enemigo disparo, GraphicsContext graficos,Pigsi pigsi,Soldado isac) {
		if(!tile.isCapturado4() && obtenerRectangulocofre().intersects( tile.obtenerRectangulo().getBoundsInLocal())&&tipoTile==22 
		 && tile.getLlaves()>0) {
			
		tile.setCaso((int)(Math.random()*3)+1);
		  if(tile.getCaso()==1) {
			  tile.setVidas(tile.getVidas()+1);
			  
		  }
		  else if(tile.getCaso()==2) {
					 
			  if(tile.getVidaActual()<100) {
				  tile.setVidaActual(100);
				  
				}
			  }
			  else if(tile.getCaso()==3 ) {
				  graficos.fillText("mejora arma ",1000,20 );
				  disparo.setAtaque(1);
				  pigsi.setAtaque(1);
				  isac.setAtaque(1);}
				  
		 tile.setCapturado4(true);
		 tile.setLlaves(tile.getLlaves()-1);
		 return true;}
			
		 return false;}
	
	public boolean verificarColision24(JugadorAnimado tile) {
		if(!tile.isCapturado2() && ObtenerRectangulo().intersects( tile.obtenerRectangulo().getBoundsInLocal())&&tipoTile==24 
		 ) {tile.setPuntos(tile.getPuntos()+5);
		 tile.setCapturado2(true);
		 return true;
			} 
		 return false;}
	
	




	public void setTipoTile(int tipoTile) {
		this.tipoTile = tipoTile;
	}

	
		
			
	}

	
	


