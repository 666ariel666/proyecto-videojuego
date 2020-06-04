package clases;

//
//import java.awt.GraphicsEnvironment;
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.DataLine;



import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


public class JugadorAnimado extends ItemJuego{
private boolean capturado=false;
private boolean capturado1=false;
private boolean capturado2=false;
private boolean capturado3=false;
private boolean capturado4=false;
private int puntos;
private int llaves=0;
public boolean isCapturado1() {
	return capturado1;
}
public void setCapturado1(boolean capturado1) {
	this.capturado1 = capturado1;}

private int vidas;
private int vidaActual;
private int xImagen;
private int yImagen;
private int anchoImagen;
private int altoImagen;
private int vidasMaxima;
private int caso;
private String animacionActual;
private int direccion=1;
private String imagen2;
private HashMap<String,animacion>animaciones;


public JugadorAnimado(int x, int y, int velocidad, String nombreImagen, int vidas, String animacionActual,int vidaActual,String imagen2,int puntos) {
	super(x, y, velocidad, nombreImagen);
	this.imagen2=imagen2;
	this.vidas = vidas;
	this.vidasMaxima=vidasMaxima;
	this.vidaActual=vidaActual;
	this.animacionActual=animacionActual;
	animaciones=new HashMap<String,animacion>();
	inicializarAnimaciones();

	this.vidasMaxima=100;
}
public boolean verificarPoder(Recolectables cosas,JugadorAnimado tile) {
	if(cosas.verificarColisionArma(tile)==true) {
		inicializarAnimaciones1();
	return true;}
	else return false;
	
}
public void inicializarAnimaciones( ) {

	
	Rectangle coordenadasCorrer[]= {
		 
			new Rectangle(192,32,33,32),
			new Rectangle(226,32,33,30),
			new Rectangle(256,32,33,30)
		
	//new Rectangle(294,78,110,86),
	//new Rectangle(400,80,110,86),
	//new Rectangle(498,78,110,86),
	//new Rectangle(588,88,110,86),
	//new Rectangle(672,96,110,86),
	//new Rectangle(746,100,110,86),
	
	
	};
	animacion animacionCorrer=new animacion(0.09,coordenadasCorrer);
	animaciones.put("correr",animacionCorrer);
	
	Rectangle coordenadasCorrerD[]= {
			new Rectangle(194,65,33,32),
			new Rectangle(229,63,33,26),
			new Rectangle(259,65,31,27)
	//new Rectangle(294,78,110,86),
	//new Rectangle(400,80,110,86),
	//new Rectangle(498,78,110,86),
	//new Rectangle(588,88,110,86),
	//new Rectangle(672,96,110,86),
	//new Rectangle(746,100,110,86),
	
	
	};
	animacion animacionCorrerD=new animacion(0.09,coordenadasCorrerD);
	animaciones.put("correrD",animacionCorrerD);
	
	
	Rectangle coordenadasDescanso[]= {
			new Rectangle(226,0,31,27),
			new Rectangle(226,32,31,27),
			new Rectangle(227,63,31,27),
			new Rectangle(227,97,33,30),
		
			
	};
	
	animacion animacionDescanso=new animacion(0.4,coordenadasDescanso);
    animaciones.put("descanso",animacionDescanso);
    
    Rectangle coordenadasArriba[]={
    		new Rectangle(194,97,31,27),
    		new Rectangle(227,97,31,27),
    		new Rectangle(259,97,31,27)
    		
    };
    animacion animacionArriba=new animacion(0.09,coordenadasArriba);
    animaciones.put("arriba", animacionArriba);
    
    Rectangle coordenadasAbajo[]={
    		new Rectangle(194,0,31,27),
    		new Rectangle(226,0,31,27),
    		new Rectangle(259,0,31,27),
    	
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




public int getDireccion() {
	return direccion;
}
public void setDireccion(int direccion) {
	this.direccion = direccion;
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
public boolean isCapturado() {
	return capturado;
}
public void setCapturado(boolean capturado) {
	this.capturado = capturado;
}




@Override 
public void pintar(GraphicsContext graficos) {
	graficos.drawImage(Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,direccion*altoImagen,x,y,3*anchoImagen,altoImagen);
	
	
}
public void textovidas(GraphicsContext graficos) {
	graficos.fillRect(this.x, this.y-5, this.anchoImagen*vidaActual/this.vidasMaxima*3,2);
	graficos.setFill(Color.GOLD);
	 
	
}

@Override 
public void mover() {
	if (y<this.alto)
		y=640;
	if(y>640)
	y=640;
	if(x<0)
		x=0;
	if(x>800)
		x=800;
	if(Juego.derecha)
	x+=velocidad;
	if(Juego.izquierda)
	x-=velocidad;
	if(Juego.arriba)
		y-=velocidad;
	if(Juego.abajo) 
		y+=velocidad;
     
}



public Rectangle obtenerRectangulo() {
	
		return new Rectangle(x, y,direccion*altoImagen-10,anchoImagen-10);
		}
		
	



//public Clip noSe() {
//Clip clip=null;
//try {
//    InputStream Is=ClassLoader.getSystemResourceAsStream("cancion.wav");
//    AudioInputStream ais=AudioSystem.getAudioInputStream(Is);
//    DataLine.Info info=new DataLine.Info(Clip.class, ais.getFormat());
//    clip=(Clip) AudioSystem.getLine(info);
//    clip.open(ais); // 10000 milisegundos (10 segundos)
// } catch (Exception tipoError) {
//   System.out.println("" + tipoError);
// }
//return clip;
//}}




//return new Rectangle(0, 0, 0, 0);}
public void inicializarAnimaciones1( ) {

	
	Rectangle coordenadasCorrer[]= {
		 
			new Rectangle(24,152,33,40),
			new Rectangle(91,156,33,40),
			new Rectangle(160,156,33,40)
		
	//new Rectangle(294,78,110,86),
	//new Rectangle(400,80,110,86),
	//new Rectangle(498,78,110,86),
	//new Rectangle(588,88,110,86),
	//new Rectangle(672,96,110,86),
	//new Rectangle(746,100,110,86),
	
	
	};
	animacion animacionCorrer=new animacion(0.09,coordenadasCorrer);
	animaciones.put("correr",animacionCorrer);
	
	Rectangle coordenadasCorrerD[]= {
			new Rectangle(10,188,32,44),
			new Rectangle(75,188,32,44),
			new Rectangle(142,186,32,44)
	//new Rectangle(294,78,110,86),
	//new Rectangle(400,80,110,86),
	//new Rectangle(498,78,110,86),
	//new Rectangle(588,88,110,86),
	//new Rectangle(672,96,110,86),
	//new Rectangle(746,100,110,86),
	
	
	};
	animacion animacionCorrerD=new animacion(0.09,coordenadasCorrerD);
	animaciones.put("correrD",animacionCorrerD);
	
	
	Rectangle coordenadasDescanso[]= {
			new Rectangle(13,33,31,33),
			new Rectangle(112,33,39,38),
			new Rectangle(8,121,34,66),
			
		
			
	};
	
	animacion animacionDescanso=new animacion(0.4,coordenadasDescanso);
    animaciones.put("descanso",animacionDescanso);
    
    Rectangle coordenadasArriba[]={
    		new Rectangle(9,220,34,62),
    		new Rectangle(76,221,34,64),
    		new Rectangle(140,223,34,64)
    		
    };
    animacion animacionArriba=new animacion(0.09,coordenadasArriba);
    animaciones.put("arriba", animacionArriba);
    
    Rectangle coordenadasAbajo[]={
    		new Rectangle(6,120,34,65),
    		new Rectangle(75,120,34,65),
    		new Rectangle(141,124,34,60),
    	
    };
    animacion animacionAbajo=new animacion(0.09,coordenadasAbajo);
    animaciones.put("abajo", animacionAbajo);
    
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
public String getImagen2() {
	return imagen2;
}
public void setImagen2(String imagen2) {
	this.imagen2 = imagen2;
}
public int getPuntos() {
	return puntos;
}
public void setPuntos(int puntos) {
	this.puntos = puntos;
}
public boolean isCapturado2() {
	return capturado2;
}
public void setCapturado2(boolean capturado2) {
	this.capturado2 = capturado2;
}
public int getLlaves() {
	return llaves;
}
public void setLlaves(int llaves) {
	this.llaves = llaves;
}
public boolean isCapturado3() {
	return capturado3;
}
public void setCapturado3(boolean capturado3) {
	this.capturado3 = capturado3;
}
public boolean isCapturado4() {
	return capturado4;
}
public void setCapturado4(boolean capturado4) {
	this.capturado4 = capturado4;
}
public int getCaso() {
	return caso;
}
public void setCaso(int caso) {
	this.caso = caso;
}

	
}




